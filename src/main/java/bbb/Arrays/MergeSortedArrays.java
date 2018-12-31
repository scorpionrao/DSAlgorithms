package bbb.Arrays;

import java.util.*;

public class MergeSortedArrays {

    /* Time: O(kn * log(kn), Space: O(1) */
    private static List<Integer> mergeSortedArraysApproach1(List<List<Integer>> input) {
        List<Integer> result = new ArrayList<>();
        for(List<Integer> list : input) {
            result.addAll(list);
        }
        Collections.sort(result);
        return result;
    }

    /*
        Time: O(kn * k,
        Space: O(1)

        K - comparisons
    */
    private static List<Integer> mergeSortedArraysApproach2(List<List<Integer>> input) {
        List<Integer> result = new ArrayList<>();
        int total = 0;
        for(List<Integer> list : input) {
            total += list.size();
        }

        while(total - 1 >= 0) {
            int minHead = Integer.MAX_VALUE;
            int minHeadIndex = -1;
            for(int i = 0; i < input.size(); i++) {
                if(!input.get(i).isEmpty() && input.get(i).get(0) < minHead) {
                    minHead = input.get(i).get(0);
                    minHeadIndex = i;
                }
            }
            result.add(input.get(minHeadIndex).remove(0));
            total--;
        }
        return result;
    }

    public static class Node implements Comparable<Node> {
        int arrayIndex;
        int valueIndex;
        int value;

        public Node(int arrayIndex, int valueIndex, int value) {
            this.arrayIndex = arrayIndex;
            this.valueIndex = valueIndex;
            this.value = value;
        }

        public int compareTo(Node node) {
            if(this.value > node.value) {
                return 1;
            } else if(this.value < node.value) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    /* Time: O(kn * log k, Space: O(1) */
    public static List<Integer> mergeSortedArraysApproach3(List<List<Integer>> input) {

        PriorityQueue<Node> pq = new PriorityQueue<>(input.size());

        int size = 0;
        for(int i = 0; i < input.size(); i++) {
            size += input.get(i).size();
            pq.add(new Node(i, 0, input.get(i).get(0)));
        }

        List<Integer> result = new ArrayList<>(size);
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            //
            result.add(node.value);
            //
            int arrayIndex = node.arrayIndex;
            int valueIndex = node.valueIndex;
            if(input.get(arrayIndex).size() - 1 > valueIndex) {
                pq.add(new Node(arrayIndex, valueIndex+1, input.get(arrayIndex).get(valueIndex+1)));
            }
        }
        return result;
    }



    private static void evaluate(List<List<Integer>> input) {
        for(List list : input) {
            System.out.println(list.toString());
        }
        /*
        List<List<Integer>> list1 = new ArrayList<>(input);
        List<Integer> result1 = mergeSortedArraysApproach1(list1);
        System.out.println("Approach1   : " + result1.toString());
        List<List<Integer>> list2 = new ArrayList<>(input);
        List<Integer> result2 = mergeSortedArraysApproach2(list2);
        System.out.println("Approach2   : " + result2.toString());
        */
        List<List<Integer>> list3 = new ArrayList<>(input);
        List<Integer> result3 = mergeSortedArraysApproach3(list3);
        System.out.println("Approach3   : " + result3.toString());
    }

    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(4);
        list1.add(7);
        List<Integer> list2 = new ArrayList<>();
        list2.add(2);
        list2.add(5);
        list2.add(8);
        List<Integer> list3 = new ArrayList<>();
        list3.add(3);
        list3.add(6);
        list3.add(9);

        List<List<Integer>> input = new ArrayList<>();
        input.add(list1);
        input.add(list2);
        input.add(list3);

        evaluate(input);
    }
}
