package live;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TicTacToeG {

    int rows;
    int cols;

    TicTacToeG(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    public boolean hasWinner1(int[][] board) {

        int[] rowSum = new int[rows];
        int[] colSum = new int[cols];
        int diagSum = 0;
        int aDiagSum = 0;

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                rowSum[i] = rowSum[i] + board[i][j];
                colSum[j] = colSum[j] + board[i][j];
                if(i == j) {
                    diagSum = diagSum + board[i][j];
                }
                if(i + j + 1 == rows) {
                    aDiagSum = aDiagSum + board[i][j];
                }
            }
        }

        for(int i = 0; i < rowSum.length; i++) {
            if(Math.abs(rowSum[i]) == rows) {
                return true;
            }
        }
        for(int j = 0; j < colSum.length; j++) {
            if(Math.abs(colSum[j]) == cols) {
                return true;
            }
        }
        return Math.abs(diagSum) == rows || Math.abs(aDiagSum) == rows;
    }


    public static void main(String[] args) {
        TicTacToeG ticTacToeG = new TicTacToeG(3, 3);
        int[][] board = {{1, -1, 0}, {1, -1, 0}, {1, 0, 0}};
        for(int[] row : board) {
            System.out.println(Arrays.toString(row));
        }
        boolean result1 = ticTacToeG.hasWinner1(board);
        System.out.println("Result1 : " + result1);
    }
}
