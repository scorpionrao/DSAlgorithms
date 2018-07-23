package zr;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class LinkedListReversalAndPalindromeCheck {

    public static class Node {
        public char aChar;
        public Node next;
        public Node(char aChar) {
            this.aChar = aChar;
        }
    }

    /*
        Time complexity - O(N)
        Space complexity - O(2*N)
     */
    public static boolean isPalindrome(Node node) {
        Stack<Node> stack = new Stack();
        Queue<Node> queue = new LinkedList<>();
        while (node != null) {
            stack.push(node);
            queue.offer(node);
            node = node.next;
        }

        while (!stack.isEmpty()) {
            Node stackNode = stack.pop();
            Node queueNode = queue.poll();
            if(stackNode.aChar != queueNode.aChar) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Node root = new Node('m');
        root.next = new Node('a');
        root.next.next = new Node('d');
        root.next.next.next = new Node('a');
        root.next.next.next.next = new Node('m');
        System.out.println(isPalindrome(root));
    }
}
