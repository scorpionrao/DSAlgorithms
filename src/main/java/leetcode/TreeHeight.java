package leetcode;

import java.util.Stack;

public class TreeHeight {

    private static class TreeNode {
        int key;
        TreeNode left;
        TreeNode right;
        TreeNode(int key) {
            this.key = key;
            this.left = null;
            this.right = null;
        }
    }

    private static class ProcessNode {
        TreeNode treeNode;
        int depth;
        ProcessNode(TreeNode treeNode, int depth) {
            this.treeNode = treeNode;
            this.depth = depth;
        }
    }

    /*
        Time : O(N), Space : O(log N)
    */
    private static int maxDepthBottomUpPostOrderRecursion(TreeNode root) {
        if(root == null) {
            return 0;
        } else if(root.left == null && root.right == null) {
            return 1;
        }
        int left = maxDepthBottomUpPostOrderRecursion(root.left);
        int right = maxDepthBottomUpPostOrderRecursion(root.right);
        return 1 + Math.max(left, right);
    }

    /* Time : O(N), Space : O(N) */
    private static int maxDepthBottomUpDfsIteration(TreeNode root) {
        Stack<ProcessNode> stack = new Stack<>();
        stack.push(new ProcessNode(root, 1));

        int maxDepth = 0;
        while(!stack.isEmpty()) {
            ProcessNode node = stack.pop();
            maxDepth = Math.max(maxDepth, node.depth);
            if(node.treeNode.right != null) {
                stack.push(new ProcessNode(node.treeNode.right, node.depth+1));
            }
            if(node.treeNode.left != null) {
                stack.push(new ProcessNode(node.treeNode.left, node.depth+1));
            }
        }
        return maxDepth;
    }

    private static void evaluate(TreeNode root) {
        int result1 = maxDepthBottomUpPostOrderRecursion(root);
        System.out.println("Recursion Post Order - " + result1);
        int result2 = maxDepthBottomUpDfsIteration(root);
        System.out.println("Iteration DFS Pre Order - " + result2);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(5);
        root.left.right.right = new TreeNode(6);
        root.right = new TreeNode(7);
        evaluate(root);
    }
}
