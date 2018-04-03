package algorithms.fibonacci;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FibonacciPartialSum {
    private static long getFibonacciPartialSumNaive(long from, long to) {
        if (to <= 1)
            return to;

        long previous = 0;
        long current  = 1;

        for (long i = 0; i < from - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
        }

        long sum = current;

        for (long i = 0; i < to - from; ++i) {
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
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long from = scanner.nextLong();
        long to = scanner.nextLong();
        //System.out.println(getFibonacciPartialSumNaive(from, to));

        // from
        long value1 = getFibonacciLastDigitHugeFast(from - 1 + 2, 10);
        long result1;
        result1 = (value1 == 0) ? 9 : value1 - 1;

        // to
        long value2 = getFibonacciLastDigitHugeFast(to + 2, 10);
        long result2;
        result2 = (value2 == 0) ? 9 : value2 - 1;

        if(result1 <= result2) {
            System.out.println(result2 - result1);
        } else {
            System.out.println(result2 - result1 + 10);
        }

    }
}

