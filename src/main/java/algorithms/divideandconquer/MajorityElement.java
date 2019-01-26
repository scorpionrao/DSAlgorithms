package algorithms.divideandconquer;

import java.util.Arrays;
import java.util.Scanner;

public class MajorityElement {

    /* Time: O(N log N), Space: O(1) */
    private static int getMajorityElementSlow(int[] elements) {
        Arrays.sort(elements);
        return elements[elements.length/2];
    }

    /*
        Count will always be >= 1
        Time: O(N), Space: O(1)
     */
    private static int getMajorityElementFast(int[] elements) {
        int count = 0;
        int majorityElement = -1;
        for(int i = 0; i < elements.length; i++) {
            if(count == 0) {
                count++;
                majorityElement = elements[i];
            } else if(majorityElement == elements[i]) {
                count++;
            } else {
                count--;
            }
        }
        System.out.println("Count = " + count);
        return majorityElement;
    }

    public static void evaluate(int[] elements) {
        // System.out.println(Arrays.toString(elements));
        System.out.println(getMajorityElementFast(elements));
    }

    public static void main(String[] args) {
        int[] elements1 = {5, 5, 5, 5, 5, 1, 1, 1, 5};
        evaluate(elements1);
        int[] elements2 = {1, 1, 1, 1, 5, 5, 5, 5, 5};
        evaluate(elements2);
        int[] elements3 = {1, 5, 2, 5, 3, 5, 4, 5, 5};
        evaluate(elements3);
    }
}
