package live;

import java.util.*;

public class PairWiseSum {

    /*
        [1, 10, 5, 4, 8, 2, 3]
        6
        [1, 5]

        Time - O(N)
        Space - O(N)
     */

    public int[] getFirstPair(int[] unsortedArray, int target) {
        if(unsortedArray == null || unsortedArray.length < 2) {
            return new int[] {-1, -1};
        }

        Map<Integer, Integer> reminderIndexMap = new HashMap<>();
        for(int i = 0; i < unsortedArray.length; i++) {
            if(unsortedArray[i] < target) {
                if(reminderIndexMap.get(unsortedArray[i]) != null) {
                    return new int[]{reminderIndexMap.get(unsortedArray[i]), i};
                } else {
                    reminderIndexMap.put(target - unsortedArray[i], i);
                }
            }
        }
        return new int[]{-1, -1};
    }

    /*
        Time - O(N), Space - O(1)
     */
    public boolean doesPairExistsSortedArray(int[] sortedArray, int target) {
        if(sortedArray == null) {
            throw new NullPointerException();
        }

        int leftPointer = 0;
        int rightPointer = sortedArray.length - 1;

        while(leftPointer < rightPointer) {
            int temp = sortedArray[leftPointer] + sortedArray[rightPointer];
            if(temp < target) {
                leftPointer++;
            } else if (temp > target) {
                rightPointer--;
            } else {
                return true;
            }
        }
        return false;
    }

    /*
        Time - O(N), Space - O(N)
     */
    public boolean doesPairExistsUnsortedArray(int[] unsortedArray, int target) {

        if(unsortedArray == null) {
            throw new NullPointerException();
        }

        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < unsortedArray.length; i++) {
            if(set.contains(unsortedArray[i])) {
                return true;
            } else {
                set.add(target - unsortedArray[i]);
            }
        }
        return false;
    }



    public static void main(String[] args) {
        int[] array = {1, 10, 5, 4, 8, 2, 3};
        int target = 6;
        int[] firstPairIndexes = new PairWiseSum().getFirstPair(array, target);
        System.out.println(Arrays.toString(firstPairIndexes));

        PairWiseSum pws = new PairWiseSum();

        int[] array1 = {7, 2, 9, 1};
        int target1 = 8;
        System.out.println(Arrays.toString(array1) + " : " + pws.doesPairExistsUnsortedArray(array1, target1));
        int[] array2 = {};
        int target2 = 8;
        System.out.println(Arrays.toString(array2) + " : " + pws.doesPairExistsUnsortedArray(array2, target2));
        int[] array3 = {1};
        int target3 = 1;
        System.out.println(Arrays.toString(array3) + " : " + pws.doesPairExistsUnsortedArray(array3, target3));
        int[] array4 = {1, 1};
        int target4 = 3;
        System.out.println(Arrays.toString(array4) + " : " + pws.doesPairExistsUnsortedArray(array4, target4));
    }

}
