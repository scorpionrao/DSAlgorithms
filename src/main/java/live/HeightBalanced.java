package live;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
public class HeightBalanced {

    private static class Node {
        int val;
        int height;
        Node left;
        Node right;
        public Node(int val) {
            this.val = val;
        }
        public Node(int val, int height) {
            this.val = val;
            this.height = height;
        }
    }

    private static boolean recursiveDecideAtEnd(Node root) {
        int[] maxMin = {Integer.MIN_VALUE, Integer.MAX_VALUE};
        recursiveInOrderNoDecision(root, 0, maxMin);
        return Math.abs(maxMin[0] - maxMin[1]) <= 1;
    }
    private static void recursiveInOrderNoDecision(Node root, int height, int[] maxMin) {
        if(root == null) { return; }
        recursiveInOrderNoDecision(root.left, height + 1, maxMin);
        if(isLeaf(root)) {
            maxMin[0] = Math.max(maxMin[0], height);
            maxMin[1] = Math.min(maxMin[1], height);
        }
        recursiveInOrderNoDecision(root.right, height + 1, maxMin);
    }

    private static boolean recursiveDecideAtEndCarryToParent(Node root) {
        int leftHeight = recursiveDecideAtEndCarryToParent(root.left, 1);
        int rightHeight = recursiveDecideAtEndCarryToParent(root.right, 1);
        // Diff only at the root
        return Math.abs(leftHeight - rightHeight) <= 1;
    }

    private static int recursiveDecideAtEndCarryToParent(Node node, int height) {
        // NO DIFF
        if(node == null) {
            return -1;
        } else if(isLeaf(node)) {
            return height;
        } else {
            // Parent - one child or two child
            return Math.max(recursiveDecideAtEndCarryToParent(node.left, height + 1),
                            recursiveDecideAtEndCarryToParent(node.right, height + 1));

        }
    }

    private static boolean isLeaf(Node root) {
        return root.left == null && root.right == null;
    }

    private static boolean recursiveDecideAtEachLeaf(Node root) {
        int[] maxMin = {Integer.MIN_VALUE, Integer.MAX_VALUE};
        return recursivePreOrderDecideAtEachLeaf(root, 0, maxMin);
    }

    private static boolean recursivePreOrderDecideAtEachLeaf(Node root, int height, int[] maxMin) {
        // nodes with one child
        if(root == null) { return true; }
        // leafs
        if(isLeaf(root)) {
            maxMin[0] = Math.max(maxMin[0], height);
            maxMin[1] = Math.min(maxMin[1], height);
            return Math.abs(maxMin[0] - maxMin[1]) <= 1;
        }
        return recursivePreOrderDecideAtEachLeaf(root.left, height + 1, maxMin)
                && recursivePreOrderDecideAtEachLeaf(root.right, height + 1, maxMin);
    }

    private static boolean iterateDfsPreOrderDecisionAtEnd(Node root) {
        int maxLeafHeight = Integer.MIN_VALUE;
        int minLeafHeight = Integer.MAX_VALUE;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            Node node = stack.pop();
            // inspection of parent before child
            if(isLeaf(node)) {
                maxLeafHeight = Math.max(maxLeafHeight, node.height);
                minLeafHeight = Math.max(minLeafHeight, node.height);
            }
            // inspection of left before right
            if(node.right != null) {
                stack.push(node.right);
            }
            if(node.left != null) {
                stack.push(node.left);
            }
        }
        return Math.abs(maxLeafHeight - minLeafHeight) <= 1;
    }

        private static boolean iterateDfsPreOrderDecisionAtEachLeaf(Node root) {
            int maxLeafHeight = Integer.MIN_VALUE;
            int minLeafHeight = Integer.MAX_VALUE;
            Stack<Node> stack = new Stack<>();
            stack.push(root);
            while(!stack.isEmpty()) {
                Node node = stack.pop();
                if(isLeaf(node)) {
                    maxLeafHeight = Math.max(maxLeafHeight, node.height);
                    minLeafHeight = Math.max(minLeafHeight, node.height);
                    if(!(Math.abs(maxLeafHeight - minLeafHeight) <= 1)) {
                        return false;
                    }
                }
                if(node.right != null) {
                    stack.push(node.right);
                }
                if(node.left != null) {
                    stack.push(node.left);
                }
            }
            return true;
        }

        private static boolean iterateBfsDecisionAtEnd(Node root) {
            int maxLeafHeight = Integer.MIN_VALUE;
            int minLeafHeight = Integer.MAX_VALUE;
            Queue<Node> queue = new LinkedList<>();
            queue.offer(root);
            while(!queue.isEmpty()) {
                Node node = queue.poll();
                if(isLeaf(node)) {
                    maxLeafHeight = Math.max(maxLeafHeight, node.height);
                    minLeafHeight = Math.max(minLeafHeight, node.height);
                }
                if(node.left != null) {
                    queue.offer(node.left);
                }
                if(node.right != null) {
                    queue.offer(node.right);
                }
            }
            return Math.abs(maxLeafHeight - minLeafHeight) <= 1;
        }

        private static boolean iterateBfsDecisionAtEachLeaf(Node root) {
            int maxLeafHeight = Integer.MIN_VALUE;
            int minLeafHeight = Integer.MAX_VALUE;
            Queue<Node> queue = new LinkedList<>();
            queue.offer(root);
            while(!queue.isEmpty()) {
                Node node = queue.poll();
                if(isLeaf(node)) {
                    maxLeafHeight = Math.max(maxLeafHeight, node.height);
                    minLeafHeight = Math.max(minLeafHeight, node.height);
                    if(!(Math.abs(maxLeafHeight - minLeafHeight) <= 1)) {
                        return false;
                    }
                }
                if(node.left != null) {
                    queue.offer(node.left);
                }
                if(node.right != null) {
                    queue.offer(node.right);
                }
            }
            return true;
        }

    public static void main(String[] args) {
            Node root = new Node(1);
            root.left = new Node(2);
            root.right = new Node(3);
            root.left.left = new Node(4);
            root.left.right = new Node(5);
            root.left.left.left = new Node(6);
            /*
            root.right.right = new TrieNode(7);
            root.right.left.right = new TrieNode(8);

            System.out.println("Recursive - \tTraverse InOrder - \tDecision at End : \t\t" + recursiveDecideAtEnd(root));
            System.out.println("Recursive - \tTraverse PreOrder - \tDecision at Each Leaf : \t" + recursiveDecideAtEachLeaf(root));
            System.out.println("Iterate DFS - \tTraverse PreOrder - \tDecision at End : \t\t" + iterateDfsPreOrderDecisionAtEnd(root));
            System.out.println("Iterate DFS - \tTraverse PreOrder - \tDecision at Each Leaf : \t" + iterateDfsPreOrderDecisionAtEachLeaf(root));
            System.out.println("Iterate BFS - \t\t\t\tDecision at End : \t\t" + iterateBfsDecisionAtEnd(root));
            System.out.println("Iterate BFS - \t\t\t\tDecision at Each Leaf : \t" + iterateBfsDecisionAtEachLeaf(root));
            */
            System.out.println(recursiveDecideAtEndCarryToParent(root));
        }
}
