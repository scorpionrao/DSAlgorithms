package datastructures.basicdatastructures;

import java.util.Arrays;
import java.util.Random;

public class RandomizedShuffle {

    /** Returns a random shuffling of the array. */
    public static int[] shuffle(int[] nums) {
        if(nums == null) {
            new NullPointerException();
        }

        Random random = new Random();
        for (int solvingIndex = 0; solvingIndex < nums.length; solvingIndex++) {
            int range = nums.length - solvingIndex;
            int randomIndex = solvingIndex + random.nextInt(range);
            System.out.println(String.format("Solving index: %d, Random range: %d, Random index picked: %d", solvingIndex, range, randomIndex));
            int temp = nums[randomIndex];
            nums[randomIndex] = nums[solvingIndex];
            nums[solvingIndex] = temp;
            System.out.println(Arrays.toString(nums));
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] shuffledArray = shuffle(nums);
        for(int i = 0; i < shuffledArray.length; i++) {
            System.out.print(shuffledArray[i] + " ");
        }
        System.out.println();
    }
}
