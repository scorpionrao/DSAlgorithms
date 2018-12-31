package bbb.Strings;

import java.util.Arrays;

public class LongestContinuousSequence {

    /*
        Compare all windows of short string within long string

        Time: O(N^2 * M)
        N -> min(S1.length, S2.length)
        M -> max(S1.length, S2.length))

        Space: O(1)
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

    private static String longestContinuousSequence3(String shortString, String longString) {
        if(shortString.length() > longString.length()) {
            return longestContinuousSequence3(longString, shortString);
        }
        int[][] cache = new int[shortString.length()][longString.length()];
        for(int[] row : cache) {
            Arrays.fill(row, -1);
        }
        for(int i = 0; i < shortString.length(); i++) {
            for(int j = 0; j < longString.length(); j++) {
                longestContinuousSequence3(shortString, longString, i, j, cache);
            }
        }

        int longestLength = 0;
        int endIndex = 0;
        for(int i = 0; i < shortString.length(); i++) {
            for(int j = 0; j < longString.length(); j++) {
                if(cache[i][j] > longestLength) {
                    longestLength = cache[i][j];
                    endIndex = i;
                }
            }
        }
        return shortString.substring(endIndex - longestLength + 1, endIndex + 1);
    }

    private static void longestContinuousSequence3(String shortString, String longString,
                                                   int row, int col, int[][] cache) {

        if(cache[row][col] >= 0) {
            return;
        }
        if(shortString.charAt(row) == longString.charAt(col)) {
            if(row == 0 || col == 0) {
                cache[row][col] = 1;
            } else {
                cache[row][col] = 1 + cache[row-1][col-1];
            }
        } else {
            cache[row][col] = 0;
        }
    }


    /* Time: O(m * n), Space: O(m * n) */
    private static String longestContinuousSequence4(String shortString, String longString) {

        if(longString.length() < shortString.length()) {
            return longestContinuousSequence4(longString, shortString);
        }

        // Same Length + Considers only matches
        int[][] dpCache = new int[shortString.length()][longString.length()];

        int maxLength = 0;
        String candidate = "";
        for(int i = 0; i < shortString.length(); i++) {
            for(int j = 0; j < longString.length(); j++) {
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
        return candidate;
    }


    public static void evaluate(String str1, String  str2) {
        System.out.println("String1 : " + str1);
        System.out.println("String2 : " + str2);
        String result1 = longestContinuousSequence1(str1, str2);
        System.out.println("Approach1 : " + result1);
        String result3 = longestContinuousSequence3(str1, str2);
        System.out.println("Approach3 : " + result3);
        String result4 = longestContinuousSequence4(str1, str2);
        System.out.println("Approach4 : " + result4);
    }

    public static void main(String[] args) {
        String str1 = "bbbbbb";
        String str2 = "abbba";
        evaluate(str1, str2);
    }
}
