package live;

import java.util.Arrays;

public class ArrayEquilibrium {

    /*
        Time: O(N)
        Space: O(1)

        1) Initialize leftsum  as 0
        2) Get the total sum of the array as sum
        3) Iterate through the array and for each index i, do following.
            a)  Update sum to get the right sum.
            b) If leftsum is equal to sum, then return current index.
            c) leftsum = leftsum + arr[i]
        4) return -1 // there is no equilibrium index
    */
    public int getEquilibrium(int[] input) {

        if(input == null || input.length == 0) {
            return -1;
        }

        long total = 0;
        for(int num : input) {
            total = total + num;
        }

        long leftSum = 0;
        for(int i = 0; i < input.length; i++) {
            if(leftSum == total - leftSum - input[i]) {
                return i;
            }
            leftSum = leftSum + input[i];
        }
        return -1;
    }

    public static void main(String[] args) {

        ArrayEquilibrium ae = new ArrayEquilibrium();
        int[] nums1 = {2, 1, 4, 3};
        System.out.println(Arrays.toString(nums1) + " --> Equilibrium index: " + ae.getEquilibrium(nums1));

        /*
        int[] nums2 = {2};
        System.out.println(Arrays.toString(nums2) + " --> Equilibrium index: " + ae.getEquilibrium(nums2));
        /* All zeros - return 0
        int[] nums3 = {0, 0};
        System.out.println(Arrays.toString(nums3) + " --> Equilibrium index: " + ae.getEquilibrium(nums3));
        /* Non zeros + length 2 = -1
        int[] nums4 = {2, 2};
        System.out.println(Arrays.toString(nums4) + " --> Equilibrium index: " + ae.getEquilibrium(nums4));
        /* Numeric overflow
        int[] nums5 = {Integer.MAX_VALUE, 1};
        System.out.println(Arrays.toString(nums5) + " --> Equilibrium index: " + ae.getEquilibrium(nums5));
        int[] nums6 = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
        System.out.println(Arrays.toString(nums6) + " --> Equilibrium index: " + ae.getEquilibrium(nums6));
        */
    }
}
