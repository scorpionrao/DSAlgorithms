package bbb.Arrays;

import java.util.Arrays;

public class SquareSubmatrix {

    /*
        Time: O(N^4), Space: O(1)

        Bruteforce preferred when lack of space and smaller arrays
    */
    private static int squareSubmatrixApproach1(int[][] input) {
        if (input.length == 0 || input[0].length == 0) {
            return 0;
        }

        int maxLength = 0;
        for(int i = 0; i < input.length; i++) {
            for(int j = 0; j < input[i].length; j++) {
                for(int size = 1; size <= Math.min(input.length - i, input[i].length - j); size++) {
                    int countOnes = 0;
                    for(int gridRow = i; gridRow < i + size; gridRow++) {
                        for (int gridCol = j; gridCol < j + size; gridCol++) {
                            if(input[gridRow][gridCol] == 1) {
                                countOnes++;
                            }
                        }
                    }
                    if(countOnes == size * size) {
                        maxLength = Math.max(maxLength, size);
                    }
                }
            }
        }
        return maxLength;
    }

    /*
        Time: O(N^M), Space: O(N^M)

        DP when space is available and longer arrays where time is important.
    */
    private static int squareSubmatrixApproach2(int[][] input) {
        if(input.length == 0 || input[0].length == 0) {
            return 0;
        }

        int maxLength = 0;

        int[][] sizes = new int[input.length][input[0].length];
        for(int i = 0; i < sizes.length; i++) {
            for(int j = 0; j < sizes[i].length; j++) {
                if(i == 0 || j == 0) {
                    // Because input values are 0 or 1, it goes with length 0 or 1
                    sizes[i][j] = input[i][j];
                } else if(input[i][j] == 1) {
                    sizes[i][j] = 1 + Math.min(Math.min(sizes[i-1][j-1], sizes[i-1][j]), sizes[i][j-1]);
                } else {
                    // Pure 0 case, Java initializes to 0
                }
                maxLength = Math.max(maxLength, sizes[i][j]);
            }
        }
        return maxLength;
    }

    private static int squareSubmatrixApproach3(int[][] input) {
        return recurse(input, input.length - 1, input[0].length - 1);
    }

    private static int recurse(int[][] input, int row, int col) {
        if(row < 0 || col < 0) {
            return 0;
        }
        if(row == 0 || col == 0) {
            // assumption only 0 or 1 values are allowed
            return input[row][col];
        }


        return Math.max(1,
                1 + Math.min(
                        Math.min(recurse(input, row-1, col-1), recurse(input, row-1, col)),
                        recurse(input, row, col-1)));
    }

    private static void evaluate(int[][] matrix) {
        for(int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
        int result1 = squareSubmatrixApproach1(matrix);
        System.out.println("Approach1 : " + result1);
        int result2 = squareSubmatrixApproach2(matrix);
        System.out.println("Approach2 : " + result2);
        int result3 = squareSubmatrixApproach3(matrix);
        System.out.println("Approach3 : " + result3);
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 1, 1, 0}, {1, 1, 1, 1}, {1, 1, 1, 0}};
        evaluate(matrix);
    }
}
