package zr;

import java.util.Stack;

public class BinaryTreeInOrderTraversalNoRecursion {

    /* Class containing left and right child of current node and key value*/
    static class Node {
        int data;
        Node left, right;

        public Node(int item) {
            data = item;
            left = right = null;
        }
    }

    /* Class to print the inorder traversal */
    static class BinaryTree {
        Node root;

        /*
            1) Create an empty stack S.
            2) Initialize current node as root
            3) Push the current node to S and set current = current->left until current is NULL
            4) If current is NULL and stack is not empty then
                a) Pop the top item from stack.
                b) Print the popped item, set current = popped_item->right
                c) Go to step 3.
            5) If current is NULL and stack is empty then we are done.
         */
        void inorder() {
            if (root == null)
                return;

            Stack<Node> s = new Stack<Node>();
            Node curr = root;

            System.out.println();
            /* traverse the tree */
            while (curr != null || !s.isEmpty()) {

                /* Reach the left most Node of the curr Node */
                while (curr !=  null) {
                    /* place pointer to a tree node on the stack
                    before traversing the node's left subtree */
                    s.push(curr);
                    curr = curr.left;
                }

                /* Current must be NULL at this point */
                curr = s.pop();

                System.out.print(curr.data + " ");

                /* we have visited the node and its
                    left subtree.  Now, it's right subtree's turn
                */
                curr = curr.right;
            }
        }

        /*
            1) Create an empty stack S.
            2) Initialize current node as root
            3) Push the current node to S and set current = current->left until current is NULL
            4) If current is NULL and stack is not empty then
                a) Pop the top item from stack.
                b) Print the popped item, set current = popped_item->right
                c) Go to step 3.
            5) If current is NULL and stack is empty then we are done.
         */
        boolean isBST() {
            if (root == null)
                return false;

            Stack<Node> s = new Stack<Node>();
            Node curr = root;
            int previousValue = curr.data;

            /* traverse the tree */
            while (curr != null || !s.isEmpty()) {

                /* Reach the left most Node of the curr Node */
                while (curr !=  null) {
                    /* place pointer to a tree node on the stack
                    before traversing the node's left subtree */
                    s.push(curr);
                    previousValue = curr.data;
                    curr = curr.left;
                }

                /* Current must be NULL at this point */
                curr = s.pop();

                if(curr.data < previousValue) {
                    return false;
                } else {
                    previousValue = curr.data;
                }

                /* we have visited the node and its
                    left subtree.  Now, it's right subtree's turn
                */
                curr = curr.right;
            }
            return true;
        }

        public static void main(String args[]) {

            /* creating a binary tree and entering the nodes */
            BinaryTree tree = new BinaryTree();
            tree.root = new Node(1);
            tree.root.left = new Node(2);
            tree.root.right = new Node(3);
            tree.root.left.left = new Node(4);
            tree.root.left.right = new Node(5);
            tree.inorder();

            BinaryTree tree1 = new BinaryTree();
            tree1.root = new Node(4);
            tree1.root.left = new Node(2);
            tree1.root.right = new Node(6);
            tree1.root.left.left = new Node(1);
            tree1.root.left.right = new Node(3);
            tree1.root.right.left = new Node(5);
            tree1.root.right.right = new Node(7);
            tree1.inorder();
            System.out.println("\n" + tree1.isBST());
        }
    }
}
