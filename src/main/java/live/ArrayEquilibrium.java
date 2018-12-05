package live;

import java.util.Arrays;

public class ArrayEquilibrium {

    /* Time: O(N), Space: O(1) */
    public static int getEquilibrium(int[] nums) {

        if(nums == null || nums.length == 0) {
            return -1;
        }
        if(nums.length == 1) {
            return 0;
        }

        long total = 0;
        for(int num : nums) {
            total = total + num;
        }

        long preTotal = 0;
        long postTotal = total;
        for(int i = 0; i < nums.length; i++) {
            /* First index - preTotal is unchanged */
            if(i != 0) {
                preTotal = preTotal + nums[i-1];
            }
            postTotal = postTotal - nums[i];
            if(preTotal == postTotal) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        int[] nums1 = {2, 1, 4, 3};
        System.out.println(Arrays.toString(nums1) + " --> Equilibrium index: " + getEquilibrium(nums1));
        int[] nums2 = {2};
        System.out.println(Arrays.toString(nums2) + " --> Equilibrium index: " + getEquilibrium(nums2));
        int[] nums3 = {0, 0};
        System.out.println(Arrays.toString(nums3) + " --> Equilibrium index: " + getEquilibrium(nums3));
        int[] nums4 = {2, 2};
        System.out.println(Arrays.toString(nums4) + " --> Equilibrium index: " + getEquilibrium(nums4));
        int[] nums5 = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
        System.out.println(Arrays.toString(nums5) + " --> Equilibrium index: " + getEquilibrium(nums5));
    }
}
