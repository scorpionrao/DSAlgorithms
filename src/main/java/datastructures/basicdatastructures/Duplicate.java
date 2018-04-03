package datastructures.basicdatastructures;

public class Duplicate {

    public static boolean hasDuplicate(int[] array) {
        for(int i = 0; i < array.length; i++) {
            for(int j = 0; j < array.length; j++) {
                if(array[i] == array[j] && i != j) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int binarySearch(int[] sortedArray, int search) {

        int low = 0;
        int high = sortedArray.length - 1;
        while(low <= high) {
            int mid = low + (high - low) / 2;
            if(sortedArray[mid] == search) {
                return mid;
            } else if(sortedArray[mid] < search) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low - 1;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(binarySearch(array, 4));
    }
}
