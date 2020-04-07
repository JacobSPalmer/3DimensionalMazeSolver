import java.util.Arrays;

public class Main {

    public static void main(String[] args){

        String[][] sample = new String[3][3];
        sample[0] = new String[]{"***", "*.*", "***"};
        sample[1] = new String[]{"*.*", "*.*", "*.*"};
        sample[2] = new String[]{"...", "*.*", "*.*"};

        Maze maze = new Maze(sample);
        maze.printMaze();
    }
}
