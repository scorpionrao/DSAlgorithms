package codecamp;

import java.util.*;

public class MinInsertionsToPalindrome {

    /*
        minInsertionsRecursion(str[l to h]) = {
            if(str[l] == str[h]) -> minInsertionsRecursion(str[l+1 to h-1])
            else -> 1 + Math.min(minInsertionsRecursion(str[l to h-1]), minInsertionsRecursion(str[l+1 to h]))
        }
     */
    public static int minInsertionsRecursion(String str) {
        List<String> result = new ArrayList<>();
        int minInsertions = minInsertionsRecursion(str, 0, str.length()-1, "", result);
        System.out.println(result.toString());

        Collections.sort(result, new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                if(str1.length() > str2.length()) {
                    return 1;
                } else if(str1.length() < str2.length()) {
                    return -1;
                } else {
                    return str1.compareTo(str2);
                }
            }
        });

        System.out.println("First Palindrome : " + result.get(0));

        return minInsertions;
    }

    private static int minInsertionsRecursion(String str, int left, int right, String prefix, List<String> result) {
        if(left > right ) {
            String tail = prefix + new StringBuilder(prefix).reverse().toString();
            result.add(tail);
            return 0;
        } else if(left == right) {
            String tail = prefix + str.charAt(left) + new StringBuilder(prefix).reverse().toString();
            result.add(tail);
            return 0;
        }
        if(str.charAt(left) == str.charAt(right)) {
            return minInsertionsRecursion(str, left+1, right-1, prefix + str.charAt(left), result);
        } else {
            int ignoreLeft = minInsertionsRecursion(str, left+1, right, prefix + str.charAt(left), result);
            int ignoreRight = minInsertionsRecursion(str, left, right-1, prefix + str.charAt(right), result);
            return 1 + Math.min(ignoreLeft, ignoreRight);
        }
    }

    /*
                                                                             RACE

                                 (E) RAC (E)                                                                   (R) ACE (R)


               (EC) RA (CE)                         (ER) AC (RE)                             (RA) CE (AR)                      (RE) AC (ER)


       (ECR) A (RCE)   **(ECA) R (ACE)**         (ERA) C (ARE)    (ERC) A (CRE)            (RAC) E (CAR)   (RAE) C (EAR)      (REA) C (AER)    (REC) A (CER)



     */
    public static int minInsertionsDP(String str) {
        int[][] dp = new int[str.length()][str.length()];
        for(int gap = 1; gap < str.length(); gap++) {
            for(int left = 0, right = left + gap; right < str.length(); left++, right++) {
                if(str.charAt(left) == str.charAt(right)) {
                    dp[left][right] = dp[left+1][right-1];
                } else {
                    dp[left][right] = 1 + Math.min(dp[left][right-1],
                                                   dp[left+1][right]);
                }
            }
        }
        return dp[0][str.length()-1];
    }

    public static void evaluate(String str) {
        System.out.println("Input : " + str);
        int result1 = minInsertionsRecursion(str);
        System.out.println("Approach 1 - Recursion - " + result1);
        int result2 = minInsertionsDP(str);
        System.out.println("Approach 2 - DP - " + result2);
    }

    public static void main(String[] args) {
        evaluate("race");
        evaluate("google");
        evaluate("geeks");
    }
}
