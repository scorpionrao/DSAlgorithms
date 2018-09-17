package algorithms.fibonacci;

import java.util.Random;
import java.util.Scanner;

public class fibonacci {
  /*
    Input:
    - How is input presented - In-memory
    - What is input data structure - int - 4.2 X 10^10 values
    - What is the range of values - 0 to 45.
    - What is the pattern of values - just one value so not pattern

    Output:
    - Payload : long

    Analyze:
    - Finalize Time complexity
    - Finalize Space complexity

    Implement:
    - Start implementing

    Test:
    - Test on few small manual tests - done with n = 3, 10.
    - Test on smallest answers = -2123456789, 45
    - Time limit FAILED
    - Memory limit PASSED
    - Corner cases for input - negative values, input values until 45, zero - Passed.

      STRESS TESTING - 1) solution to test, 2) slow but correct solution, 3) test generator, 4) infinite SinglyLinkedListLoop in which a new test is generated.
      STRESS TESTING - Find smallest test cases on which your solution fails.
      STRESS TESTING - Try different regions of the test space when generating cases for stress testing.
      STRESS TESTING - Outcomes - Wrong answer, time limit exceeded, memory limit exceeded, wrong output format, unknown signal.

     */
  private static long calc_fib(int n) {
    if (n <= 1)
      return n;

    return calc_fib(n - 1) + calc_fib(n - 2);
  }

  private static long naive(int n) {
    if(n <= 1) {
      return n;
    }
    return naive(n-1) + naive(n-2);
  }

  private static long fast(int n) {

    if(n <= 1) {
      return n;
    }
    int previous = 0;
    int current = 1;

    for( int i = 2; i <= n; i++) {
      int temp = current;
      current = previous + current;
      previous = temp;
    }
    return current;
  }

  private static long calc_fibFast(int n) {
    if (n <= 1)
      return n;

    int[] F = new int[n];

    F[0] = 1;
    F[1] = 1;
    for(int i = 2; i < n; i++) {
      F[i] = F[i-1] + F[i-2];
    }

    return F[n - 1];
  }

  public static void main(String args[]) {

    while(true) {
      int maxInput = 60;
      int minInput = 10;
      int randomNum = new Random().nextInt(maxInput - minInput + 1) + minInput;
      System.out.println("Size:" + randomNum);
      long naiveSolution = naive(randomNum);
      long fastSolution = fast(randomNum);
      if(naiveSolution == fastSolution) {
        System.out.println("OK");
      } else {
        System.out.println("Wrong answer: " + naiveSolution + " " + fastSolution);
        break;
      }
    }

    /*while(true) {
      // IndexRange
      int max = 45, min = 0;
      int randomNum = new Random().nextInt((max - min) + 1) + min;
      System.out.println("Size: " + randomNum);
      long bruteForceSolution = calc_fib(randomNum);
      long optimizedSolution = calc_fibFast(randomNum);
      if(bruteForceSolution == optimizedSolution) {
        System.out.println("OK");
      } else {
        System.out.println("Wrong answer: " + bruteForceSolution + " " + optimizedSolution);
        break;
      }
    }*/

    Scanner in = new Scanner(System.in);
    int n = in.nextInt();

    //System.out.println(calc_fib(n));
    System.out.println(calc_fibFast(n));
  }
}
