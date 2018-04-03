package algorithms.fibonacci;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FibonacciSumLastDigit {

    private static long getFibonacciSumNaive(long n) {

        if (n <= 1)
            return n;

        long previous = 0;
        long current  = 1;
        long sum      = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
            sum += current;
        }

        return sum % 10;
    }

    private static long getFibonacciLastDigitHugeFast(long n, long m) {

        if (n <= 1) { return n; }

        // because it cannot be greater than 100000
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);

        int i = 0;
        while(!(i > 0 && list.get(i) == 0 && list.get(i+1) == 1)) {
            long currentModM = list.get(i) % m;
            long previousModM = list.get(i+1) % m;
            list.add((int) ((currentModM + previousModM) % m));
            i++;
        }

        int pisatanoPeriod = list.size() - 2;

        int index = (int) (n % pisatanoPeriod);
        return list.get(index);
    }

    private static long fibonacciDPSpaceOptimized(long n) {

        /* 0th and 1st number of the series are 0 and 1 */
        if(n <= 1) {
            return (int) n;
        }

        if(n > 60) {
            long value = getFibonacciLastDigitHugeFast(n, 10);
            return (value == 0) ? 9 : value - 1;
        }

        long previous = 0;
        long current = 1;

        for(long i = 2; i <= n; i++) {
            long temp = previous + current;
            previous = current;
            current = temp % 10;
        }
        return current;
    }


    public static void main(String[] args) {
/*
        while(true) {
            // IndexRange
            int max = 500, min = 100;
            int randomNum = new Random().nextInt((max - min) + 1) + min;
            System.out.println("Number: " + randomNum);
            long bruteForceSolution = getFibonacciSumNaive(randomNum);
            long optimizedSolution = matrixPowerOptimizedSum(randomNum + 2);
            if(bruteForceSolution == optimizedSolution) {
                System.out.println("OK");
            } else {
                System.out.println("Wrong answer: " + bruteForceSolution + " " + optimizedSolution);
            break;
            }
        }
*/
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();

        long value = getFibonacciLastDigitHugeFast(n + 2, 10);
        long result;
        result = (value == 0) ? 9 : value - 1;
        System.out.println(result);
    }
}

