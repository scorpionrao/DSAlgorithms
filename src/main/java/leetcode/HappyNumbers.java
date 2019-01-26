package leetcode;

public class HappyNumbers {

    public static boolean isHappyIterate(int n) {

        int slow = n, fast = n;
        do {
            slow = findReplacement(slow);
            fast = findReplacement(fast);
            fast = findReplacement(fast);
        } while(slow != fast);
        return slow == 1;

    }

    private static int findReplacement(int input) {
        int replacement = 0;
        while(input > 0) {
            int digit = input % 10;
            replacement = replacement + digit * digit;
            input = input / 10;
        }
        return replacement;
    }

    public static boolean isHappyRecurse(int n) {

        int replacement = findReplacement(n);
        if(replacement == 1) {
            return true;
        } else if(replacement <= 4) {
            // why 4 is not happy ?
            return false;
        }
        return isHappyRecurse(replacement);
    }

    private static void evaluate(int input) {
        System.out.println("Input = " + input);
        boolean result1 = isHappyIterate(input);
        System.out.println("Approach 1 : " + result1);
        boolean result2 = isHappyRecurse(input);
        System.out.println("Approach 2 : " + result2);
    }

    public static void main(String[] args) {
        evaluate(19);
        evaluate(Integer.MAX_VALUE);
    }
}
