package bbb.DynamicProgramming;

import java.util.Arrays;

public class MakingChange {

    private int[] coins = {10, 6, 1};

    /*
        Recursion Tree : O(money) height with c recursion calls at each node.

        Time : O(money ^ coins)

        Optimal substructure : Yes substructure exists.
        Overlapping subproblems : Yes duplicate subproblems in recursion tree.
     */
    public int makeChangeNaiveFirstApproach(int money) {
        if(money == 0) {
            return 0;
        }
        int minCoins = Integer.MAX_VALUE;

        for(int coin : coins) {
            if(money >= coin) {
                int currMinCoins = 1 + makeChangeNaiveFirstApproach(money - coin);
                minCoins = Math.min(minCoins, currMinCoins);
            }
        }
        return minCoins;
    }

    public int makeChangeTopDownCacheSecondApproach(int money) {
        int[] cache = new int[money];
        Arrays.fill(cache, -1);
        return makeChangeTopDownCacheSecondApproach(money, cache);
    }

    private int makeChangeTopDownCacheSecondApproach(int money, int[] cache) {
        if(cache[money] >= 0) {
            return cache[money];
        }

        int minCoins = Integer.MAX_VALUE;

        for(int coin : coins) {
            if(money >= coin) {
                int currMinCoins = 1 +
                                    makeChangeTopDownCacheSecondApproach(money - coin, cache);
                minCoins = Math.min(minCoins, currMinCoins);
            }
        }
        cache[money] = minCoins;
        return cache[money];
    }

    private int makeChangeBottomUpCacheThirdApproach(int money) {

        int[] cache = new int[money];
        Arrays.fill(cache, -1);

        for(int i = 0; i < money; i++) {
            int minCoins = Integer.MAX_VALUE;
            for(int coin : coins) {
                if(i >= coin) {
                    int currMinCoins = 1 + cache[i - coin];
                    minCoins = Math.min(minCoins, currMinCoins);
                }
            }
            cache[i] = minCoins;
        }
        return cache[money - 1];
    }
}
