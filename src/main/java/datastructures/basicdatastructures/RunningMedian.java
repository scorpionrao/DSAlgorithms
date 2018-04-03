package datastructures.basicdatastructures;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class RunningMedian {

    public static class MaxHeapComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer x, Integer y) {
            return y - x;
        }
    }

    public static class MinHeapComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer x, Integer y) {
            return x - y;
        }
    }

    public static class MedianFinder {

        private PriorityQueue<Integer> maxHeap;
        private PriorityQueue<Integer> minHeap;

        public MedianFinder(int numOfValues) {
            maxHeap = new PriorityQueue<>(numOfValues, new MaxHeapComparator());
            minHeap = new PriorityQueue<>(numOfValues, new MinHeapComparator());
        }

        public void add(int newInput) {

            if(maxHeap.size() == minHeap.size()) {
                // the new element should go to max heap
                if(minHeap.peek() != null && newInput > minHeap.peek()) {
                    maxHeap.offer(minHeap.poll());
                    minHeap.offer(newInput);
                } else {
                    maxHeap.offer(newInput);
                }
            } else {
                if(newInput < maxHeap.peek()) {
                    minHeap.offer(maxHeap.poll());
                    maxHeap.offer(newInput);
                } else {
                    minHeap.offer(newInput);
                }
            }
            printMedian();
        }

        public void printMedian() {
            double median = 0.0;
            if(maxHeap.isEmpty()) {
                System.out.println(median);
                return;
            }
            if(maxHeap.size() == minHeap.size()) {
                median = ((double) maxHeap.peek() + (double)minHeap.peek()) / 2;
            } else {
                median = (double) maxHeap.peek();
            }
            System.out.println(median);
        }

    }
    public static void main(String args[] ) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int numOfValues = scanner.nextInt();

        MedianFinder medianFinder = new MedianFinder(numOfValues);
        for(int i = 0; i < numOfValues; i++) {
            int nextInput = scanner.nextInt();
            medianFinder.add(nextInput);
        }
    }
}
