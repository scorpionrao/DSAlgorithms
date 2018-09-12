package algorithms.gayle;

import java.util.Arrays;
import java.util.Collections;

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

        /* O(N) */
        public int nonGreedyComputesOneValue(int[] array) {
            if(array == null) {
                throw new NullPointerException();
            }
            int current = 0;
            int max = 0;
            for(int i = 0; i < array.length; i++) {
                if(current < 0) {
                    current = 0; // Ignore -Ve values
                }
                current = current + array[i];
                if(current > max) {
                    max = current;
                }
            }
            return max;
        }

        /* O(N^2) */
        public int[] nonGreedyComputesLISAtEachIndex(int[] array) {

            int[] lis = new int[array.length];

            Arrays.fill(lis, 1);

            /* Bottom Up */
            for(int windowEnds = 1; windowEnds < array.length; windowEnds++) {
                for(int windowBegins = 0; windowBegins < windowEnds; windowBegins++) {
                    System.out.println(Arrays.toString(lis));
                    if(array[windowEnds] > array[windowBegins]
                            && lis[windowEnds] < lis[windowBegins] + 1) {
                        lis[windowEnds] = lis[windowBegins] + 1;
                    }
                }
            }
            return lis;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] array = {10, 22, 9, 33, 21, 50, 41, 60};
        System.out.println("Length of LIS : " +
                solution.greedyComputesOneValue(array));
        //System.out.println("Length of LIS : " +
                //solution.nonGreedyComputesOneValue(array));
        System.out.println("Length of LIS at each index : " +
                Arrays.toString(solution.nonGreedyComputesLISAtEachIndex(array)));
    }
}
