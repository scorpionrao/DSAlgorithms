package algorithms.divideandconquer;

import java.util.Arrays;
import java.util.Scanner;

public class MajorityElement {

    private static int getMajorityElementSlow(int[] elements) {

        for(int i = 0; i < elements.length; i++) {
            int count = 0;
            for(int j = i+1; j < elements.length; j++) {
                if(elements[i] == elements[j]) {
                    count++;
                }
                if(count > elements.length / 2) {
                    return 1;
                }
            }
        }
        return 0;
    }

    /*
        Sort elements - Merge sort - worst case n log n
        Scan through - O(N/2)
     */
    private static int getMajorityElementFast(int[] elements) {

        Arrays.sort(elements);
        int count = 0;
        int majorityElement = -1;
        for(int i = 0; i < elements.length; i++) {
            if(majorityElement == elements[i]) {
                count++;
            } else {
                majorityElement = elements[i];
                count = 1;
            }
            if(count > elements.length / 2) {
                return 1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numOfElements = scanner.nextInt();
        int[] elements = new int[numOfElements];
        for(int i = 0; i < elements.length; i++) {
            elements[i] = scanner.nextInt();
        }
        System.out.println(getMajorityElementFast(elements));
    }
}
