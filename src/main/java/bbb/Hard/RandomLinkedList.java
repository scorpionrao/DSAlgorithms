package bbb.Hard;

public class RandomLinkedList {

    public static class Node {
        int key;
        Node next;
        Node random;

        Node(int key) {
            this.key = key;
            this.next = null;
            this.random = null;
        }
    }

    private static Node cloneApproach1(Node head) {

        /* OVERALL - Time : O(3 * N), Space : O(1) */

        /*
            Insert clone nodes
            Time: O(N), Space: O(1)
         */
        Node current = head;
        while(current != null) {
            Node temp = current.next;
            current.next = new Node(current.key);
            current.next.next = temp;
            current = current.next.next;
        }

        /*
            Since all result nodes are available, random setting can be done
            Time: O(N), Space: O(1)
         */
        current = head;
        while(current != null) {
            current.next.random = current.random.next;
            current = current.next.next;
        }
        print(head);

        /*
            Remove previous nodes and retain only clone nodes
            Time: O(N), Space: O(1)
         */
        head = head.next;
        current = head;
        while(current != null && current.next != null) {
            current.next = current.next.next;
            current = current.next;
        }
        return head;
    }

    private static void evaluate(Node head) {
        print(head);
        Node head1 = cloneApproach1(head);
        print(head1);
    }

    private static void print(Node head) {
        Node current = head;
        while(current != null) {
            System.out.print(current.key + "[" + current.random.key + "] --> ");
            current = current.next;
        }
        System.out.print("NULL");
        System.out.println();
    }

    public static void main(String[] args) {

        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);

        head.random = head.next.next;
        head.next.random = head;
        head.next.next.random = head.next.next;
        head.next.next.next.random = head.next;

        evaluate(head);
    }
}
