package live;

import java.util.Arrays;

public class ProductSkipIndex {

    private static int[] solve(int[] inputArray) {
        if(inputArray == null) {
            throw new NullPointerException();
        }

        if(inputArray.length <= 1) {
            return inputArray;
        }

        int product = 1;
        for(int i = 0; i < inputArray.length; i++) {
            if(inputArray[i] > 0) {
                product = product * inputArray[i];
            }
        }

        // Space Optimized to O(1)
        int[] resultArray = new int[inputArray.length];
        for(int i = 0; i < inputArray.length; i++) {
            if(inputArray[i] > 0) {
                resultArray[i] = product / inputArray[i];
            }
        }
        return resultArray;
    }

    public static int[] productExceptSelf(int[] nums) {
        System.out.println("Input: \t\t" + Arrays.toString(nums));
        int n = nums.length;
        int[] res = new int[n];
        res[0] = 1;
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        System.out.println("First scan: " + Arrays.toString(res));
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            res[i] = res[i] * right;
            System.out.println("Intermediate: " + Arrays.toString(res));
            right = right * nums[i];
            System.out.println("Right: " + right);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] inputArray = {9, 3, 5};
        /*
        System.out.println("Input Array: " + Arrays.toString(inputArray));
        System.out.println("Output Array: " + Arrays.toString(solve(inputArray)));
        */

        int[] result = productExceptSelf(inputArray);
        System.out.println("Answer: \t" + Arrays.toString(result));


    }
}
