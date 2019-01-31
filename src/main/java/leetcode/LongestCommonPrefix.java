package leetcode;

import java.util.Arrays;

public class LongestCommonPrefix {

    private static String horizontalScanning(String[] input) {

        String prefix = input[0];
        for(int i = 1; i < input.length; i++) {
            while(!input[i].startsWith(prefix)) {
                prefix = prefix.substring(0, prefix.length()-1);
                if(prefix.isEmpty()) {
                    return prefix;
                }
            }
        }
        return prefix;
    }

    private static String verticalScanning(String[] input) {

        int minLength = Integer.MAX_VALUE;

        for(String str : input) {
            minLength = Math.min(minLength, str.length());
        }

        for(int i = 0; i < minLength; i++) {
            char ch = input[0].charAt(i);
            for(int j = 1; j < input.length; j++) {
                if(ch != input[j].charAt(i)) {
                    return input[j].substring(0, i);
                }
            }
        }
        return input[0].substring(0, minLength);
    }

    private static String divideAndConquer(String[] input) {
        return divideAndConquer(input, 0, input.length - 1);
    }

    private static String divideAndConquer(String[] input, int low, int high) {
        if(low == high) {
            return input[low];
        }
        int mid = (low + high) / 2;
        String left = divideAndConquer(input, low, mid);
        String right = divideAndConquer(input, mid + 1, high);

        int minLength = Math.min(left.length(), right.length());
        for(int i = 0; i < minLength; i++) {
            if(left.charAt(i) != right.charAt(i)) {
                return left.substring(0, i);
            }
        }
        return left.substring(0, minLength);
    }

    private static void evaluate(String[] input, String expected) {
        System.out.println(Arrays.toString(input));
        System.out.println("Expected : " + expected);
        String result1 = horizontalScanning(input);
        System.out.println("Horizontal Scanning : " + result1);
        String result2 = verticalScanning(input);
        System.out.println("Vertical Scanning : " + result2);
        String result3 = divideAndConquer(input);
        System.out.println("Divide and Conquer : " + result3);
    }

    public static void main(String[] args) {
        String[] input1 = {"flower", "flow", "flight"};
        evaluate(input1, "fl");
        String[] input2 = {"dog", "racecar", "car"};
        evaluate(input2, "");

        Arrays.binarySearch(input1, 1);
    }
}
