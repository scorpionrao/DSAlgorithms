package algorithms.dynamicprogramming;

import java.util.Arrays;

public class ChangeDP {

    /*
        Greedy Change - use the exhaust max denomination first approach and convert into smaller sub problems.

        Psuedo code:
            GreedyChange(money, coins) {
                numOfCoins = 0;
                coinArray = {}
                while(money > 0) {
                    for each DENOMINATION biggest to lowest
                        if(money >= denomination) {
                            money = money - denomination
                            numOfCoins = numOfCoins + 1
                            coinArray append coin
                }
                return numOfCoins or coinArray;
            }
     */

    public static void main(String[] args) {

        int[] denominations = {25, 20, 10, 5, 1};
        int money = 41;

        ChangeDP changeDP = new ChangeDP();
        System.out.println("Min num of coins : " + changeDP.greedy(money, denominations));
        System.out.println("Min num of coins : " + changeDP.recursion(money, denominations));
        System.out.println("Min num of coins : " + changeDP.iterative(money, denominations));
    }

    private int greedy(int money, int[] denominations) {

        int minNumOfCoins = 0;
        for(int i = 0; i < denominations.length; i++) {
            while(money >= denominations[i]) {
                minNumOfCoins++;
                money = money - denominations[i];
            }
            if(money == 0) {
                break;
            }
        }
        return minNumOfCoins;
    }

    /*
        Recursive Change - reduce sub problem by coin denomination at a time.

        RecursiveChange(money, coins) {
            if(money == 0) {return 0;}
            int minCoins = infinity
            // ONE REGRESSION LEVEL
            // CALCULATE MINS FOR EACH DENOMINATION
            for( 1 to coin(d)) {
                // LEAF CHECK
                if(money > coin(d)) {
                    // SUB-TREE FOR EACH NODE
                    numCoinsAtRecursionLevel = RecursiveChange(money - coin(d), coins)
                    // UPDATE AS BETTER RESULTS ARE IDENTIFIED
                    if(numCoinsAtRecursionLevel + 1 < minCoins) {
                        minCoins = numCoinsAtRecursionLevel + 1;
                    }
                }
            }
            return minCoins;
        }
     */
    private int recursion(int money, int[] denominations) {
        if(money == 0) {
            return 0;
        }
        int minNumOfCoins = Integer.MAX_VALUE;
        for(int d = 0; d < denominations.length; d++) {
            if(money >= denominations[d]) {
                int numOfCoins = recursion(money - denominations[d], denominations);
                if (numOfCoins + 1 < minNumOfCoins) {
                    minNumOfCoins = numOfCoins + 1;
                }
            }
        }
        return minNumOfCoins;
    }

    /*
        DP Change - calculate min number of coins for each money value and reach target money value.

        DPChange(money, coins) {
            minCoins[0....money]
            minCoins(0) = 0
            for(m = 1 to money) {
                minNumCoins = infinity
                for(1 to coin(d)) {
                    if(m >= coin(d)) {
                        // while calculating for m, m-coin should have been already calculated
                        numOfCoins = minCoins(m - coin(d)) + 1
                        if(numOfCoins < minNumCoins) {
                            minNumCoins[m] = numOfCoins
                        }
                    }
                }
            }

            return minCoins[];
        }
     */
    private int iterative(int money, int[] denominations) {

        int[] minNumberOfCoins = new int[money+1];
        Arrays.fill(minNumberOfCoins, Integer.MAX_VALUE);

        minNumberOfCoins[0] = 0;

        for(int m = 1; m <= money; m++) {
            for(int d = 0; d < denominations.length; d++) {
                if(m >= denominations[d]) {
                    int numOfCoins = minNumberOfCoins[m - denominations[d]] + 1;
                    if(numOfCoins < minNumberOfCoins[m]) {
                        minNumberOfCoins[m] = numOfCoins;
                    }
                }
            }
        }
        return minNumberOfCoins[money];
    }
}