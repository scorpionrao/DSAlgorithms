package gayle;

import java.util.Arrays;

public class LICS {

    /*
        Input - {1, 3, 5, 4, 7}
        LIS   - {1, 2, 1, 1, 1}

        max = 2
        i = 2
        maxVal = 0
        j = 1


     */
    public static int findLengthOfLCIS(int[] input) {
        int max = 0;
        int start = 0;
        for(int i = 0; i < input.length; i++) {
            if(i > 0 && input[i] <= input[i-1]) {
                start = i;
            }
            max = Math.max(max, i - start + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] input = {1, 3, 5, 4, 7};
        System.out.println("Max Consecutive Length = " + findLengthOfLCIS(input));
    }
}
