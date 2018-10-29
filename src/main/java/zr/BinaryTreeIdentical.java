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

            if(node1 == null || node2 == null) {
                return false;
            }

            return node1.data == node2.data
                        && isIdenticalRecursionBoolean(node1.left, node2.left)
                        && isIdenticalRecursionBoolean(node2.right, node2.right);
        }

        /* O(N) */
        public boolean isMirrorRecursionBoolean(Node node1, Node node2) {
            // covers null or same tree
            if(node1 == node2) {
                return true;
            }

            if(node1 == null || node2 == null) {
                return false;
            }

            return node1.data == node2.data
                    && isMirrorRecursionBoolean(node1.left, node2.right)
                    && isMirrorRecursionBoolean(node1.right, node2.left);
        }


        /*
            1 - Identical
            0 - Not Identical
         */
        public int isIdenticalRecursionInteger(Node root1, Node root2) {
            // covers null or same tree
            if(root1 == root2) {
                return 1;
            }

            else if(root1 == null || root2 == null) {
                return 0;
            }

            else {
                if (root1.data == root2.data
                        && isIdenticalRecursionInteger(root1.left, root2.left) == 1
                        && isIdenticalRecursionInteger(root1.right, root2.right) == 1) {

                    return 1;
                } else {
                    return 0;
                }
            }
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
            while((leftCurrent != null && rightCurrent != null)
                    || (!leftStack.isEmpty() && !rightStack.isEmpty())) {

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

        /* O(m * n) - There is possibility that n times */
        public boolean isSubTree(Node haystack, Node needle) {

            if(needle == null) {
                /* non-existing sub tree */
                return true;
            }

            if(haystack == null) {
                /* non-existing parent with existing sub tree */
                return false;
            }

            return isIdenticalRecursionBoolean(haystack, needle)
                    || isSubTree(haystack.left, needle)
                    || isSubTree(haystack.right, needle);
        }

        /* O(n) */
        public boolean isSubTreeOptimized(Node haystack, Node needle) {

            /*
                inOrderHaystack
                inOrderNeedle
                preOrderHaystack
                preOrderNeedle

                return inOrderHaystack.contains(inOrderNeedle) && preOrderHaystack.contains(preOrderNeedle);
             */
            return false;
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

        Node root4 = new Node(2);
        root4.left = new Node(4);
        root4.right = new Node(5);

        Solution solution = new Solution();
        solution.evaluate(root1, root2);
        solution.evaluate(root1, root3);

        System.out.println("SubTree: " + solution.isSubTree(root1, root4));
        System.out.println("SubTree Optimized: " + solution.isSubTreeOptimized(root1, root4));
    }
}
