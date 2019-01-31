package leetcode;

public class SquareRoot {
    /*
        result ^ 2 < input < result+1 ^ 2
     */
    private static int squareRoot1(int input) {
        long mid = input;
        while (mid * mid > input) {
            long low = input / mid;
            long high = mid;
            mid = (low + high) / 2;
        }
        return (int) mid;
    }

    private static int squareRoot2(int input) {
        return (int) Math.sqrt(input);
    }

    private static void evaluate(int input) {
        System.out.println("Input : " + input);
        int result1 = squareRoot1(input);
        System.out.println("Output : " + result1);
        int result2 = squareRoot2(input);
        System.out.println("Output : " + result2);
    }

    public static void main(String[] args) {
        int input1 = 100;
        evaluate(input1);
    }
}
