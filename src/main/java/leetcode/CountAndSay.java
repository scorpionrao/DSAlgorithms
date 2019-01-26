package leetcode;

import java.util.ArrayList;
import java.util.List;

public class CountAndSay {

    private static List<String> countAndSay(String input, int numOfTimes) {

        List<String> result = new ArrayList<>();
        result.add(input);

        for(int i = 1; i < numOfTimes; i++) {
            input = countAndSay(input);
            result.add(input);
        }
        return result;
    }

    private static String countAndSay(String input) {

        StringBuilder sb = new StringBuilder();

        char currentChar = input.charAt(0);
        int count = 1;

        for(int i = 1; i < input.length(); i++) {
            if(input.charAt(i) == currentChar) {
                count++;
            } else {
                sb.append(count);
                sb.append(currentChar);
                currentChar = input.charAt(i);
                count = 1;
            }
        }

        sb.append(count);
        sb.append(currentChar);
        return sb.toString();
    }

    private static boolean isEqual(List<String> list1, List<String> list2) {

        if(list1.size() != list2.size()) {
            return false;
        }

        for(int i = 0; i < list1.size(); i++) {
            String str1 = list1.get(i);
            String str2 = list2.get(i);
            System.out.println(str1 + ", " + str2);
            if(!str1.equals(str2)) {
                return false;
            }
        }
        return true;
    }

    private static void evaluate(String input, List<String> expectedResult) {
        System.out.println("Input = " + input);
        List<String> actualResult = countAndSay(input, 10);
        boolean check = isEqual(expectedResult, actualResult);
        System.out.println("Check = " + check);
    }

    public static void main(String[] args) {

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("1");
        expectedResult.add("11");
        expectedResult.add("21");
        expectedResult.add("1211");
        expectedResult.add("111221");
        expectedResult.add("312211");
        expectedResult.add("13112221");
        expectedResult.add("1113213211");
        expectedResult.add("31131211131221");
        expectedResult.add("13211311123113112211");

        String input = "1";
        evaluate(input, expectedResult);

    }
}
