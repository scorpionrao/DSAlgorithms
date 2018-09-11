package zr;

import java.util.LinkedHashSet;
import java.util.Set;

public class WindowNeedlePermutationsInHaystack {

    public static Set<String> search(String S, String L) {

        int numOfChars = 256;

        char[] freqS = new char[numOfChars];
        char[] freqW = new char[numOfChars];

        for(int i = 0; i < S.length(); i++) {
            freqS[S.charAt(i)]++;
            freqW[L.charAt(i)]++;
        }

        Set<String> result = new LinkedHashSet<>();

        for(int endWindow = S.length() - 1; endWindow < L.length(); endWindow++) {
            boolean success = true;
            for(int j = 0; j < numOfChars; j++) {
                if(freqS[j] != freqW[j]) {
                    success = false;
                    break;
                }
            }
            if(success) {
                String candidate = L.substring(endWindow - S.length() + 1, endWindow + 1);
                result.add(candidate);
            }

            freqW[L.charAt(endWindow - S.length() + 1)]--;
            freqW[L.charAt(endWindow)]++;

        }

        return result;
    }

    public static void main(String[] args) {

        String longString = "BACDGABCDA";
        String shortString = "ABCD";
        Set<String> result = search(shortString, longString);
        System.out.println(result.toString());
    }
}
