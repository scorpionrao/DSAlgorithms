package kickstart;


public class RecursionBinarySearchArray
{

    public static int midFinder(int start, int end) {
        return (start + end) / 2;
    }

    public static int midFinder1(int start, int end) {
        return start + (end - start) / 2;
    }

    public static void finder(int start, int end) {
        System.out.println(midFinder(5, 10));
        System.out.println(midFinder1(5, 10));
    }

    public static boolean binarySearch(int[] array, int begin, int end, int key) {

        if(end < begin) {
            return false;
        }
        int mid = (begin + end) / 2;
        if(array[mid] == key) {
            return true;
        } else if (array[mid] > key) {
            return binarySearch(array, begin, mid-1, key);
        } else {
            return binarySearch(array, mid+1, end, key);
        }
    }

    public static void main(String[] args) {
        finder(5, 10);
        finder(5, 11);
        finder(5, 12);
    }
}
