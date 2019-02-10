package kickstart;

import java.util.Arrays;

public class Queens {

    private static void placeQueens(int n) {
        System.out.println("Result : " + placeQueens(new int[n][n], 0));
    }

    private static boolean placeQueens(int[][] board, int col) {
        if(col == board.length) {
            return true;
        }
        for(int row = 0; row < board.length; row++) {
            if(isSafe(board, row, col)) {
                board[row][col] = 1;
                if(placeQueens(board, col + 1)) {
                    return true;
                }
                board[row][col] = 0;
            }
        }
        return false;
    }

    private static boolean isSafe(int[][] board, int row, int col) {

        int rowSum = Arrays.stream(board[row]).sum();

        int colSum = 0;
        for(int j = 0; j < board[row].length; j++) {
            colSum = colSum + board[row][j];
        }

        int diagSum = 0;
        for(int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            diagSum = diagSum + board[i][j];
        }
        for(int i = row, j = col; i < board.length && j < board[i].length; i++, j++) {
            diagSum = diagSum + board[i][j];
        }

        int aDiagSum = 0;
        for(int i = row, j = col; i >= 0 && j < board.length; i--, j++) {
            aDiagSum = aDiagSum + board[i][j];
        }
        for(int i = row, j = col; i < board.length && j >= 0; i++, j--) {
            aDiagSum = aDiagSum + board[i][j];
        }

        return rowSum + colSum + diagSum + aDiagSum == 0;
    }

    public static void main(String[] args) {
        placeQueens(10);
    }
}
