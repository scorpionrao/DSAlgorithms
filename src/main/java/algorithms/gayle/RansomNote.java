package algorithms.gayle;

import java.util.Arrays;

public class RansomNote {

    public static class Solution {

        /*
            Solution:
         */
        public boolean canBuild(String ransomNote, String magazine) {

            if(ransomNote == null || magazine == null) {
                throw new IllegalArgumentException("Input is null");
            } else if (ransomNote.isEmpty()) {
                return true;
            } else if (magazine.isEmpty()) {
                return false;
            } else if (ransomNote.length() > magazine.length()) {
                return false;
            }

            int MAX_CHARS = 256;
            int[] ransomHistogram = new int[MAX_CHARS];
            Arrays.fill(ransomHistogram, 0);

            for(int i = 0; i < ransomNote.length(); i++) {
                ransomHistogram[ransomNote.charAt(i)]++;
            }

            int counter = 0;
            for(int i = 0; i < magazine.length(); i++) {
                char ch = magazine.charAt(i);
                if(ransomHistogram[ch] == ' ' || ransomHistogram[ch] == 0) {
                    continue;
                } else if(ransomHistogram[ch] > 0) {
                    ransomHistogram[ch]--;
                    counter++;
                    if(counter == ransomNote.length()) {
                        // This avoids scanning entire array for zero frequencies
                        return true;
                    }
                }
            }
            return false;
        }

    }

    public static void main(String[] args) {
        String ransomNote = "god is great";
        String magazine = "dogs are beautiful greeters";

        System.out.println("Ransom Note : " + ransomNote);
        System.out.println("Magazine Note: " + magazine);

        Solution solution = new Solution();
        System.out.println(solution.canBuild(ransomNote, magazine));
    }
}