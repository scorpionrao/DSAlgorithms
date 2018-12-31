package bbb.Hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ZeroSum {

    private static int[] zeroSumArray(int[] inputArray) {
        Map<Integer, Integer> map = new HashMap<>();

        int sum = 0;
        map.put(0, -1);
        for(int i = 0; i < inputArray.length; i++) {
            sum += inputArray[i];
            if(map.containsKey(sum)) {
                int start = map.get(sum) + 1;
                int end = i;
                return Arrays.copyOfRange(inputArray, start, end + 1);
            } else {
                map.put(sum, i);
            }
        }
        return null;
    }

    private static int[] zeroSumArrayMaxLength(int[] inputArray) {
        Map<Integer, Integer> map = new HashMap<>();

        int maxLength = 0;
        int startMaxLength = 0;

        int sum = 0;
        map.put(0, -1);
        for(int i = 0; i < inputArray.length; i++) {
            sum += inputArray[i];
            if(map.containsKey(sum)) {
                int start = map.get(sum) + 1;
                int end = i;
                if(maxLength < end - start + 1) {
                    maxLength = end - start + 1;
                    startMaxLength = start;
                }
            } else {
                map.put(sum, i);
            }
        }
        return Arrays.copyOfRange(inputArray, startMaxLength, maxLength);
    }

    public static void main(String[] args) {
        int[] inputArray = {1, 2, -5, 1, 2, -1};
        int[] zeroSumArray = zeroSumArray(inputArray);
        System.out.println("Zero Sum Array : " + Arrays.toString(zeroSumArray));
        int[] zeroSumArrayMaxLength = zeroSumArrayMaxLength(inputArray);
        System.out.println("Longest Zero Sum Array : " + Arrays.toString(zeroSumArrayMaxLength));
    }
}
