package leetcode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class PalindromeLinkedList {

    private static class Node {
        int key;
        Node next;

        Node(int key) {
            this.key = key;
            this.next = null;
        }
    }

    public static void print(Node root) {
        Node current = root;
        while(current != null) {
            System.out.print(current.key + " --> ");
            current = current.next;
        }
        System.out.print("NULL");
        System.out.println();
    }

    /*
        Time : O(N)
        Space : O(2*N)
     */
    private static boolean isPalindromeApproach1(Node root) {
        Stack<Integer> stack = new Stack();
        Queue<Integer> queue = new LinkedList<>();

        Node current = root;
        while(current != null) {
            stack.push(current.key);
            queue.offer(current.key);
            current = current.next;
        }

        while(!stack.isEmpty() || !queue.isEmpty()) {
            if(stack.pop().intValue() != queue.poll().intValue()) {
                return false;
            }
        }
        return true;
    }

    /*
        Time : O(N)
        Space : O(1)
     */
    private static boolean isPalindromeApproach2(Node head) {

        Node slow = head;
        Node fast = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        if(fast != null) {
            slow = slow.next;
        }

        slow = reverse(slow);
        fast = head;

        while(slow != null) {
            if(slow.key != fast.key) {
                return false;
            }
            slow = slow.next;
            fast = fast.next;
        }
        return true;
    }

    private static Node reverse(Node head) {
        Node prev = null;
        while(head != null) {
            Node temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;
        }
        return prev;
    }

    private static void evaluate(Node root) {
        print(root);
        System.out.println("Approach 1 : " + isPalindromeApproach1(root));
        System.out.println("Approach 2 : " + isPalindromeApproach2(root));
    }

    public static void main(String[] args) {

        Node root1 = new Node(1);
        root1.next = new Node(2);
        root1.next.next = new Node(3);
        root1.next.next.next = new Node(2);
        root1.next.next.next.next = new Node(1);
        evaluate(root1);

        Node root2 = new Node(1);
        root2.next = new Node(2);
        root2.next.next = new Node(2);
        root2.next.next.next = new Node(1);
        evaluate(root2);

        Node root3 = new Node(1);
        root3.next = new Node(2);
        root3.next.next = new Node(3);
        root3.next.next.next = new Node(2);
        root3.next.next.next.next = new Node(4);
        evaluate(root3);

        Node root4 = new Node(1);
        root4.next = new Node(2);
        root4.next.next = new Node(4);
        root4.next.next.next = new Node(1);
        evaluate(root4);
    }
}
