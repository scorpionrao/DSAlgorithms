package bbb.LinkedList;

import java.util.Stack;

public class PrintReverseLinkedList {

    private static class Node {
        int key;
        Node next;

        Node(int key) {
            this.key = key;
            this.next = null;
        }
    }

    /*
     * Time : O(N)
     * Space : O(N)
     *
     * Not suitable for long lists + Not reversing the nodes, just retrieving values.
     * */
    private static void printReverseApproach1(Node root) {
        Stack<Integer> stack = new Stack<>();
        Node current = root;
        while(current != null) {
            stack.push(current.key);
            current = current.next;
        }
        while(!stack.isEmpty()) {
            System.out.print(stack.pop() + " --> ");
        }
    }


    /*
    * Time : O(N)
    * Space : O(1) + 'N' Stack calls
    *
    * Not suitable for long lists
    * */
    private static void printReverseApproach2(Node root) {
        Node current = root;
        if(current == null) {
            return;
        }
        printReverseApproach2(current.next);
        System.out.print(current.key + " --> ");
    }

    /*
     * Time : O(2*N)
     * Space : O(1)
     *
     * Suitable for long lists. No recursion stack and no additional data structure. But longer time.
     * */
    private static void printReverseApproach3(Node root) {
        Node reverseHead = reverse1(root);
        print(reverseHead);
        root = reverse1(reverseHead);
    }

    private static Node reverse1(Node head) {
        Node current = head;
        Node prev = null;
        while(current != null) {
            Node temp = current.next;
            current.next = prev;
            prev = current;
            current = temp;
        }
        return prev;
    }

    /*
     * Time : O(2*N)
     * Space : O(1)
     *
     * Suitable for long lists. No recursion stack but additional data structure with NODES. But longer time.
     * */
    private static void printReverseApproach4(Node root) {
        Node reverseHead = reverse2(root);
        print(reverseHead);
        root = reverse2(reverseHead);
    }

    private static Node reverse2(Node head) {
        Stack<Node> stack = new Stack<>();
        Node current = head;
        while(current != null) {
            stack.push(current);
            current = current.next;
        }

        Node reverseHead = null;
        current = reverseHead;
        while(!stack.isEmpty()) {
            Node node = stack.pop();
            if(reverseHead == null) {
                reverseHead = node;
                current = node;
            } else {
                current.next = node;
                current = current.next;
            }
        }
        current.next = null;
        return reverseHead;
    }



    private static void evaluate(Node root) {
        print(root);
        System.out.println("Approach 1 : ");
        printReverseApproach1(root);
        System.out.println();
        System.out.println("Approach 2 : ");
        printReverseApproach2(root);
        System.out.println();
        System.out.println("Approach 3 : ");
        printReverseApproach3(root);
        System.out.println("Approach 4 : ");
        printReverseApproach4(root);
        System.out.println();
        print(root);
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

    public static void main(String[] args) {
        Node root = new Node(1);
        root.next = new Node(2);
        root.next.next = new Node(3);
        root.next.next.next = new Node(4);
        root.next.next.next.next = new Node(5);
        evaluate(root);
    }
}