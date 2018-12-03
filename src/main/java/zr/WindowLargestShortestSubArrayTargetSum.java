package zr;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WindowLargestShortestSubArrayTargetSum {

    public class Solution {

        /*
            PSUEDOCODE:
            - Invalid inputs - null, empty, single letter - exit
            - Initiate resultStartIndex, resultEndIndex, resultLength
            - for each startWindow ( 0 to end )
                - for each endWindow ( startWindow to end )
                    - Optimize : don't care if the window is not going to improve resultLength
                    - Get information from the window --> How many unique characters
                    - isCandidate(information)
                        - if NO - Go to next window
                        - if YES -
                            - Does is improve current best candidate ?
                                - Update best candidate with this window
                            - END_YEAR_TYPE does it improve
                        - END_YEAR_TYPE yes
                    - END_YEAR_TYPE isCandidate
                - END_YEAR_TYPE end window
            - END_YEAR_TYPE start window

            Time complexity - O(N^3)
         */
        public int largestArraySizeMinorOptimization(int[] inputArray, int target) {

            if(inputArray == null || inputArray.length == 0) {
                return 0;
            }

            /* windowSize address both longest window and return values. index is not required. */
            int maxLength = Integer.MIN_VALUE;
            for(int startWindow = 0; startWindow < inputArray.length; startWindow++) {
                for(int endWindow = startWindow; endWindow < inputArray.length; endWindow++) {
                    /* optimize */
                    if(maxLength >= (endWindow - startWindow + 1)) {
                        continue;
                    }
                    int sum = 0;
                    for(int i = startWindow; i <= endWindow; i++) {
                        sum = sum + inputArray[i];
                    }
                    /* isCandidate */
                    if(sum == target) {
                        /* improvement ? */
                        if(maxLength < (endWindow-startWindow+1)) {
                            maxLength = endWindow - startWindow + 1;
                        }
                    }
                }
            }
            return maxLength;
        }

        public int shortestArraySizeMinorOptimization(int[] inputArray, int target) {

            if(inputArray == null || inputArray.length == 0) {
                return 0;
            }

            /* windowSize address both longest window and return values. index is not required. */
            int minLength = Integer.MAX_VALUE;
            for(int startWindow = 0; startWindow < inputArray.length; startWindow++) {
                for(int endWindow = startWindow; endWindow < inputArray.length; endWindow++) {
                    /* optimize */
                    if(minLength < (endWindow - startWindow + 1)) {
                        continue;
                    }
                    int sum = 0;
                    for(int i = startWindow; i <= endWindow; i++) {
                        sum = sum + inputArray[i];
                    }
                    /* isCandidate */
                    if(sum == target) {
                        /* improvement ? */
                        if(minLength > (endWindow-startWindow+1)) {
                            minLength = endWindow - startWindow + 1;
                        }
                    }
                }
            }
            return minLength;
        }

        /*
            Time complexity = O(N)
            Space complexity = O(N)
        */
        public int largestArraySizeHighlyOptimized(int[] inputArray, int target) {

            if(inputArray == null || inputArray.length == 0) {
                return 0;
            }

            int maxLength = Integer.MIN_VALUE;
            int sum = 0;
            Map<Integer, Integer> sumIndexMap = new HashMap<>();
            sumIndexMap.put(0, -1);
            for(int end = 0; end < inputArray.length; end++) {
                sum = sum + inputArray[end];
                /* this means there is "target" sum from "after the value" to "current index" */
                int targetKey = Math.abs(target - sum);
                if(sumIndexMap.containsKey(targetKey)) {
                    int targetSumLength = end - sumIndexMap.get(targetKey);
                    if(maxLength < targetSumLength) {
                        maxLength = targetSumLength;
                    }
                } else {
                    // as left as possible for longest problems
                    sumIndexMap.put(sum, end);
                }
            }
            return maxLength;
        }

        /*
            Time complexity = O(N)
            Space complexity = O(N)
        */
        public int shortestArraySizeHighlyOptimized(int[] inputArray, int target) {

            if(inputArray == null || inputArray.length == 0) {
                return 0;
            }

            int minLength = Integer.MAX_VALUE;
            int sum = 0;
            Map<Integer, Integer> sumIndexMap = new HashMap<>();
            sumIndexMap.put(0, -1);
            for(int end = 0; end < inputArray.length; end++) {
                sum = sum + inputArray[end];
                /* this means there is "target" sum from "after the value" to "current index" */
                int targetKey = Math.abs(target - sum);
                if(sumIndexMap.containsKey(targetKey)) {
                    int targetSumLength = end - sumIndexMap.get(targetKey);
                    // additional check to ensure, ZERO does not change the sum but shrinking is wrong.
                    if(inputArray[end] != 0 && minLength > targetSumLength) {
                        minLength = targetSumLength;
                    }
                }

                // as right as possible so always update
                sumIndexMap.put(sum, end);
            }
            return minLength;
        }


        public void evaluate(int[] inputArray, int target) {
            System.out.println(Arrays.toString(inputArray));

            /* O(N^3) */
            int largestMinorOptimized = largestArraySizeMinorOptimization(inputArray, target);
            System.out.println("Largest Minor optimization: " + largestMinorOptimized);
            /* O(N) */
            int largestHighlyOptimized = largestArraySizeHighlyOptimized(inputArray, target);
            System.out.println("Largest Highly optimization: " + largestHighlyOptimized);


            /* O(N^3) */
            int shortestMinorOptimized = shortestArraySizeMinorOptimization(inputArray, target);
            System.out.println("Shortest Minor optimization: " + shortestMinorOptimized);
            /* O(N) */
            int shortestHighlyOptimized = shortestArraySizeHighlyOptimized(inputArray, target);
            System.out.println("Shortest Highly optimization: " + shortestHighlyOptimized);
        }
    }



    public static void main(String[] args) {

        Solution solution = new WindowLargestShortestSubArrayTargetSum().new Solution();
        int[] inputArray1 = {4, 3, 0, 6, 1, 2, 2};
        // zero sum problem
        solution.evaluate(inputArray1, 10); // 6

        int[] inputArray2 = {5, 0, 5};
        solution.evaluate(inputArray2, 10); // 3

        int[] inputArray3 = {5, 1, 6, 12, 0, 6};
        // target 6 problem
        solution.evaluate(inputArray3, 12); //6
    }
}
