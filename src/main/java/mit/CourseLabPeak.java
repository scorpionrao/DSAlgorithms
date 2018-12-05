package mit;

import java.util.Arrays;
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
		else recursionBacktracking

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

		// Pre-mergeKLists
		int middle = (start + end) / 2;
		boolean isTallerThanLeft = array[middle] > array[middle-1];
		boolean isTallerThanRight = array[middle] > array[middle+1];

		// Then decide - Stop, recursionBacktracking left, recursionBacktracking right
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

	private static int findMaxRowIndex(int[][] array, int column) {
		return 0;
	}


	/*
		PEAK OF THE MOUNTAIN == VALUE WHOSE BOTH SIDES ARE LESS

		Input --> OUTPUT
		[1, 3, 2] --> 3
		[1, 2, 5] --> 5 (ONE SIDED MOUNTAIN)
		[4, 3, 1] --> 4 (ONE SIDED MOUNTAIN)

		NULL --> -1
		[] --> -1
		[3] --> 3 (ONLY ELEMENT)
	 */

	public static int findPeak(int[] array) {

		if(array == null || array.length == 0) {
			return -1;
		}
		if(array.length == 1) {
			return array[0];
		}
		if(array.length == 2) {
			return array[0] > array[1] ? array[0] : array[1];
		}

		int low = 0;
		int high = array.length - 1;
		int mid = 0;
		while(low <= high) {
			mid = (low + high) / 2;
			if(mid == 0 || mid == array.length - 1) {
				return array[mid];
			}
			if (array[mid] > array[mid - 1] && array[mid] > array[mid+1]) {
				return array[mid];
			}
			if(array[mid] < array[mid + 1]) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return array[mid];
	}


	static void print(int[] nums) {
		System.out.println(Arrays.toString(nums) + " --> " + findPeak(nums));
	}

	public static void main(String[] args) {
		/*
		int[] array = new int[10];
		for(int i = 0; i < array.length; i++) {
			array[i] = ThreadLocalRandom.current().nextInt(0, 50 + 1);
			System.out.print(array[i] + " ");
		}
		System.out.println();
		findPeakNaive(array);
		findPeakOptimizedIterative(array);
		*/
		System.out.println("PEAK OF MOUNTAIN == VALUE WHOSE BOTH SIDES ARE LESS");
		System.out.println("LOOKING EVERY ELEMENT TO FIND ANSWER - INEFFICIENT SOLUTION");
		System.out.println("EFFICIENT SOLUTION - ABOVE CODE");
		System.out.println("Input --> OUTPUT");
		int[] nums1 = {1, 3, 2};
		print(nums1);
		int[] nums2 = {1, 2, 5};
		print(nums2);
		int[] nums3 = {4, 3, -5};
		print(nums3);
		int[] nums4 = {10};
		print(nums4);
		int[] nums5 = {};
		print(nums5);
		int[] nums6 = null;
		print(nums6);
		int[] nums7 = {0, 2};
		print(nums7);
		int[] nums8 = {-500, -499, 7, -99, -100};
		print(nums8);
	}

}
