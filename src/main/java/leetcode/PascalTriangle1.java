package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PascalTriangle1 {

    /*
                1           0 (1)
               1 1          1 (2)
              1 2 1         2 (3)
             1 3 3 1        3 (4)
            1 4 6 4 1       4 (5)
           1 5 10 10 5 1    5 (6)
          1 6 15 20 15 6 1  6 (7)

        Time : O(N * N) -> number of rows
        Space : O(N)

        k = 3
        i = 3
        j = 0
        [1, 3, 3, 1]
     */

    private static List<List<Integer>> getRows1(int rowIndex) {

        List<List<Integer>> result = new ArrayList<>();

        for(int row = 0; row <= rowIndex; row++) {
            List<Integer> current = new ArrayList<>();
            current.add(1);

        }
        return result;
    }

    private static List<Integer> getRows2(int rowIndex) {
        List<Integer> previous = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        current.add(1);
        for(int row = 1; row <= rowIndex; row++) {
            current = new ArrayList<>();
            for(int col = 0; col < row + 1; col++) {
                if(col == 0 || col == row) {
                    current.add(1);
                } else {
                    current.add(previous.get(col-1) + previous.get(col));
                }
            }
            previous = current;
        }
        return current;
    }

    private static int[] getRows3(int rowIndex) {

        int[] result = new int[rowIndex+1];
        result[0] = 1;
        for(int row = 1; row < rowIndex+1; row++) {
            for(int end = row; end >= 1; end--) {
                result[end] = result[end] + result[end-1];
            }
        }
        return result;
    }

    private static void evaluate(int rowIndex) {
        System.out.println("Row : " + rowIndex);
        List<List<Integer>> result1 = getRows1(rowIndex);
        System.out.println("Approach 1 ");
        for(List<Integer> row : result1) {
            System.out.println();
        }
        List<Integer> result2 = getRows2(rowIndex);
        System.out.println("Approach 2 " + result2.toString());
        int[] result3 = getRows3(rowIndex);
        System.out.println("Approach 3 " + Arrays.toString(result3));
    }

    public static void main(String[] args) {
        int rowIndex = 3;
        evaluate(rowIndex);
    }
}
