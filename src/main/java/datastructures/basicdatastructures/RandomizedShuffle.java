package datastructures.basicdatastructures;

import java.util.Arrays;
import java.util.Random;

public class RandomizedShuffle {

    /** Returns a random shuffling of the array. */
    public static int[] shuffle(int[] nums) {
        if(nums == null) {
            return new int[0];
        }

        Random random = new Random();
        for (int solvingIndex = 0; solvingIndex < nums.length; solvingIndex++) {
            int randomIndex = solvingIndex + random.nextInt(nums.length - solvingIndex);
            int temp = nums[randomIndex];
            nums[randomIndex] = nums[solvingIndex];
            nums[solvingIndex] = temp;
        }
        return nums;
    }

    public static void main(String[] args) {
        //int[] nums = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] nums = {};
        shuffle(nums);
        System.out.println(Arrays.toString(nums));
    }
}
