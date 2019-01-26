package leetcode;

import java.util.Arrays;

public class LongestMountain {

    /* Time : O(3*N), Space : O(N) */
    private static int longestMountainApproach1(int[] input) {

        int maxLength = 0;

        int[] incEdgesBeforeMe = new int[input.length];
        int[] decEdgesAfterMe = new int[input.length];

        for (int i = 1; i < input.length; i++) {
            if (input[i] > input[i - 1]) {
                incEdgesBeforeMe[i] = incEdgesBeforeMe[i - 1] + 1;
            }
        }

        for (int i = input.length - 2; i >= 0; i--) {
            if (input[i] > input[i + 1]) {
                decEdgesAfterMe[i] = decEdgesAfterMe[i + 1] + 1;
            }
        }

        for (int i = 1; i < input.length - 1; ++i) {
            if (incEdgesBeforeMe[i] > 0 && decEdgesAfterMe[i] > 0) {
                // guarantees >= 3
                maxLength = Math.max(maxLength, incEdgesBeforeMe[i] + decEdgesAfterMe[i] + 1);
            }
        }
        return maxLength;

    }

    private static void evaluate(int[] input) {
        System.out.println(Arrays.toString(input));
        int result1 = longestMountainApproach1(input);
        System.out.println("Approach 1 : " + result1);
    }

    public static void main(String[] args) {
        int[] input1 = {2, 1, 4, 7, 3, 2, 5};
        evaluate(input1);
        int[] input2 = {2, 2, 2};
        evaluate(input2);
    }
}
