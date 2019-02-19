package leetcode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class MaximumOfFixedSizeSubArray
{

    /* Time : O(N * K), Space : O(1) */
    private static int[] maximumSubArray1(int[] input, int window) {
        int[] maxes = new int[input.length - window + 1];
        for(int start = 0; start < input.length - window + 1; start++) {
            int max = input[start+0];
            for(int end = 1; end < window; end++) {
                max = Math.max(max, input[start+end]);
            }
            maxes[start] = max;
        }
        return maxes;
    }

    /* Time : O(N), Space : O(K) */
    /*
        Algorithm:
        - Use Deque to store indexes
        - For the first K elements,
            add indexes to Deque while removing useless indexes on the right.
        - For remaining elements,
            clean up heads if out of the window on left.
            add indexes to while removing useless indexes on the right.

        - NOTE : The indexes are always sorted in Deque.

     */
    private static int[] maximumSubArray2(int[] input, int window) {

        int[] result = new int[input.length - window + 1];

        Deque<Integer> deque = new LinkedList<>();
        int i;
        for(i = 0; i < window; i++) {
            while(!deque.isEmpty() && input[i] >= input[deque.peekLast()]) {
                deque.removeLast();
            }
            deque.addLast(i);
        }

        for(; i < input.length; i++) {
            result[i - window] = input[deque.peekFirst()];
            while(!deque.isEmpty() && (i - window) >= deque.peekFirst()){
                deque.removeFirst();
            }
            while(!deque.isEmpty() && input[i] >= input[deque.peekLast()]) {
                deque.removeLast();
            }
            deque.addLast(i);
        }
        result[i - window] = input[deque.peekFirst()];
        return result;
    }

    private static void evaluate(int[] input, int k, int[] expected) {
        System.out.println("Input : " + Arrays.toString(input));
        System.out.println("Size : " + k);
        System.out.println("Expected : " + Arrays.toString(expected));
        int[] result1 = maximumSubArray1(input, k);
        System.out.println("Approach1: " + Arrays.toString(result1));
        int[] result2 = maximumSubArray2(input, k);
        System.out.println("Approach2: " + Arrays.toString(result2));
        System.out.println("*******************************************");
    }
    public static void main(String[] args) {
        int[] input1 = {1, 2, 3, 1, 4, 5, 2, 3, 6};
        int[] expected1 = {3, 3, 4, 5, 5, 5, 6};
        int k1 = 3;
        evaluate(input1, k1, expected1);

        int[] input2 = {8, 5, 10, 7, 9, 4, 15, 12, 90, 13};
        int[] expected2 = {10, 10, 10, 15, 15, 90, 90};
        int k2 = 4;
        evaluate(input2, k2, expected2);

        int[] input3 = {10, 5, 2, 7, 8, 7};
        int[] expected3 = {10, 7, 8, 8};
        int k3 = 3;
        evaluate(input3, k3, expected3);

        int[] input4 = {34, -50, 42, 14, -5, 86};
        int[] expected4 = {42, 42, 42, 86};
        int k4 = 3;
        evaluate(input4, k4, expected4);
    }
}