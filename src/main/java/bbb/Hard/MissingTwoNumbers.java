package bbb.Hard;

import java.util.Arrays;

public class MissingTwoNumbers {

    private static int[] missionTwoNumbersApproach1(int[] input) {

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
                totalLeftXor ^= i;
            } else {
                totalRightXor ^= i;
            }
        }

        for(int num : input) {
            if(num <= pivot) {
                arrLeftXor ^= num;
            } else {
                arrRightXor ^= num;
            }
        }
        int missingLeft = totalLeftXor ^ arrLeftXor;
        int missingRight = totalRightXor ^ arrRightXor;
        return new int[]{missingLeft, missingRight};
    }

    private static void evaluate(int[] input) {
        int[] missingNumbers1 = missionTwoNumbersApproach1(input);
        System.out.println("Approach1 : " + Arrays.toString(missingNumbers1));
    }

    public static void main(String[] args) {
        int[] input = {5, 1, 7, 3, 2};
        evaluate(input);
    }
}
