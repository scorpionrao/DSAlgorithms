package bbb.Stack;

public class MaxStack {

    private static class Node {
        int key;
        Node next;
        Node previousMax;
        Node(int key) {
            this.key = key;
        }
    }

    static Node head = null;
    static Node currentMaxNode = null;

    /* Insert at head */
    private static void push(int key) {
        Node newNode = new Node(key);
        // when will head change - always
        if(head != null) {
            newNode.next = head;
        }
        head = newNode;

        // when will current max node change
        if(currentMaxNode == null || key > currentMaxNode.key) {
            newNode.previousMax = currentMaxNode;
            currentMaxNode = newNode;
        }
        System.out.print("Push : ");
        print();
        System.out.println("Max : " + max());
    }

    /* Remove at head */
    private static int pop() {
        // when will head change - ALWAYS
        Node poppedNode = head;
        head = head.next;
        // when will previous max change
        if(poppedNode.previousMax != null) {
            currentMaxNode = poppedNode.previousMax;
        }
        System.out.print("Pop : ");
        print();
        System.out.println("Max : " + max());
        return poppedNode.key;
    }

    private static int max() {
        if(currentMaxNode == null) {
            return -1;
        }
        return currentMaxNode.key;
    }

    private static void print() {
        Node current = head;
        while(current != null) {
            System.out.print(current.key + " --> ");
            current = current.next;
        }
        System.out.print("NULL");
        System.out.println();
    }

    public static void main(String[] args) {
        push(1);
        push(2);
        push(1);
        pop();
        pop();
        pop();
    }
}
