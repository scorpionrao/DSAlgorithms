package leetcode;

import java.util.Arrays;

public class NthSmallestNumber {

    /* Time : O(N^2), Space : O(1) */
    private static int kthSmallestApproach1(int[] input, int m) {
        for(int i = 0; i < input.length - 1; i++) {
            int minIndex = i;
            for(int j = i+1; j < input.length; j++) {
                if(input[j] < input[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = input[i];
            input[i] =  input[minIndex];
            input[minIndex] = temp;
        }
        return input[m-1];
    }

    /* Time : O(N log N), Space : O(1) */
    private static int kthSmallestApproach2(int[] input, int k) {
        Arrays.sort(input);
        return input[k-1];
    }

    /* Min heap : Insert all elements in a head and extract k times */
    /*
        Max heap :
        - insert first k elements - k log N.
        - iterate through k+1 to N-1 - O(k * Log N + (n-k) * Logk) - only if the values are in descending order)
            - if (value < heap.extractMax()) - extract max and insert value.
        - at the end - extract max

    */

    /*
        Partition the input array into two parts based on the last element - O(N)

        if(pivot index == k), return element at pivot index
        if(pivot index > k), recurse on left part
        else, recurse on right part for (k-pivot)

        Time: O(N) average case, O(N^2) worst case.

        {3, 2, 5, 6, 21, 15, 1}

        {1, 2, 3, 5, 6, 15, 21}
                     ^
        pivotIndex = 5, k = 6 or k - 1 = 5

        pivotIndex = 3, k = 5 or k - 1 = 4
     */

    private static int kthSmallestApproach3(int[] input, int k, int low, int high) {

        if(low < high) {
            int pivotIndex = partition(input, low, high);
            if(pivotIndex == k) {
                return input[pivotIndex];
            } else if(pivotIndex > k) {
                return kthSmallestApproach3(input, k, low, pivotIndex - 1);
            } else {
                return kthSmallestApproach3(input, k - pivotIndex, pivotIndex + 1, high);
            }
        }
        return -1;
    }

    private static int partition(int[] array, int low, int high) {

        int pivotValue = array[high];
        int indexLowerThanPivot = low - 1;
        for(int index = low; index < high; index++) {
            if(array[index] <= pivotValue) {
                ++indexLowerThanPivot;
                swap(array, indexLowerThanPivot, index);
            }
        }
        swap(array, indexLowerThanPivot+1, high);
        return indexLowerThanPivot+1;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int[] input = {3, 2, 5, 6, 21, 1, 15};
        int nth = 4;
        System.out.println("Nth Smallest Approach 1: " + kthSmallestApproach1(input.clone(), nth));
        System.out.println("Nth Smallest Approach 2: " + kthSmallestApproach2(input.clone(), nth));
        System.out.println("Nth Smallest Approach 3: " + kthSmallestApproach3(input.clone(), nth - 1, 0, input.length - 1));
    }
}
