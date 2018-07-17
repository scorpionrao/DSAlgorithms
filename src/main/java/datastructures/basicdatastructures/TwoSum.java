package datastructures.basicdatastructures;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TwoSum {


    public static class Solution {
        /*
            Input: int[], target
            Output: int[]
            If value exists - send indexes
            If value does not exists - -1, -1

            Bruteforce:
                At each index: look for rest of the index and calculate the sum.
                If found, stop and return.
                If not found, return -1, -1.

                Run-time complexity: O(N^2)

            Psuedocode:
                traverse through the array
                at each index, calculate the (target - value).
                If the difference there, target is found. Get the index (value in Map) and current index.
                If the difference not there, store it in Map<difference, index>

                Run-time complexity: O(n)

         */
        public static int[] twoSumNaive(int[] input, int target) {
            for(int i = 0; i < input.length; i++) {
                for(int j = i+1; j < input.length; j++) {
                    if(target == input[i] + input[j]) {
                        return new int[] {i+1, j+1};
                    }
                }
            }
            return new int[] {-1, -1};
        }

        public static int[] twoSumOptimized(int[] input, int target) {
            Map<Integer, Integer> map = new HashMap<>();
            for(int i = 0; i < input.length; i++) {
                if(map.get(input[i]) != null) {
                    return new int[]{map.get(input[i]) + 1, i + 1};
                }
                map.put(target - input[i], i);
            }
            return new int[] {-1, -1};
        }

        public static int[] createArray(int length) {
            int[] input = new int[length];
            Random random = new Random();
            for(int i = 0; i < input.length; i++) {
                input[i] = random.nextInt(1000000);
            }
            return input;
        }
    }
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] input = solution.createArray(10000);
        int target = new Random().nextInt(5000);
        System.out.println("Array: " + Arrays.toString(input));
        System.out.println("Target: " + target);
        long start = System.nanoTime();
        int[] outputNaive = solution.twoSumNaive(input, target);
        long end = System.nanoTime();
        System.out.println(String.format("Naive Output: %d, %d. Time Taken: ", outputNaive[0], outputNaive[1]) + (end-start));

        start = System.nanoTime();
        int[] outputOptimized = solution.twoSumOptimized(input, target);
        end = System.nanoTime();
        System.out.println(String.format("Optimized Output: %d, %d. Time Taken: ", outputOptimized[0], outputOptimized[1]) + (end-start));
    }
}
