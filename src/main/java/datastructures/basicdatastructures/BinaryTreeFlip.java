package datastructures.basicdatastructures;

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

        public Node flip(Node node) {
            if(node == null) {
                return null;
            }

            flip(node.left);
            flip(node.right);

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
        solution.flip(node);
        solution.print(node);
    }
}
