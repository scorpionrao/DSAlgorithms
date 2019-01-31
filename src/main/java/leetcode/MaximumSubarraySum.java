package leetcode;

import java.util.Arrays;

public class MaximumSubarraySum {

    /*

        Time: O(N^2), Space: O(N^2)
     */
    private static int maxSubarraySumApproach1(int[] input) {

        int[][] sumMatrix = new int[input.length][input.length];
        int maxSum = Integer.MIN_VALUE;
        for(int start = 0; start < input.length; start++) {
            for(int end = start; end < input.length; end++) {
                int sum = 0;
                for(int index = start; index <= end; index++) {
                    sum = sum + input[index];
                }
                sumMatrix[start][end] = sum;
                maxSum = Math.max(maxSum, sumMatrix[start][end]);
            }
        }
        return maxSum;
    }


    /*
        Algorithm:
        Best(i) = Best(i-1) + MaxSumIncluding(i)

        Time: O(N), Space: O(N)
     */
    private static int maxSubarraySumApproach2(int[] input) {

        int[] maxEndingAtMe = new int[input.length];

        maxEndingAtMe[0] = input[0];
        int maxSum = maxEndingAtMe[0];

        for(int i = 1; i < maxEndingAtMe.length; i++) {
            // Will prev improve me ?
            maxEndingAtMe[i] = (maxEndingAtMe[i-1] > 0 ? maxEndingAtMe[i-1] : 0) + input[i];
            maxSum = Math.max(maxSum, maxEndingAtMe[i]);
        }
        return maxSum;

    }


    /*
        Algorithm:
        Best(i) = Best(i-1) + MaxSumIncluding(i)

        Time: O(N), Space: O(1)
     */
    private static int maxSubarraySumApproach3(int[] input) {
        int maxWithMe = input[0];
        int maxSum = input[0];
        for(int i = 1; i < input.length; i++) {
            maxWithMe = Math.max(maxWithMe + input[i], input[i]);
            maxSum = Math.max(maxSum, maxWithMe);
        }
        return maxSum;

    }

    private static int[] maxSubarray(int[] input, int k) {
        int[] maxes = new int[input.length - k + 1];
        for(int i = 0; i < input.length - k + 1; i++) {
            int max = input[i+0];
            for(int j = 1; j < k; j++) {
                max = Math.max(max, input[i+j]);
            }
            maxes[i] = max;
        }
        return maxes;
    }

    private static void evaluate(int[] input) {
        System.out.println("Input : " + Arrays.toString(input));
        int result1 = maxSubarraySumApproach1(input);
        System.out.println("Approach 1 : " + result1);
        System.out.println("*********************************");
        int result2 = maxSubarraySumApproach2(input);
        System.out.println("Approach 2 : " + result2);
        System.out.println("*********************************");
        int result3 = maxSubarraySumApproach3(input);
        System.out.println("Approach 3 : " + result3);
        System.out.println("*********************************");
        int[] result = maxSubarray(input, 3);
        System.out.println("List of max sub array : " + Arrays.toString(result));
    }

    public static void main(String[] args) {
        int[] input1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        evaluate(input1);
    }
}
