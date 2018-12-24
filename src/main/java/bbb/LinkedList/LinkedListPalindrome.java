package bbb.LinkedList;

import java.util.Stack;

public class LinkedListPalindrome {

    public static class Node {
        private int key;
        private Node next;
        Node(int key) {
            this.key = key;
            this.next = null;
        }
    }

    private static boolean isPalindrome(Node head) {

        Node slow = head;
        Node fast = head;
        Stack<Integer> stack = new Stack<>();

        // odd and even scenario
        while(fast != null && fast.next != null) {
            stack.push(slow.key);
            slow = slow.next;
            fast = fast.next.next;
        }

        // odd scenario
        if(fast != null) {
            slow = slow.next;
        }

        // while(slow != null) is also good
        while(!stack.isEmpty()) {
            if(stack.pop() != slow.key) {
                return false;
            }
            slow = slow.next;
        }
        return true;
    }

    private static void  print(Node head) {
        Node current = head;
        while(current != null) {
            System.out.print(current.key + " --> ");
            current = current.next;
        }
        System.out.print("NULL");
        System.out.println();
    }

    public static void main(String[] args) {
        Node[] nodes = new Node[10];
        for(int i = 0; i < nodes.length / 2; i++) {
            nodes[i] = new Node(i);
            nodes[nodes.length - i - 1] = new Node(i);
        }
        for(int i = 0; i < nodes.length - 1; i++) {
            nodes[i].next = nodes[i+1];
        }
        print(nodes[0]);
        System.out.println("Is Palindrome : " + isPalindrome(nodes[0]));
    }
}
