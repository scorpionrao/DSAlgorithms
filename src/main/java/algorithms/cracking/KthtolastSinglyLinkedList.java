package algorithms.cracking;

public class KthToLastSinglyLinkedList {

    public static class Node {

        int data;
        Node(int data) {
            this.data = data;
        }
        Node next;

    }

    public static int printKthToLast(Node head, int k) {
        if (head == null) {
            return 0;
        }
        int index = printKthToLast(head.next, k) + 1;
        if (index == k) {
            System.out.println(k + "th to last node is " + head.data);
        }
        return index;
    }

    public static void main(String[] args) {
        int[] array = {0, 1, 2, 3, 4, 5, 6};
        Node head = new Node(array[0]);
        Node n = head;
        for (int i = 1; i < array.length; i++) {
            n.next = new Node(i);
            n = n.next;
        }
        for (int i = 0; i < array.length; i++) {
            printKthToLast(head, i);
        }
    }

}
