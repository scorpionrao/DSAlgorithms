package live;

import java.util.Scanner;

public class MineSweeper {

    public static class Board {

        double probabilityOfBombs;

        /*
            Bombs - Integer.MAX_VALUE (Visible - *)
            Adjacent - Integer (Visible - Integer)
            0 - Flood (Empty)
         */
        int[][] preComputedMatrix;
        boolean[][] gameMatrix;
        int bombCount;

        public Board(int numOfRows, int numOfCols, double probabilityOfBombs) {

            this.probabilityOfBombs = probabilityOfBombs;
            this.preComputedMatrix = new int[numOfRows][numOfCols];
            this.gameMatrix = new boolean[numOfRows][numOfCols];
            fillBombs();
            fillAdjacentBombCount();
        }

        private void fillBombs() {
            for(int i = 0; i < preComputedMatrix.length; i++) {
                for(int j = 0; j < preComputedMatrix[i].length; j++) {
                    if(Math.random() < probabilityOfBombs) {
                        preComputedMatrix[i][j] = Integer.MAX_VALUE;
                        bombCount++;
                    } else {
                        preComputedMatrix[i][j] = 0;
                    }
                }
            }
        }

        private void fillAdjacentBombCount() {
            for(int i = 0; i < preComputedMatrix.length; i++) {
                for(int j = 0; j < preComputedMatrix[i].length; j++) {
                    // Non Bombs
                    if(preComputedMatrix[i][j] != Integer.MAX_VALUE) {
                        for(int adjRow = i-1; adjRow <= i+1; adjRow++) {
                            for (int adjCol = j-1; adjCol <= j+1; adjCol++) {
                                if (adjRow >= 0
                                    && adjCol >= 0
                                    && adjRow < preComputedMatrix.length
                                    && adjCol < preComputedMatrix[i].length
                                    && preComputedMatrix[adjRow][adjCol] == Integer.MAX_VALUE) {

                                        preComputedMatrix[i][j]++;
                                }
                            }
                        }
                    }
                }
            }
        }

        private void printHiddenMatrix() {
            System.out.println("*** HIDDEN MATRIX ***");
            for(int i = 0; i < preComputedMatrix.length; i++) {
                for(int j = 0; j < preComputedMatrix[i].length; j++) {

                    if(preComputedMatrix[i][j] == Integer.MAX_VALUE) {
                        // BOMB
                        System.out.print("| * ");
                    } else if (preComputedMatrix[i][j] > 0){
                        // ADJACENT
                        System.out.print("| " + preComputedMatrix[i][j] + " ");
                    } else {
                        // EMPTY
                        System.out.print("|   ");
                    }
                    if(j == preComputedMatrix[i].length - 1) {
                        System.out.print("|");
                    }
                }
                System.out.println();
                System.out.println("- - - - - - - - -");
            }
            System.out.println("\nEND_YEAR_TYPE\n");
        }

        private void printGameMatrix() {
            System.out.println("*** GAME MATRIX ***");

            int openCells = 0;

            for(int i = 0; i < preComputedMatrix.length; i++) {
                for(int j = 0; j < preComputedMatrix[i].length; j++) {

                    if(gameMatrix[i][j]) {
                        if(preComputedMatrix[i][j] == Integer.MAX_VALUE) {
                            System.out.println("GAME ENDED. You clicked on " + i + ", " + j);
                            return;
                        } else if (preComputedMatrix[i][j] > 0){
                            // ADJACENT
                            System.out.print("| " + preComputedMatrix[i][j] + " ");
                        } else {
                            // EMPTY
                            System.out.print("|   ");
                        }
                        openCells++;
                    } else {
                        System.out.print("| H ");
                    }
                    if(j == preComputedMatrix[i].length - 1) {
                        System.out.print("|");
                    }
                }
                System.out.println();
                System.out.println("- - - - - - - - -");

            }
            System.out.println("\nEND_YEAR_TYPE\n");

            if(openCells == (preComputedMatrix.length * preComputedMatrix[0].length) - bombCount) {
                System.out.println("***GAME WON***");
            }
        }

        private void play(int row, int col) {
            gameMatrix[row][col] = true;
            printHiddenMatrix();
            printGameMatrix();
        }

    }

    public static void main(String[] args) {
        Board board = new Board(4, 4, 0.2);
        board.printHiddenMatrix();
        board.printGameMatrix();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            board.play(row, col);
        }
    }
}
