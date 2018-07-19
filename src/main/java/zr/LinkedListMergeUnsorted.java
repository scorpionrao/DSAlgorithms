package zr;

import java.util.Set;
import java.util.TreeSet;

public class LinkedListMergeUnsorted {

    public static class Node implements Comparable<Node> {
        public int data;
        public Node next;
        public Node(int data) {
            this.data = data;
        }

        /* Required when using HashMap and HashSet */
        @Override
        public int hashCode() {
            return this.data;
        }

        @Override
        public boolean equals(Object object) {
            if(object == null) {
                return false;
            } else if(object == this) {
                return true;
            }
            boolean isInstance = object instanceof Node;
            if(!isInstance) {
                return false;
            }
            Node node = (Node) object;
            return this.data == node.data;
        }

        @Override
        public int compareTo(Node node) {
            if(this.data == node.data) {
                return 0;
            } else if(this.data < node.data) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    public static class Solution {

        public Node mergeUnsortedLists(Node head1, Node head2) {

            Set<Integer> set = new TreeSet();

            loadValues(head1, set);
            loadValues(head2, set);

            Node resultHead = new Node(0);
            Node current = resultHead;
            for(Integer entry : set) {
                // SPACE complexity
                current.next = new Node(entry);
                current = current.next;
            }
            return resultHead.next;
        }

        /* O(length) */
        private void loadValues(Node head, Set<Integer> set) {
            while(head != null) {
                set.add(head.data);
                head = head.next;
            }
        }

        public Node mergeUnsortedListsWithoutSpace(Node head1, Node head2) {

            Set<Node> set = new TreeSet();

            loadNodes(head1, set);
            loadNodes(head2, set);

            Node resultHead = new Node(0);
            Node current = resultHead;
            for(Node entry : set) {
                current.next = entry;
                current = current.next;
            }
            return resultHead.next;
        }

        /* O(length) */
        private void loadNodes(Node head, Set<Node> set) {
            while(head != null) {
                set.add(head);
                head = head.next;
            }
        }

        public void print(Node node) {
            if (node == null) {
                return;
            }
            Node current = node;
            while (current != null) {
                System.out.print(current.data);
                if (current.next != null)
                {
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

        long start = System.nanoTime();
        Node mergedHead = solution.mergeUnsortedListsWithoutSpace(head1, head2);
        long end = System.nanoTime();
        System.out.println("Time taken: " + (end-start));
        solution.print(mergedHead);
    }
}
