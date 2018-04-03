package algorithms.fibonacci;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FibonacciHuge {
    private static long getFibonacciHugeNaive(long n, long m) {
        if (n <= 1)
            return n;

        double previous = 0;
        double current  = 1;

        for (long i = 0; i < n - 1; ++i) {
            double tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
        }

        return (long) (current % m);
    }

    private static long fast(long n, long m) {
        if(n <= 1) {
            return n;
        }

        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);

        int i = 0;
        while(!(list.get(i) == 0 && list.get(i+1) == 1)) {
            long currentModM = list.get(i) % m;
            long previousModM = list.get(i+1) % m;
            list.add((int) (currentModM + previousModM % m));
            i++;
        }
        int pisatanoPeriod = list.size() - 2;
        int index = (int) (n % pisatanoPeriod);
        return list.get(index);
    }

    private static long getFibonacciHugeFast(long n, long m) {

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
        long n = scanner.nextLong();
        long m = scanner.nextLong();
        System.out.println(getFibonacciHugeFast(n, m));
    }
}

