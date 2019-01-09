package leetcode;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinations {

    private static List<String> letterCombinations(String input) {
        List<String> result = new ArrayList<>();
        letterCombinations(input, "", result);
        return result;
    }

    private static void letterCombinations(String remaining, String soFar, List<String> result) {
        if(remaining.isEmpty()) {
            result.add(soFar);
            return;
        }

        letterCombinations(remaining.substring(1), soFar + convert(remaining, 1), result);
        if(remaining.length() > 1) {
            letterCombinations(remaining.substring(2), soFar + convert(remaining, 2), result);
        }
    }

    private static char convert(String str, int numChar) {
        return (char) (Integer.valueOf(str.substring(0, numChar)) + 'a' - 1);
    }

    private static void evaluate(String input) {
        System.out.println("Input = " + input);
        List<String> result = letterCombinations(input);
        System.out.println(result.toString());
    }

    public static void main(String[] args) {
        evaluate("111");
        evaluate("123");
    }
}
