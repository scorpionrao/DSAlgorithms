package bbb.Easy;

public class IntegerToRoman {

    private static String integerToRoman(int input) {
        StringBuilder sb = new StringBuilder();

        int[] numerals = new int[]{100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = new String[]{"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int i = 0;
        while (input > 0) {
            if(input >= numerals[i]) {
                sb.append(symbols[i]);
                input = input - numerals[i];
            } else {
                i++;
            }
        }
        return sb.toString();
    }

    private static void evalate(int input) {
        System.out.println("Integer : " + input + ", Roman : " + integerToRoman(input));
    }

    public static void main(String[] args) {
        for(int i = 1; i <= 100; i++) {
            evalate(i);
        }
    }


}
