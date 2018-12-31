package bbb.DynamicProgramming;

import java.util.Arrays;

public class FibonacciNumbers {

    /*
        Recursion Tree : O(N) height with 2 recursion calls at each node.

        Time : O(2 ^ N)

        Optimal substructure : Yes substructure exists.
        Overlapping subproblems : Yes duplicate subproblems in recursion tree.
     */
    public int fibNaiveFirstApproach(int n) {
        if(n == 0) {
            return 0;
        }
        if(n == 1) {
            return 1;
        }
        return fibNaiveFirstApproach(n-1) + fibNaiveFirstApproach(n-2);
    }

    /*
        Top down

        Time : O(N)
     */
    public int fibTopDownCacheSecondApproach(int n) {
        if(n < 2) {
            return n;
        }
        int[] cache = new int[n];
        Arrays.fill(cache, -1);
        cache[0] = 0;
        cache[1] = 1;
        return fibTopDownCacheSecondApproach(n, cache);
    }

    public int fibTopDownCacheSecondApproach(int n, int[] cache) {
        if(cache[n] >= 0) {
            return cache[n];
        }
        cache[n] = fibTopDownCacheSecondApproach(n-1, cache) + fibTopDownCacheSecondApproach(n-2, cache);
        return cache[n];
    }

    public int fibBottomUpCacheThirdApproach(int n) {
        if(n == 0) {
            return 0;
        }
        int[] cache = new int[n];
        cache[1] = 1;
        for(int i = 2; i < n; i++) {
            cache[i] = cache[i-1] + cache[i-2];
        }
        return cache[n];
    }

    public int fibBottomUpConstantSpaceFourthApproach(int n) {
        if(n < 2) {
            return n;
        }
        int n1 = 1, n2 = 0;
        for(int i = 2; i < n; i++) {
            int n0 = n1 + n2;
            n2 = n1;
            n1 = n0;
        }
        return n1 + n2;
    }
}
