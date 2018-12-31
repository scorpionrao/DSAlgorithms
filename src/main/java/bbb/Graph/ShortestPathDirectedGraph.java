package bbb.Graph;

import java.util.*;

public class ShortestPathDirectedGraph {

    private static class Node {
        int key;
        List<Node> oppositeEnds;
        Node(int key) {
            this.key = key;
            oppositeEnds = new ArrayList<>();
        }

        @Override
        public String toString() {
            return String.valueOf(this.key);
        }
    }

    private static List<Node> shortestPath(Node src, Node dest) {

        if(src == null || dest == null) {
            return null;
        }

        Queue<Node> queue = new LinkedList<>();
        Map<Node, Node> parents = new HashMap<>();

        queue.offer(src);
        parents.put(src, null);

        while(!queue.isEmpty()) {
            Node node = queue.poll();
            if(node.key == dest.key) {
                break;
            } else {
                for(Node opposite : node.oppositeEnds) {
                    if(!parents.containsKey(opposite)) {
                        queue.add(opposite);
                        parents.put(opposite, node);
                    }
                }
            }
        }

        List<Node> result = new ArrayList<>();
        if(parents.containsKey(dest)) {
            Node parent = dest;
            while(parent != null) {
                result.add(parent);
                parent = parents.get(parent);
            }
        }

        Collections.reverse(result);
        return result;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);

        node1.oppositeEnds.add(node2);
        node1.oppositeEnds.add(node3);

        node2.oppositeEnds.add(node5);

        node4.oppositeEnds.add(node1);
        node4.oppositeEnds.add(node3);

        node5.oppositeEnds.add(node4);

        List<Node> shortestPath = shortestPath(node2, node3);
        System.out.println("Expect : [2, 5, 4, 3]");
        System.out.println("Actual : " + shortestPath.toString());
    }
}
