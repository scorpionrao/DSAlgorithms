package algorithms.fibonacci;

import java.util.Random;
import java.util.Scanner;

public class FibonacciLastDigit {

    private static byte getFibonacciLastDigit(int n) {
        if (n <= 1)
            return (byte) n;

        byte previous = 0;
        byte current  = 1;

        for (int i = 0; i < n - 1; ++i) {
            byte tmp_previous = (byte) (previous % 10);
            previous = (byte) (current % 10);
            current = (byte) ((tmp_previous + current) % 10);
        }

        return (byte) (current % 10);
    }

    private static byte getFibonacciLastDigitFast(int n) {
        if (n <= 1) {return (byte) n;}
        byte[] F = new byte[n];
        F[0] = 1;
        F[1] = 1;
        for(int i = 2; i < n; i++) {
            F[i] = (byte) ((F[i-1] + F[i-2]) % 10);
        }
        return F[n - 1];
    }
    
    public static void main(String[] args) {
        System.out.println("Total Memory - KB: " + (double) (Runtime.getRuntime().totalMemory()) / 1024);
        System.out.println("Free Memory - KB: " + (double) (Runtime.getRuntime().freeMemory()) / 1024);
        System.out.println("Used Memory - KB: " + (double) (Runtime.getRuntime().totalMemory() - Runtime.getRuntime()
                .freeMemory()) / 1024);
        while(true) {
          // IndexRange
          int max = 10000000, min = 0;
          int randomNum = new Random().nextInt((max - min) + 1) + min;
          System.out.println("Index: " + randomNum);
          long bruteForceSolution = getFibonacciLastDigit(randomNum);
          long optimizedSolution = getFibonacciLastDigitFast(randomNum);
          if(bruteForceSolution == optimizedSolution) {
            System.out.println("OK");
          } else {
            System.out.println("Wrong answer: " + bruteForceSolution + " " + optimizedSolution);
            break;
          }
        }

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(getFibonacciLastDigit(n));
        System.out.println(getFibonacciLastDigitFast(n));
    }
}

