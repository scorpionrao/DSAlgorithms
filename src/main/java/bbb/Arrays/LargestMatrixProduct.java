package bbb.Arrays;

import java.util.Arrays;

public class LargestMatrixProduct {

    private static long maxProductRecurse1(int[][] matrix) {
        return maxProductRecurse1(matrix, 0, 0);
    }

    private static long maxProductRecurse1(int[][] matrix, int row, int col) {
        if(row == matrix.length || col == matrix[row].length) {
            return 1;
        }

        return matrix[row][col] * Math.max(
                maxProductRecurse1(matrix, row, col + 1),
                maxProductRecurse1(matrix, row + 1, col));
    }

    private static long maxProductRecurse2(int[][] matrix) {
        return maxProductRecurse2(matrix, matrix.length - 1, matrix[0].length - 1);
    }

    private static long maxProductRecurse2(int[][] matrix, int row, int col) {
        if(row < 0 || col < 0) {
            return 1;
        }
        return matrix[row][col] * Math.max(
                maxProductRecurse2(matrix, row - 1, col),
                maxProductRecurse2(matrix, row, col - 1));
    }

    /*
        Time: O(2 ^ (m+n))
        Space: O(m * n) cache, O(m * n) recursion stack
     */
    private static long maxProductRecurseWithCache(int[][] matrix) {
        long[][] cache = new long[matrix.length][matrix[0].length];
        for(long[] rows : cache) {
            Arrays.fill(rows, -1);
        }
        return maxProductRecurseWithCache(matrix, 0, 0, cache);
    }

    private static long maxProductRecurseWithCache(int[][] matrix, int row, int col, long[][] cache) {
        if(row == matrix.length || col == matrix[row].length) {
            return 1;
        }
        if(cache[row][col] != -1) {
            return cache[row][col];
        }

        cache[row][col] = matrix[row][col] * Math.max(
                maxProductRecurseWithCache(matrix, row, col + 1, cache),
                maxProductRecurseWithCache(matrix, row + 1, col, cache));

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
        System.out.println("Recurse1 : " + maxProductRecurse1(matrix));
        System.out.println("Recurse2 : " + maxProductRecurse2(matrix));
        System.out.println("RecurseWithCache : " + maxProductRecurseWithCache(matrix));
        System.out.println("BottomUp: " + maxProductBottomUp(matrix));
    }

    public static void main(String[] args) {
        int[][] matrix1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        evaluate(matrix1);
        int[][] matrix2 = {{-1, 2, 3}, {4, 5, -6}, {7, 8, 9}};
        evaluate(matrix2);
    }
}
