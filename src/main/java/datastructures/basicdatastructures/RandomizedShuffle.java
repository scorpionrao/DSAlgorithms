package datastructures.basicdatastructures;

import java.util.Random;

public class RandomizedShuffle
{

    public static void findMedian(int[] array) {
        int size = array.length;
        int k = size / 2;

        int pivot = new Random().nextInt(array.length - 0 + 1) + 0;
    }

    /** Returns a random shuffling of the array. */
    public static void shuffle(int[] nums) {
        if(nums == null) return;
        int[] a = nums.clone();
        Random random = new Random();
        for(int j = 1; j < a.length; j++) {
            int i = random.nextInt(j + 1);
            System.out.println("Index: " + j + ", RandomIndex: 0 to " + (j));
            swap(a, i, j);
        }
        for(int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        shuffle(nums);
    }
}
