package mit;

import java.util.ArrayList;
import java.util.List;

/*
 * COMPARISON BASED SORTING
 * Best Case - If items are not preprocessed - n log n
 * Best Case - If items are preprocessed - log n
 */
public class CourseLabCountingSort {
	
	// O(N + range)
	public static void countingSort(int[] input, int range) {
		int[] counters = new int[range];
		for(int i = 0; i < input.length; i++) {
			counters[input[i]] = counters[input[i]] + 1;
		}
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < counters.length; i++) {
			while(counters[i] != 0) {
				list.add(i);
				counters[i]--;
			}
		}
		System.out.println(list.toString());
	}
	
	public static void main(String[] args) {
		int[] input = {3, 7, 3, 5, 5, 4};
		int range = 8;
		countingSort(input, range);
	}

}
