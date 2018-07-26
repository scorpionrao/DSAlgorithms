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

        /* Verify with Inorder Traversal */
        public void isIdenticalIterativeVoid1(Node leftRoot, Node rightRoot) {

            // covers null or same tree
            if(leftRoot == rightRoot) {
                System.out.println("Iterative Void InOrder - " + true);
                return;
            }

            Stack<Node> leftStack = new Stack<>();
            Stack<Node> rightStack = new Stack<>();

            Node leftCurrent = leftRoot;
            Node rightCurrent = rightRoot;

            /* traverse the tree */
            while(
                    (leftCurrent != null && rightCurrent != null) || (!leftStack.isEmpty() && !rightStack.isEmpty())
                  ) {

                while (leftCurrent !=  null && rightCurrent != null) {
                    leftStack.push(leftCurrent);
                    rightStack.push(rightCurrent);
                    leftCurrent = leftCurrent.left;
                    rightCurrent = rightCurrent.left;
                }

                /*
                    STRUCTURE discrepancy
                */
                if(leftCurrent != null || rightCurrent != null) {
                    /* One of the nodes is child of leaf and other is not */
                    System.out.println("Iterative Void InOrder - " + false);
                    return;
                }

                leftCurrent = leftStack.pop();
                rightCurrent = rightStack.pop();

                /* DATA discrepancy - "InOrder" verification */
                if(leftCurrent.data != rightCurrent.data) {
                    System.out.println("Iterative Void InOrder - " + false);
                    return;
                }

                leftCurrent = leftCurrent.right;
                rightCurrent = rightCurrent.right;
            }

            /* Structure Discrepancy */
            if(leftCurrent != null || rightCurrent != null || !leftStack.isEmpty() || !rightStack.isEmpty()) {
                System.out.println("Iterative Void InOrder - " + false);
            } else {
                System.out.println("Iterative Void InOrder - " + true);
            }
        }

        /* Verify without PreOrder Traversal */
        public void isIdenticalIterativeVoid2(Node leftRoot, Node rightRoot) {

            // covers null or same tree
            if(leftRoot == rightRoot) {
                System.out.println("Iterative Void PreOrder - " + true);
                return;
            }

            Stack<Node> leftStack = new Stack<>();
            Stack<Node> rightStack = new Stack<>();

            Node leftCurrent = leftRoot;
            Node rightCurrent = rightRoot;

            /* traverse the tree */
            while(
                    (leftCurrent != null && rightCurrent != null) || (!leftStack.isEmpty() && !rightStack.isEmpty())
                    ) {


                /* DATA discrepancy - "PreOrder" verification */
                if(leftCurrent != null && rightCurrent != null) {
                    if (leftCurrent.data != rightCurrent.data) {
                        System.out.println("Iterative Void PreOrder - " + false);
                        return;
                    }
                }

                while (leftCurrent !=  null && rightCurrent != null) {
                    leftStack.push(leftCurrent);
                    rightStack.push(rightCurrent);
                    leftCurrent = leftCurrent.left;
                    rightCurrent = rightCurrent.left;
                }

                /*
                    STRUCTURE discrepancy
                */
                if(leftCurrent != null || rightCurrent != null) {
                    /* One of the nodes is child of leaf and other is not */
                    System.out.println("Iterative Void PreOrder - " + false);
                    return;
                }

                leftCurrent = leftStack.pop();
                rightCurrent = rightStack.pop();

                leftCurrent = leftCurrent.right;
                rightCurrent = rightCurrent.right;
            }

            /* Structure Discrepancy */
            if(leftCurrent != null || rightCurrent != null || !leftStack.isEmpty() || !rightStack.isEmpty()) {
                System.out.println("Iterative Void PreOrder - " + false);
            } else {
                System.out.println("Iterative Void PreOrder - " + true);
            }
        }

        public void isMirrorIterativeVoid(Node leftRoot, Node rightRoot) {

            // covers null or same tree
            if(leftRoot == rightRoot) {
                System.out.println("Iterative Void InOrder - " + true);
                return;
            }

            Stack<Node> leftStack = new Stack<>();
            Stack<Node> rightStack = new Stack<>();

            Node leftCurrent = leftRoot;
            Node rightCurrent = rightRoot;

            /* traverse the tree */
            while(
                    (leftCurrent != null && rightCurrent != null) || (!leftStack.isEmpty() && !rightStack.isEmpty())
                    ) {

                while (leftCurrent !=  null && rightCurrent != null) {
                    leftStack.push(leftCurrent);
                    rightStack.push(rightCurrent);
                    leftCurrent = leftCurrent.left;
                    rightCurrent = rightCurrent.right;
                }

                /*
                    STRUCTURE discrepancy
                */
                if(leftCurrent != null || rightCurrent != null) {
                    /* One of the nodes is child of leaf and other is not */
                    System.out.println("Iterative Void InOrder - " + false);
                    return;
                }

                leftCurrent = leftStack.pop();
                rightCurrent = rightStack.pop();

                /* DATA discrepancy - "InOrder" verification */
                if(leftCurrent.data != rightCurrent.data) {
                    System.out.println("Iterative Void InOrder - " + false);
                    return;
                }

                leftCurrent = leftCurrent.right;
                rightCurrent = rightCurrent.left;
            }

            /* Structure Discrepancy */
            if(leftCurrent != null || rightCurrent != null || !leftStack.isEmpty() || !rightStack.isEmpty()) {
                System.out.println("Iterative Void InOrder - Mirror " + false);
            } else {
                System.out.println("Iterative Void InOrder - Mirror " + true);
            }
        }

        public void evaluate(Node root1, Node root2) {
            boolean recursionBoolean = isIdenticalRecursionBoolean(root1, root2);
            System.out.println("Recursion Boolean - " + recursionBoolean);
            int recursionInteger = isIdenticalRecursionInteger(root1, root2);
            System.out.println("Recursion Integer - " + recursionInteger);
            isIdenticalIterativeVoid1(root1, root2);
            isIdenticalIterativeVoid2(root1, root2);
            isMirrorIterativeVoid(root1, root2);
            System.out.println();
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

        Node root3 = new Node(1);
        root3.right = new Node(2);
        root3.left = new Node(3);
        root3.right.right = new Node(4);
        root3.right.left = new Node(5);

        Solution solution = new Solution();
        solution.evaluate(root1, root2);
        solution.evaluate(root1, root3);
    }
}
