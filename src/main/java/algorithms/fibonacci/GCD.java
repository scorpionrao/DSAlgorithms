package algorithms.fibonacci;

import java.util.Scanner;

public class GCD {
  private static int gcd_naive(int a, int b) {
    int current_gcd = 1;
    for(int d = 2; d <= a && d <= b; ++d) {
      if (a % d == 0 && b % d == 0) {
        if (d > current_gcd) {
          current_gcd = d;
        }
      }
    }
    return current_gcd;
  }

  private static int naive(int a, int b) {
    int current_gcd = 1;
    for(int i = 2; i <= a && i <= b; i++) {
      if(a % i == 0 && b % i == 0) {
        current_gcd = i;
      }
    }
    return current_gcd;
  }
  private static int recursiongcd(int a, int b) {
    if(b == 0) {
      return a;
    }
    return gcdFast(b, a % b);
  }

  private static int gcdFast(int a, int b) {

    if(b == 0) {
      return a;
    }

    int c = a % b;
    return gcdFast(b, c);
  }

  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    int a = scanner.nextInt();
    int b = scanner.nextInt();

    System.out.println(gcdFast(a, b));
  }
}
