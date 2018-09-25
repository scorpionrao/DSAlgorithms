package algorithms.cracking;

import java.util.Stack;

public class KthToLastBST {

    public static class Node {
        int key;
        Node left;
        Node right;
        Node(int key) {
            this.key = key;
        }
    }

    public static class Solution {

        public void dfsInOrderRecursive1(Node root, int k) {
            int[] counter = new int[1];
            dfsInOrderRecursive1(root.left, k, counter);
        }

        /* O(N) */
        public void dfsInOrderRecursive1(Node root, int k, int[] counter) {
            if(root == null) {
                return;
            }
            dfsInOrderRecursive1(root.left, k, counter);
            counter[0]++;
            if(counter[0] == k) {
                System.out.println(root.key);
            }
            dfsInOrderRecursive1(root.right, k, counter);
        }

        int counter;
        public void dfsInOrderRecursive2(Node root, int k) {
            counter = k;
            dfsInOrderRecursive2(root);
            return;
        }

        public void dfsInOrderRecursive2(Node root) {
            if(root.left != null) {
                dfsInOrderRecursive2(root.left);
            }
            counter--;
            if(counter == 0) {
                System.out.println(root.key);
            }
            if(root.right != null) {
                dfsInOrderRecursive2(root.right);
            }
        }

        public void dfsInOrderIterative(Node root, int k) {
            if(root == null) {
                return;
            }

            Stack<Node> stack = new Stack();

            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            while (k != 0 && !stack.isEmpty()) {
                Node node = stack.pop();
                k--;
                if(k == 0) {
                    System.out.println(node.key);
                    return;
                }
                Node right = node.right;
                while(right != null) {
                    stack.push(node);
                    // left node
                    right = right.left;
                }
            }
        }

        public int dfsBinarySearch(Node root, int k) {
            if(root == null) {
                return 0;
            }

            int count = countNodes(root.left);
            if(k <= count) {
                dfsBinarySearch(root.left, k);
            } else {
                dfsBinarySearch(root.right, k - count - 1);
            }
            return root.key;
        }

        public int countNodes(Node root) {
            if(root == null) {return 0;}
            return 1 + countNodes(root.left) + countNodes(root.right);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        Node root = new Node(4);
        root.left = new Node(2);
        root.right = new Node(6);

        root.left.left = new Node(1);
        root.left.right = new Node(3);

        root.right.left = new Node(5);
        root.right.right = new Node(7);

        solution.dfsInOrderRecursive1(root, 3);
        solution.dfsInOrderRecursive2(root, 3);
        solution.dfsInOrderIterative(root, 3);
        solution.dfsBinarySearch(root, 3);
    }
}
