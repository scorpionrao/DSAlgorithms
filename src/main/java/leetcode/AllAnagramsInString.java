package leetcode;

import java.util.*;

/*
    Collect starting indexes of anagrams of target in source string
 */
public class AllAnagramsInString {

    /*
        Find anagrams of T in S
     */
    public static List<Integer> findAnagrams2(String source, String target) {
        List<Integer> result = new ArrayList<>();
        if(source == null || target == null || source.length() < target.length()) {
            return result;
        }

        Map<Character, Integer> targetFrequency = new HashMap<>();
        for(char ch : target.toCharArray()) {
            targetFrequency.put(ch, targetFrequency.getOrDefault(ch, 0) + 1);
        }

        int windowStart = 0;
        int windowEnd = 0;
        int numOfUnindentifiedDistinctElements = targetFrequency.size();

        while(windowEnd < source.length()) {
            char chAtEnd = source.charAt(windowEnd);
            if(targetFrequency.containsKey(chAtEnd)) {
                targetFrequency.put(chAtEnd, targetFrequency.get(chAtEnd)-1);
                if(targetFrequency.get(chAtEnd) == 0) {
                    numOfUnindentifiedDistinctElements--;
                }
            }
            windowEnd++;

            while(numOfUnindentifiedDistinctElements == 0) {
                char chStart = source.charAt(windowStart);
                if(targetFrequency.containsKey(chStart)) {
                    targetFrequency.put(chStart, targetFrequency.get(chStart)+1);
                    if(targetFrequency.get(chStart) > 0) {
                        numOfUnindentifiedDistinctElements++;
                    }
                }
                if(target.length() == windowEnd - windowStart) {
                    result.add(windowStart);
                }
                windowStart++;
            }
        }
        return result;
    }

    public static List<Integer> findAnagrams1(String source, String target) {

        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < source.length() - target.length() + 1; i++) {
            String candidate = source.substring(i, i + target.length());
            if(isAnagram(candidate, target)) {
                result.add(i);
            }
        }
        return result;
    }

    private static boolean isAnagram(String candidate, String target) {

        if(candidate.length() != target.length()) {
            return false;
        }
        int[] freq = new int['z' - 'a' + 1];
        for(char ch : candidate.toCharArray()) {
            freq[ch - 'a']++;
        }

        for(char ch : candidate.toCharArray()) {
            if(freq[ch - 'a'] == 0) {
                return false;
            } else {
                freq[ch - 'a']--;
            }
        }
        return true;
    }

    private static char[] getFrequency(String str) {
        char[] charArray = new char['z' - 'a' + 1];
        for(char ch : str.toCharArray()) {
            charArray[ch - 'a']++;
        }
        return charArray;
    }

    private static void evaluate(String s, String p) {
        System.out.println("Input : " + s);
        System.out.println("Anagram : " + p);
        List<Integer> result1 = findAnagrams1(s, p);
        System.out.println("Starting Indexes Approach 1 : " + result1.toString());
        List<Integer> result2 = findAnagrams2(s, p);
        System.out.println("Starting Indexes Approach 2 : " + result2.toString());
    }

    public static void main(String[] args) {
        String s1 = "cbaebabacd";
        String p1 = "abc";
        evaluate(s1, p1);

        String s2 = "abab";
        String p2 = "ab";
        evaluate(s2, p2);

        String s3 = "baa";
        String p3 = "aa";
        evaluate(s3, p3);
    }

}
