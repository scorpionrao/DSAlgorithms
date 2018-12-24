package bbb.Easy;

import java.util.Arrays;

public class SwapWithoutTemp {

    private static void swap(int[] array) {
        if(array == null || array.length != 2) {
            return;
        }

        array[0] = array[0] + array[1];
        array[1] = array[0] - array[1];
        array[0] = array[0] - array[1];
    }

    public static void main(String[] args) {
        int[] array = {1, 2};
        System.out.println(Arrays.toString(array));
        swap(array);
        System.out.println(Arrays.toString(array));
    }
}
