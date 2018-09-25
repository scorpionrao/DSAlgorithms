package algorithms.fibonacci;

import java.util.*;
import java.io.*;

public class MaxPairwiseProduct {
    static long getMaxPairwiseProduct(int[] numbers) {
        long result = 0;
        int n = numbers.length;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                long temp = Long.valueOf(numbers[i]) * Long.valueOf(numbers[j]);
                if (temp > result) {
                    result = temp;
                }
            }
        }
        return result;
    }

    static long getMaxPairwiseProductFast(int[] numbers) {

        int n = numbers.length;
        int max_index1 = -1;
        // Array max
        for(int i = 0; i < n; ++i) {
            if((max_index1 == -1) || (numbers[i] > numbers[max_index1])) {
                max_index1 = i;
            }
        }

        int max_index2 = -1;
        // Array one less than max
        for(int j = 0; j < n; ++j) {
            if((j != max_index1) && ((max_index2 == -1) || (numbers[j] >= numbers[max_index2]))) {
                max_index2 = j;
            }
        }

        System.out.println("Indexes in Fast solution: " + max_index1 + " " + max_index2);
        return (long) numbers[max_index1] * numbers[max_index2];
    }


    public static void main(String[] args) {
/*
        while(true) {
            // IndexRange
            int max = 200, min = 2;
            int randomNum = new Random().nextInt((max - min) + 1) + min;
            System.out.println("Size: " + randomNum);
            int[] array = new int[randomNum];
            for(int i = 0; i < randomNum; i++) {
                int maxValue = 100000, minValue = 0;
                array[i] = new Random().nextInt((maxValue - minValue) + 1) + minValue;
            }
            for(int i = 0; i < randomNum; i++) {
                System.out.printBoard(array[i] + " ");
            }
            System.out.println();
            long bruteForceSolution = getMaxPairwiseProduct(array);
            long optimizedSolution = getMaxPairwiseProductFast(array);
            if(bruteForceSolution == optimizedSolution) {
                System.out.println("OK");
            } else {
                System.out.println("Wrong answer: " + bruteForceSolution + " " + optimizedSolution);
                break;
            }
        }
*/
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }

        long bruteForceSolution = getMaxPairwiseProductFast(numbers);
        System.out.println(bruteForceSolution);
        
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

}