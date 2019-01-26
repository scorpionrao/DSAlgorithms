package leetcode;

public class ClosestValue {

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

    private static void print(TreeNode root) {
        if(root == null) {
            return;
        }
        print(root.left);
        System.out.print(root.key + " -> ");
        print(root.right);
    }

    /*
        Top Down - Update result as you traverse.

        Time: O(Log N), Space: O(1)
    */
    private static int closestValueIteration(TreeNode root, double target) {

        if(root == null) {
            return -1;
        }

        TreeNode currentNode = root;
        int result = currentNode.key;
        while(currentNode != null) {
            double currentBest = Math.abs(target - result);
            double newBest = Math.abs(target - currentNode.key);
            if(newBest < currentBest) {
                result = currentNode.key;
            }
            currentNode = target < currentNode.key ? currentNode.left : currentNode.right;
        }
        return result;
    }

    /*
        Bottom Up - Bring the best value from sub-tree and compare it with root

        Time: O(Log N), Space: O(1)
    */
    private static int closestValueRecursive(TreeNode root, double target) {
        if(root == null) {
            return -1;
        }

        TreeNode currentNode = root;
        int rootResult = root.key;
        TreeNode subTree = target < currentNode.key ? currentNode.left : currentNode.right;
        int bestResultFromSubTree = closestValueRecursive(subTree, target);

        if(bestResultFromSubTree == -1) {
            return root.key;
        } else {
            double rootDifference = Math.abs(target - rootResult);
            double kidDifference = Math.abs(target - bestResultFromSubTree);
            return rootDifference < kidDifference ? rootResult : bestResultFromSubTree;
        }
    }

    private static void evaluate(TreeNode root, double target) {
        print(root);
        System.out.println();
        int result1 = closestValueIteration(root, target);
        System.out.println("Approach 1 : " + result1);
        int result2 = closestValueRecursive(root, target);
        System.out.println("Approach 2 : " + result2);
    }

    public static void main(String[] args) {
        /*
            TreeNode root = new TreeNode(4);
            root.left = new TreeNode(2);
            root.left.left = new TreeNode(1);
            root.left.right = new TreeNode(3);
            root.right = new TreeNode(5);
        */
        TreeNode root = new TreeNode(Integer.MAX_VALUE);
        evaluate(root, 0.0);
    }
}
