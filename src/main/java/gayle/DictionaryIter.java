package gayle;

import java.util.*;

/*
    Write a program to compute all preprocessedDictionaryOrAnagrams of a given word
    from a dictionary.
 */
public class DictionaryIter {

    private String[] dictionary = {/* all words */};

    private Map<Integer, Map<String, Set<String>>> preprocessedLengthToAnagrams;

    /*
        Pre-compute dictionary into buckets.

        Potential drawbacks - NULL values
     */
    public void buildMap() {
        // can be in constructor or here
        preprocessedLengthToAnagrams = new HashMap<>();

        for(int i = 0; i < dictionary.length; i++) {

            char[] array = dictionary[i].toCharArray();
            Arrays.sort(array);
            String signatureOrUniqueKeyOrIndex = new String(array);

            Map<String, Set<String>> currentLengthMap;
            if(preprocessedLengthToAnagrams.containsKey(signatureOrUniqueKeyOrIndex.length())) {
                currentLengthMap = preprocessedLengthToAnagrams.get(signatureOrUniqueKeyOrIndex.length());
            } else {
                currentLengthMap = new HashMap<>();
                preprocessedLengthToAnagrams.put(signatureOrUniqueKeyOrIndex.length(), currentLengthMap);
            }

            Set<String> currentSet;
            if(currentLengthMap.containsKey(signatureOrUniqueKeyOrIndex)) {
                currentSet = currentLengthMap.get(signatureOrUniqueKeyOrIndex);
            } else {
                currentSet = new HashSet<>();
                currentLengthMap.put(signatureOrUniqueKeyOrIndex, currentSet);
            }
            currentSet.add(dictionary[i]);
        }
    }

    public Set<String> getAnagrams(String word) {

        char[] array = word.toCharArray();
        Arrays.sort(array);
        String signatureOrUniqueKeyOrIndex = new String(array);

        return this.preprocessedLengthToAnagrams.get(signatureOrUniqueKeyOrIndex.length()).get(signatureOrUniqueKeyOrIndex);
    }
    
}
