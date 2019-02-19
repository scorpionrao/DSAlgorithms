package codecamp;

public class NearestPalindrome {

    private static String nearestPalindrome(String input) {
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

    public static void main(String[] args) {
        String input = "11";
        String result = nearestPalindrome(input);
        System.out.println("Input : " + input);
        System.out.println("Result : " + result);
    }
}
