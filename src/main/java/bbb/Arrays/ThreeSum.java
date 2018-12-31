package bbb.Arrays;

import java.util.*;

public class ThreeSum {

    /* Time : O(N^2), Space : O(1) */
    private static List<int[]> threeSumApproach3(int[] input) {

        Arrays.sort(input);
        List<int[]> result = new ArrayList<>();
        for(int i = 0; i < input.length - 2; i++) {
            int start = i + 1;
            int end = input.length - 1;

            while(start < end) {
                if(input[i] + input[start] + input[end] == 0) {
                    result.add(new int[]{input[i], input[start], input[end]});
                }
                if(input[i] + input[start] + input[end] > 0) {
                    end--;
                    while(start < end && input[end-1] == input[end]) {
                        end--;
                    }
                } else {
                    start++;
                    while(start < end && input[start] == input[start+1]) {
                        start++;
                    }
                }
            }
        }
        return result;
    }

    /* Time : O(N^2), Space : O(N) */
    private static List<int[]> threeSumApproach2(int[] input) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < input.length; i++) {
            if(!map.containsKey(input[i])) {
                map.put(input[i], new ArrayList<>());
            }
            map.get(input[i]).add(i);
        }

        List<int[]> result = new ArrayList<>();
        for(int i = 0; i < input.length - 2; i++) {
            for(int j = i + 1; j < input.length - 1; j++) {
                int twoSum = (input[i] + input[j]) * -1;
                if(map.containsKey(twoSum)) {
                    for(int index : map.get(twoSum)) {
                        result.add(new int[]{input[i], input[j], input[index]});
                    }
                }
            }
        }
        return result;
    }

    /* Time : O(N^3), Space : O(1) */
    private static List<int[]> threeSumApproach1(int[] input) {
        List<int[]> result = new ArrayList<>();
        for(int i = 0; i < input.length - 2; i++) {
            for(int j = i + 1; j < input.length - 1; j++) {
                for(int k = j + 1; k < input.length; k++) {
                    int sum = input[i] + input[j] + input[k];
                    if(sum == 0) {
                        result.add(new int[]{input[i], input[j], input[k]});
                    }
                }
            }
        }
        return result;
    }

    private static void print(String approach, List<int[]> result) {
        System.out.println(approach + " : ");
        for(int[] candidate : result) {
            System.out.println(Arrays.toString(candidate));
        }
    }

    private static void evaluate(int[] input) {

        System.out.println("Input : " + Arrays.toString(input));
        print("Approach1", threeSumApproach1(input));
        print("Approach2", threeSumApproach2(input));
        print("Approach3", threeSumApproach3(input));
    }

    public static void main(String[] args) {

        int[] input = {-1, 0, 1, 2, -1, -4};
        evaluate(input);
    }
}
