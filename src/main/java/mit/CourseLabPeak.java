package mit;

import java.util.concurrent.ThreadLocalRandom;

public class CourseLabPeak {

	/* Time Complexity - O(N) */
	public static void findPeakNaive(int[] array) {
		long start = System.nanoTime();
		boolean peakExists = false;
		for(int i = 0; i < array.length - 1; i++) {
			if(i == 0) {
				continue;
			} else if(i == array.length - 1) {
				if(array[i] >= array[i-1]) {
					peakExists = true;
					break;
				}
			} else {
				if(array[i] >= array[i-1] && array[i] >= array[i+1]) {
					peakExists = true;
					break;
				}
			}
		}
		System.out.println(peakExists);
		System.out.println("Naive: " + (System.nanoTime() - start));
	}

	/*
		psuedocode:
		if(n<=1) {peak not found}
		find middle
		look at neighbors - O(1)
		if greater than neighbors - peak found - O(1)
		else recurse

		T(n) = O(1) + T(n/2)
		T(n) = O(1) + O(1) + T(n/4)
		T(n) = O(log n)
		T(1) = O(1)
	 */
	public static void findPeakOptimizedRecurse(int[] array) {
		long start = System.nanoTime();
		boolean peakExists = findPeakOptimizedRecurse(array, 0, array.length - 1);
		System.out.println(peakExists);
		System.out.println("Optimized Recursion: " + (System.nanoTime() - start));
	}

	/*
	requires indexes in each recursion
	do not require additional data structure to track anything
	 */
	private static boolean findPeakOptimizedRecurse(int[] array, int start, int end) {
		// base case
		if(end <= start) {
			return false;
		}

		// Pre-process
		int middle = (start + end) / 2;
		boolean isTallerThanLeft = array[middle] > array[middle-1];
		boolean isTallerThanRight = array[middle] > array[middle+1];

		// Then decide - Stop, recurse left, recurse right
		if(isTallerThanLeft && isTallerThanRight) {
			return true;
		} else if(!isTallerThanLeft){
			return findPeakOptimizedRecurse(array, start, middle - 1);
		} else {
			return findPeakOptimizedRecurse(array, middle + 1, end);
		}
	}

	private static int findPeakElementOptimizedRecurse(int[][] array, int startColumn, int endColumn) {
		if(endColumn <= startColumn) {
			int rowIndex = findMaxRowIndex(array, startColumn);
			// in this case we want value
			return array[rowIndex][startColumn];
		}

		int midColumn = (startColumn + endColumn) / 2;
		int rowIndex = findMaxRowIndex(array, midColumn);

		// in this case we want value
		boolean isTallerThanLeft = array[rowIndex][midColumn] > array[rowIndex][midColumn-1];
		boolean isTallerThanRight = array[rowIndex][midColumn] > array[rowIndex][midColumn+1];
		if(isTallerThanLeft && isTallerThanRight) {
			// second return
			return array[rowIndex][midColumn];
		} else if(!isTallerThanLeft) {
			return findPeakElementOptimizedRecurse(array, startColumn, midColumn - 1);
		} else {
			return findPeakElementOptimizedRecurse(array, midColumn+1, endColumn);
		}
	}

	/*

	 */
	private static int findMaxRowIndex(int[][] array, int column) {
		return 0;
	}


	public static void findPeakOptimizedIterative(int[] array) {
		long start = System.nanoTime();
		boolean peakExists = false;
		int low = 0;
		int high = array.length - 1;
		while(low <= high) {
			int mid = low + (high - low) / 2;
			if(array[mid] < array[mid - 1]) {
				high = mid - 1;
			} else if (array[mid] < array[mid + 1]) {
				low = mid + 1;
			} else {
				peakExists = true;
				break;
			}
		}
		System.out.println(peakExists);
		System.out.println("Optimized Iterative: " + (System.nanoTime() - start));
	}
	
	public static void main(String[] args) {
		int[] array = new int[10];
		for(int i = 0; i < array.length; i++) {
			array[i] = ThreadLocalRandom.current().nextInt(0, 50 + 1);
			System.out.print(array[i] + " ");
		}
		System.out.println();
		findPeakNaive(array);
		findPeakOptimizedIterative(array);
	}

}
