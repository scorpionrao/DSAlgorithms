package datastructures.basicdatastructures;

import java.util.Random;

public class LinkedListRemoveNthFromLast {

    public static class Node {
        int data;
        Node next;
        Node(int data) {
            this.data = data;
        }
    }

    public static class Solution {

        public Node createLinkedList(int length) {
            Random random = new Random();
            Node head = new Node(random.nextInt(100));
            Node current = head;
            for(int i = 0; i < length-1; i++) {
                current.next = new Node(random.nextInt(100));
                current = current.next;
            }
            return head;
        }

        public void print(Node node) {
            if (node == null) {
                return;
            }
            Node current = node;
            while (current != null) {
                System.out.print(current.data);
                if (current.next != null) {
                    System.out.print(" --> ");
                }
                current = current.next;
            }
            System.out.println();
        }

        /*
            Length of Linked list --> L
            Traverses to end of Linked list --> L-1

            Initial --> Fast moves n
            (This establishes a gap of n + 1)
            Final   --> Fast and Slow moves L - n - 1
            (Fast reaches end)
            Final   --> Slow is still n away from end.

            Remove the node pointing to slow pointer.
         */
        public Node removeNthFromLast(Node head, int numOfNodesFromEnd) {
            Node fastPointer = head;
            Node slowPointer = head;
            while(numOfNodesFromEnd >= 0) {
                fastPointer = fastPointer.next;
                numOfNodesFromEnd--;
            }
            while(fastPointer != null) {
                slowPointer = slowPointer.next;
                fastPointer = fastPointer.next;
            }
            slowPointer.next = slowPointer.next.next;
            return head;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        Node head = solution.createLinkedList(7);
        solution.print(head);
        head = solution.removeNthFromLast(head, 2);
        solution.print(head);
    }
}
