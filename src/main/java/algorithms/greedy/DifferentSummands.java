package algorithms.greedy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DifferentSummands {
    private static List<Integer> optimalSummands(int n) {
        List<Integer> summands = new ArrayList<Integer>();
        if(n <= 2) {
            summands.add(n);
        } else {
            int l = 1;
            while(n > 2 * l) {
                summands.add(l);
                n = n - l;
                l = l + 1;
            }
            summands.add(n);
        }
        return summands;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> summands = optimalSummands(n);
        System.out.println(summands.size());
        for (Integer summand : summands) {
            System.out.print(summand + " ");
        }
    }
}

