package algorithms.divideandconquer;

public class BinarySearch {

    /*

    Input:  Sorted Array A[low....high]
            low <= i <= high
            A[i] <= A[i+1] - duplicates allowed
            A[i] < A[i+1] - duplicates not allowed
            searchAllPermutations - keyword

    Source Input : user
    Output : An index i (low <= i <= high) where A[i] = searchAllPermutations
            Otherwise, greatest index i, where A[i] < searchAllPermutations
            Otherwise, low - 1, (searchAllPermutations < A[low])

    RECURSIVE PSUEDO CODE:

    BinarySearch1(array, low, high, searchAllPermutations) {
        if(high < low)
            return NOT FOUND
        midIndex = low + (high - low) / 2
        if(A[midIndex] == searchAllPermutations)
            return midIndex;
        if(A[midIndex] > searchAllPermutations) {
            return BinarySearch1(A, low, midIndex - 1, searchAllPermutations);
        } else {
            return BinarySearch1(A, midIndex + 1, high, searchAllPermutations);
        }
    }
    Analysis:
        c --> constant checks
        T(n) = T(n/2) + c;
        T(0) = c;

        n --> c
        n/2 --> c
        0 --> c

        Run time = log2N * c = O(log N)

    ITERATIVE PSEUDO CODE:

    BinarySearch1(array, low, high, searchAllPermutations) {
        base case (reverse of condition) stops while SinglyLinkedListLoop
        while(low <= high) {
            midIndex = low + (high - low) / 2
            if(A[midIndex] == searchAllPermutations)
                return midIndex;
            else if(A[midIndex] > searchAllPermutations)
                high = midIndex - 1
            else
                low = midIndex + 1
        }
        return low - 1
    }
     */

    private static int[] binarySearch(int[] array, int[] searchKeys) {
        int[] searchResultIndexes = new int[searchKeys.length];
        for(int i = 0; i < searchResultIndexes.length; i++) {
            searchResultIndexes[i] = binarySearchIterative(array, 0, array.length - 1, searchKeys[i]);
        }
        return searchResultIndexes;
    }

    private static int binarySearchRecursive(int[] array, int low, int high, int searchKey) {
        if(high < low) {
            return -1;
        }
        int midIndex = low + (high - low) / 2;
        if(array[midIndex] == searchKey) {
            return midIndex;
        } else if(array[midIndex] > searchKey) {
            return binarySearchRecursive(array, low, midIndex - 1, searchKey);
        } else {
            return binarySearchRecursive(array, midIndex + 1, high, searchKey);
        }
    }

    private static int binarySearchIterative(int[] array, int low, int high, int searchKey) {
        while(low <= high) {
            int midIndex = low + (high - low) / 2;
            if (array[midIndex] == searchKey) {
                return midIndex;
            } else if (array[midIndex] > searchKey) {
                high = midIndex - 1;
            } else {
                low = midIndex + 1;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
/*
        Scanner scanner = new Scanner(System.in);
        int arraySize = scanner.nextInt();
        int[] array = new int[arraySize];
        for(int i = 0; i < array.length; i++) {
            array[i] = scanner.nextInt();
        }
        int searchKeysSize = scanner.nextInt();
        int[] searchKeys = new int[searchKeysSize];
        for(int i = 0; i < searchKeys.length; i++) {
            searchKeys[i] = scanner.nextInt();
        }
*/

        int[] array = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        int[] searchKeys = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

        int[] searchResultIndexes = binarySearch(array, searchKeys);
        for(int i = 0; i < searchResultIndexes.length; i++) {
            System.out.print(searchResultIndexes[i] + " ");
        }
    }
}
