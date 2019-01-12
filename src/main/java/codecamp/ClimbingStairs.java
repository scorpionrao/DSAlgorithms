package codecamp;

public class ClimbingStairs {
    /*
        For example, if N is 4, then there are 5 unique ways:

        1, 1, 1, 1
        2, 1, 1
        1, 2, 1
        1, 1, 2
        2, 2
     */

    /*
        Top Down
        Time: O(2^N)
        Space: O(N)
    */
    private static int climbStairs1(int input) {
        if(input < 0) {
            return 0;
        }
        if(input == 0) {
            return 1;
        }
        return climbStairs1(input - 1) + climbStairs1(input - 2);
    }

    /*
        Bottom Up
        Time: O(2^N)
        Space: O(N)
    */
    private static int climbStairs2(int input) {
        return climbStairs2(0, input);
    }

    private static int climbStairs2(int start, int input) {
        if(start > input) {
            return 0;
        }
        if(start == input) {
            return 1;
        }
        return climbStairs2(start+1, input) + climbStairs2(start+2, input);
    }

    /*
        Top Down with Memoization
        Time: O(N)
        Space: O(N)
    */
    private static int climbStairs3(int input) {
        int[] cache = new int[input+1];
        climbStairs3(input, cache);
        return cache[input];
    }

    private static int climbStairs3(int input, int[] cache) {
        if(input < 0) {
            return 0;
        }
        if(input == 0) {
            return 1;
        }
        if(cache[input] != 0) {
            return cache[input];
        }

        cache[input] = climbStairs3(input-1, cache) + climbStairs3(input-2, cache);
        return cache[input];
    }

    /*
        Time: O(N)
     */
    public static int climbStairs4(int input) {
        // (1) -> { {1} }
        // (2) -> { {1,1}, {2} }
        if(input == 1 || input == 2) {
            return input;
        }

        int oneResult = 1;
        int twoResult = 2;
        int result = 0;
        for(int i = 3; i <= input; i++) {
            result = oneResult + twoResult;
            oneResult = twoResult;
            twoResult = result;
        }
        return result;
    }

    private static void evaluate(int input) {
        System.out.println("Input = " + input);
        System.out.println("Approach1 = " + climbStairs1(input));
        System.out.println("Approach2 = " + climbStairs2(input));
        System.out.println("Approach3 = " + climbStairs3(input));
        System.out.println("Approach4 = " + climbStairs4(input));
    }

    public static void main(String[] args) {
        evaluate(1);
        evaluate(2);
        evaluate(3);
        evaluate(4);
    }
}
