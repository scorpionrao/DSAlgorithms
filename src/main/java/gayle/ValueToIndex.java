package gayle;


import java.util.Arrays;

public class ValueToIndex {

    public static void main(String[] args) {
        int[] inputArray = {2, 3, 1, 0};
        // o/p = a = {1,0,3,2}
        int[] expectedOutputArray = {1, 0, 3, 2};
        System.out.println("Input: " + Arrays.toString(inputArray));
        System.out.println("Expected Output: " + Arrays.toString(expectedOutputArray));
        System.out.println("Actual Output: " + Arrays.toString(valueToIndex(inputArray)));
    }

    /*
        (x + y*z)/z = y    provided x and y is less than z.
        (x + y*z)%z = x    provided x and y is less than z.
        This is the concept used here.
        Example:
        (3 + 4*5)/5 = 4
        (3 + 4*5)%5 = 3

        arr[i] = arr[i] + arr[arr[i]]*size
        so arr[i]/size = arr[arr[i]]
     */
    private static int[] valueToIndex(int[] array) {

        int size = array.length;

        for(int i = 0; i < size; i++) {
            array[i] = array[i] + (array[array[i]] % size) * size;
        }
        for(int i = 0; i < size; i++) {
            array[i] = array[i] / size;
        }

        int[] result = new int[size];
        for(int i = 0; i < size; i++) {
            result[i] = array[array[i]];
        }
        return result;
    }
}
