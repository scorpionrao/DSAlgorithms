package bbb.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class TreeRandomNode {

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

    /* Time: O(N), Space: O(N) */
    private static int treeRandomNodeApproach1(Node root, int randomIndex) {
        List<Integer> sorted = new ArrayList<>();

        Stack<Node> stack = new Stack<>();
        addLongLeftNodes(root, stack);
        while(!stack.isEmpty()) {
            Node node = stack.pop();
            sorted.add(node.key);
            addLongLeftNodes(node.right, stack);
        }
        return sorted.get(randomIndex);
    }

    private static void addLongLeftNodes(Node root, Stack<Node> stack) {
        while(root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    /* Time: O(N), Space: O(N) */
    private static int treeRandomNodeApproach2(Node root, int randomIndex) {
        int localCopy = randomIndex;
        Stack<Node> stack = new Stack<>();
        addLongLeftNodes(root, stack);
        while(!stack.isEmpty()) {
            Node node = stack.pop();
            localCopy--;
            if(localCopy < 0) {
                return node.key;
            }
            addLongLeftNodes(node.right, stack);
        }
        return -1;
    }

    private static int getChildrenCount(Node root) {

        if(root == null) {
            return 0;
        }

        int left = getChildrenCount(root.left);
        int right = getChildrenCount(root.right);
        return 1 + left + right;
    }

    /* Time: O(Log N), Space: O(1) */
    private static int treeRandomNodeApproach3(Node root, int randomIndex) {

        int oneBasedLeftChildrenCount = getChildrenCount(root.left);
        if(randomIndex == oneBasedLeftChildrenCount) {
            return root.key;
        } else if(randomIndex < oneBasedLeftChildrenCount) {
            return treeRandomNodeApproach3(root.left, randomIndex);
        } else {
            return treeRandomNodeApproach3(root.right, randomIndex - oneBasedLeftChildrenCount - 1);
        }
    }


    public static void evaluate(Node root, int size) {

        int randomIndex = new Random().nextInt(size - 1);
        System.out.println("Size : " + size + ", Random Index : " + randomIndex);
        int result1 = treeRandomNodeApproach1(root, randomIndex);
        System.out.println("Approach1 : " + result1);
        int result2 = treeRandomNodeApproach2(root, randomIndex);
        System.out.println("Approach2 : " + result2);
        int result3 = treeRandomNodeApproach3(root, randomIndex);
        System.out.println("Approach3 : " + result3);
    }

    public static void main(String[] args) {
        Node root = new Node(4);
        root.left = new Node(2);
        root.left.left = new Node(1);
        root.left.right = new Node(3);
        root.right = new Node(6);
        root.right.left = new Node(5);
        root.right.right = new Node(7);

        evaluate(root, 7);
    }
}
