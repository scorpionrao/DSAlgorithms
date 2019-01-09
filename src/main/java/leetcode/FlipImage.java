package leetcode;

import java.util.Arrays;

public class FlipImage {

    public static int[][] flipAndInvertImage(int[][] A) {
        int rows = A.length;
        for(int i = 0; i < rows; i++) {
            int cols = A[i].length;
            for(int j = 0; j < cols / 2 + cols % 2; j++) {
                int rightCol = cols-j-1;
                if(A[i][j] == A[i][rightCol]) {
                    int temp = A[i][j];
                    A[i][j] = A[i][rightCol] ^ 1;
                    A[i][rightCol] = temp ^ 1;
                }
            }
        }
        return A;
    }

    public static void main(String[] args) {

        int[][] A = {{1, 1, 0}, {1, 0, 1}, {0, 0, 0}};
        int[][] result = flipAndInvertImage(A);
        System.out.println("Expected : [[1,0,0],[0,1,0],[1,1,1]]");
        System.out.println("Actual");
        for(int[] row : result) {
            System.out.println(Arrays.toString(row));
        }
    }
}
