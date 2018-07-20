package zr;

import java.util.HashSet;
import java.util.Set;

public class WindowLongestUniqueSequence {

    public static class Solution {

        public String longestCharSequence(String str) {
            if(str == null || str.isEmpty() || str.length() == 1) {
                return str;
            }
            int startIndex = 0;
            int endIndex = 0;
            int maxLength = endIndex - startIndex + 1;
            for(int startWindow = 0; startWindow < str.length(); startWindow++) {
                for(int endWindow = startWindow; endWindow < str.length(); endWindow++) {
                    if(maxLength >= (endWindow - startWindow + 1)) {
                        continue;
                    }
                    Set<Character> charSet = new HashSet<>();
                    for (int i = startWindow; i <= endWindow; i++) {
                        charSet.add(str.charAt(i));
                    }
                    if(charSet.size() == (endWindow - startWindow + 1)) {
                        // all characters are unique
                        if(charSet.size() > maxLength) {
                            // improves from previous best
                            startIndex = startWindow;
                            endIndex = endWindow;
                            maxLength = endIndex - startIndex + 1;
                        }
                    } else {
                        // duplicate found. No need to increase the endWindow.
                        break;
                    }
                }
            }
            return str.substring(startIndex, endIndex+1);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String str = solution.longestCharSequence("arsivvxcbdpklrssq");
        System.out.println(String.format("Expected: %s, Actual: %s", "vxcbdpklrs", str));
    }
}
