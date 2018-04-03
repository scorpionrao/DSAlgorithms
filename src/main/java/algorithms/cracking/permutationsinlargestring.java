package algorithms.cracking;

import java.util.HashMap;
import java.util.Map;

public class permutationsinlargestring {

    Map<Character, Integer> map = new HashMap();

    private boolean getIndexesOfPermutations(String largeString, String shortString) {

        for(int i = 0; i < shortString.length(); i++) {
            int currentCount = map.getOrDefault(shortString.charAt(i), 0);
            map.put(shortString.charAt(i), currentCount + 1);
        }
        for(int i = 0; i < largeString.length() - shortString.length() + 1; i++) {
            String window = largeString.substring(i, i + shortString.length());
            Map<Character, Integer> subStringMap = new HashMap();
            for(int j = 0; j < shortString.length(); j++) {
                int currentCount = subStringMap.getOrDefault(window.charAt(i), 0);
                subStringMap.put(window.charAt(i), currentCount + 1);
                if (matches(subStringMap, map)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean matches(Map<Character, Integer> map1, Map<Character, Integer> map2) {
        for(Character c : map1.keySet()) {
            if(map1.get(c) - map2.get(c) != 0) {
                return false;
            }
        }
        return true;
    }

    private boolean arraySolution(String largeString, String shortString) {

        // array with keys and frequencies
        int[] array1 = new int[26];
        int[] array2 = new int[26];
        for(int i = 0; i < shortString.length(); i++) {
            int index = shortString.charAt(i) - 'a';
            array1[index]++;
        }
        for(int i = 0; i < largeString.length() - shortString.length() + 1; i++) {
            String window = largeString.substring(i, i + shortString.length());
            for(int j = 0; j < window.length(); j++) {
                array2[largeString.charAt(i + shortString.length()) - 'a']++;
                array2[largeString.charAt(i) - 'a']--;
            }
            if (matches(array1, array2)) {
                return true;
            }
        }
        return false;
    }

    private boolean matches(int[] array1, int[] array2) {
        for(int i = 0; i < array1.length; i++) {
            if(array1[i] != array2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String shortString = "abbca";
        String largeString = "cbabadcbbabbcbabaabccbabc";
        System.out.println(new permutationsinlargestring().arraySolution(largeString, shortString));
    }
}
