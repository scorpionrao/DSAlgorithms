package bbb.DynamicProgramming;

import java.util.Arrays;

public class SquareSubmatrix {

    /*
        Convert check at each cell --> UPPER LEFT HAND CORNER recursion problem

        If(cell == true), min(upper left hand corner sub arrays starting at bottom, right, bottom right)

        Recursion Tree : O(N*M) height with 3 recursion calls at each node.

        Time : O(N * M * 3 ^ (N*M)), Space : O(1), O(N + M) due to recursive stack.

        Optimal substructure : Yes exists. Smaller subarray solves bigger subarray.
        Overlapping subproblems : Repeated calls to same [i][j]
     */
    public int squareSubmatrixNaiveFirstApproach(boolean[][] input) {
        if(input.length == 0 || input[0].length == 0) {
            return 0;
        }
        int max = 0;
        for(int i = 0; i < input.length; i++) {
            for(int j = 0; j < input[i].length; j++) {
                if(input[i][j]) {
                    max = Math.max(max, squareSubmatrixNaiveFirstApproach(input, i, j));
                }
            }
        }
        return max;
    }

    public int squareSubmatrixNaiveFirstApproach(boolean[][] input, int row, int col) {

        if(row == input.length || col == input[0].length) {
            return 0;
        }

        if(!input[row][col]) {
            return 0;
        }
        return 1 + Math.min(
                        Math.min(squareSubmatrixNaiveFirstApproach(input, row+1, col),
                                squareSubmatrixNaiveFirstApproach(input, row, col+1)
                                ),
                        squareSubmatrixNaiveFirstApproach(input, row+1, col+1)
                    );
    }

    /*
        Each cell calculated only once. Each cell UPPER LEFT HAND OF SUB ARRAY

        Time : O(N * M) complexity.

        Space : O(N * M) complexity.
     */
    public int squareSubmatrixTopDownCacheSecondApproach(boolean[][] input) {
        int[][] cache = new int[input.length][input[0].length];
        for(int[] rows : cache) {
            Arrays.fill(rows, -1);
        }

        int max = 0;
        for(int i = 0; i < input.length; i++) {
            for(int j = 0; j < input[i].length; j++) {
                // start from [0,0] to [bottom, right]
                if(input[i][j]) {
                    max = Math.max(max,
                            squareSubmatrixTopDownCacheSecondApproach(input, i, j, cache));
                }
            }
        }
        return max;
    }

    private int squareSubmatrixTopDownCacheSecondApproach(boolean[][] input,
                                              int row, int col, int[][] cache) {

        if(row == input.length || col == input[row].length) {
            return 0;
        }

        if(!input[row][col]) {
            return 0;
        }

        if(cache[row][col] > 0) {
            return cache[row][col];
        }

        cache[row][col] =
                1 + Math.min(
                        Math.min(
                            squareSubmatrixTopDownCacheSecondApproach(input, row+1, col, cache),
                            squareSubmatrixTopDownCacheSecondApproach(input, row, col+1, cache)),
                        squareSubmatrixTopDownCacheSecondApproach(input, row+1, col+1, cache)
                );

        return cache[row][col];
    }


    /*
        Each cell calculated only once. Each cell BOTTOM RIGHT HAND OF SUB ARRAY

        Time : O(N * M) complexity.

        Space : O(N * M) complexity.
     */
    public int squareSubmatrixBottomUpCacheThirdApproach(boolean[][] input) {
        int[][] cache = new int[input.length][input[0].length];
        for(int[] rows : cache) {
            Arrays.fill(rows, -1);
        }

        int max = 0;
        for(int i = 0; i < input.length; i++) {
            for(int j = 0; j < input[i].length; j++) {
                if(i == 0 || j == 0) {
                    cache[i][j] = input[i][j] ? 1 : 0;
                } else if(input[i][j]) {
                    // start from [0,0] to [bottom, right]
                    cache[i][j] = 1 + Math.min(
                                        Math.min(cache[i][j-1], cache[i-1][j]),
                                        cache[i-1][j-1]
                                      );
                }
                max = Math.max(max, cache[i][j]);
            }
        }
        return max;
    }
}
