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
        Node current = head;
        while(current != null) {
            System.out.print(current.key + " --> ");
            current = current.right;
        }
        System.out.print("NULL");
    }

    private static Node treeToDLLApproach1Util(Node root) {

        if(root == null) {
            return root;
        }

        if(root.left != null) {

            Node sameLeftNode = treeToDLLApproach1Util(root.left);
            while(sameLeftNode.right != null) {
                sameLeftNode = sameLeftNode.right;
            }
            sameLeftNode.right = root;
            root.left = sameLeftNode;
        }

        if(root.right != null) {

            Node sameRightNode = treeToDLLApproach1Util(root.right);
            while(sameRightNode.left != null) {
                sameRightNode = sameRightNode.left;
            }
            sameRightNode.left = root;
            root.right = sameRightNode;
        }

        return root;
    }

    public static Node treeToDLLApproach1(Node root) {
        if(root == null) {
            return null;
        }

        Node head = treeToDLLApproach1Util(root);
        while(head.left != null) {
            head = head.left;
        }
        return head;
    }

    private static Node previous;
    private static void fixPrevPtr(Node root) {
        if(root == null) {
            return;
        }
        fixPrevPtr(root.left);
        root.left = previous;
        previous = root;
        fixPrevPtr(root.right);
    }

    private static Node fixNextPtr(Node root) {
        if(root == null) {
            return null;
        }

        Node current = root;
        while(current.right != null) {
            current = current.right;
        }

        while(current != null && current.left != null) {
            Node previous = current.left;
            previous.right = current;
            current = current.left;
        }
        return current;
    }

    public static Node treeToDLLApproach2(Node root) {
        if(root == null) {
            return null;
        }
        fixPrevPtr(root);
        return fixNextPtr(root);
    }

    // Very simple In Order traversal

    public static void treeToDLLApproach3(Node root) {

        if (root == null) {
            return;
        }
        Node head = null;
        Node prev = null;
        treeToDLLApproach3(root.left, prev, head);
    }

    public static void treeToDLLApproach3(Node root, Node previous, Node head) {

        if (root == null) {
            return;
        }
        treeToDLLApproach3(root.left, previous, head);
        /* Inorder */
        if (previous == null) {
            head = root;
        } else {
            root.left = previous;
            previous.right = root;
        }
        previous = root;
        /* Inorder */
        treeToDLLApproach3(root.right, previous, head);
    }

    public static void evaluate(Node root) {
/*
        Node head = treeToDLLApproach1(root);
        System.out.println("Approach1 : ");
        print(head);
*/
        Node head = treeToDLLApproach2(root);
        System.out.println("Approach2 : ");
        print(head);
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right = new Node(3);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        evaluate(root);
    }
}
