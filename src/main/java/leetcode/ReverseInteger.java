package leetcode;

public class ReverseInteger {

    private static int ONE_TENTH_MAX = Integer.MAX_VALUE / 10;
    private static int ONE_TENTH_MIN = Integer.MIN_VALUE / 10;

    /*
        Environment could only store integers within the 32-bit signed integer range: [−231,  231 − 1].

        Function returns 0 when the reversed integer overflows.
     */
    public static int reverse(int input) {
        int reverse = 0;
        while(input != 0) {
            int tailDigit = input % 10;
            input = input / 10;
            // 8, 9, 10
            if(reverse > ONE_TENTH_MAX || (reverse == ONE_TENTH_MAX && tailDigit > 7)) {
                return 0;
            }
            // -9
            if(reverse < ONE_TENTH_MIN || (reverse == ONE_TENTH_MIN && tailDigit < -8)) {
                return 0;
            }
            reverse = reverse * 10 + tailDigit;
        }
        return reverse;
    }

    private static void evaluate(int input) {
        System.out.println("Input = " + input);
        int result = reverse(input);
        System.out.println("Output = " + result);
    }

    public static void main(String[] args) {
        evaluate(123);
        evaluate(-123);
        evaluate(120);
        evaluate(Integer.MAX_VALUE);
        evaluate(Integer.MIN_VALUE);
    }
}
