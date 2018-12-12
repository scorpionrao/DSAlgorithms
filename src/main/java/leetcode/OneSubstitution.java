package leetcode;

import java.util.*;

/*
    Test Cases:
    NULL, "a"       - NullPointerException
    "a", NULL       - NullPointerException
    "a", ""         - false
    "", "a"         - false
    "b", "a"        - true

    Time Complexity: O(K) - length of word
*/

/*
    Example: vocab = ['apple', 'pineapple', 'banana', 'cucumber']
    singleTypo('adple') # True
    singleTypo('addle') # False
    singleTypo('aple') # False

    Test Cases:
    NULL, {"a"}     - NullPointerException
    "a", NULL       - NullPointerException
    "a", {}         - false
    "", {"a"}       - false
    "b", {"a"}      - true

    Time Complexity: O(N * K), N = Length of vocabulary, K = Avg length of word.
*/
public class OneSubstitution {

    /*
         Can target can be converted to word with <= 1 letter substitution ?

         oneSubstitutionAtleast("apple", "adple") --> True
         oneSubstitutionAtleast("apple", "addle") --> False

         Time Complexity: O(K)
         K --> Average size of word in vocabulary.
    */
    private boolean oneSubstitutionAtleast(String target, String word) {

        if(target.length() != word.length()) {
            return false;
        }

        for(int i = 0; i < target.length(); i++) {
            if(target.charAt(i) != word.charAt(i)) {
                return target.substring(i+1).equals(word.substring(i+1));
            }
        }
        // Same strings
        return true;
    }

    /* Time - O(K) */
    private boolean oneSubstitutionExactly(String target, String word) {

        if(target.length() != word.length()) {
            return false;
        }

        int numOfEdits = 0;
        for(int i = 0; i < target.length(); i++) {
            if(target.charAt(i) != word.charAt(i)) {
                numOfEdits++;
            }
            if(numOfEdits > 1) {
                return false;
            }
        }
        // Same strings return false
        return numOfEdits == 1 ? true : false;
    }


    /*
        Does the target word is single typo away from valid word in vocabulary ?

        vocab = ['apple', 'pineapple', 'banana', 'cucumber']
        singleTypo('adple', vocab) --> True
        singleTypo('addle', vocab) -->   False
        singleTypo('aple', vocab) --> False

        Time Complexity: O(Number of words *  length of word)
        N --> Size of list.
        K --> Average size of word in vocabulary.
   */
    public boolean singleTypo(String target, List<String> vocabulary) {

        if(target == null || vocabulary == null) {
            throw new NullPointerException("Input is null");
        }

        // O(N)
        for(String word : vocabulary) {
            // O(K)
            if(word != null && oneSubstitutionExactly(target, word)) {
                return true;
            }
        }
        return false;
    }

    /* Vocabulary static list. If this API becomes a service, avoid passing entire vocabulary in the request */
    Set<String> vocabulary = new HashSet<>(Arrays.asList("apple", "pineapple", "banana", "cucumber"));

    /* O(N * K) */
    public boolean singleTypoPreCompute(String target) {

        if(target == null) {
            throw new NullPointerException("Input is null");
        }
        // O(N)
        for(String word : vocabulary) {
            // O(K)
            if(word != null && oneSubstitutionExactly(target, word)) {
                return true;
            }
        }
        return false;
    }

    /*
        Assumptions: All characters are alphabets and lower case.

        Constant Time solution: m * 25.
     */
    public boolean singleTypoPreComputeConstantTime(String target) {

        if(target == null) {
            throw new NullPointerException("Input is null");
        }

        char[] alphabets = new char[26];
        for(int i = 0; i < alphabets.length; i++) {
            alphabets[i] = (char) ('a' + i);
        }

        // avoid string concatenation
        char[] inputArray = target.toCharArray();

        /* m * 26 */
        for(int i = 0; i < target.length(); i++) {
            for(char replacement : alphabets) {
                if(replacement != target.charAt(i)) {
                    inputArray[i] = replacement;
                    String candidate = String.valueOf(inputArray);
                    // O(1)
                    if (vocabulary.contains(candidate)) {
                        return true;
                    }
                    // revert
                    inputArray[i] = target.charAt(i);
                }
            }
        }
        return false;
    }

    /* Time Complexity: O(MaxLength(S1, S2)) */
    private boolean oneEditOptimization1(String str1, String str2) {

        if(str2.length() - str1.length() > 1) {
            return false;
        }

        int counter = 0;
        int numOfEdits = 0;
        int i = 0, j = 0;
        while(i < str1.length() && j < str2.length()) {
            counter++;
            if(str1.charAt(i) != str2.charAt(j)) {
                numOfEdits++;
                if(numOfEdits > 1) {
                    System.out.println("Counter: " + counter);
                    return false;
                }
                if(str1.length() > str2.length()) {
                    // remove character solution
                    i++;
                } else if(str1.length() < str2.length()) {
                    // remove character solution
                    j++;
                } else {
                    // replace character solution
                    i++;
                    j++;
                }
            } else {
                i++;
                j++;
            }
        }
        System.out.println("Counter: " + counter);
        return true;
    }

    /* Time Complexity: O(S1 * S2) */
    private boolean editDistanceNaive(String str1, String str2) {
        int counter = 0;
        int[][] dp = new int[str1.length()+1][str2.length()+1];
        for(int i = 0; i <= str1.length(); i++) {
            for(int j = 0; j <= str2.length(); j++) {
                counter++;
                if(i == 0) {
                    dp[i][j] = j;
                } else if(j == 0) {
                    dp[i][j] = i;
                } else if(str1.charAt(i-1) == str2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j]));
                }
            }
        }
        System.out.println("Counter: " + counter);
        return dp[str1.length()][str2.length()] < 2;
    }

    private void evaluate(String str1, String str2) {
        System.out.println(String.format("String1: %s, String2: %s", str1, str2));
        System.out.println("Naive: " + editDistanceNaive(str1, str2));
        System.out.println("Optimization1: " + oneEditOptimization1(str1, str2));
        System.out.println("Optimization2: " + oneSubstitutionAtleast(str1, str2));
        System.out.println("*******************************************");
    }

    public static void main(String[] args) {
        OneSubstitution oneSubstitution = new OneSubstitution();
        System.out.println("#adple: " + oneSubstitution.singleTypoPreComputeConstantTime("adple"));
        System.out.println("#addle: " + oneSubstitution.singleTypoPreComputeConstantTime("addle"));
        System.out.println("#aple: " + oneSubstitution.singleTypoPreComputeConstantTime("aple"));
    }
}
