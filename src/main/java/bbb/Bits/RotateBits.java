package bbb.Bits;

public class RotateBits {

    private static int shiftRight(int x, int times) {
        for(int i = 0; i < times; i++) {
            x = x >> 1;
            System.out.println(x + ", " + Integer.toBinaryString(x));
        }
        return x;
    }

    private static int shiftLeft(int x, int times) {
        for(int i = 0; i < times; i++) {
            x = x << 1;
            System.out.println(x + ", " + Integer.toBinaryString(x));
        }
        return x;
    }

    public static void main(String[] args) {

        int INTEGER_BIT = 32;
        int x = 27;
        int times = 8;

        System.out.println(x + ", " + Integer.toBinaryString(x));
        int rightShifted = shiftRight(x, times);
        System.out.println("Right Shifted = " + rightShifted);
        System.out.println("**********************************");
        System.out.println(x + ", " + Integer.toBinaryString(x));
        int leftShifted = shiftLeft(x, INTEGER_BIT - times);
        System.out.println("Left Shifted = " + leftShifted);
        System.out.println("**********************************");
        int expectedResult = (x >> times | x << (32 - times));
        System.out.println("Expected Result = " + expectedResult);
        System.out.println("Expected Result = " + Integer.toBinaryString(expectedResult));
        System.out.println("**********************************");
        int actualResult = leftShifted | rightShifted;
        System.out.println("Actual Result = " + actualResult);
        System.out.println("Actual Result = " + Integer.toBinaryString(actualResult));
        System.out.println("**********************************");
    }
}
