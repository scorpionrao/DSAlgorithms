package codecamp;

import java.util.Arrays;

public class RGB {
    /*
        Explore until you reach 'G' insert index -> O(N)
            Continuously swap if exploring index is 'G' AND that index is left of nextGInsert
            Continuously swap if exploring index is 'R' AND that index is right of nextRInsert
     */
    private static void aggregate(char[] input) {
        int nextRInsert = 0;
        int nextGInsert = input.length - 1;
        for (int i = 0; i <= nextGInsert; i++) {

            while (input[i] == 'G' && i < nextGInsert) {
                swap(input, i, nextGInsert);
                nextGInsert--;
            }

            while (input[i] == 'R' && i > nextRInsert) {
                swap(input, i, nextRInsert);
                nextRInsert++;
            }
        }
    }

    private static void swap(char[] input, int a, int b) {
        char temp = input[a];
        input[a] = input[b];
        input[b] = temp;
    }

    public static void main(String[] args) {
        char[] input = {'G', 'B', 'R', 'R', 'B', 'B', 'G'};
        System.out.println(Arrays.toString(input));
        aggregate(input);
        System.out.println(Arrays.toString(input));
    }
}
