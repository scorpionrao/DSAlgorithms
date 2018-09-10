package live;

import java.util.HashMap;
import java.util.Map;

public class NumbersToWords
{

    public static Map<Integer, String> map = new HashMap<>();

    private static void fillMap() {
        map.put(0, "zero");
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.put(4, "four");
        map.put(5, "five");
        map.put(6, "six");
        map.put(7, "seven");
        map.put(8, "eight");
        map.put(9, "nine");
        map.put(10, "ten");
        map.put(11, "eleven");
        map.put(12, "twelve");
        map.put(13, "thirteen");
        map.put(14, "fourteen");
        map.put(15, "fifteen");
        map.put(16, "sixteen");
        map.put(17, "seventeen");
        map.put(18, "eighteen");
        map.put(19, "nineteen");
        map.put(20, "twenty");
        map.put(30, "thirty");
        map.put(40, "forty");
        map.put(50, "fifty");
        map.put(60, "sixty");
        map.put(70, "seventy");
        map.put(80, "eighty");
        map.put(90, "ninety");
        map.put(100, " hundred ");
        map.put(1000, " thousand ");
        map.put(1000000, " million ");
        map.put(1000000000, " billion ");
    }

    public static String getNumbersToWords(int input) {

        fillMap();
        StringBuilder sb = new StringBuilder();

        // handle zero
        if(input == 0) {
            sb.append(map.get(0));
            return sb.toString().trim();
        }

        // handle negative at the beginning
        if(input < 0) {
            sb.append("negative ");
        }

        // get rid of sign
        input = Math.abs(input);

        if(input >= 1000000000) {
            int billion = input / 1000000000;
            sb.append(convert(billion) + map.get(1000000000));
            input = input % 1000000000;
        }

        if(input >= 1000000) {
            int million = input / 1000000;
            sb.append(convert(million) + map.get(1000000));
            input = input % 1000000;
        }

        if(input >= 1000) {
            int thousand = input / 1000;
            sb.append(convert(thousand) + map.get(1000));
            input = input % 1000;
        }

        if(input > 0) {
            sb.append(convert(input));
        }

        return sb.toString().trim();
    }

    private static String convert(int input) {

        StringBuilder sb = new StringBuilder();

        if(input >= 100) {
            int hundred = input / 100;
            sb.append(map.get(hundred) + map.get(100));
            input = input % 100;
        }

        if(input > 20) {
            int tens = input / 10;
            sb.append(map.get(tens * 10) + " ");
            input = input % 10;
        }

        // handle first twenty
        if(input <= 20 && input > 0) {
            sb.append(map.get(input));
        }

        return sb.toString().trim();
    }

    private static void validate(String expected, String actual, int number) {
        if(expected.equals(actual)) {
            System.out.println("Number: " + number + " OK");
        } else {
            System.out.println("Number: " + number + " WRONG: " + expected + " " + actual);
        }
    }

    public static void main(String[] args) {
        int number = 0;
        validate("zero", getNumbersToWords(number), number);
        number = 1;
        validate("one", getNumbersToWords(number), number);
        number = -1;
        validate("negative one", getNumbersToWords(number), number);
        number = 10;
        validate("ten", getNumbersToWords(number), number);
        number = -10;
        validate("negative ten", getNumbersToWords(number), number);
        number = 11;
        validate("eleven", getNumbersToWords(number), number);
        number = -11;
        validate("negative eleven", getNumbersToWords(number), number);
        number = 20;
        validate("twenty", getNumbersToWords(number), number);
        number = -20;
        validate("negative twenty", getNumbersToWords(number), number);
        number = 21;
        validate("twenty one", getNumbersToWords(number), number);
        number = -21;
        validate("negative twenty one", getNumbersToWords(number), number);
        number = 99;
        validate("ninety nine", getNumbersToWords(number), number);
        number = -99;
        validate("negative ninety nine", getNumbersToWords(number), number);
        number = 100;
        validate("one hundred", getNumbersToWords(number), number);
        number = -100;
        validate("negative one hundred", getNumbersToWords(number), number);
        number = 101;
        validate("one hundred one", getNumbersToWords(number), number);
        number = -101;
        validate("negative one hundred one", getNumbersToWords(number), number);
        number = 200;
        validate("two hundred", getNumbersToWords(number), number);
        number = -200;
        validate("negative two hundred", getNumbersToWords(number), number);
        number = 999;
        validate("nine hundred ninety nine", getNumbersToWords(number), number);
        number = -999;
        validate("negative nine hundred ninety nine", getNumbersToWords(number), number);
        number = 1000;
        validate("one thousand", getNumbersToWords(number), number);
        number = -1000;
        validate("negative one thousand", getNumbersToWords(number), number);
        number = 1001;
        validate("one thousand one", getNumbersToWords(number), number);
        number = -1001;
        validate("negative one thousand one", getNumbersToWords(number), number);
        number = 1010;
        validate("one thousand ten", getNumbersToWords(number), number);
        number = -1010;
        validate("negative one thousand ten", getNumbersToWords(number), number);
        number = 1099;
        validate("one thousand ninety nine", getNumbersToWords(number), number);
        number = -1099;
        validate("negative one thousand ninety nine", getNumbersToWords(number), number);
        number = 1100;
        validate("one thousand one hundred", getNumbersToWords(number), number);
        number = -1100;
        validate("negative one thousand one hundred", getNumbersToWords(number), number);
        number = 1119;
        validate("one thousand one hundred nineteen", getNumbersToWords(number), number);
        number = -1119;
        validate("negative one thousand one hundred nineteen", getNumbersToWords(number), number);
        number = 10531;
        validate("ten thousand five hundred thirty one", getNumbersToWords(number), number);
        number = -10531;
        validate("negative ten thousand five hundred thirty one", getNumbersToWords(number), number);
        number = 99999;
        validate("ninety nine thousand nine hundred ninety nine", getNumbersToWords(number), number);
        number = -99999;
        validate("negative ninety nine thousand nine hundred ninety nine", getNumbersToWords(number), number);
        number = 805710;
        validate("eight hundred five thousand seven hundred ten", getNumbersToWords(number), number);
        number = -805710;
        validate("negative eight hundred five thousand seven hundred ten", getNumbersToWords(number), number);
        number = 305805710;
        validate("three hundred five million eight hundred five thousand seven hundred ten", getNumbersToWords(number), number);
        number = -305805710;
        validate("negative three hundred five million eight hundred five thousand seven hundred ten", getNumbersToWords(number), number);
        number = 999999999;
        validate("nine hundred ninety nine million nine hundred ninety nine thousand nine hundred ninety nine", getNumbersToWords(number), number);
        number = -999999999;
        validate("negative nine hundred ninety nine million nine hundred ninety nine thousand nine hundred ninety nine", getNumbersToWords(number), number);
        number = 1000000000;
        validate("one billion", getNumbersToWords(number), number);
        number = -1000000000;
        validate("negative one billion", getNumbersToWords(number), number);
    }
}
