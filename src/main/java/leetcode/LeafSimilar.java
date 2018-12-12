package leetcode;

import java.util.*;

public class LeafSimilar {

    private static class TreeNode {
        int key;
        TreeNode left;
        TreeNode right;
        public TreeNode(int key) {
            this.key = key;
            this.left = null;
            this.right = null;
        }
    }

    public static void dfsLeafRecurseStack(TreeNode node, Stack<Integer> stack) {
        if(node == null) {
            return;
        }
        if(node.left == null && node.right == null) {
            stack.push(node.key);
            return;
        }
        dfsLeafRecurseStack(node.right, stack);
        dfsLeafRecurseStack(node.left, stack);
    }

    public static boolean leafSimilarStack(TreeNode node1, TreeNode node2) {
        Stack<Integer> stack1 = new Stack<>();
        dfsLeafRecurseStack(node1, stack1);
        Stack<Integer> stack2 = new Stack<>();
        dfsLeafRecurseStack(node2, stack2);

        while(!stack1.isEmpty() && !stack2.isEmpty()) {
            if(stack1.pop() != stack2.pop()) {
                return false;
            }
        }
        return stack1.isEmpty() && stack2.isEmpty();
    }

    public static void main(String[] args) {

        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(5);
        root1.left.left = new TreeNode(6);
        root1.left.right = new TreeNode(2);
        root1.left.right.left = new TreeNode(7);
        root1.left.right.right = new TreeNode(4);
        root1.right = new TreeNode(1);
        root1.right.left = new TreeNode(9);
        root1.right.right = new TreeNode(8);

        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(5);
        root2.left.left = new TreeNode(6);
        root2.left.right = new TreeNode(2);
        root2.left.right.left = new TreeNode(7);
        root2.left.right.right = new TreeNode(4);
        root2.right = new TreeNode(1);
        root2.right.left = new TreeNode(9);
        root2.right.right = new TreeNode(8);

        System.out.println("Stack: " + leafSimilarStack(root1, root2));
    }
}
