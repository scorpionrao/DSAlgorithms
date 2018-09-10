package live;

import java.util.*;

public class PairWiseSum {

    /*
        [1, 10, 5, 4, 8, 2, 3]
        6
        [1, 5]

        Time - O(N)

     */

    public int[] getFirstPair(int[] array, int target) {
        if(array == null || array.length < 2) {
            return new int[] {-1, -1};
        }

        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < array.length; i++) {
            if(array[i] < target) {
                if(map.get(array[i]) != null) {
                    return new int[]{map.get(array[i]), i};
                } else {
                    map.put(target - array[i], i);
                }
            }
        }
        return new int[]{-1, -1};
    }



    public static void main(String[] args) {
        int[] array = {1, 10, 5, 4, 8, 2, 3};
        int target = 6;
        int[] firstPairIndexes = new PairWiseSum().getFirstPair(array, target);
        System.out.println(Arrays.toString(firstPairIndexes));
    }

}
