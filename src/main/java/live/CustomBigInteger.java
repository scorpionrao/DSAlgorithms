package live;

import java.util.Arrays;

public class CustomBigInteger {

    public static String add(String string1, String string2) {

        StringBuilder sb = new StringBuilder();
        int index1 = string1.length() - 1;
        int index2 = string2.length() - 1;
        int carry = 0;

        while(index1 >= 0 || index2 >= 0 || carry != 0) {
            int digit1 = (index1 < 0) ? 0 : Integer.parseInt(string1.substring(index1, index1 + 1));
            index1--;
            int digit2 = (index2 < 0) ? 0 : Integer.parseInt(string2.substring(index2, index2 + 1));
            index2--;

            int sum = digit1 + digit2 + carry;
            sb.insert(0, sum % 10);
            carry = sum / 10;
        }
        return sb.toString();
    }

    public static String multiplySingleDigit(String string, String singleLength) {

        StringBuilder sb = new StringBuilder();
        int index = string.length() - 1;
        int carry = 0;

        while(index >= 0 || carry != 0) {
            int digit1 = (index < 0) ? 0 : Integer.parseInt(string.substring(index, index + 1));
            index--;
            int digit2 = Integer.parseInt(singleLength);

            int product = (digit1 * digit2) + carry;
            sb.insert(0, product % 10);
            carry = product / 10;
        }
        return sb.toString();
    }

    public static String multiply(String topNumber, String bottomNumber) {

        int bottomIndex = bottomNumber.length() - 1;
        String sum = "";
        // right to left like manual multiplication
        while(bottomIndex >= 0) {
            String singleLength = bottomNumber.substring(bottomIndex, bottomIndex + 1);
            String line = multiplySingleDigit(topNumber, singleLength) + generateZeros(bottomNumber.length() - 1 - bottomIndex);
            sum = add(sum, line);
            bottomIndex--;
        }
        return sum;
    }

    private static String generateZeros(int num) {
        char[] charArray = new char[num];
        Arrays.fill(charArray, '0');
        return new String(charArray);
    }

    public static void main(String[] args) {
        String string1 = "25000";
        String string2 = "25000";
        String sumString = multiply(string1, string2);
        System.out.println(sumString);
    }
}