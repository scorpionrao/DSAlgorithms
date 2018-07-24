package zr;

public class LinkedListDeleteNode {

    public class Node {
        public int data;
        public Node next;
        public Node(int data) {
            this.data = data;
        }
    }

    Node head;

    public Node createLinkedList(Node[] nodeArray) {
        if (nodeArray == null || nodeArray.length == 0) {
            return null;
        }
        for (int i = 0; i < nodeArray.length - 1; i++) {
            if(i == 0) {
                head = nodeArray[i];
            }
            nodeArray[i].next = nodeArray[i + 1];
        }
        return head;
    }

    /*
        Deletion when "node" is passed.
        Time complexity - O(N)
        Space complexity - O(1)
     */
    public Node deleteNode(Node node) {
        if(node == null || head == null) {
            return null;
        }

        Node current = head;

        // If the node to be deleted is head
        if(current == node) {
            head = head.next;
            return current;
        }

        // Traverse until node before the required node
        while(current.next != null && current.next != node) {
            current = current.next;
        }

        if(current.next == null) {
            // no node was deleted
            return null;
        }

        // required node found and to be deleted
        Node temp = current.next;
        current.next = current.next.next;
        return temp;
    }

    /*
        Deletion when "data" is passed.
        Time complexity - O(N)
        Space complexity - O(1)
     */
    public void deleteNode(int data) {
        if(head == null) {
            return;
        }

        Node current = head;
        if(current.data == data) {
            head = head.next;
        }

        while(current.next != null && current.next.data != data) {
            current = current.next;
        }

        if(current.next == null) {
            return;
        }

        current.next = current.next.next;
    }

    public void print() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(" --> ");
            }
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LinkedListDeleteNode linkedListDeleteNode = new LinkedListDeleteNode();
        Node[] nodeArray = new Node[10];
        for(int i = 0; i < nodeArray.length; i++) {
            nodeArray[i] = linkedListDeleteNode.new Node(i);
        }
        linkedListDeleteNode.createLinkedList(nodeArray);
        linkedListDeleteNode.print();
        linkedListDeleteNode.deleteNode(5);
        linkedListDeleteNode.print();
    }
}
