package gayle;

import java.util.Arrays;

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

        public int[] nonGreedyComputesLISAtEachIndex2(int[] input) {

            int[] lis = new int[input.length];
            // Every value is an increasing sequence of 1
            Arrays.fill(lis, 1);
            for(int i = 1; i < input.length; i++) {
                for(int j = 0; j < i; j++) {
                    if(input[i] > input[j]) {
                        lis[i] = Math.max(lis[i], lis[j] + 1);
                    }
                }
            }
            return lis;
        }

        public int longestAlternatingSubsequence(int[] input) {

            int[][] las = new int[input.length][2];
            /* Each element by itself is an increasing or decreasing by 1 */
            for(int[] row : las) {
                Arrays.fill(row, 1);
            }

            int result = 1;
            /* Bottom up Dynamic Programming */
            for (int i = 1; i < input.length; i++) {
                for (int j = 0; j < i; j++) {
                    if(input[i] > input[j] && las[i][0] < las[j][1] + 1) {
                        las[i][0] = las[j][1] + 1;
                    }
                    if(input[i] < input[j] && las[i][1] < las[j][0] + 1) {
                        las[i][1] = las[j][0] + 1;
                    }
                }
                if(result < Math.max(las[i][0], las[i][1])) {
                    result = Math.max(las[i][0], las[i][1]);
                }
            }

            return result;
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
        int[] array = {10, 22, 9, 33, 21, 50, 41, 60};
        System.out.println("Length of LIS : " + solution.greedyComputesOneValue(array));
        System.out.println("Length of LIS at each index DP : " +
                Arrays.toString(solution.nonGreedyComputesLISAtEachIndex2(array)));
        System.out.println("Length of LIS at each index Recursion : " +
                solution.nonGreedyComputesLISAtEachIndex3(array));
    }
}
