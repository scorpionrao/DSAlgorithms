package algorithms.cracking;

public class bcr {

    /*
    Time complexity: O(a+b)
        a - length of array1
        b - length of array2
     */
    private static int getIntersection(int[] array1, int[] array2) {
        int commonElements = 0;
        int aIndex = 0;
        int bIndex = 0;
        while (aIndex < array1.length && bIndex < array2.length) {
            if(array1[aIndex] == array2[bIndex]) {
                commonElements++;
                aIndex++;
                bIndex++;
            } else if (array1[aIndex] < array2[bIndex]) {
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
