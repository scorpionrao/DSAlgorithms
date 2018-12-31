package bbb.Arrays;

import java.util.Arrays;

public class SearchSortedMatrix {

    /* Time: O(m*n), Space: O(1) */
    private static boolean searchSortedMatrixApproach1(int[][] input, int search) {

        for(int i = 0; i < input.length; i++) {
            for(int j = 0; j < input[i].length; j++) {
                if(input[i][j] == search) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Time: O(m * log n), Space: O(1) */
    private static boolean searchSortedMatrixApproach2(int[][] input, int search) {

        for(int[] row : input) {
            if(binarySearch(row, search)) {
                return true;
            }
        }
        return false;
    }

    private static boolean binarySearch(int[] array, int search) {
        int low = 0;
        int high = array.length - 1;
        while(low <= high) {
            int mid = (low + high) / 2;
            if(array[mid] == search) {
                return true;
            } else if(array[mid] < search) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }


    /* Time: O(m+n), Space: O(1) */
    private static boolean searchSortedMatrixApproach3(int[][] input, int search) {

        int row = 0;
        int col = input[row].length - 1;

        while(row < input.length && col >= 0) {
            System.out.println("Examining - " + row + " " + col);
            if(input[row][col] == search) {
                return true;
            }
            if(input[row][col] < search) {
                row++;
            } else {
                col--;
            }
        }
        return false;
    }


    private static void evaluate(int[][] input, int search) {
        for(int[] row : input) {
            System.out.println(Arrays.toString(row));
        }
        boolean searchResult1 = searchSortedMatrixApproach1(input, search);
        System.out.println("Approach1 : " + searchResult1);
        boolean searchResult2 = searchSortedMatrixApproach2(input, search);
        System.out.println("Approach2 : " + searchResult2);
        boolean searchResult3 = searchSortedMatrixApproach3(input, search);
        System.out.println("Approach3 : " + searchResult3);
    }

    public static void main(String[] args) {
        int[][] input = {{0, 1, 2, 11},
                         {4, 5, 6, 12},
                         {8, 9, 10, 13}
                        };
        evaluate(input, 8);
    }
}
