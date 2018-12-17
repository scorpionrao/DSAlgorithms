package bbb.Strings;

import java.util.Arrays;

public class LongestContinuousSequence {

    /*
        Time: O(N^2 * M)
        Time: O(min(S1.length, S2.length) ^ 2 * max(S1.length, S2.length)),
        Space: O(10)
    */
    private static String longestContinuousSequence1(String shortString, String longString) {

        if(shortString.length() > longString.length()) {
            return longestContinuousSequence1(longString, shortString);
        }

        int maxLength = 0;
        String result = "";

        for(int i = 0; i < shortString.length(); i++) {
            for (int j = i; j < shortString.length(); j++) {
                if(j - i + 1 <= maxLength) {
                    continue;
                }
                String window = shortString.substring(i, j+1);
                if(longString.contains(window) && window.length() > maxLength) {
                    maxLength = window.length();
                    result = window;
                }
            }
        }
        return result;
    }

    /* Time: O(m * n), Space: O(m * n) */
    private static String longestContinuousSequence2(String shortString, String longString) {

        // Same Length + Considers only matches
        int[][] dpCache = new int[shortString.length()][longString.length()];

        int maxLength = 0;
        String candidate = "";
        for(int i = 0; i < dpCache.length; i++) {
            for(int j = 0; j < dpCache[i].length; j++) {
                if(shortString.charAt(i) == longString.charAt(j)) {
                    if(i == 0 || j == 0) {
                        // 1 char vs upto rest, max is 1
                        // since character is equal, value will be exactly 1
                        dpCache[i][j] = 1;
                    } else {
                        // Either beginning or extending sequence
                        dpCache[i][j] = 1 + dpCache[i-1][j-1];
                    }
                    // Non matching characters bring everything to zero.

                    // this is the only place, value is updated
                    if(dpCache[i][j] > maxLength) {
                        maxLength = dpCache[i][j];
                        candidate = shortString.substring(i - maxLength + 1, i+1);
                    }
                }
            }
        }
        for(int[] row : dpCache) {
            System.out.println(Arrays.toString(row));
        }
        return candidate;
    }

    /* Time: O(m * n), Space: O(m * n) */
    /* ONLY DELETION ALLOWED */
    private static int longestContinuousSequence3(String shortString, String longString) {

        // Same Length + Considers only matches
        int[][] dpCache = new int[shortString.length()+1][longString.length()+1];

        String candidate = "";
        for(int i = 0; i < dpCache.length; i++) {
            for(int j = 0; j < dpCache[i].length; j++) {
                if(i == 0 || j == 0) {
                    // Empty String
                    dpCache[i][j] = 0;
                } else {
                    if(shortString.charAt(i-1) == longString.charAt(j-1)) {
                        // Either beginning or extending sequence
                        dpCache[i][j] = 1 + dpCache[i-1][j-1];
                    } else {
                        // deletion or insertion
                        dpCache[i][j] = Math.max(dpCache[i-1][j], dpCache[i][j-1]);
                    }
                }
            }
        }
        int maxLength = dpCache[shortString.length()][longString.length()];
        System.out.println("Max Length : " + maxLength);
        return maxLength;
    }


    public static void evaluate(String str1, String  str2) {
        System.out.println("String1 : " + str1);
        System.out.println("String2 : " + str2);
        String result1 = longestContinuousSequence1(str1, str2);
        System.out.println("Approach1 : " + result1);
        String result2 = longestContinuousSequence2(str1, str2);
        System.out.println("Approach2 : " + result2);
        int result3 = longestContinuousSequence3(str1, str2);
        System.out.println("Approach3 : " + result3);
    }

    public static void main(String[] args) {
        String str1 = "abcdef";
        String str2 = "abcde";
        evaluate(str1, str2);
    }
}
