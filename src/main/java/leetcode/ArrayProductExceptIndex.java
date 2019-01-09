package leetcode;

import java.util.Arrays;

public class ArrayProductExceptIndex {

    /*
        Time : O(N^2)
        Space : O(1)
     */
    private static int[] productExceptIndexApproach1(int[] input) {

        int[] result = new int[input.length];
        Arrays.fill(result, 1);

        for(int i = 0; i < input.length; i++) {
            for(int j = 0; j < input.length; j++) {
                if(i != j) {
                    result[i] = result[i] * input[j];
                }
            }
        }
        return result;
    }

    /*
       Time : O(N)
       Space : O(N)
    */
    private static int[] productExceptIndexApproach2(int[] input) {

        int[] leftProduct = new int[input.length];
        Arrays.fill(leftProduct, 1);
        int[] result = new int[input.length];
        Arrays.fill(result, 1);

        for(int i = 1; i < input.length; i++) {
            leftProduct[i] = leftProduct[i-1] * input[i-1];
        }

        int rightSum = 1;
        for(int i = input.length - 1; i >= 0; i--) {
            result[i] = leftProduct[i] * rightSum;
            rightSum = rightSum * input[i];
        }
        return result;
    }

    /*
       Time : O(N)
       Space : O(N)
    */
    private static int[] productExceptIndexApproach3(int[] input) {

        int[] result = new int[input.length];
        Arrays.fill(result, 1);

        for(int i = 1; i < input.length; i++) {
            result[i] = result[i-1] * input[i-1];
        }

        int rightSum = 1;
        for(int i = input.length - 1; i >= 0; i--) {
            result[i] = result[i] * rightSum;
            rightSum = rightSum * input[i];
        }
        return result;
    }

    /*
       Time : O(N)
       Space : O(N)
    */
    private static int[] productExceptIndexApproach4(int[] input) {

        int[] result = new int[input.length];
        Arrays.fill(result, 1);

        int product = 1;
        for(int i = 0; i < input.length; i++) {
            product = product * input[i];
        }

        for(int i = 0; i < input.length; i++) {
            result[i] = product / input[i];
        }
        return result;
    }

    private static void evaluate(int[] input) {
        System.out.println("Input : " + Arrays.toString(input));
        int[] result1 = productExceptIndexApproach1(input);
        System.out.println("Approach 1 : " + Arrays.toString(result1));
        int[] result2 = productExceptIndexApproach2(input);
        System.out.println("Approach 2 : " + Arrays.toString(result2));
        int[] result3 = productExceptIndexApproach3(input);
        System.out.println("Approach 3 : " + Arrays.toString(result3));
        int[] result4 = productExceptIndexApproach4(input);
        System.out.println("Approach 4 : " + Arrays.toString(result4));
    }

    public static void main(String[] args) {
        int[] input = {1, 2, 3};
        evaluate(input);
    }
}
