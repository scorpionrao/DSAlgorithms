package bbb.DynamicProgramming;

import java.util.Arrays;

public class MinimumChange {

    private static int minCoinsApproach1(int amount, int[] coins) {

        if(amount == 0) {
            return 0;
        }

        int[] cache = new int[amount];
        Arrays.fill(cache, -1);
        return minCoinsApproach1(amount, coins, cache);
    }

    private static int minCoinsApproach1(int amount, int[] coins, int[] cache) {

        if(amount == 0) {
            return 0;
        }

        int minCoins = Integer.MAX_VALUE;
        // options
        for(int coin : coins) {
            // is safe
            if(amount >= coin) {
                int minCount;
                if(cache[amount - coin] >= 0) {
                    minCount = cache[amount - coin];
                } else {
                    minCount = minCoinsApproach1(amount - coin, coins, cache);
                    cache[amount - coin] = minCount;
                }
                int temp = 1 + cache[amount - coin];
                if(temp < minCoins) {
                    minCoins = temp;
                }
            }
        }
        return minCoins;
    }

    private static int minCoinsApproach2(int amount, int[] coins) {

        if(amount == 0) {
            return 0;
        }

        int minCoins = Integer.MAX_VALUE;
        // options
        for(int coin : coins) {
            // is safe
            if(amount >= coin) {
                int minCount = minCoinsApproach2(amount - coin, coins);
                int temp = 1 + minCount;
                if(temp < minCoins) {
                    minCoins = temp;
                }
            }
        }
        return minCoins;
    }

    /* Time : O(amount * coins), Space : O(amount * coins) */
    private static int minCoinsApproach3(int amount, int[] coins) {

        int[][] dp = new int[coins.length+1][amount+1];
        for(int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp[i].length; j++) {
                if(i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (j < coins[i-1]) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    int dividend = j / coins[i-1];
                    int remainder = j % coins[i-1];
                    dp[i][j] = dividend + dp[i][remainder];
                }
            }
        }
        return dp[coins.length][amount];
    }


    private static int minCoinsApproach4(int amount, int[] coins) {
        return minCoinsApproach4(coins, coins.length, amount);
    }

    private static int minCoinsApproach4(int[] coins, int coinsAllowed, int amount) {

        if(coinsAllowed == 0 || amount == 0) {
            return 0;
        } else if(amount < coins[coinsAllowed-1]) {
            return minCoinsApproach4(coins, coinsAllowed-1, amount);
        } else {
            int dividend = amount / coins[coinsAllowed-1];
            int remainder = amount % coins[coinsAllowed-1];
            return dividend + minCoinsApproach4(coins, coinsAllowed, remainder);
        }
    }


    private static void evaluate(int amount, int[] coins) {
        System.out.println("Coins : " + Arrays.toString(coins));
        System.out.println("Amount : " + amount);
        int minCoins1 = minCoinsApproach1(amount, coins);
        System.out.println("Approach1 : " + minCoins1);
        int minCoins2 = minCoinsApproach2(amount, coins);
        System.out.println("Approach2 : " + minCoins2);
        int minCoins3 = minCoinsApproach3(amount, coins);
        System.out.println("Approach3 : " + minCoins3);
        int minCoins4 = minCoinsApproach4(amount, coins);
        System.out.println("Approach4 : " + minCoins4);
    }

    public static void main(String[] args) {

        int[] coins = {1, 5, 10, 25};
        evaluate(32, coins);
    }
}
