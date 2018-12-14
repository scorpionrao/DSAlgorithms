package algorithms.dynamicprogramming;

public class Knapsack {

    /*
        Discrete algorithms.dynamicprogramming.Knapsack problem

        Take as much value into knapsack as possible

        no repetition: there is just one copy of each item and should be taken in full or not

        with repetition: unlimited for each item

        Constraints: 1 ≤ W ≤ 10^4; 1 ≤ n ≤ 300; 0 ≤ w0, ..., wn−1 ≤ 10^5

        Try input 200 out of 300 weights with 10^5 as weight
     */


    /*
        Key Idea: with repetition
        Subproblem: given knapsack of value(w), remove ith item, W-wi still optimal;
        Recurrence: Build v(i) from the max of all W - w(i)
        Example:
            value(w, i) = max{ value(w-w(i)) + v(i), value(w knapsack) }

            W = 10, n = 4
            w = [6,     3,  4,  2]
            v = [30,   14,  16, 9]

        Recurrence example (knapsack weight = 4)
        item = 0: 6 > weight=4, ignore
        item = 1: value(4, 1) = value(4 - 3) + v[1] = 14
        item = 2: value(4, 2) = value(4 - 4) + v[2] = 16
        item = 3: value(4, 3) = value(4 - 2) + v[3] = value(2) + 9 = 9 + 9 = 18
     */
    static int optimalWeight_repetition(int maxBagSize, int[] weights, int[] values) {
        // bottom up from each smaller weight W
        int n = weights.length;
        /* 1D array */
        int[] table = new int[maxBagSize+1];
        for(int weight = 1; weight <= maxBagSize; weight++) {
            for(int item = 0; item < n; item++) {
                if(weights[item] > weight) {continue;}
                /* Always GUESS TAKE */
                int value = table[weight - weights[item]] + values[item];
                table[weight] = Math.max(table[weight], value);
            }
        }
        return table[maxBagSize];
    }

    /*
        Key Idea: without repetition
        Subproblem: given knapsack of value(w, i),
                    if ith item taken, (W-w(i)), i-1) is still optimal
                    if ith item ignored, (W, i-1) is still optimal
        Recurrence: value(w,i) = max{value(w-wi, i-1) + vi, value(w, i-1) }
        Example:
     */
    static int optimalWeight_norepetition(int W, int[] w, int[] v) {
        // bottom-up from all combinations of smaller item i and weight
        int n = w.length;
        int[][] table = new int[n + 1][W + 1];
        // if no item is chosen (i=0), value is 0;
        for(int i = 0; i <= n; i++) {
            table[i][0] = 0;
        }
        // if total weight is 0 (weight=0), value is 0;
        for(int j = 0; j <= W; j++) {
            table[0][j] = 0;
        }
        // either take ith: (w - wi, i - 1); or no taking: (w, i - 1)
        for(int item = 1; item <= n; item++) {
            for(int weight = 1; weight <= W; weight++) {
                // BZ: if total weight < wi, still regard as not taking i;
                // cannot simply ignoring as 0!
                table[item][weight] = table[item - 1][weight];
                // BZ: the ith item has index i - 1;
                // since row is from 0 to n;
                if (weight < w[item - 1]) {
                    continue;
                }
                table[item][weight] = Math.max(
                        table[item][weight],
                        table[item - 1][weight - w[item - 1]] + v[item - 1]);
            }
        }
        return table[n][W];
    }

    /* Default is without repetition */
    static int optimalWeight(int maxBagSize, int[] weights) {

        int numOfItems = weights.length;
        int[][] table = new int[numOfItems + 1][maxBagSize + 1];

        int noItem = 0;
        for (int weightColumn = 0; weightColumn <= maxBagSize; weightColumn++) {
            table[noItem][weightColumn] = 0;
        }

        int noCapacity = 0;
        for (int itemRow = 0; itemRow <= numOfItems; itemRow++) {
            table[itemRow][noCapacity] = 0;
        }
        // either take ith: (w - wi, i - 1); or no taking: (w, i - 1)
        for (int item = 1; item <= numOfItems; item++) {
            for (int availableWeight = 1; availableWeight <= maxBagSize; availableWeight++) {

                if (availableWeight < weights[item - 1]) {
                    continue;
                }
                table[item][availableWeight] =
                    Math.max(
                        table[item - 1][availableWeight],
                        table[item][availableWeight - weights[item - 1]] + weights[item - 1]);
            }
        }
        return table[numOfItems][maxBagSize];
    }

    /*
        Time complexity: 2^n (2 guesses for each item. N items)
     */
    private static int knapsackRecursion(int[] weights, int[] values, int maxWeight) {
        return knapsackRecursion(weights, values, maxWeight, 0);
    }

    private static int knapsackRecursion(int[] weights, int[] values, int maxWeight, int index) {
        // All weights are explored
        if(index == weights.length) {
            return 0;
        }
        // Not enough space for this weight
        if(maxWeight < weights[index]) {
            return knapsackRecursion(weights, values, maxWeight, index+1);
        }
        return Math.max(
                /* include */
                knapsackRecursion(weights, values, maxWeight - weights[index], index+1) + values[index],
                /* exclude */
                knapsackRecursion(weights, values, maxWeight, index+1)
        );
    }

    private static int dpBottomUpCacheIsGiven(int[] weights, int[] values, int maxWeight) {
        int[][] cache = new int[weights.length+1][maxWeight+1];
        for(int i = 1; i < cache.length; i++) {
            for(int j = 1; j < cache[i].length; j++) {
                // Is this item more than max weight in this loop
                if(weights[i-1] > j) {
                    // not taken scenario
                    cache[i][j] = cache[i-1][j];
                } else {
                    // not taken OR taken (add value,
                    int notTaken = cache[i-1][j];
                    int taken = cache[i-1][j-weights[i-1]] + values[i-1];
                    cache[i][j] = Math.max(notTaken, taken);
                }
            }
        }

        return cache[weights.length][maxWeight];
    }

    /*
        Dynamic Programming - Optimal Substructure, Overlapping subproblems.

        Optimal substructure
            - All recursion problems have optimal substructures.
            - By solving subproblems, we can solve bigger subproblems.

        Overlapping subproblems
            - Same inputs are used multiple times in calling subproblems.

        Topdown solution:
            - Start with end solution to smallest subproblem.
            - Cache will address this problem. Cache should be based on variants.
        Bottomup solution:
            - Start with subproblems and build up to end solution.


     */

    public static void main(String[] args) {
        /*
        Scanner scanner = new Scanner(System.in);
        int W, n;
        W = scanner.nextInt();
        n = scanner.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        System.out.println(optimalWeight(W, w));


        int[] v = new int[n];
        for (int i = 0; i < n; i++) {
            v[i] = scanner.nextInt();
        }
        System.out.println(optimalWeight_repetition(W, w, v));
        System.out.println(optimalWeight_norepetition(W, w, v));

        scanner.close();
        */

        int[] weights = {1, 2, 3};
        int[] values = {6, 10, 12};
        int maxWeight = 5;
        System.out.println("Recursion: Max Value: Expected - 22, Actual - " + knapsackRecursion(weights, values, maxWeight));
        System.out.println("DynamicPr: Max Value: Expected - 22, Actual - " + dpBottomUpCacheIsGiven(weights, values, maxWeight));
    }
}

