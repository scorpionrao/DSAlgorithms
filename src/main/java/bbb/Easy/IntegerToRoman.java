package bbb.Easy;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class IntegerToRoman {

    static Map<Integer, String> integerToRoman = new LinkedHashMap<>();
    static Map<String, Integer> romanToInteger = new HashMap<>();
    static Map<String, Integer> romanToIntegerDifference = new HashMap<>();

    private static void loadIntegerToRoman() {
        integerToRoman.put(1000, "M");
        integerToRoman.put(900, "CM");
        integerToRoman.put(500, "D");
        integerToRoman.put(400, "CD");
        integerToRoman.put(100, "C");
        integerToRoman.put(90, "XC");
        integerToRoman.put(50, "L");
        integerToRoman.put(40, "XL");
        integerToRoman.put(10, "X");
        integerToRoman.put(9, "IX");
        integerToRoman.put(5, "V");
        integerToRoman.put(4, "IV");
        integerToRoman.put(1, "I");
    }

    private static void loadRomanToInteger() {
        romanToInteger.put("M", 1000);
        romanToInteger.put("D", 500);
        romanToInteger.put("C", 100);
        romanToInteger.put("L", 50);
        romanToInteger.put("X", 10);
        romanToInteger.put("V", 5);
        romanToInteger.put("I", 1);
    }

    private static void romanToIntegerDifference() {
        romanToIntegerDifference.put("CM", 200);
        romanToIntegerDifference.put("CD", 200);
        romanToIntegerDifference.put("XC", 20);
        romanToIntegerDifference.put("XL", 20);
        romanToIntegerDifference.put("IX", 2);
        romanToIntegerDifference.put("IV", 2);
    }

    static {
        loadIntegerToRoman();
        loadRomanToInteger();
        romanToIntegerDifference();
    }

    private static String integerToRoman(int input) {

        StringBuilder sb = new StringBuilder();
        for(Integer decimal : integerToRoman.keySet()) {
            while(input >= decimal) {
                sb.append(integerToRoman.get(decimal));
                input = input - decimal;
            }
        }
        return sb.toString();
    }

    private static int romanToInteger(String roman) {

        int sum = 0;
        for(char ch : roman.toCharArray()) {
            sum = sum + romanToInteger.get(String.valueOf(ch));
        }

        for(String str : romanToIntegerDifference.keySet()) {
            if(roman.indexOf(str) != -1) {
                sum = sum - romanToIntegerDifference.get(str);
            }
        }
        return sum;
    }

    private static void evaluate(int input) {
        for(int i = 1; i <= input; i++) {
            String roman = integerToRoman(i);
            int decimal = romanToInteger(roman);
            if(i != decimal) {
                System.out.println("WRONG");
            }
            System.out.println(String.format("%d -> %s", i, roman));
        }
    }

    public static void main(String[] args) {
        evaluate(100);
    }
}
