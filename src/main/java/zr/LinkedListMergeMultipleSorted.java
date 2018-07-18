package zr;


import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class LinkedListMergeMultipleSorted {

    public static class Node {
        public int data;
        public Node next;
        public Node(int data) {
            this.data = data;
        }
    }
    public static class Solution {

        /* O(L) */
        public Node deleteDuplicates(Node head) {
            Node current = head;
            
            // until all nodes are examined
            while(current != null) {
                // am i last node ?
                if(current.next == null) {
                    break;
                } // is the next node same ?
                else if(current.data == current.next.data) {
                    current.next = current.next.next;
                } // include and move on
                else {
                    current = current.next;
                }
            }
            return head;
        }

        /*
            Delete Duplicates within a list - O(L1)
            Delete Duplicates within a list - O(L2)

         */
        public Node mergeSortedLinkedLists(Node head1, Node head2) {

            head1 = deleteDuplicates(head1);
            head2 = deleteDuplicates(head2);

            Node resultHead = new Node(0);
            Node current = resultHead;

            while(head1 != null || head2 != null) {
                if (head1 != null && head2 != null) {
                    if (head1.data < head2.data) {
                        current.next = head1;
                        head1 = head1.next;
                    } else if (head2.data < head1.data){
                        current.next = head2;
                        head2 = head2.next;
                    } else {
                        current.next = head1;
                        head1 = head1.next;
                        head2 = head2.next;
                    }
                    current = current.next;
                } else if (head1 == null) {
                    // connect to head of remaining part of second list
                    current.next = head2;
                    break;
                } else if (head2 == null) {
                    // connect to head of remaining part of first list
                    current.next = head1;
                    break;
                }
            }
            return resultHead;
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
        Node head1 = new Node(1);
        head1.next = new Node(1);
        head1.next.next = new Node(5);
        head1.next.next.next = new Node(7);
        head1.next.next.next.next = new Node(9);
        solution.print(head1);

        Node head2 = new Node(2);
        head2.next = new Node(4);
        head2.next.next = new Node(4);
        head2.next.next.next = new Node(8);
        head2.next.next.next.next = new Node(10);
        solution.print(head2);

        Node mergedHead = solution.mergeSortedLinkedLists(head1, head2);
        solution.print(mergedHead);
    }
}
