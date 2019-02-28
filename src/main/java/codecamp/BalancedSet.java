package codecamp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BalancedSet {

    private static boolean isPivotExists(List<Integer> input) {

        int powerSetSize = (int) Math.pow(2, input.size());
        for(int i = 0; i < powerSetSize; i++) {
            int zeroIndexSum = 0;
            int oneIndexSum = 0;
            for(int j = 0; j < input.size(); j++) {
                /*
                    ((i & (1 << j)) > 0)
                    ((1 & (i >> j)) > 0)
                    ((1 & (i >> j)) == 0)
                    ((1 & (i >> j)) == 1)
                 */
                if((i & (1 << j)) > 0) {
                    oneIndexSum = oneIndexSum + input.get(j);
                } else {
                    zeroIndexSum = zeroIndexSum + input.get(j);

                }
            }
            if(zeroIndexSum == oneIndexSum) {
                System.out.println("Equal Sum : " + zeroIndexSum);
                return true;
            }
        }
        return false;
    }

    private static void evaluate(int[] input) {
        System.out.println("Input : " + Arrays.toString(input));
        List<Integer> inputList = new ArrayList<>();
        for(int num : input) {
            inputList.add(num);
        }
        boolean result = isPivotExists(inputList);
        System.out.println("Output : " + result);
    }

    public static void main(String[] args) {
        int[] input = {15, 5, 20, 10, 35, 15, 10};
        evaluate(input);
    }
}
