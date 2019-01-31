package codecamp;

import java.util.*;

public class SentenceInList {

    /* Time: O(N), Space: O(N) */
    private static String[] sentenceInList(String[] dictionary, String sentence) {

        // O(1)
        Set<String> set = new HashSet<>();
        for(String word : dictionary) {
            set.add(word);
        }

        List<String> result = new ArrayList<>();
        int startIndex = 0;
        for(int i = 1; i <= sentence.length(); i++) {
            String candidate = sentence.substring(startIndex, i);
            if(set.contains(candidate)) {
                result.add(candidate);
                startIndex = i;
            }
        }
        String[] strs = new String[result.size()];
        for(int i = 0; i < result.size(); i++) {
            strs[i] = result.get(i);
        }
        return strs;
    }

    private static void evaluate(String[] dictionary, String sentence, String[] expected) {
        System.out.println("Dictionary : " + Arrays.toString(dictionary));
        System.out.println("Sentence : " + sentence);
        System.out.println("Expected : " + Arrays.toString(expected));
        String[] result = sentenceInList(dictionary, sentence);
        System.out.println("Actual : " + Arrays.toString(result));
    }

    public static void main(String[] args) {
        String[] dictionary1 = {"quick", "brown", "the", "fox"};
        String sentence1 = "thequickbrownfox";
        String[] expected1 = {"the", "quick", "brown", "fox"};
        evaluate(dictionary1, sentence1, expected1);

        String[] dictionary2 = {"bed", "bath", "bedbath", "and", "beyond"};
        String sentence2 = "bedbathandbeyond";
        String[] expected2 = {"bed", "bath", "and", "beyond"};
        // String[] expected2 = {"bedbath", "and", "beyond"};
        evaluate(dictionary2, sentence2, expected2);
    }
}
