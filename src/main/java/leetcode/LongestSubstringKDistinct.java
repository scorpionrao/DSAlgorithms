package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubstringKDistinct {

    /* Time: O(N^2), Space: O(N) */
    private static int longestSubstringKDistinctApproach1(String input, int distinctCount) {

        int maxLength = 0;

        for(int i = 0; i < input.length(); i++) {
            for(int j = i; j < input.length(); j++) {
                String candidate = input.substring(i, j+1);
                Set<Character> set = new HashSet<>();
                for(char ch : candidate.toCharArray()) {
                    set.add(ch);
                }
                if(set.size() <= distinctCount) {
                    maxLength = Math.max(maxLength, j - i + 1);
                }
            }
        }
        return maxLength;
    }


    /* Time: O(N), Space: O(N) */
    private static int longestSubstringKDistinctApproach2(String input, int distinctCount) {

        int maxLength = 0;

        int start = 0;
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < input.length(); i++) {
            if(!map.containsKey(input.charAt(i))) {
                map.put(input.charAt(i), 0);
            }
            map.put(input.charAt(i), map.get(input.charAt(i)) + 1);

            while(map.size() > distinctCount) {
                map.put(input.charAt(start), map.get(input.charAt(start)) - 1);
                if(map.get(input.charAt(start)) == 0) {
                    map.remove(input.charAt(start));
                }
                start++;
            }
            maxLength = Math.max(maxLength, i - start + 1);
        }
        return maxLength;
    }

    private static void evaluate(String input, int distinctCount) {
        System.out.println("Input = " + input);
        int result1 = longestSubstringKDistinctApproach1(input, distinctCount);
        System.out.println("Approach1 = E: 3, A: " + result1);
        int result2 = longestSubstringKDistinctApproach2(input, distinctCount);
        System.out.println("Approach2 = E: 3, A: " + result2);
    }

    public static void main(String[] args) {
        String input = "abcba";
        int distinctCount = 2;
        evaluate(input, distinctCount);
    }
}
