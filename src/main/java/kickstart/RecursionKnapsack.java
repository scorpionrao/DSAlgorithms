package kickstart;

import java.util.Arrays;

public class RecursionKnapsack {

    private static boolean solve(int[] weights, int[] values, int[][] board) {
        int[] rowCol = {0, 0};
        if(!hasUnassignedCell(board, rowCol)) {
            return true;
        }
        // there is only ONE option, find the best value
        for(int option = 0; option < 1; option++) {
            if(true /* isSafe = no restrictions */) {
                // MAKE - because no multiple options
                int row = rowCol[0];
                int previousRow = row - 1;
                int index = previousRow;
                int col = rowCol[1];

                if(row <= 0 || col <= 0) {
                    board[row][col] = 0;
                } else if(weights[previousRow] > col) {
                    board[row][col] = board[previousRow][col];
                } else {
                    int guessIgnoreWeight = board[previousRow][col];
                    int guessAddWeight = values[index] + board[row][col - weights[index]];
                    board[row][col] = Math.max(guessIgnoreWeight, guessAddWeight);
                }
                if(solve(weights, values, board)) {
                    return true;
                }
                // UNMAKE - no multiple options
            }
        }
        // backtracking
        return false;
    }

    private static boolean hasUnassignedCell(int[][] board, int[] nextUnassigned) {
        for(int row = 0; row < board.length; row++) {
            for(int col = 0; col < board[row].length; col++) {
                if(board[row][col] == -1) {
                    nextUnassigned[0] = row;
                    nextUnassigned[1] = col;
                    return true;
                }
            }
        }
        return false;
    }

    private static void print(int[][] board) {
        for(int row = 0; row < board.length; row++) {
            for(int col = 0; col < board[row].length; col++) {
                System.out.print(board[row][col] + "\t");
            }
            System.out.println();
        }
        System.out.println("*****************");
    }

    private static void solve(int[] weights, int[] values, int maxBagSize) {
        int[][] board = new int[weights.length+1][maxBagSize+1];
        for(int[] row : board) {
            Arrays.fill(row, -1);
        }
        print(board);
        solve(weights, values, board);
        print(board);
    }

    public static void main(String[] args) {

        int val[] = new int[]{5, 11, 13};
        int wt[] = new int[]{3, 7, 11};
        int W = 17;

        solve(wt, val, W);
    }

}
