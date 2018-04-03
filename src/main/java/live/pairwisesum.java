package live;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class pairwisesum {

    /*
        [1, 10, 5, 4, 8, 2, 3]
        6
        [1, 5]

        Time - O(N^2)
        Space - O(1)
     */
    public static int[] getFirstPair(int[] array, int target) {

        for(int i = 0; i < array.length; i++) {
            if(array[i] < target) {
                for(int j = i+1; j < array.length; j++) {
                    // search, can be replaced by hash table
                    if(array[i] + array[j] == target) {
                        return new int[]{array[i], array[j]};
                    }
                }
            }
        }
        return new int[2];
    }

    static Map<Integer, Integer> map;
    public static void createMap(int[] array) {
        map = new HashMap<>(array.length / 10);
        for(Integer key : array) {
            map.put(key, key);
        }
    }

    public static int[] getFirstPairFast(int[] array, int target) {
        for(int i = 0; i < array.length; i++) {
            if(array[i] < target) {
                Integer rem = map.get(target - array[i]);
                if (rem != null) {
                    return new int[]{array[i], target - array[i]};
                }
            }
        }
        return new int[2];
    }

    public static void main(String[] args) {
/*
        int[] array = new int[10000000];
        for(int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * (array.length / 2));
        }

        int target = (array.length / 2);
        long start = System.nanoTime();
        int[] firstPair = getFirstPair(array, target);
        long end = System.nanoTime();
        System.out.println("Naive: " + (end - start));

        createMap(array);
        start = System.nanoTime();
        int[] firstPairFast = getFirstPairFast(array, target);
        end = System.nanoTime();
        System.out.println("Fast: " + (end - start));
        if(firstPair[0] != firstPairFast[0] || firstPair[1] != firstPairFast[1]) {
            System.out.println("Mismatch");
        }
        */

        String str = "abcdefghijklmnopqrstuvwxyz";
        int i = 0;
        while(i<=str.length()-1) {
            System.out.println(str.charAt(i) + " " + (int)str.charAt(i));
            i++;
        }
    }

}
