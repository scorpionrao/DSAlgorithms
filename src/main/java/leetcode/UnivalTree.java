package leetcode;

public class UnivalTree {

    private static class Node {
        int key;
        Node left;
        Node right;
        Node(int key) {
            this.key = key;
            this.left = null;
            this.right = null;
        }
    }

    public static boolean isUnivalTree(Node root) {
        return isUnivalTree(root, root.key);
    }

    /* Pre-order traversal */
    private static boolean isUnivalTree(Node root, int val) {
        /* Base case - Null node. Return value can be combined. */
        if(root == null) {
            return true;
        }

        return root.key == val && isUnivalTree(root.left, val) && isUnivalTree(root.right, val);
    }

    private static int countUnivalTrees(Node root) {
        int[] count = new int[1];
        isNodeUnival(root, count);
        return count[0];
    }

    /* Post-Order Traversal - Process children before Root */
    private static boolean isNodeUnival(Node root, int[] count) {

        if(root == null) {
            return true;
        }
        boolean leftSubTree = isNodeUnival(root.left, count);
        boolean rightSubTree = isNodeUnival(root.right, count);
        boolean leftVal = (root.left == null) ? true : root.key == root.left.key;
        boolean rightVal = (root.right == null) ? true : root.key == root.right.key;

        boolean isNodeUnival = leftSubTree && rightSubTree && leftVal && rightVal;

        if(isNodeUnival) {
            count[0]++;
        }
        return isNodeUnival;
    }

    private static int countNodes(Node root) {
        if(root == null) {
            return 0;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    private static class Tuple {
        Node node;
        int depth;
        Tuple(Node node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    private static Tuple deepestNode(Node node) {

        if(node.left == null && node.right == null) {
            return new Tuple(node, 1);
        }

        Tuple maxTuple = null;

        if(node.left == null) {
            maxTuple = deepestNode(node.right);
        } else if(node.right == null) {
            maxTuple = deepestNode(node.left);
        } else {
            Tuple leftTuple = deepestNode(node.left);
            Tuple rightTuple = deepestNode(node.right);
            maxTuple = (leftTuple.depth > rightTuple.depth) ? leftTuple : rightTuple;
        }
        // increase the depth for whoever won the competition
        maxTuple.depth++;
        return maxTuple;
    }

    public static void main(String[] args) {
        Node root = new Node(0);
        root.left = new Node(1);
        root.right = new Node(0);
        root.right.left = new Node(1);
        root.right.right = new Node(0);
        root.right.left.left = new Node(1);
        root.right.left.right = new Node(1);

        System.out.println("Is Unival Tree : " + isUnivalTree(root));
        System.out.println("Count Unival Trees : " + countUnivalTrees(root));
        System.out.println("Count Noes : " + countNodes(root));

        Node root1 = new Node(4);
        root1.left = new Node(2);
        root1.right = new Node(6);
        root1.right.left = new Node(5);
        root1.right.right = new Node(7);
        root1.right.right.right = new Node(8);
        Tuple resultTuple = deepestNode(root1);
        System.out.println("Deepest Node : " + resultTuple.node.key + " with depth of " + resultTuple.depth);
    }
}
