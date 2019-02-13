package codecamp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubSetTargetSum {

    /*
        Generate all sub sets.
        Calculate sum of each sub set
        If sum equals target, return subset
     */
    private static List<Integer> subsetTargetSum(int[] input, int targetSum) {
        Arrays.sort(input);

        int endIndex = input.length - 1;
        while(input[endIndex] > targetSum) {
            endIndex--;
        }

        List<List<Integer>> subSets = generateSubSets(input, 0, endIndex);
        for(List<Integer> subSet : subSets) {
            int sum = subSet.stream().mapToInt(i -> i.intValue()).sum();
            if(sum == targetSum) {
                return subSet;
            }
        }
        return null;
    }

    private static List<List<Integer>> generateSubSets(int[] input, int startIndex, int endIndex) {
        List<List<Integer>> result = new ArrayList<>();
        int totalCount = 1 << (endIndex - startIndex + 1);
        for(int count = 0; count < totalCount; count++) {
            List<Integer> subSet = new ArrayList<>();
            for(int j = endIndex; j >= startIndex; j--) {
                if((count & (1 << j)) > 0) {
                    subSet.add(input[j]);
                }
            }
            result.add(subSet);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] input = {12, 1, 61, 5, 9, 2};
        int targetSum = 24;
        List<Integer> result = subsetTargetSum(input, targetSum);
        System.out.println("Input : " + Arrays.toString(input));
        System.out.println("Expected : [12, 9, 2, 1]");
        System.out.println("Actual : " + result.toString());
    }
}
