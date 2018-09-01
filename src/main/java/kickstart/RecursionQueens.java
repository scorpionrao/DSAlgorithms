package kickstart;

import java.util.Arrays;

public class RecursionQueens {

    /*
        Decision    : can we find a place for queen in a column
        Options     : 8 rows
        Design      : return true/false
        Backtracking: choose one option, recursionBacktracking further
        UnMake      : revert the change
        Base Case   : TRUE
        No options  : FALSE
     */
    private static boolean recurse(boolean[][] board, int col) {

        if(col >= board.length) {
            return true;
        }
        for(int row = 0; row < board.length; row++) {
            if(decision(board, row, col)) {
                /* ********************************** */
                board[row][col] = true;
                /* ********************************** */
                if(recurse(board, col+1)) {
                    // all columns finished, base case reached
                    return true;
                }
                board[row][col] = false;
            }

        }
        return false;
    }

    private static boolean decision(boolean[][] board, int row, int col) {

        // check all columns
        for(int j = 0; j < board[row].length; j++) {
            if(board[row][j]) {
                return false;
            }
        }

        // check all rows
        for(int i = 0; i < board.length; i++) {
            if(board[i][col]) {
                return false;
            }
        }

        // check top-left diagonal
        for(int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if(board[i][j]) {
                return false;
            }
        }

        // check top-right diagonal
        for(int i = row, j = col; i >= 0 && j < board.length; i--, j++) {
            if(board[i][j]) {
                return false;
            }
        }

        // check bottom-right diagonal
        for(int i = row, j = col; i < board.length && j < board[i].length; i++, j++) {
            if(board[i][j]) {
                return false;
            }
        }

        // check bottom-left diagonal
        for(int i = row, j = col; i < board.length && j >= 0; i++, j--) {
            if(board[i][j]) {
                return false;
            }
        }
        return true;
    }

    private static void printBoard(boolean[][] board) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println("CHESS BOARD");
        boolean[][] board = new boolean[8][8];
        for(boolean[] row : board) {
            Arrays.fill(row, false);
        }
        // printBoard(board);
        recurse(board, 0);
        printBoard(board);
    }
}
