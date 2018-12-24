package bbb.Easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MissingNumber {

    /* Time: O(N log N), Space: O(1) */
    private static int missionNumberApproach1(int[] input) {
        Arrays.sort(input);
        for(int i = 0; i < input.length; i++) {
            if(i != input[i]) {
                return i;
            }
        }
        return input[input.length-1];
    }

    /* Time: O(N), Space: O(N) */
    private static int missionNumberApproach2(int[] input) {
        Set<Integer> set = new HashSet<>();
        for(int num : input) {
            set.add(num);
        }
        for(int i = 0; i < set.size(); i++) {
            if(!set.contains(i)) {
                return i;
            }
        }
        return set.size();
    }

    /* Time: O(N), Space: O(1) */
    private static int missionNumberApproach3(int[] input) {
        int totalSum = input.length * (input.length+1) / 2;
        for(int num : input) {
            totalSum -= num;
        }
        return totalSum;
    }

    /* Time: O(N), Space: O(1) */
    private static int missionNumberApproach4(int[] input) {
        int missingNumber = input.length;
        for(int i = 0; i < input.length; i++) {
            missingNumber = missingNumber ^ i ^ input[i];
        }
        return missingNumber;
    }

    private static void evaluate(int[] input) {
        int missingNumber1 = missionNumberApproach1(input);
        System.out.println("Approach1 : " + missingNumber1);
        int missingNumber2 = missionNumberApproach2(input);
        System.out.println("Approach2 : " + missingNumber2);
        int missingNumber3 = missionNumberApproach3(input);
        System.out.println("Approach3 : " + missingNumber3);
        int missingNumber4 = missionNumberApproach4(input);
        System.out.println("Approach4 : " + missingNumber4);
    }

    public static void main(String[] args) {
        int[] input = {5, 1, 7, 3, 0, 6, 2};
        evaluate(input);
    }
}
