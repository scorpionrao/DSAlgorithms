package algorithms.dynamicprogramming;

import java.util.*;

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
    static int optimalWeight_repetition(int W, int[] w, int[] v) {
        // bottom up from each smaller weight W
        int n = w.length;
        int[] table = new int[W+1];
        for(int weight = 1; weight <= W; weight++) {
            for(int item = 0; item < n; item++) {
                if(w[item] < weight) continue;
                int value = table[weight - w[item]] + v[item];
                table[weight] = Math.max(table[weight], value);
            }
        }
        return table[W];
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

    static int optimalWeight(int W, int[] w) {
        // Example: 10 3 1 4 8 -> 9
        // build from all combinations of smaller i and weight;
        int n = w.length;
        int[][] table = new int[n + 1][W + 1];
        // fill in first row and first col by 0
        // if total weight is 0 (weight=0)
        for (int j = 0; j <= W; j++) {
            table[0][j] = 0;
        }
        // if no item is chosen (i=0)
        for (int i = 0; i <= n; i++) {
            table[i][0] = 0;
        }
        // either take ith: (w - wi, i - 1); or no taking: (w, i - 1)
        for (int item = 1; item <= n; item++) {
            for (int weight = 1; weight <= W; weight++) {
                // even wi exceeds, keep value of (w, i - 1) as no taking
                table[item][weight] = table[item - 1][weight];
                // BZ: the ith item has index i - 1,
                // since row is from 0 to n
                if (weight < w[item - 1]) {
                    continue;
                }
                table[item][weight] = Math.max(
                        table[item][weight],
                        table[item - 1][weight - w[item - 1]] + w[item - 1]);
                // vi is related with wi, so add wi into knapsack
            }
        }
        return table[n][W];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W, n;
        W = scanner.nextInt();
        n = scanner.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        System.out.println(optimalWeight(W, w));
        /*
        int[] v = new int[n];
        for (int i = 0; i < n; i++) {
            v[i] = scanner.nextInt();
        }
        System.out.println(optimalWeight_repetition(W, w, v));
        System.out.println(optimalWeight_norepetition(W, w, v));
        */
        scanner.close();
    }
}

