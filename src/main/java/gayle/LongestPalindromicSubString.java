package gayle;

public class LongestPalindromicSubString {

    /* Time - O(N^2), Space - O(N^2) */
    private static String longestPalindromicSubString1(String input) {

        boolean[][] isPalindromeTable = new boolean[input.length()][input.length()];
        String longestString = "";

        for (int length = 1; length <= input.length(); length++) {
            for(int startIndex = 0; startIndex < input.length() - length + 1; startIndex++) {
                int endIndex = startIndex + length - 1;
                if(input.charAt(startIndex) == input.charAt(endIndex)) {
                    if(length == 1 || length == 2) {
                        // Always True
                        isPalindromeTable[startIndex][endIndex] = true;
                    } else {
                        // True, if inner palindrome is True
                        if (isPalindromeTable[startIndex + 1][endIndex - 1]) {
                            isPalindromeTable[startIndex][endIndex] = true;
                        }
                    }
                }

                if(isPalindromeTable[startIndex][endIndex]) {
                    if (length > longestString.length()) {
                        // longestString = input.substring(startIndex, endIndex + 1);
                        longestString = input.substring(startIndex, startIndex + length);
                    }
                }
            }
        }
        return longestString;
    }

    /* Time - O(N^2), Space - O(1) */
    private static String longestPalindromicSubString2(String input) {
        String longestString = "";
        for(int i = 0; i < input.length(); i++) {
            int oddLength = expand(input, i, i);
            int evenLength = expand(input, i, i+1);
            if(oddLength > longestString.length()) {
                int start = i - (oddLength/2);
                int end = i + (oddLength/2);
                longestString = input.substring(start, end + 1);
            }
            if(evenLength > longestString.length()) {
                int start = i - (evenLength/2) + 1;
                int end = i + (evenLength/2);
                longestString = input.substring(start, end + 1);
            }
        }
        return longestString;
    }

    private static int expand(String input, int left, int right) {
        while(left >= 0 && right < input.length() && input.charAt(left) == input.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    private static void evaluate(String input) {
        System.out.println("Input : " + input);
        System.out.println("Approach 1 : " + longestPalindromicSubString1(input));
        System.out.println("Approach 2 : " + longestPalindromicSubString2(input));
    }

    public static void main(String[] args) {
        evaluate("babad");
        evaluate("cbbd");
    }
}
