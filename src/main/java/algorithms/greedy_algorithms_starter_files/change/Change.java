package algorithms.greedy_algorithms_starter_files.change;

import java.util.Scanner;

public class Change {
    private static int getChange(int m) {
        if(m < 1) {return 0;}
        if(m > 1000) {return 0;}

        int[] denominations = {10, 5, 1};

        int remainingAmount = m;
        int numOfCoins = 0;
        for(int denomination : denominations) {
            numOfCoins += remainingAmount / denomination;
            remainingAmount = remainingAmount % denomination;
        }
        return numOfCoins;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));

    }
}

