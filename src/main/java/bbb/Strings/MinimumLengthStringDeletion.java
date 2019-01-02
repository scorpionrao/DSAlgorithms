package bbb.Strings;

import java.util.*;

public class MinimumLengthStringDeletion {

    private static Set<String> generateCombinationsRecurse(String word) {
        Set<String> combinations = new LinkedHashSet<>();
        generateCombinationsRecurse(word, "", combinations);
        return combinations;
    }

    /* Time : O(2 ^ N), Space : O(2 ^ N) */
    private static void generateCombinationsRecurse(String remaining, String soFar, Set<String> combinations) {
        if(remaining.isEmpty()) {
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

    private static String stringDeleteApproach3(String input, List<String> dictionary) {
        Collections.sort(dictionary, new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                return (str1.length() != str2.length()) ? str2.length() - str1.length() : str1.compareTo(str2);
            }
        });
        return getLongestWordWithDeletionSortedDictionary(input, dictionary);
    }

    private static String getLongestWordWithDeletionSortedDictionary(String input, List<String> dictionary) {
        for(String word : dictionary) {
            if (isSubSequence(input, word)) {
                return word;
            }
        }
        return "";
    }

    public String getLongestWordWithDeletionUnsortedDictionary(String s, List<String> d) {
        if (d.isEmpty()) return "";
        String res = "";
        for (String word : d) {
            if (isSubSequence(s, word) &&
                    (word.length() > res.length() || (word.length() == res.length() && word.compareTo(res) < 0))) {
                res = word;
            }
        }
        return res;
    }

    private static boolean isSubSequence(String input, String word) {
        int i = -1;
        for (char ch : word.toCharArray()) {
            i = input.indexOf(ch, i + 1);
            if (i < 0) {
                return false;
            }
        }
        return true;
    }

    public static void evaluate(String word, List<String> dictionary) {
        System.out.println("Dictionary : " + dictionary.toString());
        System.out.println("Input : " + word);
        int minLength1 = stringDeleteApproach1(word, dictionary);
        System.out.println("Approach1 : " + minLength1);
        int minLength2 = stringDeleteApproach2(word, dictionary);
        System.out.println("Approach2 : " + minLength2);
        String str3 = stringDeleteApproach3(word, dictionary);
        System.out.println("Approach3 : " + str3);
    }

    public static void main(String[] args) {
        List<String> dictionary = Arrays.asList(new String[]{"a", "aa", "aaa"});
        evaluate("abc", dictionary);
        evaluate("abpcplea", Arrays.asList(new String[]{"ale", "apple", "monkey", "plea"}));
        evaluate("abpcplea", Arrays.asList(new String[]{"a", "b", "c"}));
    }
}