package live;

import java.util.Arrays;

public class ArrayEquilibrium {

    /* Time : O(N^2), Space : O(1) */
    public int getEquilibrium1(int[] input) {
        for(int i = 0; i < input.length; i++) {
            long leftSum = 0;
            long rightSum = 0;
            for(int j = 0; j < input.length; j++) {
                if(i == j) {
                    continue;
                } else if(i < j) {
                    leftSum += input[j];
                } else {
                    rightSum += input[j];
                }
            }
            if(leftSum == rightSum) {
                return i;
            }
        }
        return -1;
    }

    /* Time : O(N), Space : O(2*N) */
    public int getEquilibrium2(int[] input) {

        long[] leftSum = new long[input.length];
        for(int i = 0; i < input.length; i++) {
            if(i > 0) {
                leftSum[i] = leftSum[i-1] + input[i-1];
            }
        }

        long[] rightSum = new long[input.length];
        for(int i = input.length - 1; i >= 0; i--) {
            if(i < input.length - 1) {
                rightSum[i] = rightSum[i+1] + input[i+1];
            }
        }

        for(int i = 0; i < input.length; i++) {
            if(leftSum[i] == rightSum[i]) {
                return i;
            }
        }
        return -1;
    }

    /* Time : O(N), Space : O(N) */
    public int getEquilibrium3(int[] input) {

        long[] leftSum = new long[input.length];
        for(int i = 0; i < input.length; i++) {
            if(i > 0) {
                leftSum[i] = leftSum[i-1] + input[i-1];
            }
        }

        long rightSum = 0;
        for(int i = input.length - 1; i >= 0; i--) {
            if(i < input.length - 1) {
                rightSum = rightSum + input[i+1];
            }
            if(leftSum[i] == rightSum) {
                return i;
            }
        }

        return -1;
    }


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
    public int getEquilibrium4(int[] input) {

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

    private void evaluate(int[] nums) {
        System.out.println(Arrays.toString(nums));
        System.out.println("Equilibrium1 index: " + getEquilibrium1(nums));
        System.out.println("Equilibrium2 index: " + getEquilibrium2(nums));
        System.out.println("Equilibrium3 index: " + getEquilibrium3(nums));
        System.out.println("Equilibrium4 index: " + getEquilibrium4(nums));
    }

    public static void main(String[] args) {

        ArrayEquilibrium ae = new ArrayEquilibrium();
        int[] nums = {2, 1, 4, 3};
        ae.evaluate(nums);
    }


}
