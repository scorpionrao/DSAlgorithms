package live;

import java.util.Arrays;

public class ArrayEquilibrium {

    /* Time: O(N), Space: O(1) */
    public int getEquilibrium(int[] nums) {

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
            if(i > 0) {
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
        ArrayEquilibrium ae = new ArrayEquilibrium();
        int[] nums1 = {2, 1, 4, 3};
        System.out.println(Arrays.toString(nums1) + " --> Equilibrium index: " + ae.getEquilibrium(nums1));
        int[] nums2 = {2};
        System.out.println(Arrays.toString(nums2) + " --> Equilibrium index: " + ae.getEquilibrium(nums2));
        /* All zeros - return 0 */
        int[] nums3 = {0, 0};
        System.out.println(Arrays.toString(nums3) + " --> Equilibrium index: " + ae.getEquilibrium(nums3));
        /* Non zeros + length 2 = -1 */
        int[] nums4 = {2, 2};
        System.out.println(Arrays.toString(nums4) + " --> Equilibrium index: " + ae.getEquilibrium(nums4));
        /* Numeric overflow */
        int[] nums5 = {Integer.MAX_VALUE, 1};
        System.out.println(Arrays.toString(nums5) + " --> Equilibrium index: " + ae.getEquilibrium(nums5));
        int[] nums6 = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
        System.out.println(Arrays.toString(nums6) + " --> Equilibrium index: " + ae.getEquilibrium(nums6));
    }
}
