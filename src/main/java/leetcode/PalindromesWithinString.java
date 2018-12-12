package leetcode;

import java.util.LinkedHashSet;
import java.util.Set;

public class PalindromesWithinString {

    /*
        Time complexity - O(N^3)
     */
    public static void findPalindromesNaive(String str) {
        if(str == null || str.isEmpty()) {
            return;
        }

        int maxLength = 0;
        int startindex = 0;
        String maxLengthWord = null;

        Set<String> set = new LinkedHashSet<>();
        for(int i = 0; i < str.length(); i++) {
            for(int j = i; j < str.length(); j++) {
                String window = str.substring(i, j + 1);
                if(isPalindrome(window)) {
                    set.add(window);
                    if(maxLength < window.length()) {
                        maxLength = window.length();
                        startindex = i;
                        maxLengthWord = window;
                    }
                }

            }
        }
        System.out.println("BRUTEFORCE");
        System.out.println(set.toString());
        System.out.println("Max Length = " + maxLength);
        System.out.println("Max Length Word = " + str.substring(startindex, startindex + maxLength));
    }

    public static void findPalindromeOptimized(String str) {

        if(str == null || str.isEmpty()) {
            return;
        }

        boolean[][] memo = new boolean[str.length()][str.length()];

        Set<String> set = new LinkedHashSet<>();
        int maxLength = 0;
        int start = 0;

        // maxLength = 1
        for(int i = 0; i < str.length(); i++) {
            memo[i][i] = true;
            set.add(str.substring(i, i+1));
            if(maxLength < 1) {
                maxLength = 1;
                start = i;
            }
        }

        // maxLength = 2
        for(int i = 0; i < str.length() - 1; i++) {
            if(str.charAt(i) == str.charAt(i+1)) {
                memo[i][i] = true;
                set.add(str.substring(i, i+1));
                if(maxLength < 2) {
                    maxLength = 2;
                    start = i;
                }
            }
        }

        // maxLength >= 3
        for(int size = 3; size <= str.length(); size++) {
            for(int begin = 0; begin <= str.length() - size; begin++) {
                int end = begin + size - 1;
                if(memo[begin+1][end-1] && str.charAt(begin) == str.charAt(end)) {
                    memo[begin][end] = true;
                    set.add(str.substring(begin, begin + size));
                    if(maxLength < size) {
                        maxLength = size;
                        start = begin;
                    }
                }
            }
        }

        System.out.println("OPTIMIZED");
        System.out.println(set.toString());
        System.out.println("Number of Palindromes = " + set.size());
        System.out.println("Max Length = " + maxLength);
        System.out.println("Max Length Word = " + str.substring(start, start + maxLength));
    }

    public static boolean isPalindrome(String str) {
        String reverse = new StringBuffer(str).reverse().toString();
        return str.equals(reverse);
    }

    public static void main(String[] args) {
        String str = "madam";
        findPalindromesNaive(str);
        findPalindromeOptimized(str);
    }
}
