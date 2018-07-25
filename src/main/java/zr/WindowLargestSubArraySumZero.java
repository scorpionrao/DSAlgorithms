package zr;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WindowLargestSubArraySumZero {

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
                            - END does it improve
                        - END yes
                    - END isCandidate
                - END end window
            - END start window

            Time complexity - O(N^3)
         */
        public int largestArraySizeMinorOptimization(int[] inputArray) {

            if(inputArray == null || inputArray.length == 0) {
                return 0;
            }

            /* windowSize address both longest window and return values. index is not required. */
            int maxLength = 0;
            int counter = 0;
            for(int startWindow = 0; startWindow < inputArray.length; startWindow++) {
                for(int endWindow = startWindow; endWindow < inputArray.length; endWindow++) {
                    //System.out.println("Counter: " + ++counter + ", " + startWindow + " " + endWindow);
                    /* optimize */
                    if(maxLength >= (endWindow - startWindow + 1)) {
                        continue;
                    }
                    int sum = 0;
                    for(int i = startWindow; i <= endWindow; i++) {
                        sum = sum + inputArray[i];
                    }
                    /* isCandidate */
                    if(sum == 0) {
                        /* improvement ? */
                        if(maxLength < (endWindow-startWindow+1)) {
                            maxLength = endWindow - startWindow + 1;
                        }
                    }
                }
            }
            return maxLength;
        }

        /*
            Time complexity = O(N)
            Space complexity = O(N)
        */
        public int largestArraySizeHighlyOptimized(int[] inputArray) {

            if(inputArray == null || inputArray.length == 0) {
                return 0;
            }

            int maxLength = Integer.MIN_VALUE;
            int sumFromZero = 0;
            Map<Integer, Integer> sumIndexMap = new HashMap<>();
            sumIndexMap.put(0, -1);
            for(int end = 0; end < inputArray.length; end++) {
                sumFromZero = sumFromZero + inputArray[end];

                /* this means there is zero sum from "after the value" to "current index" */
                if(sumIndexMap.containsKey(sumFromZero)) {
                    int zeroSumLength = end - sumIndexMap.get(sumFromZero);
                    if(maxLength < zeroSumLength) {
                        maxLength = zeroSumLength;
                    }
                } else {
                    sumIndexMap.put(sumFromZero, end);
                }
            }
            return maxLength;
        }

        public void evaluate(int[] inputArray) {
            System.out.println(Arrays.toString(inputArray));
            /* O(N^3) */
            int minorOptimized = largestArraySizeMinorOptimization(inputArray);
            System.out.println("Minor optimization: " + minorOptimized);
            /* O(N) */
            int highlyOptimized = largestArraySizeHighlyOptimized(inputArray);
            System.out.println("Highly optimization: " + highlyOptimized);
        }
    }



    public static void main(String[] args) {

        Solution solution = new WindowLargestSubArraySumZero().new Solution();
        int[] inputArray1 = {4, -3, 0, 6, 1, -2, -2};
        solution.evaluate(inputArray1);

        int[] inputArray2 = {5, 0, -5};
        solution.evaluate(inputArray2);
    }
}
