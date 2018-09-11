package zr;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class WindowNeedlePermutationsInHaystack {

    int numOfChars = 256;

    public Set<String> search(String S, String L) {

        /* end cases */
        if(S == null || S.isEmpty() || L == null || L.isEmpty()) {
            return new HashSet<>();
        }

        /* DS 1 - index is char value */
        char[] freqS = new char[numOfChars];
        char[] freqW = new char[numOfChars];

        /* O(S) */
        for(int i = 0; i < S.length(); i++) {
            /* O(1) */
            freqS[S.charAt(i)]++;
            freqW[L.charAt(i)]++;
        }

        Set<String> result = new LinkedHashSet<>();
        /* O(L) */
        for(int endWindow = S.length() - 1; endWindow < L.length() - 1; endWindow++) {
            if(match(freqS, freqW)) {
                String candidate = L.substring(endWindow - S.length() + 1, endWindow + 1);
                result.add(candidate);
            }

            int removeIndex = endWindow - S.length() + 1;
            int addIndex = endWindow + 1;
            freqW[L.charAt(removeIndex)]--;
            freqW[L.charAt(addIndex)]++;

        }
        /* O(numOfChars) */
        if(match(freqS, freqW)) {
            String candidate = L.substring(L.length() - S.length());
            result.add(candidate);
        }
        return result;
    }

    private boolean match(char[] freqS, char[] freqW) {
        /* O(numOfChars) */
        for(int j = 0; j < numOfChars; j++) {
            if(freqS[j] != freqW[j]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        String longString = "BACDGABCDA";
        String shortString = "ABCD";
        Set<String> result = new WindowNeedlePermutationsInHaystack().search(shortString, longString);
        System.out.println(result.toString());
    }
}
