package leetcode;

public class IntegerPalindrome {

    private static boolean isPalindrome(int input) {
        // negative numbers and ending with zeros will never be palindrome
        if(input < 0 || (input % 10 == 0 && input != 0)) {
            return false;
        }
        // convert right half with right most number MSB and middle number LSB.
        int rightMostAsMSB = 0;
        while(input > rightMostAsMSB) {
            int remainder = input % 10;
            rightMostAsMSB = rightMostAsMSB * 10 + remainder;
            input = input / 10;
        }
        return input == rightMostAsMSB || input == rightMostAsMSB / 10;
    }
}
