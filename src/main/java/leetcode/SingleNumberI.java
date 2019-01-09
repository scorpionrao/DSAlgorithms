package leetcode;

import java.util.Arrays;

public class SingleNumberI {

    public static int singleNumberAmongTwoSetsApproach2(int[] nums) {
        int ans = 0;
        for(int i = 0; i < 32; i++) {
            int sum = 0;
            for(int j = 0; j < nums.length; j++) {
                if(((nums[j] >> i) & 1) == 1) {
                    sum++;
                }
            }
            sum %= 2;
            if(sum != 0) { // can only be 0 or 1. Odd number appears exactly once.
                ans |= sum << i;
            }
        }
        return ans;
    }

    public static int singleNumberAmongTwoSetsApproach1(int[] nums) {
        int r = 0;
        for(int n : nums) {
            r ^= n;
        }
        return r;
    }

    private static void evaluate(int[] nums) {
        System.out.println(Arrays.toString(nums));
        int result1 = singleNumberAmongTwoSetsApproach1(nums);
        System.out.println("Approach 1 - Single Number among three sets : " + result1);
        int result2 = singleNumberAmongTwoSetsApproach2(nums);
        System.out.println("Approach 2 - Single Number among three sets : " + result2);
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 2, 3};
        evaluate(nums1);
        int[] nums2 = {0, 1, 0, 1, 99};
        evaluate(nums2);
    }


}
