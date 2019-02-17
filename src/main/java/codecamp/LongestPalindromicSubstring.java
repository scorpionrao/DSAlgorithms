package codecamp;

public class LongestPalindromicSubstring {

    private static String approach1(String input) {

        int maxLength = 0;
        String longestString = "";

        for(int i = 0; i < input.length(); i++) {
            for(int j = i; j < input.length(); j++) {
                String candidate = input.substring(i, j+1);
                if(isPalindrome(candidate)) {
                    if(maxLength < candidate.length()) {
                        maxLength = candidate.length();
                        longestString = candidate;
                    }
                }
            }
        }
        return longestString;
    }

    private static String approach2(String input) {
        int maxLength = 0;
        String longestString = "";

        for(int i = 0; i < input.length(); i++) {
            // odd
            int oddLength = longestPalindrome(input, i, i);
            if(maxLength < oddLength) {
                maxLength = oddLength;
                int leftIndex = i - (oddLength / 2);
                int rightIndex = i + (oddLength / 2);
                longestString = input.substring(leftIndex, rightIndex+1);
            }

            // even
            int evenLength = longestPalindrome(input, i, i+1);
            if(maxLength < evenLength) {
                maxLength = evenLength;
                int leftIndex = i - (evenLength / 2) + 1;
                int rightIndex = i + (oddLength / 2);
                longestString = input.substring(leftIndex, rightIndex+1);
            }

        }
        return longestString;
    }

    private static int longestPalindrome(String input, int leftIndex, int rightIndex) {
        int length = 0;
        while(leftIndex >= 0 && rightIndex < input.length() && input.charAt(leftIndex) == input.charAt(rightIndex)) {
            length = rightIndex - leftIndex + 1;
            leftIndex--;
            rightIndex++;
        }
        return length;
    }

    private static boolean isPalindrome(String str) {
        for(int i = 0; i < str.length() / 2; i++) {
            if(str.charAt(i) != str.charAt(str.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    private static void evaluate(String input, String expected) {
        System.out.println("Input : \t\t" + input);
        System.out.println("Expected : \t\t" + expected);
        String result1 = approach1(input);
        System.out.println("Approach1 : \t" + result1);
        String result2 = approach2(input);
        System.out.println("Approach2 : \t" + result2);
    }

    public static void main(String[] args) {
        evaluate("aabcdcb", "bcdcb");
        evaluate("bananas", "anana");
    }
}
