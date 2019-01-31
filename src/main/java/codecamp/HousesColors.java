package codecamp;

import java.util.Arrays;

public class HousesColors {

    /*
        Assumes only 3 colors exist
     */
    private static int[][] nonAdjacentColors1(int[][] cost) {
        int[][] result = new int[cost.length][cost[0].length];
        for(int i = 0; i < result.length; i++) {
            for(int j = 0; j < result[i].length; j++) {
                result[i][j] = cost[i][j];
            }
        }
        for(int i = 1; i < result.length; i++) {
            result[i][0] = result[i][0] + Math.min(result[i-1][1], result[i-1][2]);
            result[i][1] = result[i][1] + Math.min(result[i-1][0], result[i-1][2]);
            result[i][2] = result[i][2] + Math.min(result[i-1][0], result[i-1][1]);
        }
        return result;
    }

    private static int[][] nonAdjacentColors2(int[][] cost) {
        int[][] result = new int[cost.length][cost[0].length];
        for(int i = 0; i < result.length; i++) {
            for(int j = 0; j < result[i].length; j++) {
                result[i][j] = cost[i][j];
            }
        }
        for(int i = 1; i < result.length; i++) {
            for(int j = 0; j < result[i].length; j++) {
                result[i][j] = result[i][j] + getMin(result, i-1, j);
            }
        }
        return result;
    }

    private static int getMin(int[][] result, int row, int excludeCol) {
        int min = Integer.MAX_VALUE;
        for(int col = 0; col < result[row].length; col++) {
            if(col != excludeCol) {
                min = Math.min(min, result[row][col]);
            }
        }
        return min;
    }


    public static void main(String[] args) {
        int[][] cost = {
                {6, 1, 2},
                {3, 7, 4},
                {5, 1, 1},
                {1, 3, 2},
                {4, 4, 6}
        };
        for(int[] row : cost) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("**********************");
        System.out.println("Approach 1");
        int[][] result1 = nonAdjacentColors1(cost);
        for(int[] row : result1) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("**********************");
        System.out.println("Approach 2");
        int[][] result2 = nonAdjacentColors2(cost);
        for(int[] row : result2) {
            System.out.println(Arrays.toString(row));
        }
    }
}
