package bbb.Bits;

public class NumberOfOnes {

    private static int numberOfOnes(int input) {

        int countOfOnes = 0;
        while(input > 0) {
            int oneOrZero = input & 1;
            countOfOnes += oneOrZero;
            input = input >> 1;
        }
        return countOfOnes;
    }

    public static void main(String[] args) {
        int input = 28;
        System.out.println("Number of Bit Count : " + Integer.bitCount(input));
        System.out.println("Number of Ones : " + numberOfOnes(input));
    }
}
