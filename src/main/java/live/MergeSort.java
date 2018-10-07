package live;

import java.util.Arrays;

public class MergeSort {

    public static void merge(int[] zipCodes, int low, int mid, int high) {

        int leftSize = mid - low + 1;
        int rightSize = high - mid;

        int[] leftArray = new int[leftSize];
        int[] rightArray = new int[rightSize];

        for(int i = 0; i < leftSize; i++) {
            leftArray[i] = zipCodes[low + i];
        }

        for(int j = 0; j < rightSize; j++) {
            rightArray[j] = zipCodes[mid + 1 + j];
        }

        int leftPointer = 0;
        int rightPointer = 0;
        int resultPointer = low;

        while(leftPointer < leftArray.length && rightPointer < rightArray.length) {
            if(leftArray[leftPointer] <= rightArray[rightPointer]) {
                zipCodes[resultPointer] = leftArray[leftPointer];
                leftPointer++;
            } else {
                zipCodes[resultPointer] = rightArray[rightPointer];
                rightPointer++;
            }
            resultPointer++;
        }

        while(leftPointer < leftArray.length) {
            zipCodes[resultPointer] = leftArray[leftPointer];
            leftPointer++;
            resultPointer++;
        }

        while(rightPointer < rightArray.length) {
            zipCodes[resultPointer] = rightArray[rightPointer];
            rightPointer++;
            resultPointer++;
        }
    }

    public static void divide(int[] zipCodes, int low, int high) {

        if(low < high) {
            int midIndex = (low + high) / 2;
            divide(zipCodes, low, midIndex);
            divide(zipCodes, midIndex + 1, high);
            merge(zipCodes, low, midIndex, high);
        }
    }

    public static void main(String[] args) {
        int[] zipCodes = {98052, 95050, 95051, 91231, 81913, 94568};
        divide(zipCodes, 0, zipCodes.length - 1);
        System.out.println(Arrays.toString(zipCodes));
    }
}
