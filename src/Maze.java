import java.util.List;

public class Maze {

    private final char[][][] maze;
    private final char WALL = '*', PATH = '.', VISITED = '+', DEADEND = '-', START = '0', LADDER = '!';
    private int totalRow;
    private int totalCol;
    private int totalLevel;
    private boolean foundExit;

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

    public void printMaze () {
        for (int i1 = 0; i1 < maze.length; i1++) {
            System.out.println("Level " + i1);
            for (int i2 = 0; i2 < maze[0].length; i2++) {
                System.out.println(maze[i1][i2]);
            }
        }
    }

    public char get( final int level, final int row, final int col){
        final char result = maze[level][row][col];
        return result;
    }

    public boolean checkValidPath( final int level, final int row, final int col){
        boolean isValid = false;
        if (this.get(level, row, col) == '.' || this.get(level, row, col) == '!') {
            isValid = true;
        }
        return isValid;
    }

    public boolean simpleSolve ( final int level, final int row, final int col) throws IllegalArgumentException{
        //Checks for valid entry given maze data
        if (level > totalLevel || level < 0 || row < 0 || row > totalRow - 1 || col < 0 || col > totalCol - 1){ throw new IllegalArgumentException("Starting point is out of bounds"); }
        //Starts in a wall -> exit failure
        if(maze[level][row][col] == WALL){
            foundExit = false;
            System.out.println("Ouch, you started in a wall!");
            return foundExit;
        }
        simpleSolveR(level,row,col);
        maze[level][row][col] = START;
        return foundExit;
    }

    protected boolean simpleSolveR(final int level, final int row, final int col) {
        //checks if space is valid
        if(maze[level][row][col] == WALL || maze[level][row][col] == VISITED || maze[level][row][col] == DEADEND){
            return false;
        }
        //Checks to see if on exit
        if(row == (totalRow - 1) || row == 0 || col == (totalCol - 1) || col == 0){ return foundExit = true; }
        //try directions and up or down if valid
        if (simpleSolveR(level, row + 1, col)){return true;}
        if (simpleSolveR(level, row, col + 1) == true){return true;}
        if (simpleSolveR(level, row - 1, col) == true){return true;}
        if (simpleSolveR(level, row, col - 1) == true){return true;}
        if (maze[level][row][col] == LADDER ){
            if(simpleSolveR(level + 1, row, col) == true){return true;}
            if(simpleSolveR(level - 1, row, col) == true){return true;}
        }
        maze[level][row][col] = DEADEND;
        return false;
    }

}