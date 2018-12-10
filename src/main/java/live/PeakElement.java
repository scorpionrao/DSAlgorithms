package live;

import java.util.Arrays;

public class PeakElement {

    /* Time Complexity: O(N), Space Complexity: O(1) */
    public static int findPeakElementNaive(int[] input) {
        for(int i = 0; i < input.length - 1; i++) {
            // first element
            if(input[i] > input[i+1]) {
                return i;
            }
        }
        return input.length - 1;
    }

    /* Time Complexity: O(Log N), Space Complexity: O(Log N) */
    public static int findPeakElementBinarySearchRecursive(int[] input) {
        return findPeakElementBinarySearchRecursive(input, 0, input.length - 1);
    }
    public static int findPeakElementBinarySearchRecursive(int[] nums, int l, int r) {
        if (l == r)
            return l;
        int mid = (l + r) / 2;
        if (nums[mid] > nums[mid + 1]) {
            return findPeakElementBinarySearchRecursive(nums, l, mid);
        } else {
            return findPeakElementBinarySearchRecursive(nums, mid + 1, r);
        }
    }

    /* Time Complexity: O(Log N), Space Complexity: O(1) */
    public static int findPeakElementBinarySearchIterative(int[] input) {

        int low = 0, high = input.length - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            System.out.println(String.format("Low-%d, Mid-%d, High-%d", low, mid, high));
            if (input[mid] > input[mid + 1]) {
                /* mid is possible solution */
                high = mid;
            } else {
                /* mid + 1 is possible solution */
                low = mid + 1;
            }
        }
        // Every solution arrives here.
        // Low and High are same at this point.
        return high;
    }

    /* Time Complexity: O(Log N), Space Complexity: O(1) */
    public static int findPeak(int[] input) {

        int low = 0, high = input.length - 1;
        while(low < high) {
            int mid = (low + high) / 2;
            System.out.println(String.format("Low-%d, Mid-%d, High-%d", low, mid, high));
            if(input[mid] > input[mid+1]) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public static void evaluate(int[] input) {
        System.out.println(Arrays.toString(input));
        System.out.println("Naive\t\t\t\t\t : " + findPeakElementNaive(input));
        System.out.println("BinarySearchRecursive\t : " + findPeakElementBinarySearchRecursive(input));
        System.out.println("BinarySearchIterative\t : " + findPeakElementBinarySearchIterative(input));
        System.out.println("BinarySearchInterview\t : " + findPeak(input));
    }

    public static void main(String[] args) {
        int[] input = {1, 3, 5, 7};
        evaluate(input);
    }

}
