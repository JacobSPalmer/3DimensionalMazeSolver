import java.util.*;

public class Maze {

    public char[][][] maze;
    private final char WALL = '*', PATH = '.', VISITED = '+', DEADEND = '-', START = '0', TRAPDOOR = '!', TRAMPOLINE = '#';
    private int totalRow;
    private int totalCol;
    private int totalLevel;
    private boolean foundExit;
    public List<String> direction = Arrays.asList("north", "south", "east", "west", "up", "down");
    public List<Map.Entry<char[][][], Integer>> sortMapStep;
    private final String[][] rData;

    public Maze(final String[][] rawData) {
        rData = rawData;
        maze = new char[rawData.length][rawData[0].length][rawData[0][0].length()];
        for (int i1 = 0; i1 < rawData.length; i1++) {
            for (int i2 = 0; i2 < rawData[0].length; i2++) {
                maze[i1][i2] = rawData[i1][i2].toCharArray();
            }
            //TODO create a check that all cols and levels are the same length
            totalLevel = maze.length;
            totalRow = maze[0].length;
            totalCol = maze[0][0].length;
        }
    }

    /**Print Functions!
     * Multiple were used for testing as well as dealing with the more complex printing methods used for implementing with a GUI!**/

    public void printMaze() {
        for (int i1 = 0; i1 < maze.length; i1++) {
            System.out.println("Level " + i1);
            for (int i2 = 0; i2 < maze[0].length; i2++) {
                System.out.println(maze[i1][i2]);
            }
        }
    }

    public void printSortedMaze() {
        char[][][] sortedMaze = sortMapStep.get(0).getKey();
        int shortedCount = sortMapStep.get(0).getValue();
        for (int i1 = 0; i1 < sortedMaze.length; i1++) {
            System.out.println("Level " + i1);
            for (int i2 = 0; i2 < sortedMaze[0].length; i2++) {
                System.out.println(sortedMaze[i1][i2]);
            }
        }
        System.out.println("Out of " + sortMapStep.size() + " trials, this map took " + shortedCount + " and the longest took " + sortMapStep.get(sortMapStep.size() - 1).getValue());
    }

    public static void printMazeAt(char[][][] maze2) {
        for (int i1 = 0; i1 < maze2.length; i1++) {
            System.out.println("Level " + i1);
            for (int i2 = 0; i2 < maze2[0].length; i2++) {
                System.out.println(maze2[i1][i2]);
            }
        }
    }

    public void printAll(){
        int u = 0;
        for (final Map.Entry<char[][][], Integer> d:sortMapStep) {
            char[][][] maze = sortMapStep.get(u).getKey();
            System.out.println("Maze #: " + u);
            for (int i1 = 0; i1 < maze.length; i1++) {
                System.out.println("Level " + i1);
                for (int i2 = 0; i2 < maze[0].length; i2++) {
                    System.out.println(maze[i1][i2]);
                }
            }
            u++;
            System.out.println();
        }
    }

    public String printMazeAsString() {
        StringBuilder s = new StringBuilder();
        for(int i1 = 0; i1 < maze.length; i1++) {
            s.append("Level " + i1 + "\n");
            for(int i2 = 0; i2 < maze[0].length; i2++) {
                String temp = new String(maze[i1][i2]);
                s.append(temp + "\n");
            }
        }
        String result = s.toString();
        return result;
    }

    public String printTrailStats(){
        String result = "Out of " + sortMapStep.size() + " trials, this map took " + sortMapStep.get(0).getValue() + " and the longest took " + sortMapStep.get(sortMapStep.size() - 1).getValue();
        return result;
    }

    public String printStringAsSortedMaze() {
        StringBuilder s = new StringBuilder();
        char[][][] sortedMaze = sortMapStep.get(0).getKey();
        int shortedCount = sortMapStep.get(0).getValue();
        for (int i1 = 0; i1 < sortedMaze.length; i1++) {
            s.append("Level " + i1 + "\n");
            for (int i2 = 0; i2 < sortedMaze[0].length; i2++) {
                String temp = new String(sortedMaze[i1][i2]);
                s.append(temp + "\n");
            }
        }
        String result = s.toString();
        return result;
    }

    /**Helper and Test Functions!**/

    public char get(final int level, final int row, final int col) {
        final char result = maze[level][row][col];
        return result;
    }

    public boolean checkValidPath(final int level, final int row, final int col) {
        boolean isValid = false;
        if (this.get(level, row, col) == '.' || this.get(level, row, col) == '!') {
            isValid = true;
        }
        return isValid;
    }

    public void resetMaze() {
        for (int i1 = 0; i1 < rData.length; i1++) {
            for (int i2 = 0; i2 < rData[0].length; i2++) {
                maze[i1][i2] = rData[i1][i2].toCharArray();
            }
        }
    }

    public int stepCount(final char[][][] path){
        int count = 0;
        for(int l = 0; l < totalLevel; l++){
            for(int r = 0; r < totalRow; r++){
                for(int c = 0; c < totalCol; c++){
                    if(path[l][r][c] == '+' || path[l][r][c] == '-'){
                        count++;
                    }
                }
            }
        }
        return count;
    }

    /** Simple Solve Portion!**/

