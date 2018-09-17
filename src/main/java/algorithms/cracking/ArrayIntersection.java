package algorithms.cracking;

public class ArrayIntersection {

    /*
        Time complexity: O(a+b)
            a - length of array1
            b - length of array2

        Space complexity: O(a)
     */
    private static int getIntersection(int[] sortedArray1, int[] sortedArray2) {
        int commonElements = 0;
        int aIndex = 0;
        int bIndex = 0;
        while (aIndex < sortedArray1.length && bIndex < sortedArray2.length) {
            if(sortedArray1[aIndex] == sortedArray2[bIndex]) {
                commonElements++;
                aIndex++;
                bIndex++;
            } else if (sortedArray1[aIndex] < sortedArray2[bIndex]) {
                aIndex++;
            } else {
                bIndex++;
            }
        }
        return commonElements;
    }

    public static void main(String[] args) {
        int[] array1 = {13, 27, 35, 40, 49, 55, 59};
        int[] array2 = {17, 35, 39, 40, 55, 58, 60};
        System.out.println(getIntersection(array1, array2));
    }
}
