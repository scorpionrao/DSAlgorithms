package bbb.Strings;

import java.util.*;

public class StringDeletion {

    private static Set<String> generateCombinationsRecurse(String word) {
        Set<String> combinations = new LinkedHashSet<>();
        generateCombinationsRecurse(word, "", combinations);
        return combinations;
    }

    private static void generateCombinationsRecurse(String remaining, String soFar, Set<String> combinations) {
        if(remaining.isEmpty()) {
            System.out.println(soFar);
            combinations.add(soFar);
            return;
        }
        // BFS
        generateCombinationsRecurse(remaining.substring(1), soFar + remaining.charAt(0), combinations);
        generateCombinationsRecurse(remaining.substring(1), soFar, combinations);
    }

    /* Typical Backtrack example */
    private static int stringDeleteApproach1(String word, List<String> dictionary) {
        return getMinimumWordLengthDifference(word, dictionary, generateCombinationsRecurse(word));
    }

    private static int getMinimumWordLengthDifference(String word, List<String> dictionary, Set<String> combinations) {
        for(String str : combinations) {
            if(dictionary.contains(str)) {
                return word.length() - str.length();
            }
        }
        // Can be -1
        return word.length();
    }

    private static Set<String> generateCombinationsIterate(String word) {

        Set<String> set = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(word);
        while(!queue.isEmpty()) {
            String node = queue.poll();
            set.add(node);
            for(int i = 0; i < node.length(); i++) {
                String nextLevelNode = node.substring(0, i) + node.substring(i+1);
                queue.add(nextLevelNode);
            }
        }
        return set;
    }

    private static int stringDeleteApproach2(String word, List<String> dictionary) {
        return getMinimumWordLengthDifference(word, dictionary, generateCombinationsIterate(word));
    }


    public static void evaluate(String word, List<String> dictionary) {
        System.out.println("Dictionary : " + dictionary.toString());
        System.out.println("Word : " + word);
        int minLength1 = stringDeleteApproach1(word, dictionary);
        System.out.println("Approach1 : " + minLength1);
        int minLength2 = stringDeleteApproach2(word, dictionary);
        System.out.println("Approach2 : " + minLength2);
    }

    public static void main(String[] args) {
        List<String> dictionary = Arrays.asList(new String[]{"a", "aa", "aaa"});
        evaluate("abc", dictionary);
    }
}