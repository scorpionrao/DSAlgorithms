package mit;

import java.util.Arrays;

/*
 * Subproblems: 
 * F(k) for 1 to n with step size of 1
 * # of subproblems = n.
 * 
 * Guesses: 1
 * 
 * Recurrence: Relate the original problem to subproblem.
 * 			DP(i) = DP(i-1) + DP(i-2)
 * 
 * Recurrence time: T(n) = T(n-1) + T(n-2) + O(1)
 * 						 = T(n-2) + T(n-3) + T(n-2)
 * 						 >= 2 * T(n-2) = Theta(2^(n/2))
 * 
 * As 2 is generated every time n reduce by 2. So n/2 2's is generated until n = 1
 * 
 * Time/subproblem = Theta(1)
 * 
 * Topological order: for k = 1....n (memoized)
 * 
 * Total time: Theta(n)
 * 
 * Original prob: F(n), Extra time: O(1)
 * 
 */

public class CourseLabDP1Fibonacci {
	
	public static int fibonacciNaiveTopDown(int n) {
		if(n <=2) {
			return 1;
		}
		return fibonacciNaiveTopDown(n-1) + fibonacciNaiveTopDown(n-2);
	}
	
	public static int recursionMemoized(int n) {
		int[] dataStructure = new int[n];
		Arrays.fill(dataStructure, -1);
		return recursionMemoized(n, dataStructure);
	}
	
	public static int recursionMemoized(int n, int[] memoized) {
		// O(1) - will be encountered 2^n - n
		if(memoized[n-1] != -1) {
			return memoized[n-1];
		}
		if(n <= 2) {
			memoized[n-1] = 1;
			return memoized[n-1];
		}
		
		// recursion will be encountered O(N) times
		memoized[n-1] = recursionMemoized(n-1, memoized) + recursionMemoized(n-2, memoized);
		return memoized[n-1];
	}
	
	public static int recursionBottomUp(int n) {
		if(n <= 2) {
			return 1;
		}
		int previous = 1;
		int current = 1;
		for(int i = 2; i < n; i++) {
			int temp = current;
			current = previous + temp;
			previous = temp;
		}
		return current;
	}
	
	public static void printTimeTaken(long start, long end) {
		System.out.println("Time taken - " + (end - start));
	}
	
	public static void main(String[] args) {
		int index = 40;
		long start = System.currentTimeMillis();
		System.out.println("NaiveTopDown = " + fibonacciNaiveTopDown(index));
		long end = System.currentTimeMillis();
		printTimeTaken(start, end);
		
		start = System.currentTimeMillis();
		System.out.println("Memoized = " + recursionMemoized(index));
		end = System.currentTimeMillis();
		printTimeTaken(start, end);
		
		start = System.currentTimeMillis();
		System.out.println("BottomUp = " + recursionBottomUp(index));
		end = System.currentTimeMillis();
		printTimeTaken(start, end);
	}

}
