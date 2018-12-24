package bbb.LinkedList;

public class LinkedListCycle {

    public static class Node {
        private int key;
        private Node next;
        Node(int key) {
            this.key = key;
            this.next = null;
        }
    }

    private static boolean hasCycle(Node head) {
        if(head == null || head.next == null) {
            return false;
        }

        Node slow = head;
        Node fast = head;

        while(slow.next != null && fast.next.next != null) {
            if(slow == fast) {
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        // end of list
        return true;
    }
    public static void main(String[] args) {
        Node[] nodes = new Node[10];
        for(int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node(i);
        }
        for(int i = 0; i < nodes.length - 1; i++) {
            nodes[i].next = nodes[i+1];
        }
        nodes[nodes.length-1].next = nodes[0];
        System.out.println("Has Cycle : " + hasCycle(nodes[0]));
    }
}
