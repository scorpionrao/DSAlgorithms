package bbb.Tree;

public class TreeToDoublyLinkedList {

    public static class Node {
        int key;
        Node left;
        Node right;

        public Node(int key) {
            this.key = key;
            this.left = null;
            this.right = null;
        }
    }

    private static void print(Node head) {

        while(head.left != null) {
            head = head.left;
        }
        Node current = head;
        while(current != null) {
            System.out.print(current.key + " <--> ");
            current = current.right;
        }
    }

    public static Node treeToDLLApproach1(Node root) {
        if(root == null) {
            return null;
        }
        fixPrevPtr(root);
        return fixNextPtr(root);
    }

    private static Node previousApproach1;
    private static void fixPrevPtr(Node root) {
        if(root == null) {
            return;
        }
        fixPrevPtr(root.left);
        root.left = previousApproach1;
        previousApproach1 = root;
        fixPrevPtr(root.right);
    }

    private static Node fixNextPtr(Node root) {
        if(root == null) {
            return null;
        }

        Node leftMostNode = root;
        while(leftMostNode.right != null) {
            leftMostNode = leftMostNode.right;
        }

        while(leftMostNode != null && leftMostNode.left != null) {
            Node previous = leftMostNode.left;
            previous.right = leftMostNode;
            leftMostNode = leftMostNode.left;
        }
        return leftMostNode;
    }

    // Very simple In Order traversal

    public static Node headApproach2 = null;
    public static Node previousApproach2 = null;

    public static Node treeToDLLApproach2(Node root) {

        if (root == null) {
            return null;
        }
        root.left = treeToDLLApproach2(root.left);
        /* Inorder */
        if (previousApproach2 == null) {
            headApproach2 = root;
        } else {
            root.left = previousApproach2;
            previousApproach2.right = root;
        }
        previousApproach2 = root;
        /* Inorder */
        root.right = treeToDLLApproach2(root.right);

        return root;
    }

    public static void evaluate(Node root) {
/*
        Node head = treeToDLLApproach1(root);
        System.out.println("Approach1 : ");
        print(head);
*/
        root = treeToDLLApproach2(root);

        System.out.println("Approach2 : ");
        print(root);

    }

    public static void main(String[] args) {
        Node root = new Node(4);
        root.left = new Node(2);
        root.left.left = new Node(1);
        root.left.right = new Node(3);
        root.right = new Node(6);
        root.right.left = new Node(5);
        root.right.right = new Node(7);

        evaluate(root);
    }
}
