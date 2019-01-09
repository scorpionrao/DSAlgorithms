package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TwoSum {

    /*
        Time : O(N^2)
        Space : O(1)
     */
    private static boolean twoSumApproach1(int[] input, int target) {
        for(int i = 0; i < input.length; i++) {
            for(int j = i+1; j < input.length; j++) {
                if(input[i] + input[j] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
            Time : O(N log N)
            Space : O(N)
         */
    private static boolean twoSumApproach2(int[] input, int target) {
        Arrays.sort(input);
        int low = 0;
        int high = input.length - 1;
        while(low < high) {
            int sum = input[low] + input[high];
            if(sum == target) {
                return true;
            }
            if(sum > target) {
                high--;
            } else {
                low++;
            }
        }
        return false;
    }

    /*
        Time : O(N)
        Space : O(N)
     */
    private static boolean twoSumApproach3(int[] input, int target) {
        Set<Integer> set = new HashSet<>();
        for(int num : input) {
            if(set.contains(num)) {
                return true;
            } else {
                set.add(target - num);
            }
        }
        return false;
    }

    private static void evaluate(int[] input, int target) {
        System.out.println("Input : " + Arrays.toString(input));
        System.out.println("Target : " + target);
        boolean result1 = twoSumApproach1(input, target);
        System.out.println("Approach 1 : " + result1);
        boolean result2 = twoSumApproach2(input, target);
        System.out.println("Approach 2 : " + result2);
        boolean result3 = twoSumApproach3(input, target);
        System.out.println("Approach 3 : " + result3);
    }

    public static void main(String[] args) {
        int[] input = {10, 15, 3, 7};
        int target = 17;
        evaluate(input, target);
    }
}
