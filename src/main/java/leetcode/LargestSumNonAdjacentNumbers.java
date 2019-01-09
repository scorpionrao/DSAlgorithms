package leetcode;

import java.util.Arrays;

public class LargestSumNonAdjacentNumbers {
    /*
        Time: O(N), Space: O(1)
     */
    private static int largestSumNonAdjacentNumbersApproach1(int[] input) {
        int iMax = 0;
        int eMax = 0;
        int iMaxNew = 0;
        for(int i = 0; i < input.length; i++) {
            iMaxNew = eMax + input[i];
            eMax = Math.max(iMax, eMax);
            iMax = iMaxNew;
            System.out.println(
                    String.format("I: %d, E: %d", iMax, eMax));
        }
        return Math.max(iMax, eMax);
    }

    private static void evaluate(int[] input) {
        System.out.println(Arrays.toString(input));
        System.out.println("Approach 1 = " +
                largestSumNonAdjacentNumbersApproach1(input));
    }

    public static void main(String[] args) {
        int[] input1 = {2, 4, 6, 2, 5};
        evaluate(input1);
        int[] input2 = {5, 1, 1, 5};
        evaluate(input2);
    }
}
