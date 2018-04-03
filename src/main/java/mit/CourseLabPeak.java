package mit;

import java.util.concurrent.ThreadLocalRandom;

public class CourseLabPeak {
	
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
				}
			}
		}
		System.out.println(peakExists);
		System.out.println("Naive: " + (System.nanoTime() - start));
	}
	
	public static void findPeakOptimized(int[] array) {
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
		System.out.println("Optimized: " + (System.nanoTime() - start));
	}
	
	public static void main(String[] args) {
		int[] array = new int[10];
		for(int i = 0; i < array.length; i++) {
			array[i] = ThreadLocalRandom.current().nextInt(0, 50 + 1);
			System.out.print(array[i] + " ");
		}
		System.out.println();
		findPeakNaive(array);
		findPeakOptimized(array);
	}

}
