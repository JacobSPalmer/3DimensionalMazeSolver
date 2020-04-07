import java.util.List;

public class Maze {

    private final char[][][] maze;
    private final char WALL = '*', PATH = '.', VISITED = '+', DEADEND = '-', START = '0', MOVEUP = '^', MOVEDOWN = '!';
    private int TotalRow;
    private int TotalCol;
    private int TotalLevel;

    public Maze(final String[][] rawData){
        maze = new char[rawData.length][rawData[0].length][rawData[0][0].length()];
        for(int i1 = 0; i1 < rawData.length; i1++){
            for(int i2 = 0; i2 < rawData[0].length; i2++){
                maze[i1][i2] = rawData[i1][i2].toCharArray();
            }
        }
    }

    public void printMaze(){
        for(int i1 = 0; i1 < maze.length; i1++){
            System.out.println("Level " + i1);
            for(int i2 = 0; i2 < maze[0].length; i2++){
                System.out.println(maze[i1][i2]);
            }
        }
    }


}