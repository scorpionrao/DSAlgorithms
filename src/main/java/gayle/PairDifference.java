package gayle;

import java.util.*;

public class PairDifference {

    public static void getPairs(int[] input, int difference) {
        Set<Integer> set = new HashSet<>();
        for(int num : input) {
            set.add(num);
        }

        for(int i = 0; i < input.length; i++) {
            if(set.contains(input[i] + difference)) {
                System.out.println(input[i] + " " + (input[i] + difference));
            } else if(set.contains(input[i] - difference)) {
                System.out.println(input[i] + " " + (input[i] - difference));
            }
        }
    }

    public static void main(String[] args) {
        getPairs(new int[]{1, 7, 5, 9, 2, 12, 3}, 2);
    }
}