    public boolean simpleSolve(final int level, final int row, final int col) throws IllegalArgumentException {
        //Checks for valid entry given maze data
        if (level > totalLevel || level < 0 || row < 0 || row > totalRow - 1 || col < 0 || col > totalCol - 1) {
            throw new IllegalArgumentException("Starting point is out of bounds");
        }
        //Starts in a wall -> exit failure
        if (maze[level][row][col] == WALL) {
            foundExit = false;
            System.out.println("Ouch, you started in a wall!");
            return foundExit;
        }
        simpleSolveR(level, row, col);
        maze[level][row][col] = START;
        printMaze();
        return foundExit;
    }

    private boolean simpleSolveR(final int level, final int row, final int col) {
        //checks if space is valid
        try {
            if (maze[level][row][col] == WALL || maze[level][row][col] == VISITED || maze[level][row][col] == DEADEND) {
                return false;
            }
            if (maze[level][row][col] != TRAMPOLINE && maze[level][row][col] != TRAPDOOR) {
                maze[level][row][col] = VISITED;
            }
            //Checks to see if on exit
            if (row == (totalRow - 1) || row == 0 || col == (totalCol - 1) || col == 0) {
                foundExit = true;
                return true;
            }
            //try directions and up or down if valid
            if (simpleSolveR(level, row + 1, col) == true) {
                return true;
            }
            if (simpleSolveR(level, row - 1, col) == true) {
                return true;
            }
            if (simpleSolveR(level, row, col + 1) == true) {
                return true;
            }
            if (simpleSolveR(level, row, col - 1) == true) {
                return true;
            }
            if (maze[level][row][col] == TRAMPOLINE) {
                if (simpleSolveR(level + 1, row, col) == true) {
                    return true;
                }
            }
            if(maze[level][row][col] == TRAPDOOR){
                if (simpleSolveR(level - 1, row, col) == true) {
                    return true;
                }
            }
            if (maze[level][row][col] != TRAMPOLINE && maze[level][row][col] != TRAPDOOR) {
                maze[level][row][col] = DEADEND;
            }
            return false;
        } catch (StackOverflowError e1) {
            return false;
        }
    }

    /** Random Solve Portion!**/

    public void randomSolveTrial(final int level, final int row, final int col, int SampleSize){
        sortMapStep = new ArrayList<>();
        Map<char[][][], Integer> mapSet = new HashMap<>();
        for(int d = 0; d < SampleSize; d++){
            Map.Entry<char[][][], Integer> temp = randomSolve(level,row,col);
            sortMapStep.add(d, temp);
        }
        Comparator<Map.Entry<char[][][], Integer>> c = new DescendingStep();
        sortMapStep.sort(c);
        printSortedMaze();
    }

    public Map.Entry<char[][][],Integer> randomSolve(final int level, final int row, final int col) {
            resetMaze();
            if (level > totalLevel || level < 0 || row < 0 || row > totalRow - 1 || col < 0 || col > totalCol - 1) {
                throw new IllegalArgumentException("Starting point is out of bounds");
            }
            //Starts in a wall -> exit failure
            if (maze[level][row][col] == WALL) {
                System.out.println("Ouch, you started in a wall!");
            }
            randomSolveR(level, row, col);
            maze[level][row][col] = START;
            char[][][] temp = new char[maze.length][maze[0].length][maze[0][0].length];
            for(int k = 0; k < totalLevel; k++){
                for(int j = 0; j < totalRow; j++){
                    for(int i = 0; i < totalCol; i++){
                        temp[k][j][i] = maze[k][j][i];
                    }
                }
            }
            int step = stepCount(temp);
            return Map.entry(temp,step);
    }

    protected boolean randomSolveR(final int level, final int row, final int col) {
        if (maze[level][row][col] == WALL || maze[level][row][col] == VISITED) {
            return false;
        }
        if (maze[level][row][col] != TRAPDOOR && maze[level][row][col] != TRAMPOLINE) {
            maze[level][row][col] = VISITED;

        }
        //Checks to see if on exit
        if (row == (totalRow - 1) || row == 0 || col == (totalCol - 1) || col == 0) {
            foundExit = true;
            return true;
        }
        Collections.shuffle(direction);
        for (final String d : direction) {
            switch (d) {
                case "north":
                    if (randomSolveR(level, row + 1, col)) {
                        return true;
                    }
                case "south":
                    if (randomSolveR(level, row - 1, col) == true) {
                        return true;
                    }
                case "west":
                    if (randomSolveR(level, row, col - 1) == true) {
                        return true;
                    }
                case "east":
                    if (randomSolveR(level, row, col + 1) == true) {
                        return true;
                    }
                case "up":
                    if (maze[level][row][col] == TRAMPOLINE) {
                        if (randomSolveR(level + 1, row, col) == true) {
                            return true;
                        }
                    }
                case "down":
                    if (maze[level][row][col] == TRAPDOOR) {
                        if (randomSolveR(level - 1, row, col) == true) {
                            return true;
                        }
                    }
            }
        }
        if (maze[level][row][col] != TRAPDOOR && maze[level][row][col] != TRAMPOLINE) {
            maze[level][row][col] = DEADEND;
        }
        return false;
        }
    }



