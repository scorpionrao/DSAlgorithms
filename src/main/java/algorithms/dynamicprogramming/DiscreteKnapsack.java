package algorithms.dynamicprogramming;

public class DiscreteKnapsack {

    /*
        Discrete knapsack cannot be solved by algorithms.greedy problem and requires dynamic programming.

        Eg: Ad placements in the web page - maximize profit, while fitting in duration.
        Eg: Purchasing computers -
     */

    /*
        Pseudo Code: For discrete Knapsack1 WITH repetitions, we solve for each W by playing with each item.

        Knapsack1(W) {
            value(0) = 0
            for(w = 1 to W) {
                value(w) = 0;
                for(item = 1 to n) {
                    if(w(item) <= w) {
                        // go back to previous optimal solution before adding this
                        intermediateValue = value(w - weight(item)) + value(item)
                        if(intermediateValue > value(w)) {
                            value(w) = intermediateValue
                        }
                    }
                }
            }
            return value(W);
        }
     */

    /*
        Pseudo Code: For discrete Knapsack1 WITHOUT repetitions, we solve for each item by playing with each W.

        Knapsack1(W) {
            // optimization used for recursive only
            if w is in hash table {return value(w)}
            value[][]
            all weights no items
            value(w, 0) = 0;
            all items no weight
            value(0, n) = 0;
            for(item = 1 to items) {
                // ITERATIVE SOLUTION
                for(w = 1 to W) {
                    // consider I am not there. If you an notSynchronizedMethod and increase value, change me.
                    value(w, item) = value(w, item-1)
                    if(w(item) <= w) {
                        temp = value(w - w(item), item-1) + v(item);
                        if(value(w, item) < temp) {
                            value(w, item) = temp;
                        }
                    }
                }
                // RECURSIVE SOLUTION
                if(w(item) <= w) {
                    temp = Knapsack1(w - w(item)) + v(item);
                    if(value(w, item) < temp) {
                        value(w, item) = temp;
                    }
                }
                // insertAtHead value(w) into hash table with key w
            }
            return value(W);
        }
     */
}
