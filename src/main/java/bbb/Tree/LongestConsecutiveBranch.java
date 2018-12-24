package bbb.Tree;

public class LongestConsecutiveBranch {

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

    private static int lcApproach1(Node root) {
        if(root == null) {
            return 0;
        }

        // ZERO at the end is the length from top.
        return Math.max(Math.max(lcApproach1(root.left, root.key, 1), lcApproach1(root.right, root.key, 1)), 0);
    }

    private static int lcApproach1(Node child, int parentKey, int parentLength) {
        if(child == null) {
            return parentLength;
        }

        int currentLength = 0;
        if(child.key == parentKey + 1) {
            currentLength = parentLength + 1;
        } else {
            currentLength = 1;
        }

        int leftSubTreeMaxLength = lcApproach1(child.left, child.key, currentLength);
        int rightSubTreeMaxLength = lcApproach1(child.right, child.key, currentLength);
        return Math.max(Math.max(leftSubTreeMaxLength, rightSubTreeMaxLength), parentLength);
    }

    public static void evaluate(Node root) {
        int result1 = lcApproach1(root);
        System.out.println("Approach1 : " + result1);}

    public static void main(String[] args) {
        Node root = new Node(0);
        root.left = new Node(1);
        root.left.left = new Node(1);
        root.left.right = new Node(2);
        root.right = new Node(2);
        root.right.left = new Node(1);
        root.right.right = new Node(3);

        evaluate(root);
    }
}
