package algorithms.dynamicprogramming;

import java.util.*;

public class PrimitiveCalculator {
    private static List<Integer> optimal_sequence(int n) {
        List<Integer> sequence = new ArrayList<Integer>();
        while (n >= 1) {
            sequence.add(n);
            if (n % 3 == 0) {
                n /= 3;
            } else if (n % 2 == 0) {
                n /= 2;
            } else {
                n -= 1;
            }
        }
        Collections.reverse(sequence);
        return sequence;
    }

    private static List<Integer> dp_sequence(int n) {
        /*
            Key Idea:
            Subproblem: given some min # of operations to obtain n: count(n)
                        n comes from either x*3, x*2, x+1

            Recurrence: count(n) = min {
                count(n / 2) + 1
                count(n / 3) + 1
                count(n - 1) + 1
            }

            Bottom-up: build from 1 to n is the same as going from n to 1

            Backtrack: find left with the farthest t[n] - 1 from the 3 outcomes
         */

        int[] minOperations = new int[n+1];
        minOperations[0] = 0;
        for(int i = 1; i < minOperations.length; i++) {
            minOperations[i] = minOperations[i-1] + 1;
            if(i % 2 == 0) {
                minOperations[i] = Math.min(minOperations[i], minOperations[i/2] + 1);
            }
            if(i % 3 == 0) {
                minOperations[i] = Math.min(minOperations[i], minOperations[i/3] + 1);
            }
        }

        int j = n;
        List<Integer> sequence = new ArrayList<>();
        while (j > 1) {
            sequence.add(j);
            if(minOperations[j] - 1 == minOperations[j-1]) {
                j = j - 1;
            } else if (j % 2 == 0 && minOperations[j] - 1 == minOperations[j/2]) {
                j = j / 2;
            } else if (j % 3 == 0 && minOperations[j] - 1 == minOperations[j/3]) {
                j = j / 3;
            }
        }
        sequence.add(j);
        Collections.reverse(sequence);
        return sequence;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> sequence = dp_sequence(n);
        System.out.println(sequence.size() - 1);
        for (Integer x : sequence) {
            System.out.print(x + " ");
        }
    }
}

