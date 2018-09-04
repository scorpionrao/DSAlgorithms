package datastructures.priorityqueues;

import java.util.PriorityQueue;
import java.util.Scanner;

public class PriorityQueueDocumentation {

    /*

        Comparison based algorithm (not data structure), run time O(N log N), in-place comparison

        Examples:

        Dijsktra's algorithm - finding a shortest path in a graph

        Prim's algorithm - constructing a minimum spanning tree of a graph - network of computers with min length

        Huffman's algorithm - constructing an optimum prefix-free Encoding of a string

        Heap sort - sorting a given sequence

        Shift up
        Shift down
        extract = remove max, replace with any leaf, shift down - O(log N)
        insertAtHead = insertAtHead as any leaf, shift up - O(log N)
        remove = replace removing element with any leaf, shift up or down

        Shallow Height ensures O(log N) - requires balanced tree
        extract = remove max, replace with LEFT MOST leaf (ensured balance), shift down
        insertAtHead = insertAtHead LEFT MOST(ensured balance), shift up

        DATA STRUCTURE implementation - ARRAY

        Priority Queue - Abstract data type

        ARRAY -> BINARY TREE -> BINARY (sorted) HEAP (**DS**) -> BALANCED (heap) TREE -> PRIORITY QUEUE -> HEAP SORT

        Priority Queue - Fast and Space Efficient.
        Heap Sort - Sort taking advantage of Priority queue.

        1-based array
        Parent(i) { return array[i/2] }
        LeftChild(i) { return array[2i] }
        RightChild(i) { return array[2i+1] }

        0-based array
        Parent(i) { return array[i-1/2] }
        LeftChild(i) { return array[2i+1] }
        RightChild(i) { return array[2i+2] }


        shiftUp {
            while(not root) and (array[Parent(i)] < array[i]) {
                swap array[Parent(i)] & array[i]
                i = Parent(i)
            }
        }

        PSEUDO Code : HEAP SORT (comparison based)

        HeapSort(A[0...N-1) {
            Create empty priority queue
            for i = 0 to N-1
                Insert A[i]
            for i = N-1 to 1
                A[i] = ExtractMax()
        }

        Time: O(N log N)
        Space: Create new Priority Queue

        IN-PLACE HEAP SORT:

        // create a non-violating heap out of array in-place
        Running Time: 2n --> O(N)

        BuildHeap1(A[0...N-1]) {
            for i = n/2 to 1 {
                shiftDown(A[i])
            }
        }

        // heap sort - in-place + O(N long N) running time
        HeapSort(A[0...N-1]) {
            BuildHeap1(A)
            for i = N-1 to 0 {
                swap A[i] and A[0]
                shiftDown(A[0])
            }
        }

        // partial sorting -
        PartialSort(

     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] array = new int[size];

        for(int i = 0; i < array.length; i++) {
            array[i] = scanner.nextInt();
        }
        //heapSortNLogNTime(array);
        buildHeap(array);
        for(int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }

    }

    private static void heapSortNLogNTime(int[] array) {
        // HEAPSORT
        PriorityQueue<Integer> pq = new PriorityQueue();
        for(int i = 0; i < array.length; i++) {
            pq.add(array[i]);
        }
        for(int i = array.length - 1; i >= 0; i--) {
            array[i] = pq.poll();
        }
    }

    private static void buildHeap(int[] array) {
        for(int i = array.length - 1; i >= 0; i--) {
            shiftDown(array, i);
        }
    }

    private static void shiftUp(int[] array, int i) {
        while(i > 0 && array[Parent(i)] < array[i]) {
            swap(array, Parent(i), i);
            i = Parent(i);
        }
    }

    private static void shiftDown(int[] array, int i) {
        int maxIndex = i;
        int l = LeftChild(i);
        if (l < array.length && array[l] > array[maxIndex]) {
            maxIndex = l;
        }
        int r = RightChild(i);
        if (r < array.length && array[r] > array[maxIndex]) {
            maxIndex = r;
        }
        if(i != maxIndex) {
            swap(array, i, maxIndex);
            shiftDown(array, maxIndex);
        }
    }

    private static int Parent(int i) {
        return (i-1) / 2;
    }

    private static int LeftChild(int i) {
        return 2 * i + 1;
    }

    private static int RightChild(int i) {
        return 2 * i + 2;
    }

    private static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
