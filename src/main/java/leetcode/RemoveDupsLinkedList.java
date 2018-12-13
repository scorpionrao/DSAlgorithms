package leetcode;

import java.util.HashSet;
import java.util.Set;

public class RemoveDupsLinkedList {

    public static class Node {
        int key;
        Node next;
        Node(int key) {
            this.key = key;
            this.next = null;
        }
    }

    public static void removeDups1(Node root) {

        Set<Integer> set = new HashSet<>();
        Node current = root;
        set.add(current.key);
        while(current.next != null) {
            if(set.contains(current.next.key)) {
                current.next = current.next.next;
            } else {
                set.add(current.next.key);
                current = current.next;
            }
        }
    }

    public static void removeDups2(Node root) {

        Set<Integer> set = new HashSet<>();
        Node current = root;
        Node prev = null;
        while(current != null) {
            if(set.contains(current.key)) {
                prev.next = current.next;
                current = current.next;
            } else {
                set.add(current.key);
                prev = current;
                current = current.next;
            }

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

    public static void main(String[] args) {
        Node root = new Node(1);
        root.next = new Node(1);
        root.next.next = new Node(2);
        root.next.next.next = new Node(1);
        root.next.next.next.next = new Node(2);
        root.next.next.next.next.next = new Node(3);

        print(root);
        removeDups1(root);
        print(root);
    }
}
