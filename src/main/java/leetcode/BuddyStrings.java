package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BuddyStrings {

    public static boolean buddyStrings(String A, String B) {
        if(A.length() != B.length()) {
            return false;
        }

        int a = -1, b = -1;
        boolean hasDup = false;
        int[] freq = new int['z' - 'a' + 1];
        for (int i = 0; i < A.length(); ++i) {
            freq[A.charAt(i) - 'a']++;
            if(freq[A.charAt(i) - 'a'] == 2) {
                hasDup = true;
            }
            if (A.charAt(i) != B.charAt(i)) {
                if(a == -1) {
                    a = i;
                } else if(b == -1) {
                    b = i;
                } else {
                    return false;
                }
            }
        }
        return (a == -1 && b == -1 && hasDup) ||
                (a != -1 && b != -1 && A.charAt(a) == B.charAt(b) && A.charAt(b) == B.charAt(a));
    }

    public static int numSimilarGroups(String[] A) {
        int[] groupIndex = new int[A.length];
        int distinctGroups = 1;
        groupIndex[0] = distinctGroups;
        for(int i = 1; i < A.length; i++) {
            int j = 0;
            for(; j < i; j++) {
                if(isSimilar(A[i], A[j])) {
                    groupIndex[i] = groupIndex[j];
                    break;
                }
            }
            if(j == i) {
                groupIndex[i] = ++distinctGroups;
            }
        }
        System.out.println(Arrays.toString(groupIndex));
        return distinctGroups;
    }

    public static boolean isSimilar(String str1, String str2) {
        int firstDiscrepancyIndex = -1;
        int secondDiscrepancyIndex = -1;
        boolean hasDups = false;
        Set<Character> set = new HashSet<>();
        for(int i = 0; i < str1.length(); i++) {
            if(set.contains(str1.charAt(i))) {
                hasDups = true;
            } else {
                set.add(str1.charAt(i));
            }
            if(str1.charAt(i) != str2.charAt(i)) {
                if(firstDiscrepancyIndex == -1) {
                    firstDiscrepancyIndex = i;
                } else if(secondDiscrepancyIndex == -1) {
                    secondDiscrepancyIndex = i;
                } else {
                    return false;
                }
            }
        }

        boolean checkWithDups = firstDiscrepancyIndex == -1 && secondDiscrepancyIndex == -1 && hasDups;

        boolean checkWithoutDups = firstDiscrepancyIndex != -1 && secondDiscrepancyIndex != -1 &&
                str1.charAt(firstDiscrepancyIndex) == str2.charAt(secondDiscrepancyIndex) &&
                str1.charAt(secondDiscrepancyIndex) == str2.charAt(firstDiscrepancyIndex);

        return checkWithDups || checkWithoutDups;
    }

    public static void main(String[] args) {
        String[] input = {"kccomwcgcs", "socgcmcwkc", "sgckwcmcoc", "coswcmcgkc", "cowkccmsgc",
                          "cosgmccwkc", "sgmkwcccoc", "coswmccgkc", "kowcccmsgc", "kgcomwcccs"};
        System.out.println(Arrays.toString(input));
        int result = numSimilarGroups(input);
        System.out.println(String.format("Expected = 5, Actual = " + result));
    }
}
