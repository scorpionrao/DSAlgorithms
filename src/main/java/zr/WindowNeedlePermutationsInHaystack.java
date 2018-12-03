package zr;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class WindowNeedlePermutationsInHaystack {

    private static int numOfChars = 256;

    public static Set<String> searchAllPermutations(String S, String L) {

        /* end cases */
        if(S == null || S.isEmpty() || L == null || L.isEmpty()) {
            return new HashSet<>();
        }

        /* DS 1 - index is char value */
        char[] histogramStaticShortString = new char[numOfChars];
        char[] histogramShifterLongString = new char[numOfChars];

        /* O(S) */
        for(int i = 0; i < S.length(); i++) {
            /* O(1) */
            histogramStaticShortString[S.charAt(i)]++;
            histogramShifterLongString[L.charAt(i)]++;
        }

        Set<String> result = new LinkedHashSet<>();
        /* O(L) */
        for(int endWindow = S.length() - 1; endWindow < L.length() - 1; endWindow++) {
            if(histogramMatch(histogramStaticShortString, histogramShifterLongString)) {
                String candidate = L.substring(endWindow - S.length() + 1, endWindow + 1);
                result.add(candidate);
            }

            int removeIndex = endWindow - S.length() + 1;
            int addIndex = endWindow + 1;
            histogramShifterLongString[L.charAt(removeIndex)]--;
            histogramShifterLongString[L.charAt(addIndex)]++;

        }
        /* O(numOfChars) - Boundary Case */
        if(histogramMatch(histogramStaticShortString, histogramShifterLongString)) {
            String candidate = L.substring(L.length() - S.length());
            result.add(candidate);
        }
        return result;
    }

    private static boolean histogramMatch(char[] freqS, char[] freqW) {
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
        Set<String> result = searchAllPermutations(shortString, longString);
        System.out.println(result.toString());
    }
}
