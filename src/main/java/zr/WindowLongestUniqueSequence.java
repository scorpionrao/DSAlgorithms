package zr;

import java.util.HashSet;
import java.util.Set;

public class WindowLongestUniqueSequence {

    public static class Solution {

        public String longestCharSequence(String str) {
            if(str == null) {
                return null;
            }
            int maxLength = Integer.MIN_VALUE;
            String longestCharSequence = "";
            for(int startWindow = 0; startWindow < str.length(); startWindow++) {
                for(int endWindow = startWindow; endWindow < str.length(); endWindow++) {
                    // explain before hand.
                    Set<Character> set = new HashSet<>();
                    for(int i = startWindow; i <= endWindow; i++) {
                        if(!set.contains(str.charAt(i))) {
                            set.add(str.charAt(i));
                        } else {
                            // this window is disqualified
                            break;
                        }
                        // all unique characters
                        if(set.size() > maxLength) {
                            maxLength = set.size();
                            longestCharSequence = str.substring(startWindow, endWindow+1);
                        }
                    }
                }
            }
            return longestCharSequence;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String str = solution.longestCharSequence("arsivvxcbdpklrssq");
        System.out.println(String.format("Expected: %s, Actual: %s", "vxcbdpklrs", str));
    }
}
