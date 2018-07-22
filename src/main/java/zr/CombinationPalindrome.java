package zr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationPalindrome {

    /*
        Input: List<String>
        Output: [{String 1, String 2}]

        for each pair {
            concatenate
            isPalindrome
            add it to result
        }
     */

    public static class Result {
        private String str1;
        private String str2;

        public Result(String str1, String str2) {
            this.str1 = str1;
            this.str2 = str2;
        }

        public String getStr1() {
            return this.str1;
        }

        public String getStr2() {
            return this.str2;
        }
    }

    public static class Solution {

        public List<Result> getResult(List<String> inputs) {
            List<Result> results = new ArrayList();
            for(int i = 0; i < inputs.size(); i++) {
                for(int j = i+1; j < inputs.size(); j++) {
                    if(isPalindrome(inputs.get(i), inputs.get(j))) {
                        results.add(new Result(inputs.get(i), inputs.get(j)));
                    }
                }
            }
            return results;
        }

        public boolean isPalindrome(String str1, String str2) {
            if(str1 == null || str2 == null) {
                return false;
            }
            String combinedString = str1 + str2;

            for(int i = 0; i < combinedString.length() / 2; i++) {
                char leftChar = combinedString.charAt(i);
                char rightChar = combinedString.charAt(combinedString.length() - i - 1);
                if(leftChar != rightChar) {
                    return false;
                }
            }
            return true;
        }

        public void print(List<Result> results) {
            for(Result result : results) {
                System.out.println(String.format("{\"%s\", \"%s\"}", result.getStr1(), result.getStr2()));
            }
        }
    }

    public static void main(String[] args) {
        List<String> inputs = Arrays.asList("abc", "ba", "ab", "cba");
        Solution solution = new Solution();
        List<Result> results = solution.getResult(inputs);
        solution.print(results);
    }
}
