package live;

import java.util.Arrays;

public class MergeSort {

    public static void merge(int[] zip, int low, int mid, int high) {

        int[] leftArray = Arrays.copyOfRange(zip, low, mid+1);
        int[] rightArray = Arrays.copyOfRange(zip, mid+1, high+1);

        int lPtr = 0;
        int rPtr = 0;
        int resultPtr = low;

        while(lPtr < leftArray.length && rPtr < rightArray.length) {
            if(leftArray[lPtr] <= rightArray[rPtr]) {
                zip[resultPtr] = leftArray[lPtr];
                lPtr++;
            } else {
                zip[resultPtr] = rightArray[rPtr];
                rPtr++;
            }
            resultPtr++;
        }

        while(lPtr < leftArray.length) {
            zip[resultPtr] = leftArray[lPtr];
            lPtr++;
            resultPtr++;
        }

        while(rPtr < rightArray.length) {
            zip[resultPtr] = rightArray[rPtr];
            rPtr++;
            resultPtr++;
        }
    }

    /*
        DFS: Post-Order processing - Process left and right and finally process current
        Base case: array size 1
     */
    public static void sort(int[] zipCodes, int low, int high) {

        if(low < high) {
            int midIndex = (low + high) / 2;
            sort(zipCodes, low, midIndex);
            sort(zipCodes, midIndex + 1, high);
            merge(zipCodes, low, midIndex, high);
        }
    }

    public static void main(String[] args) {
        int[] zipCodes = {98052, 95050, 95051, 91231, 81913, 94568};
        sort(zipCodes, 0, zipCodes.length - 1);
        System.out.println(Arrays.toString(zipCodes));
    }
}
