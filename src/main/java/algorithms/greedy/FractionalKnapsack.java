package algorithms.greedy;

import java.util.Scanner;

/*
    GREEDY PROBLEM - FRACTIONAL KNAPSACK:

    - MAKE A GREEDY SAFE MOVE - max, min, leftmost, rightmost, first
    - PROVE SAFETY
    - SOLVE SUBPROBLEM
    - ESTIMATE RUNNING TIME
 */
public class FractionalKnapsack {

    public static double getMaxValue(int capacity, int[] values, int[] weights) {

        double totalValue = 0;
        int remainingWeight = capacity;

        for (int i = 0; i < values.length; i++) {

            if(remainingWeight <= 0) {
                return totalValue;
            }
            int maxUnitPriceIndex = 0;
            double maxUnitPrice = 0.0;
            for (int j = 0; j < values.length; j++) {
                if (weights[j] != 0) {
                    double unitPrice = (double) values[j] / weights[j];
                    if (unitPrice > maxUnitPrice) {
                        maxUnitPriceIndex = j;
                        maxUnitPrice = unitPrice;
                    }
                }
            }

            int weightToBeAdded = Math.min(weights[maxUnitPriceIndex], remainingWeight);
            totalValue = totalValue +
                    (weightToBeAdded * (double) values[maxUnitPriceIndex] / weights[maxUnitPriceIndex]);

            weights[maxUnitPriceIndex] = weights[maxUnitPriceIndex] - weightToBeAdded;
            remainingWeight = remainingWeight - weightToBeAdded;
        }
        return totalValue;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        System.out.println(getMaxValue(capacity, values, weights));

    }
}

