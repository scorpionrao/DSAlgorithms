package bbb.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class PostorderTraversal {

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

    private static List<Integer> postorderTraversalApproach1(Node root) {
        List<Integer> result = new ArrayList<>();
        postorderTraversalApproach1(root, result);
        return result;
    }

    private static void postorderTraversalApproach1(Node root, List<Integer> result) {
        if(root == null) {
            return;
        }
        postorderTraversalApproach1(root.left, result);
        postorderTraversalApproach1(root.right, result);
        result.add(root.key);
    }

    /*
        InOrder   : [1, 2, 3, 4, 5, 6, 7]
        PreOrder  : [4, 2, 1, 3, 6, 5, 7]
        PostOrder : [1, 3, 2, 5, 7, 6, 4]
    */
    public static List<Integer> postorderTraversalApproach2(Node root) {

        LinkedList<Integer> result = new LinkedList<>();

        Stack<Node> stack = new Stack<>();
        stack.add(root);
        while(!stack.isEmpty()) {
            Node node = stack.pop();
            result.addFirst(node.key);
            addChild(node.left, stack);
            addChild(node.right, stack);
        }
        return result;
    }

    private static void addChild(Node root, Stack<Node> stack) {
        if(root != null) {
            stack.push(root);
        }
    }

    public static void evaluate(Node root) {
        List<Integer> result1 = postorderTraversalApproach1(root);
        System.out.println("Approach1 : " + result1.toString());
        List<Integer> result2 = postorderTraversalApproach2(root);
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
