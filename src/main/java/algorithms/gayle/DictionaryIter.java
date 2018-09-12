package algorithms.gayle;

import java.util.*;

/*
    Write a program to compute all preprocessedDictionaryOrAnagrams of a given word
    from a dictionary.
 */
public class DictionaryIter {

    private String[] dictionary = {/* all words */};

    private Map<String, Set<String>> preprocessedDictionaryOrAnagrams;

    /*
        Pre-compute dictionary into buckets.

        Potential drawbacks - NULL values
     */
    public void buildMap() {
        // can be in constructor or here
        preprocessedDictionaryOrAnagrams = new HashMap<>();

        for(int i = 0; i < dictionary.length; i++) {

            char[] array = dictionary[i].toCharArray();
            Arrays.sort(array);
            String signatureOrUniqueKeyOrIndex = new String(array);

            Set<String> currentSet;
            if(preprocessedDictionaryOrAnagrams.containsKey(signatureOrUniqueKeyOrIndex)) {
                currentSet = preprocessedDictionaryOrAnagrams.get(signatureOrUniqueKeyOrIndex);
            } else {
                currentSet = new HashSet<>();
                preprocessedDictionaryOrAnagrams.put(signatureOrUniqueKeyOrIndex, currentSet);
            }
            currentSet.add(dictionary[i]);
        }
    }

    public Set<String> getAnagrams(String word) {
        char[] array = word.toCharArray();
        Arrays.sort(array);
        String signatureOrUniqueKeyOrIndex = new String(array);

        return this.preprocessedDictionaryOrAnagrams.get(signatureOrUniqueKeyOrIndex);
    }
    
}
