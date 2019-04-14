package codecamp;

import java.util.Arrays;

public class WaysToTravel {

    /* Top Down */
    private static int approach1TopDown(int rows, int cols) {
        int[] result = new int[1];
        recurse1TopDown(rows, cols, 0, 0, rows - 1, cols - 1, result);
        return result[0];
    }

    private static void recurse1TopDown(int rows, int cols, int sr, int sc, int dr, int dc, int[] result) {
        if(sr < 0 || sr > rows - 1 || sc < 0 || sc > cols - 1) {
            return;
        }

        if(sr == dr && sc == dc) {
            result[0]++;
            return;
        }

        int[][] dirs = { {1, 0}, {0, 1} };
        for(int[] dir : dirs) {
            recurse1TopDown(rows, cols, sr + dir[0], sc + dir[1], dr, dc, result);
        }
    }

    /* Bottom Up */
    private static int approach2BottomUp(int rows, int cols) {
        return recurse2BottomUp(rows, cols, 0, 0, rows - 1, cols - 1);
    }

    private static int recurse2BottomUp(int rows, int cols, int sr, int sc, int dr, int dc) {
        if(sr < 0 || sr > rows - 1 || sc < 0 || sc > cols - 1) {
            return 0;
        }

        if(sr == dr && sc == dc) {
            return 1;
        }

        return recurse2BottomUp(rows, cols, sr + 1, sc, dr, dc) + recurse2BottomUp(rows, cols, sr, sc + 1, dr, dc);
    }

    /*
        Memoization
     */
    private static int approach3BottomUp(int rows, int cols) {
        int[][] memoize = new int[rows][cols];
        for(int[] row : memoize) {
            Arrays.fill(row, -1);
        }
        memoize[rows-1][cols-1] = 1;
        return recurse3BottomUp(rows, cols, 0, 0, rows - 1, cols - 1, memoize);
    }

    private static int recurse3BottomUp(int rows, int cols, int sr, int sc, int dr, int dc, int[][] memoize) {
        if(sr < 0 || sr > rows - 1 || sc < 0 || sc > cols - 1) {
            return 0;
        }

        if(memoize[sr][sc] != -1) {
            return memoize[sr][sc];
        }

        memoize[sr][sc] = recurse2BottomUp(rows, cols, sr + 1, sc, dr, dc) +
                          recurse2BottomUp(rows, cols, sr, sc + 1, dr, dc);

        return memoize[sr][sc];
    }

    private static int approach4DP(int rows, int cols) {
        int[][] dp = new int[rows][cols];
        for(int i = rows - 1; i >= 0; i--) {
            for(int j = cols - 1; j >= 0; j--) {
                if(i == rows - 1 && j == cols - 1) {
                    dp[i][j] = 1;
                } else if(i == rows - 1) {
                    dp[i][j] = dp[i][j+1];
                } else if(j == cols - 1) {
                    dp[i][j] = dp[i+1][j];
                } else {
                    dp[i][j] = dp[i+1][j] + dp[i][j+1];
                }
            }
        }
        return dp[0][0];
    }

    private static void evaluate(int rows, int cols, int expected) {
        System.out.println("Rows: " + rows + ", Cols: " + cols);
        System.out.println("Expected: " + expected);
        int result1 = approach1TopDown(rows, cols);
        System.out.println("Approach 1 : " + result1);
        int result2 = approach2BottomUp(rows, cols);
        System.out.println("Approach 2 : " + result2);
        int result3 = approach3BottomUp(rows, cols);
        System.out.println("Approach 3 : " + result3);
        int result4 = approach4DP(rows, cols);
        System.out.println("Approach 4 : " + result4);
        System.out.println("************************");
    }

    public static void main(String[] args) {
        evaluate(2, 2, 2);
        evaluate(5, 5, 70);
        evaluate(4, 3, 10);
    }
}
