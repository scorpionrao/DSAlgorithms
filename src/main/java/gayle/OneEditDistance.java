package gayle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
public class OneEditDistance {


    private boolean oneSubstitution(String target, String word) {

        if(target == null || word == null) {
            return false;
        }

        if(target.length() != word.length()) {
            return false;
        }

        for(int i = 0; i < target.length(); i++) {
            if(target.charAt(i) != word.charAt(i)) {
                return target.substring(i+1).equals(word.substring(i+1));
            }
        }
        return true;
    }


    public boolean singleTypo(String target, List<String> vocabulary) {

        if(target == null || vocabulary == null) {
            return false;
        }

        for(String word : vocabulary) {
            if(word != null && oneSubstitution(target, word)) {
                return true;
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
        System.out.println("Optimization2: " + oneSubstitution(str1, str2));
        System.out.println("*******************************************");
    }

    public static void main(String[] args) {
        OneEditDistance oneEditDistance = new OneEditDistance();
        List<String> vocabulary = Arrays.asList("apple", "pineapple", "banana", "cucumber");
        System.out.println("#adple: " + oneEditDistance.singleTypo("adple", vocabulary));
        System.out.println("#addle: " + oneEditDistance.singleTypo("addle", vocabulary));
        System.out.println("#aple: " + oneEditDistance.singleTypo("aple", vocabulary));

        List<String> vocabulary1 = new ArrayList<>();
        System.out.println("#a: " + oneEditDistance.singleTypo("a", vocabulary1));
        vocabulary1.add("a");
        System.out.println("#empty: " + oneEditDistance.singleTypo("", vocabulary1));
        System.out.println("#b: " + oneEditDistance.singleTypo("b", vocabulary1));



        /*oneEditDistance.evaluate("a", "b");
        oneEditDistance.evaluate("a", "ab");
        oneEditDistance.evaluate("ac", "ab");
        oneEditDistance.evaluate("bc", "ac");
        oneEditDistance.evaluate("bc", "ad");*/
    }
}
