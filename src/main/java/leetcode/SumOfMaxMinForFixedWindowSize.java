package leetcode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class SumOfMaxMinForFixedWindowSize {

    /* Time : O(N * K), Space : O(1) */
    private static int maximumMinimumSubArray1(int[] input, int k) {
        int sum = 0;
        for(int i = 0; i < input.length - k + 1; i++) {
            int max = input[i+0];
            int min = input[i+0];
            for(int j = 1; j < k; j++) {
                max = Math.max(max, input[i+j]);
                min = Math.min(min, input[i+j]);
            }
            System.out.println(max + " " + min);
            sum = sum + max + min;
        }
        return sum;
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
    private static int maximumMinimumSubArray2(int[] input, int k) {

        int sum = 0;

        Deque<Integer> maxDeque = new LinkedList<>();
        Deque<Integer> minDeque = new LinkedList<>();

        int i;
        for(i = 0; i < k; i++) {
            while(!maxDeque.isEmpty() && input[i] >= input[maxDeque.peekLast()]) {
                maxDeque.removeLast();
            }
            maxDeque.addLast(i);

            while(!minDeque.isEmpty() && input[i] <= input[minDeque.peekLast()]) {
                minDeque.removeLast();
            }
            minDeque.addLast(i);
        }

        for(; i < input.length; i++) {
            System.out.println(maxDeque.peekFirst() + " " + minDeque.peekFirst());
            sum = sum + input[maxDeque.peekFirst()];
            sum = sum + input[minDeque.peekFirst()];
            // clean up indexes
            while(!maxDeque.isEmpty() && (i - k) >= maxDeque.peekFirst()){
                maxDeque.removeFirst();
            }
            while(!minDeque.isEmpty() && (i - k) >= minDeque.peekFirst()){
                minDeque.removeFirst();
            }
            // Remove useless indexes - smaller values on the left (maxDeque), greater values on the right (minDeque)
            while(!maxDeque.isEmpty() && input[i] >= input[maxDeque.peekLast()]) {
                maxDeque.removeLast();
            }
            maxDeque.addLast(i);
            while(!minDeque.isEmpty() && input[i] <= input[minDeque.peekLast()]) {
                minDeque.removeLast();
            }
            minDeque.addLast(i);
        }
        sum = sum + input[maxDeque.peekFirst()];
        sum = sum + input[minDeque.peekFirst()];
        return sum;
    }

    private static void evaluate(int[] input, int k, int expected) {
        System.out.println("Input : " + Arrays.toString(input));
        System.out.println("Size : " + k);
        System.out.println("Expected : " + expected);
        int result1 = maximumMinimumSubArray1(input, k);
        System.out.println("Approach1: " + result1);
        int result2 = maximumMinimumSubArray2(input, k);
        System.out.println("Approach2: " + result2);
        System.out.println("*******************************************");
    }
    public static void main(String[] args) {
        int[] input1 = {2, 5, -1, 7, -3, -1, -2};
        int expected1 = 18;
        int k1 = 4;
        evaluate(input1, k1, expected1);
    }
}

