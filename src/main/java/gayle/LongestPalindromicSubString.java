package gayle;

public class LongestPalindromicSubString {

    /* Time - O(N^2), Space - O(1) */
    static int longestPalindromicSubString(String input) {

        char[] charArray = input.toCharArray();
        boolean[][] palindromeTable = new boolean[charArray.length][charArray.length];

        String longestString = "";

        for (int length = 1; length <= charArray.length; length++) {
            for(int startIndex = 0; startIndex < charArray.length - length + 1; startIndex++) {
                int endIndex = startIndex + length - 1;
                boolean edgeCheck = charArray[startIndex] == charArray[endIndex];
                if(edgeCheck) {
                    if(length == 1 || length == 2) {
                        // No inner string
                        palindromeTable[startIndex][endIndex] = true;
                    } else {
                        // Is Inner string a palindrome ?
                        if (palindromeTable[startIndex + 1][endIndex - 1]) {
                            palindromeTable[startIndex][endIndex] = true;
                        }
                    }
                }

                if(palindromeTable[startIndex][endIndex]) {
                    String candidate = input.substring(startIndex, endIndex + 1);
                    // String candidate = input.substring(startIndex, startIndex + length);
                    if (candidate.length() > longestString.length()) {
                        longestString = candidate;
                    }
                }
            }
        }
        System.out.println(longestString);
        return longestString.length();
    }

    public static void main(String[] args) {
        String str = "abccccdd";
        System.out.println("MaxLength = " + longestPalindromicSubString(str));
    }
}
