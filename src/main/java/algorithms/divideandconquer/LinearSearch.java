package algorithms.divideandconquer;

import java.util.Scanner;

public class LinearSearch {

    /*
        Input: Array of Integers
        Input source: User provided
        Output: IsElementThere ? Index of element ?

        Time limitations: 1.5 s
        Space : 1024 MB

        Psuedo code (Recursive):
        Recursive correlation: T(n) = T(n-1) + c
        O(1) * n --> O(n)

        int LinearSearch(A, low, high, searchAllPermutations) {
            if(high < low) {
                return very large index;
            }
            if(A[low] == searchAllPermutations) {
                return low;
            }
            return LinearSearch(A, low+1, high, searchAllPermutations);
        }
     */

    public static boolean linearSearch(int[] array, int key, int low, int high) {
        if(low > high) {
            return false;
        }
        if(array[low] == key) {
            return true;
        }
        return linearSearch(array, key, low + 1, high);
    }

    /*
        Psuedo code (Iterative):
        Recursive correlation: T(n) = T(n-1) + c
        O(1) * n --> O(n)

        int LinearSearchIterative(A, low, high, searchAllPermutations) {
            while(low <= high) {
                if(A[low] == searchAllPermutations)
                    return low;
                low = low + 1;
            }
            return NOT FOUND

            for(int i=low; i<=high; i++) {
                if(A[low] == searchAllPermutations) {
                    return i;
                }
            }
            return NOT FOUND
         }
     */

    public static boolean linearSearchSimplified(int[] array, int key) {
        for(int element : array) {
            if(element == key) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numOfItems = scanner.nextInt();
        int searchValue = scanner.nextInt();
        int[] array = new int[numOfItems];
        for(int i = 0; i < numOfItems; i++) {
            array[i] = scanner.nextInt();
        }
        System.out.println(linearSearch(array, searchValue, 0, numOfItems - 1));
    }
}
