package algorithms.cracking;

public class loop {

    public static class Node {
        int key;
        Node next;
        Node(int key, Node next) {
            this.key = key;
            this.next = next;
        }
    }

    public static Node getLoopNode(Node head) {
        Node slow = head;
        Node fast = head;

        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) {
                break;
            }
        }

        // check if arrived here due to end of linked list
        if(fast.next == null || fast.next.next == null) {
            return null;
        }

        slow = head;
        while(slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        // unbelievable both are pointing at start of loop.
        return fast;
    }

    public static void main(String[] args) {
        int size = 15;
        int loopLocation = 10;

        Node[] nodes = new Node[size];
        for(int i = 0; i < size; i++) {
            nodes[i] = new Node(i, null);
        }

        Node head = nodes[0];
        for(int i = 0; i < size - 1; i++) {
            nodes[i].next = nodes[i+1];
        }

        // create a loop
        nodes[size-1].next = nodes[size-loopLocation];

        Node loopNode = getLoopNode(head);

        if(loopNode != null) {
            System.out.println(loopNode.key);
        } else {
            System.out.println("Loop does not exist");
        }

    }
}
