package algorithms.cracking;

public class linkedlistpalindrome {

    public static class Node {
        int key;
        Node next;
        Node previous;
        Node(int key, Node next, Node previous) {
            this.key = key;
            this.next = next;
            this.previous = previous;
        }
    }

    public static void print(Node node) {
        while(node != null) {
            System.out.print(node.key + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static boolean isEqual(Node node1, Node node2) {
        while(node1.next != null && node2.next != null) {
            if(node1.key != node2.key) {
                return false;
            }
            node1 = node1.next;
            node2 = node2.next;
        }
        return node1.next == null && node2.next == null;
    }

    public static Node reverse(Node head) {

        Node reverseHead = new Node(head.key, null, null);
        while(head.next != null) {
            Node node = new Node(head.next.key, null, null);
            node.next = reverseHead;
            reverseHead = node;
            head = head.next;
        }
        return reverseHead;
    }

    public static boolean isPalindrome(Node node) {
        Node reverseNode = reverse(node);
        return isEqual(node, reverseNode);
    }

    public static void main(String[] args) {

        int size = 9;
        Node[] nodes = new Node[size];
        for(int i = 0; i < size; i++) {
            int key = i < size / 2 ? i : size - i - 1;
            nodes[i] = new Node(key, null, null);
        }
        for(int i = 0; i < size; i++) {
            if(i < size - 1) {
                nodes[i].next = nodes[i + 1];
            }
            if(i > 0) {
                nodes[i].previous = nodes[i - 1];
            }
        }
        Node head = nodes[0];
        print(head);
        System.out.println(isPalindrome(head));
    }
}
