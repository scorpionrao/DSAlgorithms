package gayle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LongestIncreasingSubsequence {

    public static class Solution {
        /* O(N) */
        public int greedyComputesOneValue(int[] array) {

            int max = Integer.MIN_VALUE;
            int length = 0;

            /* Bottom Up */
            for(int i = 0; i < array.length; i++) {
                if(array[i] > max) {
                    max = array[i];
                    length++;
                }
            }
            return length;
        }

        /* O(N^2) */
        public int[] nonGreedyComputesLISAtEachIndex1(int[] array) {

            int[] lis = new int[array.length];
            // Every value is an increasing sequence of 1
            Arrays.fill(lis, 1);

            /* Bottom Up */
            for(int j = 0; j < array.length; j++) {
                for(int i = j + 1; i < array.length; i++) {
                    System.out.println(Arrays.toString(lis));
                    if(array[i] > array[j]
                            && lis[i] < lis[j] + 1) {
                        lis[i] = lis[j] + 1;
                    }
                }
            }
            return lis;
        }

        /*
            L[0] = {arr[O]}
            L[i] = {Max(L[j])} + arr[i]
            where j < i and arr[j] < arr[i] and if there is no such j then L[i] = arr[i]

            [3, 2, 6, 4, 5, 1]

            {3, }
        */
        public int[] nonGreedyComputesLISAtEachIndex2(int[] input) {

            List<Integer> sequence = new ArrayList<>();
            sequence.add(input[0]);

            int[] lis = new int[input.length];
            // Every value is an increasing sequence of 1
            Arrays.fill(lis, 1);
            for(int i = 1; i < input.length; i++) {
                int maxVal = 0;
                for(int j = 0; j < i; j++) {
                    if(input[i] > input[j]) {
                        maxVal = Math.max(maxVal, lis[j]);
                    }
                }
                lis[i] = 1 + maxVal;
            }


            return lis;
        }

        /*
            Then, L(i) can be recursively written as:
            L(i) = 1 + max( L(j) ) where 0 < j < i and arr[j] < arr[i]; or
            L(i) = 1, if no such j exists.
            To find the LIS for a given array, we need to return max(L(i)) where 0 < i < n.
         */
        public int nonGreedyComputesLISAtEachIndex3(int[] input) {

            int[] max = new int[1];
            nonGreedyComputesLISAtEachIndex3(input, input.length, max);
            return max[0];
        }

        private static int nonGreedyComputesLISAtEachIndex3(int[] input, int indexPlusOne, int[] max) {
            if (indexPlusOne == 1) {
                return 1;
            }
            int res, max_ending_here = 1;
            for (int i = 1; i < indexPlusOne; i++) {
                res = nonGreedyComputesLISAtEachIndex3(input, i, max);
                if (input[i-1] < input[indexPlusOne-1] && res + 1 > max_ending_here)
                    max_ending_here = res + 1;
            }
            if (max[0] < max_ending_here) {
                max[0] = max_ending_here;
            }
            return max_ending_here;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        //int[] array = {10, 22, 9, 33, 21, 50, 41, 60};
        int[] array = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("Length of LIS : " + solution.greedyComputesOneValue(array));
        System.out.println("Length of LIS at each index DP : " +
                Arrays.toString(solution.nonGreedyComputesLISAtEachIndex2(array)));
        System.out.println("Length of LIS at each index Recursion : " +
                solution.nonGreedyComputesLISAtEachIndex3(array));
    }
}
