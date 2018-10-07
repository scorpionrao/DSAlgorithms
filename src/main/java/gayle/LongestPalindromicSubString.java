package gayle;

import java.util.*;

public class LongestPalindromicSubString {

    /* Time - O(N^2), Space - O(1) */
    public static int find(String inputText) {
        if (inputText == null) {
            System.out.println("Input cannot be null!");
            return 0;
        }

        int len = inputText.length();
        // each single char is already palindrome
        int palindromes = 0;
        for(int index = 0; index < len; index++) {
            System.out.println(inputText.charAt(index));
            palindromes++;
        }

        for (int index = 1; index < len - 1; index++) {
            // ODD occurring Palindromes
            for (int leftIndex = index - 1, rightIndex = index + 1; leftIndex >= 0 && rightIndex < len; leftIndex--, rightIndex++) {
                if (inputText.charAt(leftIndex) == inputText.charAt(rightIndex)) {
                    palindromes++;
                    System.out.println(inputText.subSequence(leftIndex, rightIndex + 1));
                } else {
                    break;
                }
            }
            // EVEN occurring Palindromes
            for (int leftIndex = index, rightIndex = index + 1; leftIndex >= 0 && rightIndex < len; leftIndex--, rightIndex++) {
                if (inputText.charAt(leftIndex) == inputText.charAt(rightIndex)) {
                    palindromes++;
                    System.out.println(inputText.subSequence(leftIndex, rightIndex + 1));
                } else {
                    break;
                }
            }
        }
        return palindromes;
    }

    static int longestPalindromicSubString(String str) {

        /* table[i][j] = true, if str.substring(i,j) is palindrome */
        char[][] isPalindrome = new char[str.length()][str.length()];
        for(char[] row : isPalindrome) {
            Arrays.fill(row, 'F');
        }

        printTable(isPalindrome);

        /* All substrings of length 1 is palindrome */
        int maxLength = 1;
        for(int i = 0; i < str.length(); i++) {
            isPalindrome[i][i] = 'T';
        }
        printTable(isPalindrome);

        /* Check substrings of length 2 */
        int start = 0;
        for(int i = 0; i < str.length() - 1; i++) {
            if(str.charAt(i) == str.charAt(i+1)) {
                isPalindrome[i][i+1] = 'T';
                start = i;
                maxLength = 2;
            }
        }
        printTable(isPalindrome);

        /* Lengths greater than 3 */
        for (int k = 3; k <= str.length(); k++) {
            for(int i = 0; i < str.length()-k+1; i++) {
                int endIndex = i + k - 1;
                if(isPalindrome[i+1][endIndex-1] == 'T'
                        && str.charAt(i) == str.charAt(endIndex)) {
                    isPalindrome[i][endIndex] = 'T';
                    if(k > maxLength) {
                        start = i;
                        maxLength = k;
                    }
                }
            }
        }
        System.out.println(str.substring(start, start + maxLength));
        return maxLength;
    }

    private static void printTable(char[][] isPalindrome) {
        System.out.println("***************************");
        for(char[] row : isPalindrome) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("***************************");
    }

    public static void main(String[] args) {
        String str = "abaaa";
        // longestPalindromicSubString(str);
        System.out.println(find(str));
    }
}
