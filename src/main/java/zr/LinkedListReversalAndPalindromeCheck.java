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
    public static boolean isPalindromeWithoutReversal(Node node) {
        Stack<Node> stack = new Stack<>();
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

    /*
        Time complexity - O(N)
        Space complexity - O(N)
     */
    public static Node reverseLinkedListUnlimitedSpace(Node node) {

        Stack<Node> stack = new Stack<>();
        while (node != null) {
            stack.push(node);
            node = node.next;
        }

        Node root = new Node('a');
        Node current = root;
        while (!stack.isEmpty()) {
            current.next = stack.pop();
            current = current.next;
        }
        return root.next;
    }

    /*
        Time complexity - O(N)
        Space complexity - O(1)
     */
    public static Node reverseLinkedList(Node node) {

        Node current = node;
        Node headOfNewList = null;
        Node headOfRemainingList = null;

        while(current != null) {
            headOfRemainingList = current.next;
            current.next = headOfNewList;
            headOfNewList = current;
            current = headOfRemainingList;
        }
        return headOfNewList;
    }

    public static void print(Node node) {
        if (node == null) {
            return;
        }
        Node current = node;
        while (current != null) {
            System.out.print(current.aChar);
            if (current.next != null)
            {
                System.out.print(" --> ");
            }
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node root = new Node('n');
        root.next = new Node('a');
        root.next.next = new Node('r');
        root.next.next.next = new Node('a');
        root.next.next.next.next = new Node('y');
        root.next.next.next.next.next = new Node('a');
        root.next.next.next.next.next.next = new Node('n');
        root.next.next.next.next.next.next.next = new Node('a');
        System.out.println("isPalindrome1 - " + isPalindromeWithoutReversal(root));

        print(root);
        Node reverseHead = reverseLinkedList(root);
        print(reverseHead);
    }
}
