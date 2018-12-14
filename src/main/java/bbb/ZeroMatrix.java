package bbb;

import java.util.Arrays;

public class ZeroMatrix {

    /* Time: O(N^2), Space: O(N^2) */
    public static boolean[][] zeroMatrixApproach1(boolean[][] matrix) {
        boolean[][] result = new boolean[matrix.length][matrix[0].length];
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j]) {
                    for(int col = 0; col < matrix[i].length; col++) {
                        result[i][col] = true;
                    }
                    for(int row = 0; row < matrix.length; row++) {
                        result[row][j] = true;
                    }
                }
            }
        }
        return result;
    }

    /* Time: O(N^2), Space: O(N) */
    public static boolean[][] zeroMatrixApproach2(boolean[][] matrix) {
        /* only for reuse, input matrix can be reused */
        boolean[][] result = new boolean[matrix.length][matrix[0].length];
        boolean[] rows = new boolean[matrix.length];
        boolean[] cols = new boolean[matrix[0].length];

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j]) {
                    rows[i] = true;
                    cols[j] = true;
                }
            }
        }
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                if(rows[i] || cols[j]) {
                    result[i][j] = true;
                }
            }
        }
        return result;
    }

    /* Time: O(N^2), Space: O(1) */
    public static boolean[][] zeroMatrixApproach3(boolean[][] matrix) {

        boolean freezeZeroRowState = false;
        boolean freezeZeroColState = false;

        for(int row = 0; row < matrix.length; row++) {
            freezeZeroColState = freezeZeroColState | matrix[row][0];
        }

        for(int col = 0; col < matrix[0].length; col++) {
            freezeZeroRowState = freezeZeroRowState | matrix[0][col];
        }

        // Use Row 0 and Col 0 for approach 2 solution, saving space
        for(int i = 1; i < matrix.length; i++) {
            for(int j = 1; j < matrix[i].length; j++) {
                if(matrix[i][j]) {
                    matrix[0][j] = true;
                    matrix[i][0] = true;
                }
            }
        }

        // update the remaining matrix based on Row 0 and Col 0
        for(int i = 1; i < matrix.length; i++) {
            for(int j = 1; j < matrix[i].length; j++) {
                if(matrix[i][0] || matrix[0][j]) {
                    matrix[i][j] = true;
                }
            }
        }

        // update Row 0 and Col 0 based on freeze
        for(int col = 0; col < matrix[0].length; col++) {
            matrix[0][col] = freezeZeroRowState;
        }

        for(int row = 0; row < matrix.length; row++) {
            matrix[row][0] = freezeZeroColState;
        }
        return matrix;
    }



    private static void evaluate(boolean[][] matrix) {
        printMatrix(matrix);
        System.out.println("Approach1   : ");
        printMatrix(zeroMatrixApproach1(matrix));
        System.out.println("Approach2   : ");
        printMatrix(zeroMatrixApproach2(matrix));
        System.out.println("Approach3   : ");
        printMatrix(zeroMatrixApproach3(matrix));
    }

    private static void printMatrix(boolean[][] matrix) {
        for(boolean[] rows : matrix) {
            System.out.println(Arrays.toString(rows));
        }
    }
    public static void main(String[] args) {
        boolean[][] matrix1 = {{true, false, false}, {false, false, false}, {false, false, false}};
        evaluate(matrix1);
        System.out.println("*********************");
    }
}
