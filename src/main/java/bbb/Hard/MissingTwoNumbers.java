package bbb.Hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MissingTwoNumbers {

    /* Time : O(N), Soace : O(N) */
    private static int[] missionTwoNumbersApproach1(int[] input) {

        boolean[] isPresent = new boolean[input.length + 2];
        for(int num : input) {
            isPresent[num-1] = true;
        }

        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < isPresent.length; i++) {
            if(!isPresent[i]) {
                result.add(i+1);
            }
        }
        return new int[]{result.get(0), result.get(1)};
    }

    /* Time : O(N * Log N), Soace : O(1) */
    private static int[] missionTwoNumbersApproach2(int[] input) {

        Arrays.sort(input);

        List<Integer> result = new ArrayList<>();
        int index = 0;
        for(int i = 0; i < input.length + 2; i++) {
            if((i+1) == input[index]) {
                index++;
            } else {
                result.add(i+1);
            }
        }
        return new int[]{result.get(0), result.get(1)};
    }


    /* Time : O(N), Soace : O(1) */
    private static int[] missionTwoNumbersApproach3(int[] input) {

        int totalSize = input.length + 2;
        long total = totalSize * (totalSize + 1) / 2;

        long inputTotal = 0;
        for(int num : input) {
            inputTotal += num;
        }

        int pivot = (int) (total - inputTotal) / 2;

        int totalLeftXor = 0;
        int totalRightXor = 0;
        int arrLeftXor = 0;
        int arrRightXor = 0;

        for(int i = 1; i <= totalSize; i++) {
            if(i <= pivot) {
                totalLeftXor = totalLeftXor ^ i;
            } else {
                totalRightXor = totalRightXor ^ i;
            }
        }

        for(int num : input) {
            if(num <= pivot) {
                arrLeftXor = arrLeftXor ^ num;
            } else {
                arrRightXor = arrRightXor ^ num;
            }
        }

        int missingLeft = totalLeftXor ^ arrLeftXor;
        int missingRight = totalRightXor ^ arrRightXor;
        return new int[]{missingLeft, missingRight};
    }

    private static void evaluate(int[] input) {
        int[] missingNumbers1 = missionTwoNumbersApproach1(input);
        System.out.println("Approach1 : " + Arrays.toString(missingNumbers1));
        int[] missingNumbers2 = missionTwoNumbersApproach2(input);
        System.out.println("Approach2 : " + Arrays.toString(missingNumbers2));
        int[] missingNumbers3 = missionTwoNumbersApproach3(input);
        System.out.println("Approach3 : " + Arrays.toString(missingNumbers3));
    }

    public static void main(String[] args) {
        int[] input = {5, 1, 7, 3, 2};
        evaluate(input);
    }
}
