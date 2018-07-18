package zr;

import java.util.List;

public class LinkedListSum {

    public static class Node {
        public int data;
        public Node next;
        public Node(int data) {
            this.data = data;
        }
    }

    public static class Solution {
        public Node sum(Node head1, Node head2) {
            if(head1 == null) {
                return head2;
            }
            if(head2 == null) {
                return head1;
            }

            Node sumHead = null;
            Node current = null;
            int carry = 0;
            while(head1 != null || head2 != null || carry != 0) {
                int sum = 0;
                if(head1 != null) {
                    sum = sum + head1.data;
                    head1 = head1.next;
                }
                if(head2 != null) {
                    sum = sum + head2.data;
                    head2 = head2.next;
                }
                sum = sum + carry;
                int reminder = sum % 10;
                Node newNode = new Node(reminder);
                if(sumHead == null) {
                    sumHead = newNode;
                    current = newNode;
                } else {
                    current.next = newNode;
                    current = current.next;
                }
                carry = sum / 10;
            }
            return sumHead;
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
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        Node head1 = new Node(9);
        head1.next = new Node(4);
        head1.next.next = new Node(3);

        Node head2 = new Node(1);
        head2.next = new Node(8);
        head2.next.next = new Node(7);
        head2.next.next.next = new Node(6);

        Node head = solution.sum(head1, head2);
        solution.print(head);

    }
}
