package datastructures.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

    /*
        Time Complexity - O(N^3)

        Problems: Allows duplicate
        Problems: Time complexity is high
    */
    private static void threeSumNaiveHasDuplicates(int[] nums) {

        List<List<Integer>> output = new ArrayList<>();
        for(int i = 0; i < nums.length; i++) {
            for(int j = i + 1; j < nums.length; j++) {
                for(int k = j + 1; k < nums.length; k++) {
                    if(nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> outputCombination = new ArrayList<>();
                        outputCombination.add(nums[i]);
                        outputCombination.add(nums[j]);
                        outputCombination.add(nums[k]);
                        output.add(outputCombination);
                    }
                }
            }
        }
        System.out.println("Naive + Has Duplicates\t: " + output.toString());
    }

    /*
        Time Complexity - O(N^2)

        Pros: No duplicate, Optimized
    */
    private static void threeSumOptimizedAndNoDuplicates(int[] nums) {

        List<List<Integer>> output = new ArrayList<>();
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 2; i++) {

            if(i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            int leftPointer = i + 1;
            int highPointer = nums.length - 1;
            while(leftPointer < highPointer) {
                if(nums[i] + nums[leftPointer] + nums[highPointer] == 0) {
                    output.add(Arrays.asList(nums[i], nums[leftPointer], nums[highPointer]));

                    while(leftPointer < highPointer && nums[leftPointer] == nums[leftPointer+1]) {
                        leftPointer++;
                    }
                    leftPointer++;

                    while(leftPointer < highPointer && nums[highPointer] == nums[highPointer-1]) {
                        highPointer--;
                    }
                    highPointer--;
                } else if(nums[i] + nums[leftPointer] + nums[highPointer] > 0) {
                    highPointer--;
                } else {
                    leftPointer++;
                }
            }
        }
        System.out.println("Optimum + No Duplicates\t: " + output.toString());
    }

    public static void main(String[] args) {
        //int[] nums = {-1, 0, 1, 2, -1, -4};
        int[] nums = {3, 0, -2, -1, 1, 2};
        threeSumNaiveHasDuplicates(nums);
        threeSumOptimizedAndNoDuplicates(nums);
    }
}
