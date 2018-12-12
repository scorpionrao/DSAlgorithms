package leetcode;

import java.util.ArrayList;
import java.util.List;

public class MatrixSpiral {

    public List<Integer> printSprial(int[][] matrix) {

        if(matrix == null) {
            throw new NullPointerException();
        }

        List<Integer> result = new ArrayList<>();
        int rowBegin = 0;
        int rowEnd = matrix.length - 1;
        int colBegin = 0;
        int colEnd = matrix[0].length - 1;
        int total = matrix.length * matrix[0].length;

        while(result.size() < total) {
            // Traverse right
            for(int j = colBegin; j <= colEnd && result.size() < total; j++) {
                result.add(matrix[rowBegin][j]);
            }
            rowBegin++;
            // Traverse down
            for(int i = rowBegin; i <= rowEnd && result.size() < total; i++) {
                result.add(matrix[i][colEnd]);
            }
            colEnd--;
            // collect bottom row
            for(int j = colEnd; j >= colBegin && result.size() < total; j--) {
                result.add(matrix[rowEnd][j]);
            }
            rowEnd--;
            // collect left col
            for(int i = rowEnd; i >= rowBegin && result.size() < total; i--) {
                result.add(matrix[i][colBegin]);
            }
            colBegin++;
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        MatrixSpiral spiral = new MatrixSpiral();
        List<Integer> result = spiral.printSprial(matrix);
        System.out.println("Expected: [1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]");
        System.out.println("Actual:   " + result.toString());
    }
}
