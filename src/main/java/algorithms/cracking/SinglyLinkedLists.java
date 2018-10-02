package algorithms.cracking;

public class SinglyLinkedLists {

    public static class Node {
        int key = 0;
        Node next = null;

        Node(int key) {
            this.key = key;
        }
    }

    public Node head;

    public SinglyLinkedLists(Node node) {
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
        SinglyLinkedLists singlyLinkedLists = new SinglyLinkedLists(node);
        singlyLinkedLists.addNode(5);
        singlyLinkedLists.addNode(10);
    }
}
