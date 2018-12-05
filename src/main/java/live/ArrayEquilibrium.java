package live;

import java.util.Arrays;

public class ArrayEquilibrium {

    /* Time: O(N), Space: O(1) */
    public int getEquilibrium(int[] input) {

        if(input == null || input.length == 0) {
            return -1;
        }
        if(input.length == 1) {
            return 0;
        }

        long total = 0;
        for(int num : input) {
            total = total + num;
        }

        /*
                Input Array
                     \
                      \
            Var -------\
         */
        long leftVarRunningValue = 0;
         /*
                    Input Array
                        |
                        |
                        |------ Var
         */
        long rightVarRunningValue = total;
        for(int i = 0; i < input.length; i++) {
            if(i > 0) {
                leftVarRunningValue = leftVarRunningValue + input[i-1];
            }
            rightVarRunningValue = rightVarRunningValue - input[i];
            System.out.println(i + " --> " + leftVarRunningValue + " " + rightVarRunningValue);
            if(leftVarRunningValue == rightVarRunningValue) {
                return i;
            }
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
