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
        String a = replicateLeftHalf(input);
        long diff1 = Math.abs(Long.parseLong(input) - Long.parseLong(a));
        if(diff1 == 0) {
            diff1 = Long.MAX_VALUE;
        }

        StringBuilder sb = new StringBuilder(input);
        int index = (sb.length() - 1) / 2;
        while(index >= 0 && sb.charAt(index) == '0') {
            sb.replace(index, index + 1, "9");
            index--;
        }
        if(index == 0 && sb.charAt(index) == '1') {
            sb.delete(0, 1);
            int mid = (sb.length() - 1) / 2;
            sb.replace(mid, mid + 1, "9");
        }  else {
            sb.replace(index, index + 1,
                    "" + (char) (sb.charAt(index) - 1));
        }
        String b = replicateLeftHalf(sb.toString());
        long diff2 = Math.abs(Long.parseLong(input) - Long.parseLong(b));

        sb = new StringBuilder(input);
        index = (sb.length() - 1) / 2;
        while(index >= 0 && sb.charAt(index) == '9') {
            sb.replace(index, index + 1, "0");
            index--;
        }
        if(index < 0) {
            sb.insert(0, "1");
        }  else {
            sb.replace(index, index + 1,
                    "" + (char) (sb.charAt(index) + 1));
        }
        String c = replicateLeftHalf(sb.toString());
        long diff3 = Math.abs(Long.parseLong(input) - Long.parseLong(c));

        if (diff2 <= diff1 && diff2 <= diff3)
            return b;
        if (diff1 <= diff3 && diff1 <= diff2)
            return a;
        else
            return c;
    }

    private static String replicateLeftHalf(String input) {
        String left = input.substring(0, input.length() / 2);
        String right = new StringBuilder(left).reverse().toString();
        if(input.length() % 2 == 1) {
            return left + input.charAt(input.length() / 2) + right;
        } else {
            return left + "" + right;
        }
    }

    public static void evaluate(String input, String expected) {

        System.out.println("Input : " + input);
        System.out.println("Expected : " + expected);
        String result1 = nearestPalindromeApproach1(input);
        System.out.println("Approach1 : " + result1);
        String result2 = nearestPalindromeApproach2(input);
        System.out.println("Approach2 : " + result2);
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
