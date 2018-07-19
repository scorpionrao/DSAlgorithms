package zr;


import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class LinkedListMergeSorted
{

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
            /* Link builder */
            Node current = head;
            
            /* until all nodes are examined */
            while(current != null) {
                /* am i last node ? - Hard exit */
                if(current.next == null) {
                    break;
                } /* is the next node same ? */
                else if(current.data == current.next.data) {
                    current.next = current.next.next;
                } /* positive scenario */
                else {
                    current = current.next;
                }
            }
            return head;
        }

        /*
            Delete Duplicates within a list - O(L1)
            Delete Duplicates within a list - O(L2)
            Merge - O(L1+L2)
            Total - O(L1+L2+L1+L2) --> O(L1+L2)
         */
        public Node mergeSortedLinkedLists(Node head1, Node head2) {

            head1 = deleteDuplicates(head1);
            head2 = deleteDuplicates(head2);

            /* Assumes unique elements */
            Node resultHead = new Node(0);
            /* Link builder */
            Node current = resultHead;

            /* condition for all nodes are examined */
            while(head1 != null || head2 != null) {
                /* positive scenario - both lists are available */
                if (head1 != null && head2 != null) {
                    /* List 1 wins */
                    if (head1.data < head2.data) {
                        current.next = head1;
                        head1 = head1.next;
                    } /* List 2 wins */ 
                    else if (head2.data < head1.data){
                        current.next = head2;
                        head2 = head2.next;
                    } /* Both values are equal */
                    else {
                        current.next = head1;
                        head1 = head1.next;
                        head2 = head2.next;
                    }
                    current = current.next;
                } /* Hard break - no more nodes to examine in List1 */
                else if (head1 == null) {
                    // connect to head of remaining part of second list
                    current.next = head2;
                    break;
                } /* Hard break - no more nodes to examine in List2 */
                else if (head2 == null) {
                    current.next = head1;
                    break;
                }
            }
            return resultHead;
        }

        /* O(L1+L2) */
        public Node merge(Node head1, Node head2) {

            Node resultHead = new Node(0);
            Node current = resultHead;

            while(head1 != null || head2 != null) {
                // both nodes exist
                if(head1 != null && head2 != null) {
                    /* Path 1: Compare result pointer and list1. */
                    if(current.data == head1.data) {
                        head1 = head1.next;
                        continue;
                    /* Path 2: Compare result pointer and list2. */
                    } else if (current.data == head2.data) {
                        head2 = head2.next;
                        continue;
                    /* Path 3: Compare list1 and list2 */
                    } else if (head1.data < head2.data) {
                        current.next = head1;
                        head1 = head1.next;
                    /* Path 4: Compare list1 and list2 */
                    } else if (head1.data > head2.data) {
                        current.next = head2;
                        head2 = head2.next;
                    /* Path 5: Compare list1 and list2 */
                    } else {
                        current.next = head1;
                        head1 = head1.next;
                        head2 = head2.next;
                    }
                    /* no skippers should arrive here */
                    current = current.next;
                } else if (head1 != null) {
                    /* Only list 1 is left for examination - HARD BREAK */
                    current.next = head1;
                    break;
                } else {
                    /* Only list 2 is left for examination - HARD BREAK */
                    current.next = head2;
                    break;
                }

            }
            return resultHead.next;
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

        Node mergedHead = solution.merge(head1, head2);
        solution.print(mergedHead);
    }
}
