package zr;

import java.util.Stack;

public class BinaryTreeIdentical {

    public static class Node {
        public int data;
        public Node left, right;
        public Node(int data) {
            this.data = data;
            this.left = this.right = null;
        }
    }

    public static class Solution {

        /*
            sameTree(tree1, tree2)
            1. If both trees are empty then return 1.
            2. Else If both trees are non -empty
                (a) Check data of the root nodes (tree1->data ==  tree2->data)
                (b) Check left subtrees recursively
                    i.e., call sameTree(tree1->left_subtree, tree2->left_subtree)
                (c) Check right subtrees recursively
                    i.e., call sameTree( tree1->right_subtree, tree2->right_subtree)
                (d) If a,b and c are true then return 1.
            3.  Else return 0 (one is empty and other is not)
         */

        /* O(N) */
        public boolean isIdenticalRecursionBoolean(Node node1, Node node2) {
            // covers null or same tree
            if(node1 == node2) {
                return true;
            }

            if(node1 != null && node2 != null) {
                return node1.data == node2.data
                        && isIdenticalRecursionBoolean(node1.left, node2.left)
                        && isIdenticalRecursionBoolean(node2.right, node2.right);
            }
            return false;
        }

        public int isIdenticalRecursionInteger(Node root1, Node root2) {
            // covers null or same tree
            if(root1 == root2) {
                return 1;
            }

            if(root1 != null && root2 != null) {
                int data;
                if(root1.data == root2.data) {
                    data = 1;
                } else {
                    data = 0;
                }
                int left = isIdenticalRecursionInteger(root1.left, root2.left);
                int right = isIdenticalRecursionInteger(root1.right, root2.right);

                if(left == 1 && right == 1 && data == 1) {
                    return 1;
                }
            }
            return 0;
        }

        public void isIdenticalIterativeVoid(Node root1, Node root2) {
            // covers null or same tree
            if(root1 == root2) {
                return;
            }

            Stack<Node> leftStack = new Stack<>();
            Stack<Node> rightStack = new Stack<>();

            System.out.println();
            /* traverse the tree */
            while (root1 != null && root2 != null
                    || !leftStack.isEmpty() && !rightStack.isEmpty()) {

                while (root1 !=  null && root2 != null) {
                    leftStack.push(root1);
                    rightStack.push(root2);
                    root1 = root1.left;
                    root2 = root2.left;
                }

                root1 = leftStack.pop();
                root2 = rightStack.pop();

                /* DATA discrepancy */
                if(root1.data != root2.data) {
                    System.out.println("Trees are not identifical");
                    return;
                }

                root1 = root1.right;
                root2 = root2.right;
            }

            /* Structure Discrepancy */
            if(root1 == null || root2 == null || !leftStack.isEmpty() || !rightStack.isEmpty()) {
                System.out.println("Trees are NOT identical");
            } else {
                System.out.println("Trees are identical");
            }
        }


        public void evaluate(Node root1, Node root2) {
            boolean recursionBoolean = isIdenticalRecursionBoolean(root1, root2);
            System.out.println("Recursion Boolean - " + recursionBoolean);
            int recursionInteger = isIdenticalRecursionInteger(root1, root2);
            System.out.println("Recursion Integer - " + recursionInteger);
            isIdenticalIterativeVoid(root1, root2);
        }

    }

    public static void main(String[] args) {

        Node root1 = new Node(1);
        root1.left = new Node(2);
        root1.right = new Node(3);
        root1.left.left = new Node(4);
        root1.left.right = new Node(5);

        Node root2 = new Node(1);
        root2.left = new Node(2);
        root2.right = new Node(3);
        root2.left.left = new Node(4);
        root2.left.right = new Node(5);

        Solution solution = new Solution();
        solution.evaluate(root1, root2);
    }
}
