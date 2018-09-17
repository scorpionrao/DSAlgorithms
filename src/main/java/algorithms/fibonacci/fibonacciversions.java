package algorithms.fibonacci;

public class fibonacciversions {
    /*
        Time complexity: T(n) = T(n-1) + T(n-2) ~ O(2 ^ n)
        Extra Space: O(n) - function stack size
     */
    private static double fibonacciBruceForce(int n) {
        if(n <= 1) {
            return n;
        }
        return fibonacciBruceForce(n-1) + fibonacciBruceForce(n-2);
    }

    /*
        Time complexity: O(n)
        Extra Space: O(n)
     */
    private static double fibonacciDP(int n) {
        double[] f = new double[n+1];
        f[0] = 0;
        f[1] = 1;

        /* 0th and 1st number of the series are 0 and 1 */
        if(n <= 1) {
            return n;
        }

        for(int i = 2; i <= n; i++) {
            f[i] = f[i-1] + f[i-2];
        }
        return f[n];
    }

    /*
        Time complexity: O(n)
        Extra Space: O(1)
     */
    private static double fibonacciDPSpaceOptimized(int n) {

        /* 0th and 1st number of the series are 0 and 1 */
        if(n <= 1) {
            return n;
        }

        double previous = 0;
        double current = 1;

        for(int i = 2; i <= n; i++) {
            double temp = previous + current;
            previous = current;
            current = temp;
        }
        return current;
    }

    /*
        Time complexity: O(n)
        Extra Space: O(1)
     */
    private static double fibonacciMatrix(int n) {
        double F[][] = new double[][]{{1,1},{1,0}};
        if(n <= 1) {
            return n;
        }

        power(F, n-1);
        return F[0][0];
    }

    /*
        Time complexity: O(Log n)
        Extra Space: O(1)
        Extra Space: O(Log n) if considering function call stack size
     */
    private static double matrixPowerOptimized(int n) {
        double F[][] = new double[][]{{1,1},{1,0}};
        if(n <= 1) {
            return n;
        }

        powerOptimized(F, n-1);
        return F[0][0];
    }


    private static void power(double[][] F, int n) {
        double[][] M = new double[][]{{1,1},{1,0}};
        for(int i=2; i<=n; i++) {
            multiply(F, M);
        }
    }

    /*
        n = 7, n/2 = 3
        F = F^3 * F^3
        n%2 = 1
        F = F * initial F
     */
    private static void powerOptimized(double[][] F, int n) {
        if((n == 0) || (n == 1)) {
            return;
        }
        double[][] M = new double[][]{{1,1},{1,0}};
        // reduce O(n) to O(Log n) in the power calculation SinglyLinkedListLoop
        powerOptimized(F, n/2);
        // since there is only 2 matrices to multiplyWithSingleDigit, use the regular 2*2 multiplyWithSingleDigit
        multiply(F, F);

        if((n%2) != 0) {
            multiply(F, M);
        }
    }

    private static void multiply(double[][] F, double[][] M) {
        double row0column0 =  F[0][0]*M[0][0] + F[0][1]*M[1][0];
        double row0column1 =  F[0][0]*M[0][1] + F[0][1]*M[1][1];
        double row1column0 =  F[1][0]*M[0][0] + F[1][1]*M[1][0];
        double row1column1 =  F[1][0]*M[0][1] + F[1][1]*M[1][1];

        F[0][0] = row0column0;
        F[0][1] = row0column1;
        F[1][0] = row1column0;
        F[1][1] = row1column1;
    }


    public static void main(String[] args) {
        long start, end;
        int value = 50;

        /*
            Time complexity: T(n) = T(n-1) + T(n-2) ~ O(2 ^ n)
            Extra Space: O(n) - function stack size
        */
        start = System.currentTimeMillis();
        double bruteForceValue = fibonacciBruceForce(value);
        end = System.currentTimeMillis();
        print(start, end, bruteForceValue, "Brute Force");

        /*
            Time complexity: O(n)
            Extra Space: O(n)
        */
        start = System.currentTimeMillis();
        double dpValue = fibonacciDP(value);
        end = System.currentTimeMillis();
        print(start, end, dpValue, "Dynamic Programming");

        /*
            Time complexity: O(n)
            Extra Space: O(1)
        */
        start = System.currentTimeMillis();
        double dpSpaceOptimizedValue = fibonacciDPSpaceOptimized(value);
        end = System.currentTimeMillis();
        print(start, end, dpSpaceOptimizedValue, "Dynamic Programming Space Optimized");

        /*
            Time complexity: O(n)
            Extra Space: O(1)
        */
        start = System.currentTimeMillis();
        double matrixValue = fibonacciMatrix(value);
        end = System.currentTimeMillis();
        print(start, end, matrixValue, "Matrix");

        /*
            Time complexity: O(Log n)
            Extra Space: O(1)
            Extra Space: O(Log n) if considering function call stack size
         */
        start = System.currentTimeMillis();
        double matrixPowerOptimizedValue = matrixPowerOptimized(value);
        end = System.currentTimeMillis();
        print(start, end, matrixPowerOptimizedValue, "Matrix Power optimized");

    }

    private static void print(long start, long end, double value, String description) {
        double duration = end - start;
        System.out.println(description + " = " + value + " in " + duration + "ms");
    }
}
