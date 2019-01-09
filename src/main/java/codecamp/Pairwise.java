package codecamp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Pairwise {

    /*  Time: O(N^2), Space: O(N)   */
    private static int pairwise(int[] input, int target) {

        Set<Integer> set = new HashSet<>();
        int indicesSum = 0;
        for(int i = 0; i < input.length; i++) {
            for(int j = i+1; j < input.length; j++) {
                if(isUseful(input, target, set, i, j)) {
                        indicesSum += i + j;
                        set.add(i);
                        set.add(j);
                }
            }
        }
        return indicesSum;
    }

    private static boolean isUseful(int[] input, int target, Set<Integer> set, int i, int j) {
        return !set.contains(i) && !set.contains(j) && input[i] + input[j] == target;
    }

    private static void evaluate(int[] input, int target, int expected) {
        System.out.println(Arrays.toString(input));
        System.out.println(String.format("E: %d, A: %d", expected, pairwise(input, target)));
    }

    public static void main(String[] args) {
        int[] input1 = {1, 1, 2};
        evaluate(input1, 3, 2);
        int[] input2 = {7, 9, 11, 13, 15};
        evaluate(input2, 20, 6);
    }
}
