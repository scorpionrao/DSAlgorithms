package mit;

public class CourseLabHeap {
	
	public static void maxHeapify(int[] array, int endOfHeapIndex, int i) {
		// simple analysis - O(log N)
		// detailed analysis - O(N)
		
		// Root of tree - array[0]
		// parent(i) = array[i/2], root is parent of array[1], array[2]
		// leftChild(i) = array[2i]
		// rightChild(i) = array[2i+1]
		// shiftDown - swap with largest parent
	}
	
	public static void buildHeapAsTree(int[] array) {
		for(int i = array.length / 2; i >= 0; i--) {
			maxHeapify(array, array.length - 1, i);
		}
	}
	
	public static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	// sort in O(N) and in-place
	/*
	 * Build max heap from unsorted array - O(N)
	 * extract max - O(1)
	 * Swap elements 1 and end of heap - O(1)
	 * Max elements is at end of array - O(1)
	 * Decrement heap size (discard extracted elements at end) - O(1)
	 * New root may violate max heap but children are max heaps - max heapify - O(log N)
	 */
	public static void heapSort(int[] unsortedArray) {
		buildHeapAsTree(unsortedArray);
		int endOfHeapIndex = unsortedArray.length - 1;
		while(endOfHeapIndex >= 0) {
			int max = unsortedArray[0];
			swap(unsortedArray, 0, endOfHeapIndex);
			endOfHeapIndex--;
			maxHeapify(unsortedArray, endOfHeapIndex, 0);
		}
		
		// max elements in the array are sorted as required
		for(Integer i : unsortedArray) {
			System.out.println(i + " ");
		}
	}
	
	public static void main(String[] args) {
		int[] unsortedArray = {4, 6, 1, 7, 3, 2, 5, 0};
		heapSort(unsortedArray);
	}

}
