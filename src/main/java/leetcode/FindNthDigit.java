package leetcode;

public class FindNthDigit {

    public static int findNthDigit(int inputNthDigit) {
        int length = 1;
        long countWithLength = 9;

        while (inputNthDigit > length * countWithLength) {
            inputNthDigit = (int) (inputNthDigit - (length * countWithLength));
            length = length + 1;
            countWithLength = countWithLength * 10;
        }

        int startNumeral = (int) Math.pow(10, length - 1);
        startNumeral = startNumeral + (inputNthDigit - 1) / length;
        String str = Integer.toString(startNumeral);
        int strIndex = (inputNthDigit - 1) % length;
        char result = str.charAt(strIndex);
        return result - '0';
    }

    public static void main(String[] args) {
        int result = findNthDigit(11);
        System.out.println(result);
    }
}
