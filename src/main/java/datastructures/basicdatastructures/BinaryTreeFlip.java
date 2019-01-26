package datastructures.basicdatastructures;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeFlip {

    public static class Node {
        int data;
        Node left;
        Node right;
        public Node(int data) {
            this.data = data;
        }
    }
    public static class Solution {

        /*
            Time: O(N), Space: O(1)
            Post Order DFS
        */
        public Node flipRecurse(Node node) {
            if(node == null) {
                return null;
            }

            node.left = flipRecurse(node.left);
            node.right = flipRecurse(node.right);

            Node temp = node.left;
            node.left = node.right;
            node.right = temp;
            return node;
        }

        public void print(Node node) {

            if(node == null) {
                return;
            }
            print(node.left);
            System.out.print(node.data + " -> ");
            print(node.right);
        }

        /*
            Time: O(N), Space: O(N)
            Post Order BFS
        */
        public Node flipIterate(Node node) {

            if(node == null) {
                return node;
            }

            Queue<Node> queue = new LinkedList<>();
            queue.add(node);
            while(!queue.isEmpty()) {
                Node polled = queue.poll();
                Node temp = polled.left;
                polled.left = polled.right;
                polled.right = temp;
                if(polled.left != null) {
                    queue.add(polled.left);
                }
                if(polled.right != null) {
                    queue.add(polled.right);
                }
            }
            return node;
        }
    }



    public static void main(String[] args) {
        Node node = new Node(1);
        node.left = new Node(2);
        node.right = new Node(3);

        node.left.left = new Node(4);
        node.left.right = new Node(5);
        node.right.left = new Node(6);
        node.right.right = new Node(7);

        Solution solution = new Solution();
        solution.print(node);
        System.out.println();
        solution.flipRecurse(node);
        solution.print(node);
        System.out.println();
        solution.flipIterate(node);
        solution.print(node);
    }
}
