package bbb.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PreorderTraversal {

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

    private static List<Integer> preorderTraversalApproach1(Node root) {
        List<Integer> result = new ArrayList<>();
        preorderTraversalApproach1(root, result);
        return result;
    }

    private static void preorderTraversalApproach1(Node root, List<Integer> result) {
        if(root == null) {
            return;
        }
        result.add(root.key);
        preorderTraversalApproach1(root.left, result);
        preorderTraversalApproach1(root.right, result);
    }


    /*
        InOrder   : [1, 2, 3, 4, 5, 6, 7]
        PreOrder  : [4, 2, 1, 3, 6, 5, 7]
        PostOrder : [1, 3, 2, 5, 7, 6, 4]
    */
    public static List<Integer> preorderTraversalApproach2(Node root) {

        List<Integer> result = new ArrayList<>();

        Stack<Node> stack = new Stack<>();
        addChild(root, stack);
        while(!stack.isEmpty()) {
            Node node = stack.pop();
            result.add(node.key);
            addChild(node.right, stack);
            addChild(node.left, stack);
        }
        return result;
    }

    private static void addChild(Node root, Stack<Node> stack) {
        if(root != null) {
            stack.push(root);
        }
    }

    public static void evaluate(Node root) {
        List<Integer> result1 = preorderTraversalApproach1(root);
        System.out.println("Approach1 : " + result1.toString());
        List<Integer> result2 = preorderTraversalApproach2(root);
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
