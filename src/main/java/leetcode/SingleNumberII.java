package leetcode;

public class SingleNumberII {

    public static int singleNumberAmongThreeSets(int[] nums) {
        int ans = 0;
        for(int i = 0; i < 32; i++) {
            int sum = 0;
            for(int j = 0; j < nums.length; j++) {
                if(((nums[j] >> i) & 1) == 1) {
                    sum++;
                }
            }
            sum %= 3;
            if(sum != 0) { // can only be 0 or 1. Odd number appears exactly once.
                ans |= sum << i;
            }
        }
        return ans;
    }

    private static void evaluate(int[] nums) {
        int result = singleNumberAmongThreeSets(nums);
        System.out.println("Single Number among three sets : " + result);
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 2, 3, 2};
        evaluate(nums1);
        int[] nums2 = {0, 1, 0, 1, 0, 1, 99};
        evaluate(nums2);
    }


}
