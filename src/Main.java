import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        /** This portion of the code reads in from the input file! Please specify the input file through a run configuration!**/
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
        maze.printMaze();
        System.out.println("Solved Map: \n");


        /** Here is where you can decide the trail limit or run the simple solve!**/
//        maze.simpleSolve(startLevel,startRow,startCol);
        maze.randomSolveTrial(startLevel,startRow,startCol,1000);
    }
}
