package kickstart;

public class SinglyLinkedList {

    public static class Node {
        int data;
        public Node next;

        Node(int data) {
            this.data = data;
        }
    }

    public Node head;

    public void printList(Node node) {
        if(node == null) {
            System.out.println("NULL");
            return;
        }
        System.out.print(node.data + " -> ");
        printList(node.next);
    }

    public void reversePrintList(Node node) {
        if(node == null) {
            System.out.print("NULL");
            return;
        }
        reversePrintList(node.next);
        System.out.print(" <- " + node.data);
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
        SinglyLinkedList list = new SinglyLinkedList();
        list.head = new Node(85);
        list.head.next = new Node(15);
        list.head.next.next = new Node(4);
        list.head.next.next.next = new Node(20);

        System.out.println("Given Linked list");
        list.printList(list.head);
        //list.head = list.reverse(list.head);
        //System.out.println("");
        //System.out.println("Reversed linked list ");
        list.reversePrintList(list.head);
    }

}
