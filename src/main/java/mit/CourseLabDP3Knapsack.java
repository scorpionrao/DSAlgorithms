package mit;

import java.util.Arrays;

public class CourseLabDP3Knapsack {
     
    // Returns the maximum value that can be put in a knapsack of capacity W
    private static int iterativeKnapsack(int maxBagSize, int weights[], int values[]) {

         int numOfItems = values.length;
         int knapsackValue[][] = new int[numOfItems+1][maxBagSize+1];

         // Build table K[][] in bottom up manner
         for (int kpIndex = 0; kpIndex <= numOfItems; kpIndex++) {

             int itemIndex = kpIndex - 1;

        	 for (int bagSize = 0; bagSize <= maxBagSize; bagSize++) {

        		 if (kpIndex==0 || bagSize==0) {
                     // no items OR no weight allowed in backpack
                     // row 1 or column 1
        			 knapsackValue[kpIndex][bagSize] = 0;
        		 } else if (weights[itemIndex] > bagSize) {
                     // bag cannot take at least 1 item at this index. Guess = NOT TAKE
                     /*
                        VARY (ONLY ONE) itemIndex (row) and look for best solution
                        use "previous best value", "without this item", "for this bag size"
                        same as this else case
                     */
                     knapsackValue[kpIndex][bagSize] = knapsackValue[kpIndex - 1][bagSize];
                 } else {
                     // bag can take at least 1 item of at this index.
                     int leftColumn = bagSize-weights[itemIndex];
                     /*
                        VARY (weight columns) - bagSize (column) and look for best solution.
                        Without changing bagSize,
                            "this value" + "best value" when "reduced by this weight"
                     */
                     int guessTake = values[itemIndex] + knapsackValue[kpIndex][leftColumn];

                     /*
                        VARY (ONLY ONE) itemIndex (row) and look for best solution.
                        use "previous best value" "without this item" "for this bag size"
                        same as this else case
                     */
                     int guessNotTake = knapsackValue[kpIndex-1][bagSize];
        			 knapsackValue[kpIndex][bagSize] = Math.max(guessNotTake, guessTake);
        		 }
        	 }

         }

         print(maxBagSize, numOfItems, knapsackValue);
         return knapsackValue[values.length][maxBagSize];
    }

    private static int recursionOnlyKnapsack(int maxBagSize, int[] weights, int[] values) {
        return recursionOnlyKnapsack(weights, values, weights.length, maxBagSize);
    }

    private static int recursionOnlyKnapsack(int[] weights, int[] values, int row, int column) {

        if(row <= 0 || column <= 0) {
            return 0;
        } else if(weights[row-1] > column) {
            return recursionOnlyKnapsack(weights, values, row-1, column);
        }

        int guessIgnoreWeight = recursionOnlyKnapsack(weights, values, row - 1, column);
        int guessAddWeight = values[row-1] + recursionOnlyKnapsack(weights, values, row, column - weights[row-1]);
        return Math.max(guessIgnoreWeight, guessAddWeight);
    }

    private static int recursionMemoizationKnapsack(int maxBagSize, int[] weights, int[] values) {
        int[][] memo = new int[weights.length+1][maxBagSize+1];
        for(int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return recursionMemoizationKnapsack(weights, values, weights.length, maxBagSize, memo);
    }

    private static int recursionMemoizationKnapsack(int[] weights, int[] values, int row, int column, int[][] memo) {

        if(memo[row][column] != -1) {
            return memo[row][column];
        }

        if(row <= 0 || column <= 0) {
            return 0;
        } else if(weights[row-1] > column) {
            return recursionOnlyKnapsack(weights, values, row-1, column);
        }

        int guessIgnoreWeight = recursionOnlyKnapsack(weights, values, row - 1, column);
        int guessAddWeight = values[row-1] + recursionOnlyKnapsack(weights, values, row, column - weights[row-1]);
        return Math.max(guessIgnoreWeight, guessAddWeight);
    }

    private static void print(int maxBagSize, int numOfItems, int[][] knapsackValue) {
        System.out.print("BagSize-->\t");
        for(int weight = 0; weight <= maxBagSize; weight++) {
            System.out.print(weight + "\t");
        }
        System.out.println();

        for (int kpIndex = 0; kpIndex <= numOfItems; kpIndex++) {
           System.out.print("Item " + kpIndex + "-->\t");
           for (int bagSize = 0; bagSize <= maxBagSize; bagSize++) {
               System.out.print(knapsackValue[kpIndex][bagSize] + "\t");
           }
           System.out.println();
        }

    }

    public static void timeTaken(int result, long start, long end, String testType) {
        System.out.println(testType + "\t: result = " + result + ", time taken = " + (end - start));
    }

    public static void main(String[] args) {
        int val[] = new int[]{5, 11, 13};
        int wt[] = new int[]{3, 7, 11};
        int  W = 17;

        long start, end;

        start = System.nanoTime();
        int iterativeCost = iterativeKnapsack(W, wt, val);
        end = System.nanoTime();
        timeTaken(iterativeCost, start, end, "iterative");

        start = System.nanoTime();
        int regressionOnlyCost = recursionOnlyKnapsack(W, wt, val);
        end = System.nanoTime();
        timeTaken(regressionOnlyCost, start, end, "regressionOnly");

        start = System.nanoTime();
        int regressionMemoizationCost = recursionMemoizationKnapsack(W, wt, val);
        end = System.nanoTime();
        timeTaken(regressionMemoizationCost, start, end, "regressionMemo");

    }
}