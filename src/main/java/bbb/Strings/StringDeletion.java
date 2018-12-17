package bbb.Strings;

import java.util.*;

public class StringDeletion {

    private static List<String> generateCombinations(String word) {
        List<String> combinations = new ArrayList<>();
        generateCombinations(word, "", combinations);
        return combinations;
    }

    private static void generateCombinations(String remaining, String soFar, List<String> combinations) {
        if(remaining.isEmpty()) {
            combinations.add(soFar);
            return;
        }
        generateCombinations(remaining.substring(1), soFar + remaining.charAt(0), combinations);
        generateCombinations(remaining.substring(1), soFar, combinations);
    }

    /* Typical Backtrack example */
    private static int stringDeleteApproach1(String word, List<String> dictionary) {
        List<String> combinations = generateCombinations(word);
        for(String str : combinations) {
            if(dictionary.contains(str)) {
                return word.length() - str.length();
            }
        }
        return word.length();
    }

    public static void evaluate(String word, List<String> dictionary) {
        System.out.println("Dictionary : " + dictionary.toString());
        System.out.println("Word : " + word);
        int minLength = stringDeleteApproach1(word, dictionary);
        System.out.println("Approach1 : " + minLength);
    }

    public static void main(String[] args) {
        List<String> dictionary = Arrays.asList(new String[]{"a", "aa", "aaa"});
        evaluate("abc", dictionary);
    }
}
