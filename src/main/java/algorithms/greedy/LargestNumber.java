package algorithms.greedy;

import java.util.Scanner;

public class LargestNumber {
    /*
    algorithms.greedy.LargestNumber(Digits):
    answer ← empty string
    while Digits is not empty:
    maxDigit ← −∞
    for digit in Digits:
    if digit ≥ maxDigit:
    maxDigit ← digit
    append maxDigit to answer
    remove maxDigit from Digits
    return answer
     */
    private static String largestNumber(String[] a) {
        //write your code here
        String result = "";
        for (int i = 0; i < a.length; i++) {
            result += a[i];
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] a = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.next();
        }
        System.out.println(largestNumber(a));
    }
}

