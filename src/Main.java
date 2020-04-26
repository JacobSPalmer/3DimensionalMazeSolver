import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){


        final Scanner input = new Scanner(System.in);
        final int totalLevels = input.nextInt();
        final int totalRows = input.nextInt();
        final int totalCols = input.nextInt();
        final int startLevel = input.nextInt();
        final int startRow = input.nextInt();
        final int startCol = input.nextInt();
        System.out.println("Maze Dimension: " + totalRows + " x " + totalCols);
        System.out.println("Starting Coords: " + startLevel + " " + startCol + " " + startRow);
        String[][] rawInput = new String[totalLevels][totalRows];
        String waste = input.nextLine();
        while(input.hasNext()) {
            for (int l = 0; l < totalLevels; l++) {
                for (int r = 0; r < totalRows; r++) {
                    String nextLine = input.nextLine();
                    rawInput[l][r] = nextLine;
                }
                waste = input.nextLine();
            }
        }
        Maze maze = new Maze(rawInput);
//        System.out.println(maze.stepCount(maze.maze));
        maze.printMaze();
//        System.out.println(maze.stepCount(maze.maze));
//        System.out.println(maze.get(startLevel,startRow,startCol));
        maze.randomSolveTrial(startLevel, startRow, startCol, 5);
//        maze.printSortedMaze();
        maze.printAll();
//        Maze.printMazeAt(maze.sortMapStep.get(maze.sortMapStep.size() - 1).getKey());
//        Maze.printMazeAt(maze.sortMapStep.get(0).getKey());
//        System.out.println(maze.sortMapStep.get(0).getValue());
//        Maze.printMazeAt(maze.sortMapStep.get(1).getKey());
//        System.out.println(maze.sortMapStep.get(1).getValue());
//        Maze.printMazeAt(maze.sortMapStep.get(2).getKey());
//        System.out.println(maze.sortMapStep.get(2).getValue());
//        Maze.printMazeAt(maze.sortMapStep.get(3).getKey());
//        System.out.println(maze.sortMapStep.get(3).getValue());
//        System.out.println(maze.mpCollect.get(maze.sortMapStep.get(54325).getKey()));
    }
}
