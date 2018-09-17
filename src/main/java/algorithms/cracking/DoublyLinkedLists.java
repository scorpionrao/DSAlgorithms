package algorithms.cracking;

public class DoublyLinkedLists {

    public static class Node {
        int key = 0;
        Node next = null;
        Node prev = null;

        Node(int key) {
            this.key = key;
        }
    }

    public Node head;

    public DoublyLinkedLists(Node node) {
        head = node;
    }

    public Node addNode(int key) {

        Node n = head;
        while(n.next != null) {
            n = n.next;
        }

        Node node = new Node(key);
        n.next = node;
        return node;
    }

    public static void main(String[] args) {
        Node node = new Node(1);
        DoublyLinkedLists doublyLinkedLists = new DoublyLinkedLists(node);
        doublyLinkedLists.addNode(5);
        doublyLinkedLists.addNode(10);
    }
}
