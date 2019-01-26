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
    private static int climbStairs1Edges(int target) {
        if(target < 0) {
            return 0;
        }
        if(target == 0) {
            return 1;
        }
        return climbStairs1Edges(target-1) + climbStairs1Edges(target-2);
    }

    /*
        Top Down with Memoization
        Time: O(N)
        Space: O(N)
    */
    private static int climbStairs3Edges(int target) {
        int[] cacheEdgeCount = new int[target+1];
        cacheEdgeCount[0] = 1;
        climbStairs3Edges(target, cacheEdgeCount);
        return cacheEdgeCount[target];
    }

    private static int climbStairs3Edges(int target, int[] cache) {
        if(target < 0) {
            return 0;
        }
        if(cache[target] != 0) {
            return cache[target];
        }

        cache[target] = climbStairs3Edges(target-1, cache) + climbStairs3Edges(target-2, cache);
        return cache[target];
    }

    /*
        Bottom Up Recurse
        Time: O(2^N)
        Space: O(N)
    */
    private static int climbStairs2Edges(int target) {
        return climbStairs2Edges(0, target);
    }

    private static int climbStairs2Edges(int start, int target) {
        if(start > target) {
            // no edges
            return 0;
        }
        if(start == target) {
            // one edge
            return 1;
        }
        return climbStairs2Edges(start+1, target) + climbStairs2Edges(start+2, target);
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

        int edgesForOne = 1;
        int edgesForTwo = 2;
        int result = 0;
        for(int i = 3; i <= input; i++) {
            result = edgesForOne + edgesForTwo;
            edgesForOne = edgesForTwo;
            edgesForTwo = result;
        }
        return result;
    }

    private static void evaluate(int input) {
        System.out.println("Input = " + input);
        System.out.println("Approach1 = " + climbStairs1Edges(input));
        System.out.println("Approach2 = " + climbStairs2Edges(input));
        System.out.println("Approach3 = " + climbStairs3Edges(input));
        System.out.println("Approach4 = " + climbStairs4(input));
    }

    public static void main(String[] args) {
        evaluate(1);
        evaluate(2);
        evaluate(3);
        evaluate(4);
    }
}
