package kickstart;

public class DoublyLinkedList {

    public static class Node {
        int data;
        Node prev = null;
        Node next = null;
        public Node(int data) {
            this.data = data;
        }
    }

    public Node globalFirstNode = null;

    public void insertAtHead(int data) {
        Node newNode = new Node(data);
        if(globalFirstNode == null) {
            globalFirstNode = newNode;
            return;
        }
        globalFirstNode.prev = newNode;
        newNode.next = globalFirstNode;
        globalFirstNode = newNode;
    }

    public void insertAtTail(int data) {
        Node newNode = new Node(data);
        Node current = globalFirstNode;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

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


    public static void main(String[] args) {
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
        doublyLinkedList.insertAtHead(5);
        doublyLinkedList.insertAtTail(6);
        doublyLinkedList.printList(doublyLinkedList.globalFirstNode);
        doublyLinkedList.reversePrintList(doublyLinkedList.globalFirstNode);
        System.out.println();
        doublyLinkedList.insertAtHead(2);
        doublyLinkedList.insertAtTail(8);
        doublyLinkedList.printList(doublyLinkedList.globalFirstNode);
        doublyLinkedList.reversePrintList(doublyLinkedList.globalFirstNode);
        System.out.println();
    }

}
