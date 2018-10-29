package live;

import algorithms.cracking.KthToLastBST;

import java.util.*;
import java.lang.Math;

public class ClosestXdestinations {

    public static class Node implements Comparable<Node> {
        public int x;
        public int y;
        public double deliveryDistance;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
            calculateHypotenuse();
        }

        private void calculateHypotenuse() {
            int absX = Math.abs(x);
            int absY = Math.abs(y);
            this.deliveryDistance = Math.sqrt(Math.pow(absX, 2) + Math.pow(absY, 2));
        }

        @Override
        public int compareTo(Node o) {
            return new Double(this.deliveryDistance).compareTo(new Double(o.deliveryDistance));
        }
    }

    public List<List<Integer>> ClosestXdestinations(int numDestinations,
                                                    List<List<Integer>> allLocations,
                                                    int numDeliveries) {

        if(allLocations == null || allLocations.isEmpty() || numDeliveries < 1) {
            return new ArrayList<>();
        }
/*
        PriorityQueue<Node> pq = new PriorityQueue<>(allLocations.size(), new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                return new Double(node1.deliveryDistance).compareTo(new Double(node2.deliveryDistance));
            }
        });
*/
        PriorityQueue<Node> pq = new PriorityQueue<>(allLocations.size());


        for(List<Integer> list : allLocations) {
            pq.add(new Node(list.get(0), list.get(1)));
        }

        List<List<Integer>> result = new ArrayList<>();

        while (!pq.isEmpty() && numDeliveries > 0) {
            Node node = pq.poll();
            List<Integer> coordinates = new ArrayList<>(Arrays.asList(node.x, node.y));
            result.add(coordinates);
            numDeliveries--;
        }
        return result;
    }

    public static void main(String[] args)
    {
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(1);
        List<Integer> list2 = new ArrayList<>();
        list2.add(2);
        list2.add(2);
        List<Integer> list3 = new ArrayList<>();
        list3.add(2);
        list3.add(2);
        List<List<Integer>> allLocations = new ArrayList<>();
        allLocations.add(list1);
        allLocations.add(list2);
        allLocations.add(list3);
        List<List<Integer>> result = new ClosestXdestinations().ClosestXdestinations(3, allLocations, 2);
        System.out.println(result.toString());
    }
}