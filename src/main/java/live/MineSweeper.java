package live;

import java.util.*;

public class MineSweeper {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numOfRows = scanner.nextInt();
        int numOfColumns = scanner.nextInt();
        double probabilityOfBombs = scanner.nextDouble();

        // prepare board
        boolean[][] bombs = new boolean[numOfRows][numOfColumns];
        for(int i = 0; i < numOfRows; i++) {
            for(int j = 0; j < numOfColumns; j++) {
                if(Math.random() < probabilityOfBombs) {
                    bombs[i][j] = true;
                } else {
                    bombs[i][j] = false;
                }
            }
        }

        // printBoard board
        for(int i = 0; i < bombs.length; i++) {
            for(int j = 0; j < bombs[i].length; j++) {
                if(bombs[i][j]) {
                    System.out.print(" * ");
                } else {
                    System.out.print(" - ");
                }
            }
            System.out.println();
        }

        // calculate adjacent values
        int[][] solution = new int[numOfRows][numOfColumns];
        for(int i = 0; i < bombs.length; i++) {
            for(int j = 0; j < bombs[i].length; j++) {
                for(int adjRows = i - 1; adjRows <= i + 1; adjRows++) {
                    for (int adjCols = j - 1; adjCols <= j + 1; adjCols++) {
                        if (adjRows >= 0
                            && adjCols >= 0
                            && adjRows < bombs.length
                            && adjCols < bombs[i].length
                            && bombs[adjRows][adjCols]) {
                            solution[i][j]++;
                        }
                    }
                }
            }
        }

        System.out.println();

        // printBoard adjacent values board
        for(int i = 0; i < bombs.length; i++) {
            for(int j = 0; j < bombs[i].length; j++) {
                if(bombs[i][j]) {
                    System.out.print(" * ");
                } else {
                    System.out.print(" " + solution[i][j] +  " ");
                }
            }
            System.out.println();
        }

    }
}
