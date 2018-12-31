package live;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class WordFrequency {

    /*
        Input : "the quick brown fox, jumps over the lazy dog"
        Output : the - 2, quick - 1, brown - 1, fox - 1, jumps - 1, over - 1, lazy - 1, dog - 1

        How is the input presented to you ?

        How will you get the individual words ?

        I will split the input with empty space as

        Go through the words and update the frequency in the map.

        What you need ? - A data structure to store word and its count.

        Time: O(N)
        Space: O(1)


     */

    public static Map<String, Integer> getFrequency(String input) {

        if(input == null) {
            return new HashMap<>();
        }

        Map<String, Integer> frequency = new LinkedHashMap<>();

        String[] words = input.split(" ");

        for(String word : words) {
            word = word.toLowerCase();
            if(frequency.containsKey(word)) {
                int currentCount = frequency.get(word);
                frequency.put(word, currentCount + 1);
            } else {
                frequency.put(word, 1);
            }
        }
        return frequency;
    }

    public static void main(String[] args) {
        getFrequency("The quick brown fox, jumps over the lazy dog");
    }
}
