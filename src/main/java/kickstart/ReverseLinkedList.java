package kickstart;

/**
 * Created by manushaonly on 4/29/18.
 */
public class ReverseLinkedList {

    public static class Node {
        int data;
        public Node next;

        Node(int data) {
            this.data = data;
        }
    }

    public Node head;

    public void printList(Node node) {
        while (node != null) {
            System.out.print(node.data + " -> ");
            node = node.next;
        }
        System.out.println("NULL");
    }

    public Node reverse(Node node) {
        Node prevDisconnected = null;
        Node nextDisconnected = null;
        Node current = node;
        while (current != null) {
            // hold on to next
            nextDisconnected = current.next;
            // reverse
            current.next = prevDisconnected;
            // move previous forward
            prevDisconnected = current;
            // move current forward
            current = nextDisconnected;
        }
        head = prevDisconnected;
        return head;
    }

    public static void main(String[] args) {
        ReverseLinkedList list = new ReverseLinkedList();
        list.head = new Node(85);
        list.head.next = new Node(15);
        list.head.next.next = new Node(4);
        list.head.next.next.next = new Node(20);

        System.out.println("Given Linked list");
        list.printList(list.head);
        list.head = list.reverse(list.head);
        System.out.println("");
        System.out.println("Reversed linked list ");
        list.printList(list.head);
    }

}
