package bbb.LinkedList;

public class NthToLastLinkedList {

    private static class Node {
        int key;
        Node next;

        Node(int key) {
            this.key = key;
            this.next = null;
        }
    }

    private static void print(Node root) {
        Node current = root;
        while(current != null) {
            System.out.print(current.key + " --> ");
            current = current.next;
        }
        System.out.print("NULL");
        System.out.println();
    }

    private static Node nthToLast(Node root, int n) {
        Node current = root;
        Node follower = root;

        for(int i = 0; i < n; i++) {
            current = current.next;
        }

        while(current.next != null) {
            current = current.next;
            follower = follower.next;
        }
        return follower;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.next = new Node(2);
        root.next.next = new Node(3);
        root.next.next.next = new Node(4);
        root.next.next.next.next = new Node(5);
        print(root);
        System.out.println("2nd to last = " + nthToLast(root, 2).key);
    }
}
