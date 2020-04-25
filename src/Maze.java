import java.util.*;

public class Maze {

    private final char[][][] maze;
    private final char WALL = '*', PATH = '.', VISITED = '+', DEADEND = '-', START = '0', TRAPDOOR = '!', TRAMPOLINE = '#';
    private int totalRow;
    private int totalCol;
    private int totalLevel;
    private boolean foundExit;
    public int count;
    public List<String> direction = Arrays.asList("north", "south", "east", "west", "up", "down");

    public Maze(final String[][] rawData) {
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

    public void printMaze() {
        for (int i1 = 0; i1 < maze.length; i1++) {
            System.out.println("Level " + i1);
            for (int i2 = 0; i2 < maze[0].length; i2++) {
                System.out.println(maze[i1][i2]);
            }
        }
        System.out.println(count);
    }

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
        return foundExit;
    }

    protected boolean simpleSolveR(final int level, final int row, final int col) {
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
            count = row;
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
            System.out.println(count);
            return false;
        }
    }

    public boolean randomSolve(final int level, final int row, final int col) {
        if (level > totalLevel || level < 0 || row < 0 || row > totalRow - 1 || col < 0 || col > totalCol - 1) {
            throw new IllegalArgumentException("Starting point is out of bounds");
        }
        //Starts in a wall -> exit failure
        if (maze[level][row][col] == WALL) {
            foundExit = false;
            System.out.println("Ouch, you started in a wall!");
            return foundExit;
        }
        randomSolveR(level, row, col);
        maze[level][row][col] = START;
        return foundExit;
    }


    protected boolean randomSolveR(final int level, final int row, final int col) {
        if (maze[level][row][col] == WALL || maze[level][row][col] == VISITED) {
            return false;
        }
        if (maze[level][row][col] != TRAPDOOR && maze[level][row][col] != TRAMPOLINE) {
            maze[level][row][col] = VISITED;

        }
        count++;
        //Checks to see if on exit
        if (row == (totalRow - 1) || row == 0 || col == (totalCol - 1) || col == 0) {
            foundExit = true;
            return true;
        }
        Collections.shuffle(direction);
        for (final String d : direction) {
//            System.out.println(d);
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
        if(count > 100000){
            return true;}
        return false;
        }
    }



