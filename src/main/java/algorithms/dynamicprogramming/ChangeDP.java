package algorithms.dynamicprogramming;

public class ChangeDP {

    /*
        Greedy Change - use the exhaust max denomination first approach and convert into smaller sub problems.

        Psuedo code:
            GreedyChange(money, coins) {
                numOfCoins = 0;
                coinArray = {}
                while(money > 0) {
                    for each denomination biggest to lowest
                        if(money >= denomination) {
                            money = money - denomination
                            numOfCoins = numOfCoins + 1
                            coinArray append coin
                }
                return numOfCoins or coinArray;
            }
     */

    /*
        Recursive Change - reduce sub problem by coin denomination at a time.

        Psuedo code:
            RecursiveChange(money, coins) {
                if(money = 0) {return 0}
                int minCoins = infinity
                // ONE REGRESSION LEVEL
                // CALCULATE MINS FOR EACH DENOMINATION
                for( 1 to coin(d)) {
                    // LEAF CHECK
                    if(money > coin(d)) {
                        // SUB-TREE FOR EACH NODE
                        numCoinsAtRecursionLevel = RecursiveChange(money - coin(d))
                        // UPDATE AS BETTER RESULTS ARE IDENTIFIED
                        if(numCoinsAtRecursionLevel + 1 < minCoins) {
                            minCoins = numCoinsAtRecursionLevel + 1;
                        }
                    }
                }
                return minCoins;
            }
     */

    /*
        DP Change - calculate min number of coins for each money value and reach target money value.

        Psuedo code to return array of min coins:
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
}
