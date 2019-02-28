package codecamp;

import java.util.Arrays;

public class SearchInRotatedArray {

    private static int approach1(int[] input, int search) {
        int pivotIndex = findPivot(input);
        if(input[0] <= search) {
            return binarySearch(input, 0, pivotIndex, search);
        } else {
            return binarySearch(input, pivotIndex+1, input.length-1, search);
        }
    }

    private static int findPivot(int[] input) {
        int low = 0;
        int high = input.length - 1;
        while(low < high) {
            int mid = (low + high) / 2;
            if(input[mid] < input[mid+1]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    private static int binarySearch(int[] input, int low, int high, int search) {
        while(low <= high) {
            int mid = (low + high) / 2;
            if(input[mid] == search) {
                return mid;
            } else if(input[mid] < search) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    private static void evaluate(int[] input, int search, int expectedIndex) {
        System.out.println("Input : " + Arrays.toString(input));
        System.out.println("Search : " + search);
        System.out.println("Expected : " + expectedIndex);
        int result1 = approach1(input, search);
        System.out.println("Approach1 : " + result1);
        System.out.println("------------------------------");
    }

    public static void main(String[] args) {
        int[] input1 = {3, 4, 5, 1, 2};
        int search1 = 1;
        int expectedIndex1 = 3;
        evaluate(input1, search1, expectedIndex1);

        int[] input2 = {4, 5, 6, 7, 0, 1, 2};
        int search2 = 0;
        int expectedIndex2 = 4;
        evaluate(input2, search2, expectedIndex2);

        int[] input3 = {4, 5, 6, 7, 0, 1, 2};
        int search3 = 3;
        int expectedIndex3 = -1;
        evaluate(input3, search3, expectedIndex3);

        int[] input4 = {5, 1, 3};
        int search4 = 1;
        int expectedIndex4 = 1;
        evaluate(input4, search4, expectedIndex4);
    }
}
