package algorithms.cracking;

public class LinkedLists {

    public static class Node {
        int key = 0;
        Node next = null;
        Node prev = null;

        Node(int key) {
            this.key = key;
        }
    }

    public Node head;

    public LinkedLists(Node node) {
        head = node;
    }

    public Node addNode(int key) {
        Node node = new Node(key);
        Node n = head;
        while(n.next != null) {
            n = n.next;
        }
        n.next = node;
        return node;
    }

    public static void main(String[] args) {
        Node node = new Node(1);
        LinkedLists linkedLists = new LinkedLists(node);
        linkedLists.addNode(5);
        linkedLists.addNode(10);
    }
}
