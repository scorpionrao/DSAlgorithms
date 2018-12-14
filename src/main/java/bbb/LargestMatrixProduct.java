package bbb;

import java.util.Arrays;

public class LargestMatrixProduct {

    /*
        Time: O(2 ^ (m+n))
        Space: O(m * n) cache, O(m * n) recursion stack
     */
    private static long maxProductRecurse(int[][] matrix) {
        long[][] cache = new long[matrix.length][matrix[0].length];
        for(long[] rows : cache) {
            Arrays.fill(rows, -1);
        }
        return maxProductRecurse(matrix, 0, 0, cache);
    }

    private static long maxProductRecurse(int[][] matrix, int row, int col, long[][] cache) {
        if(row == matrix.length || col == matrix[row].length) {
            return 1;
        }
        if(cache[row][col] != -1) {
            return cache[row][col];
        }

        cache[row][col] = matrix[row][col] * Math.max(
                            maxProductRecurse(matrix, row, col + 1, cache),
                            maxProductRecurse(matrix, row + 1, col, cache));

        return cache[row][col];
    }

    /*
        Dynamic Programming - Optimal Substructure, Overlapping subproblems.

        Optimal substructure
            - All recursion problems have optimal substructures.
            - By solving subproblems, we can solve bigger subproblems.

        Overlapping subproblems
            - Same inputs are used multiple times in calling subproblems.

        Topdown solution:
            - Start with end solution to smallest subproblem.
            - Cache will address this problem. Cache should be based on variants.
        Bottomup solution:
            - Start with subproblems and build up to end solution.


     */

    /*
        Time: O(m * n))
        Space: O(m * n) - can call it cache

        Only works for Positive values
     */
    private static long maxProductBottomUp(int[][] matrix) {
        int[][] product = new int[matrix.length][matrix[0].length];
        for(int i = matrix.length - 1; i >= 0; i--) {
            for(int j = matrix.length - 1; j >= 0; j--) {
                if(i == matrix.length - 1 && j == matrix[0].length - 1) {
                    product[i][j] = matrix[i][j];
                } else if(i == matrix.length - 1) {
                    product[i][j] = matrix[i][j] * product[i][j+1];
                } else if(j == matrix[0].length - 1) {
                    product[i][j] = matrix[i][j] * product[i+1][j];
                } else {
                    product[i][j] = matrix[i][j] * Math.max(product[i][j+1], product[i+1][j]);
                }
            }
        }
        return product[0][0];
    }

    private static void evaluate(int[][] matrix) {
        for(int[] rows : matrix) {
            System.out.print(Arrays.toString(rows) + " ");
        }
        System.out.println();
        System.out.println("Recurse : " + maxProductRecurse(matrix));
        System.out.println("BottomUp: " + maxProductBottomUp(matrix));
    }

    public static void main(String[] args) {
        int[][] matrix1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        evaluate(matrix1);
        int[][] matrix2 = {{-1, 2, 3}, {4, 5, -6}, {7, 8, 9}};
        evaluate(matrix2);
    }
}
