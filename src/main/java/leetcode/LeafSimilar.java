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

    public static boolean leafSequenceSimilar1(TreeNode node1, TreeNode node2) {
        Stack<Integer> stack1 = new Stack<>();
        dfsNextLeafRecurseStack(node1, stack1);
        Stack<Integer> stack2 = new Stack<>();
        dfsNextLeafRecurseStack(node2, stack2);
        return stack1.equals(stack2);
    }

    public static void dfsNextLeafRecurseStack(TreeNode node, Stack<Integer> stack) {
        if(node == null) {
            return;
        }
        if(node.left == null && node.right == null) {
            stack.push(node.key);
            return;
        }
        dfsNextLeafRecurseStack(node.right, stack);
        dfsNextLeafRecurseStack(node.left, stack);
    }

    public static boolean leafSequenceSimilar2(TreeNode node1, TreeNode node2) {
        List<Integer> list1 = new ArrayList<>();
        dfsNextLeafRecurseList(node1, list1);
        List<Integer> list2 = new ArrayList<>();
        dfsNextLeafRecurseList(node2, list2);
        return list1.equals(list2);
    }

    public static void dfsNextLeafRecurseList(TreeNode node, List<Integer> list) {
        if(node == null) {
            return;
        }
        if(node.left == null && node.right == null) {
            list.add(node.key);
            return;
        }
        dfsNextLeafRecurseList(node.left, list);
        dfsNextLeafRecurseList(node.right, list);
    }

    public static boolean leafSequenceSimilar3(TreeNode node1, TreeNode node2) {

        Stack<Integer> stack1 = dfsNextLeafIterativeStack(node1);
        Stack<Integer> stack2 = dfsNextLeafIterativeStack(node2);
        return isEqual(stack1, stack2);
    }

    public static Stack<Integer> dfsNextLeafIterativeStack(TreeNode node) {

        Stack<Integer> result = new Stack<>();

        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);
        while(!stack.isEmpty()) {
            TreeNode treeNode = stack.pop();
            if(treeNode.left == null && treeNode.right == null) {
                result.push(node.key);
                continue;
            }
            if(treeNode.right != null) {
                stack.push(treeNode.right);
            }
            if(treeNode.left != null) {
                stack.push(treeNode.left);
            }
        }
        return result;
    }

    private static boolean isEqual(Stack<Integer> stack1, Stack<Integer> stack2) {
        while(!stack1.isEmpty() && !stack2.isEmpty()) {
            if(stack1.pop() != stack2.pop()) {
                return false;
            }
        }
        return stack1.isEmpty() && stack2.isEmpty();
    }

    public static boolean leafSequenceSimilar4(TreeNode node1, TreeNode node2) {
        Stack<TreeNode> stack1 = new Stack<>();
        stack1.push(node1);
        Stack<TreeNode> stack2 = new Stack<>();
        stack2.push(node2);

        while(!stack1.isEmpty() && !stack2.isEmpty()) {
            if(dfsNextLeafOnly(stack1).key != dfsNextLeafOnly(stack2).key) {
                return false;
            }
        }
        return stack1.isEmpty() && stack2.isEmpty();
    }

    private static TreeNode dfsNextLeafOnly(Stack<TreeNode> stack) {
        while(true) {
            TreeNode node = stack.pop();
            if(node.left == null && node.right == null) {
                return node;
            }
            if(node.right != null) {
                stack.push(node.right);
            }
            if(node.left != null) {
                stack.push(node.left);
            }
        }
    }


    private static void evaluate(TreeNode root1, TreeNode root2) {
        boolean result1 = leafSequenceSimilar1(root1, root2);
        System.out.println("Approach 1 : Collect all leafs RECURSIVELY with STACK and compare - " + result1);
        boolean result2 = leafSequenceSimilar2(root1, root2);
        System.out.println("Approach 2 : Collect all leafs RECURSIVELY with LIST and compare - " + result2);
        boolean result3 = leafSequenceSimilar3(root1, root2);
        System.out.println("Approach 3 : Collect all leafs ITERATIVELY with STACK and compare - " + result3);
        boolean result4 = leafSequenceSimilar4(root1, root2);
        System.out.println("Approach 4 : Collect ONE leaf ITERATIVELY with STACK and compare - " + result4);
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

        evaluate(root1, root2);
    }
}
