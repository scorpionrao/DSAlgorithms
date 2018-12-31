package bbb.LinkedList;

import datastructures.Hash.HashChains;

import java.util.HashSet;
import java.util.Set;

public class RemoveDupsUnsortedLinkedList {

    private static class Node {
        int key;
        Node next;

        Node(int key) {
            this.key = key;
            this.next = null;
        }
    }

    /*
        Time : O(N), Space : O(N)

        Preferred for finite length linked lists.
    */
    private static void removeDupsApproach1(Node head) {
        if(head == null || head.next == null) {
            return;
        }
        Set<Integer> set = new HashSet<>();
        Node prev = null;
        while(head != null) {
            if(!set.contains(head.key)) {
                set.add(head.key);
                prev = head;
            } else {
                prev.next = head.next;
            }
            head = head.next;
        }
    }

    /*
        Time : O(N^2), Space : O(1)

        Preferred for infinitely long linked lists.
    */
    private static void removeDupsApproach2(Node head) {
        if(head == null || head.next == null) {
            return;
        }
        while(head != null) {
            Node current = head;
            while(current.next != null) {
                if(head.key == current.next.key) {
                    current.next = current.next.next;
                } else {
                    current = current.next;
                }
            }
            head = head.next;
        }
    }

    private static void evaluate(Node head) {
        print(head);
        /*
        System.out.println("Approach 1 : ");
        removeDupsApproach1(head);
        */
        System.out.println("Approach 2 : ");
        removeDupsApproach2(head);
        System.out.println();
        print(head);
    }

    private static void print(Node head) {
        Node current = head;
        while(current != null) {
            System.out.print(current.key + " --> ");
            current = current.next;
        }
        System.out.print("NULL");
        System.out.println();
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.next = new Node(2);
        root.next.next = new Node(3);
        root.next.next.next = new Node(2);
        root.next.next.next.next = new Node(1);
        evaluate(root);
    }
}
