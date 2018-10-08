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
            if(inputArray[i] != 0) {
                product = product * inputArray[i];
            }
        }

        // Space Optimized to O(N)
        int[] resultArray = new int[inputArray.length];
        for(int i = 0; i < inputArray.length; i++) {
            if(inputArray[i] != 0) {
                resultArray[i] = product / inputArray[i];
            }
        }
        return resultArray;
    }

    public static void main(String[] args) {
        int[] inputArray = {1, 2, 3};
        System.out.println("Input Array: " + Arrays.toString(inputArray));
        System.out.println("Output Array: " + Arrays.toString(solve(inputArray)));

    }
}
