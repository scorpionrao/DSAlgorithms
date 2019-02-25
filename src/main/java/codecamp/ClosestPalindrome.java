package codecamp;

public class ClosestPalindrome {

    private static String nearestPalindromeApproach1(String input) {
        long num = Long.parseLong(input);
        for(int i = 1; ; i++) {
            if(isPalindrome(num - i)) {
                return "" + (num - i);
            }
            if(isPalindrome(num + i)) {
                return "" + (num + i);
            }
        }
    }

    private static boolean isPalindrome(long num) {
        String input = String.valueOf(num);
        String reverse = new StringBuilder(input).reverse().toString();
        return input.equals(reverse);
    }

    private static String nearestPalindromeApproach2(String input) {

        return null;
    }

    public static void evaluate(String input, String expected) {

        System.out.println("Input : " + input);
        System.out.println("Expected : " + expected);
        String result1 = nearestPalindromeApproach1(input);
        System.out.println("Approach1 : " + result1);
        String result2 = nearestPalindromeApproach2(input);
        System.out.println("Approach2 : " + result1);
        System.out.println("-------------------");
    }

    public static void main(String[] args) {
        evaluate("11", "9");
        evaluate("18", "22");
        evaluate("123", "121");
        evaluate("10987", "11011");
        evaluate("20001", "20002");
        evaluate("10000", "9999");
    }
}
