package zr;

import java.util.HashSet;
import java.util.Set;

public class FloydCycleFinding {

    public static class Node {
        int data;
        Node next;
        Node(int data) {
            this.data = data;
        }
    }

    public static class Solution {

        public Node createLinkedList(int length) {
            if(length <= 0) {
                return null;
            }

            // Creating nodes for Linked List
            Node[] nodeArray = new Node[length];
            for(int i = 0; i < nodeArray.length; i++) {
                nodeArray[i] = new Node(i);
            }

            // Creating Linked List
            for(int i = 0; i < nodeArray.length - 1; i++) {
                nodeArray[i].next = nodeArray[i+1];
            }

            // Loop
            nodeArray[9].next = nodeArray[5];

            // Head
            return nodeArray[0];
        }

        /*
            Time complexity - O(N)
            Space complexity - O(1)
        */
        public boolean findCycleSpaceOptimized(Node head) {

            if(head == null || head.next == null) {
                return false;
            }

            /* Space complexity - O(1) */
            Node slowPointer = head;
            Node fastPointer = head;

            /* Time complexity - O(N) */
            while (slowPointer != null &&
                   fastPointer != null &&
                   fastPointer.next != null) {

                slowPointer = slowPointer.next;
                fastPointer = fastPointer.next.next;
                if(slowPointer == fastPointer) {
                    return true;
                }
            }
            return false;
        }

        public boolean findCycle(Node head) {

            if(head == null || head.next == null) {
                return false;
            }

            Set<Node> set = new HashSet<>();

            while(head != null) {
                if(set.contains(head)) {
                    return true;
                } else {
                    set.add(head);
                    head = head.next;
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        Node head = solution.createLinkedList(10);
        System.out.println(solution.findCycle(head));
    }
}
