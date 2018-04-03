package algorithms.cracking;

import java.util.HashMap;
import java.util.Map;

public class LongestSubSequence {

    public static int lengthOfLongestSubstring(String originalString) {
        int length = originalString.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int rightIndex = 0, leftIndex = 0; rightIndex < length; rightIndex++) {
            char ch = originalString.charAt(rightIndex);
            System.out.println(map);
            if (map.containsKey(ch)) {
                int currentValue = map.get(ch);
                leftIndex = Math.max(currentValue, leftIndex);
            }
            int possibleAnswer = rightIndex - leftIndex + 1;
            ans = Math.max(ans, possibleAnswer);
            map.put(originalString.charAt(rightIndex), rightIndex + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("analogous"));
    }
}
