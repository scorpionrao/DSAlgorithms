package live;

import java.util.*;
import java.lang.Math;

public class ClosestXdestinations {

    public static class Node implements Comparable<Node> {
        int x;
        int y;
        double drivingDistance;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
            calculateDrivingDistance();
        }

        public void calculateDrivingDistance() {
            int absX = Math.abs(this.x);
            int absY = Math.abs(this.y);
            double absXSquare = Math.pow(absX, 2);
            double absYSquare = Math.pow(absY, 2);
            this.drivingDistance = Math.sqrt(absXSquare + absYSquare);
        }

        @Override
        public int compareTo(Node other) {

            if(other == null) {
                return -1;
            } else if (this == other) {
                return 0;
            }

            Double distance1 = new Double(this.drivingDistance);
            Double distance2 = new Double(other.drivingDistance);
            return distance1.compareTo(distance2);
        }
    }

    public List<List<Integer>> closestXDestinations(int numOfDestinations, List<List<Integer>> allLocations,
                                                    int numOfDeliveries) {

        if(allLocations == null || numOfDestinations < 1) {
            return new ArrayList<>();
        }
/*
        PriorityQueue<Node> pq = new PriorityQueue<>(allLocations.size(),
                new Comparator<Node>() {
                    @Override
                    public int compare(Node node1, Node node2) {
                        Double distance1 = new Double(node1.drivingDistance);
                        Double distance2 = new Double(node2.drivingDistance);
                        return distance1.compareTo(distance2);
                    }
                });
*/

        PriorityQueue<Node> pq = new PriorityQueue<>(allLocations.size());

        for(List<Integer> location : allLocations) {
            pq.add(new Node(location.get(0), location.get(1)));
        }

        List<List<Integer>> result = new ArrayList<>();
        while(!pq.isEmpty() && numOfDeliveries > 0) {
            Node node = pq.poll();
            List<Integer> nextLocation = new ArrayList<>(Arrays.asList(node.x, node.y));
            result.add(nextLocation);
            numOfDeliveries--;
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 1));
        List<Integer> list2 = new ArrayList<>(Arrays.asList(2, 2));
        List<Integer> list3 = new ArrayList<>(Arrays.asList(1, 2));
        List<List<Integer>> allLocations = new ArrayList<>(Arrays.asList(list1, list2, list3));
        List<List<Integer>> result =
                new ClosestXdestinations().closestXDestinations(allLocations.size(), allLocations, 2);
        System.out.println(result.toString());
    }
}