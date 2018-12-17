package bbb.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InorderTraversal {

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

    private static List<Integer> inorderTraversalApproach1(Node root) {
        List<Integer> result = new ArrayList<>();
        inorderTraversalApproach1(root, result);
        return result;
    }

    private static void inorderTraversalApproach1(Node root, List<Integer> result) {
        if(root == null) {
            return;
        }
        inorderTraversalApproach1(root.left, result);
        result.add(root.key);
        inorderTraversalApproach1(root.right, result);
    }

    /*
        InOrder   : [1, 2, 3, 4, 5, 6, 7]
        PreOrder  : [4, 2, 1, 3, 6, 5, 7]
        PostOrder : [1, 3, 2, 5, 7, 6, 4]
    */
    public static List<Integer> inorderTraversalApproach2(Node root) {

        List<Integer> result = new ArrayList<>();

        Stack<Node> stack = new Stack<>();
        addLongLeftNodes(root, stack);
        while(!stack.isEmpty()) {
            Node node = stack.pop();
            result.add(node.key);
            addLongLeftNodes(node.right, stack);
        }
        return result;
    }

    private static void addLongLeftNodes(Node root, Stack<Node> stack) {
        while(root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    public static void evaluate(Node root) {
        List<Integer> result1 = inorderTraversalApproach1(root);
        System.out.println("Approach1 : " + result1.toString());
        List<Integer> result2 = inorderTraversalApproach2(root);
        System.out.println("Approach2 : " + result2.toString());
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
