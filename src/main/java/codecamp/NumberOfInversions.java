package codecamp;

import java.util.*;

public class NumberOfInversions {

    private static int numberOfInversions1(int[] input) {
        int result = 0;
        for(int i = 0; i < input.length; i++) {
            for(int j = i+1; j < input.length; j++) {
                if(input[i] > input[j]) {
                    result++;
                }
            }
        }
        return result;
    }

    private static int numberOfInversions2(int[] input) {
        Set<Integer> set = new HashSet<>();
        int result = 0;
        for(int i = 0; i < input.length; i++) {
            if(set.isEmpty()) {
                set.add(input[i]);
                continue;
            }
            set.add(input[i]);
            int candidate = input[i] + 1;
            while(candidate < 1000) {
                if(set.contains(candidate)) {
                    result++;
                }
                candidate++;
            }
        }
        return result;
    }

    private static void evaluate(int[] input, int expected) {
        System.out.println("Input : " + Arrays.toString(input));
        System.out.println("Expected : " + expected);
        int actual1 = numberOfInversions1(input);
        System.out.println("Approach 1 : " + actual1);
        int actual2 = numberOfInversions2(input);
        System.out.println("Approach 2 : " + actual2);
    }

    public static void main(String[] args) {
        evaluate(new int[]{2, 4, 1, 3, 5}, 3);
        evaluate(new int[]{1, 2, 3, 4, 5}, 0);
        evaluate(new int[]{5, 4, 3, 2, 1}, 10);
    }
}
