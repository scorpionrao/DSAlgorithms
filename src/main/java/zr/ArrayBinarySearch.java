package zr;

public class ArrayBinarySearch {

    public class Solution {

        public int binarySearchIterative(int[] inputArray, int searchKey) {

            int low = 0;
            int high = inputArray.length - 1;
            while(low <= high) {
                int mid = (high + low) / 2;
                if(inputArray[mid] == searchKey) {
                    return mid;
                } else if(inputArray[mid] > searchKey) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            return -1;
        }

        public int binarySearchRecursion(int[] inputArray, int searchKey) {
            return binarySearchRecursion(inputArray, searchKey, 0, inputArray.length-1);
        }

        public int binarySearchRecursion(int[] inputArray, int searchKey, int low, int high) {
            if(low > high) {
                return -1;
            }
            int mid = (high + low) / 2;
            if(inputArray[mid] == searchKey) {
                return mid;
            } else if(inputArray[mid] > searchKey) {
                return binarySearchRecursion(inputArray, searchKey, low, mid - 1);
            } else {
                return binarySearchRecursion(inputArray, searchKey, mid + 1, high);
            }
        }

        public void printResults(int[] inputArray) {
            System.out.println("ITERATIVE");
            for(int i = 0; i <= inputArray.length + 1; i++) {
                System.out.println("Index for " + i + ": " + binarySearchIterative(inputArray, i));
            }

            System.out.println("RECURSION");
            for(int i = 0; i <= inputArray.length + 1; i++) {
                System.out.println("Index for " + i + ": " + binarySearchRecursion(inputArray, i));
            }
        }
    }

    public static void main(String[] args) {
        int[] inputArray = {1, 2, 3, 4, 5};

        Solution solution = new ArrayBinarySearch().new Solution();
        solution.printResults(inputArray);
    }
}
