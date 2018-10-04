package live;

import java.util.Arrays;

public class MergeSort {

    public static void merge(int[] zipCodes, int left, int mid, int right) {

        int leftSize = mid - left + 1;
        int rightSize = right - mid; // mid is already in left arry

        int[] leftArray = new int[leftSize];
        int[] rightArray = new int[rightSize];

        for(int i = 0; i < leftSize; i++) {
            leftArray[i] = zipCodes[left + i];
        }

        for(int j = 0; j < rightSize; j++) {
            rightArray[j] = zipCodes[mid + 1 + j];
        }

        int leftPointer = 0;
        int rightPointer = 0;
        int resultPointer = left;

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

    public static int[] sortZipCodes(int[] zipCodes, int low, int high) {

        if(low < high) {
            int midIndex = (low + high) / 2;
            sortZipCodes(zipCodes, low, midIndex);
            sortZipCodes(zipCodes, midIndex + 1, high);
            merge(zipCodes, low, midIndex, high);
        }
        return zipCodes;
    }

    public static void main(String[] args) {
        int[] zipCodes = {98052, 95050, 95051, 91231, 81913, 94568};
        int[] sortedZipCodes = sortZipCodes(zipCodes, 0, zipCodes.length - 1);
        System.out.println(Arrays.toString(sortedZipCodes));
    }
}
