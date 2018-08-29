package mit;

import java.util.Arrays;

public class CourseLabDP3Knapsack {
     
    // Returns the maximum value that can be put in a knapsack of capacity W
    private static int iterativeKnapsack(int maxBagSize, int weights[], int values[]) {

         int numOfItems = values.length;
         int knapsackValue[][] = new int[numOfItems+1][maxBagSize+1];

         // Build table K[][] in bottom up manner
         for (int itemIndex = 0; itemIndex <= numOfItems; itemIndex++) {

             for (int bagSize = 0; bagSize <= maxBagSize; bagSize++) {

        		 if (itemIndex==0 || bagSize==0) {
                     // no items OR no weight allowed in backpack
                     // row 1 or column 1
        			 knapsackValue[itemIndex][bagSize] = 0;
        		 } else if (weights[itemIndex - 1] > bagSize) {
                     // bag cannot take at least 1 item at this index. Guess = NOT TAKE
                     /*
                        VARY (ONLY ONE) itemIndex (row) and look for best solution
                        use "previous best value", "without this item", "for this bag size"
                        same as this else case
                     */
                     knapsackValue[itemIndex][bagSize] = knapsackValue[itemIndex - 1][bagSize];
                 } else {
                     // bag can take at least 1 item of at this index.
                     int leftColumn = bagSize-weights[itemIndex - 1];
                     /*
                        VARY (weight columns) - bagSize (column) and look for best solution.
                        Without changing bagSize,
                            "this value" + "best value" when "reduced by this weight"
                     */
                     int guessTake = values[itemIndex - 1] + knapsackValue[itemIndex][leftColumn];

                     /*
                        VARY (ONLY ONE) itemIndex (row) and look for best solution.
                        use "previous best value" "without this item" "for this bag size"
                        same as this else case
                     */
                     int guessNotTake = knapsackValue[itemIndex-1][bagSize];
        			 knapsackValue[itemIndex][bagSize] = Math.max(guessNotTake, guessTake);
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
            return recursionMemoizationKnapsack(weights, values, row-1, column, memo);
        }

        int guessIgnoreWeight = recursionMemoizationKnapsack(weights, values, row - 1, column, memo);
        int guessAddWeight = values[row-1] + recursionMemoizationKnapsack(weights, values, row, column - weights[row-1], memo);
        return Math.max(guessIgnoreWeight, guessAddWeight);
    }

    private static boolean solve(int[] weights, int[] values, int[][] board) {
        int[] rowCol = {0, 0};
        if(!hasUnassignedCell(board, rowCol)) {
            return true;
        }
        // there is only ONE option, find the best value
        for(int option = 0; option < 1; option++) {
            if(true /* isSafe = no restrictions */) {
                // MAKE - because no multiple options
                /* ********************************** */
                int row = rowCol[0];
                int previousRow = row - 1;
                int index = previousRow;
                int col = rowCol[1];

                if(row <= 0 || col <= 0) {
                    board[row][col] = 0;
                } else if(weights[previousRow] > col) {
                    board[row][col] = board[previousRow][col];
                } else {
                    int guessIgnoreWeight = board[previousRow][col];
                    int guessAddWeight = values[index] + board[row][col - weights[index]];
                    board[row][col] = Math.max(guessIgnoreWeight, guessAddWeight);
                }
                /* ********************************** */
                if(solve(weights, values, board)) {
                    return true;
                }
                // UNMAKE - no multiple options
            }
        }
        // backtracking
        return false;
    }

    private static boolean hasUnassignedCell(int[][] board, int[] nextUnassigned) {
        for(int row = 0; row < board.length; row++) {
            for(int col = 0; col < board[row].length; col++) {
                if(board[row][col] == -1) {
                    nextUnassigned[0] = row;
                    nextUnassigned[1] = col;
                    return true;
                }
            }
        }
        return false;
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

        start = System.nanoTime();
        int[][] board = new int[wt.length+1][W+1];
        for(int[] row : board) {
            Arrays.fill(row, -1);
        }
        solve(wt, val, board);
        end = System.nanoTime();
        timeTaken(board[wt.length][W], start, end, "standardPattern");

    }
}