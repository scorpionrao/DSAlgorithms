package datastructures.binarysearchtree;

public class BSTSuccessorPredecessor {
	
	public static class Node {
		int val;
		Node left;
		Node right;
		Node(int val) {
			this.val = val;
		}
	}

	/*
        Cases:
            Left child + No Right child --> Traverse the parent.
            Left child + Single right child --> Right child.
            Right child + No Right child -->

            If a node N has a single right child, it doesn't matter when N is a left or right child.

        Recursion:
            If target < root, successorRecursion is in left tree or root.
            If target >= root, successorRecursion is in right tree.
	 */
	public Node successorRecursion(Node root, Node target) {

        if (root == null) {
			  return null;
        }

        if(target.val < root.val) {
            printForward(root, target, "left");
            Node left = successorRecursion(root.left, target);
            if(left != null) {
                printReturn(root, left, "left");
                return left;
            } else {
                printReturn(root, root, "root");
                return root;
            }
        } else {
            printForward(root, target, "right");
            Node result = successorRecursion(root.right, target);
            printReturn(root, result, "right");
            return result;
        }
    }

    public Node successorIterative(Node root, int search) {

        if(root == null) {
            throw new IllegalArgumentException();
        }

        Node ancestor = root;
        Node successor = null;

        while(ancestor.val != search) {
            if(search < ancestor.val) {
                successor = ancestor;
                ancestor = ancestor.left;
            } else {
                ancestor = ancestor.right;
            }
        }

        if(ancestor.right != null) {
            return findMin(ancestor.right);
        } else {
            return successor;
        }
    }

    private Node findMin(Node root) {
        if(root == null || root.left == null) {
            return root;
        } else {
            return findMin(root.left);
        }
    }

    /*
        Recursion:
            If target <= root, predecessor is in "left tree".
            If target > root, predecessor is in "right tree" or "root".
     */
    public Node predecessor(Node root, Node target) {

        if(root == null) {
            return null;
        }

        if(target.val <= root.val) {
            printForward(root, target, "left");
            Node result = predecessor(root.left, target);
            printReturn(root, result, "left");
            return result;
        } else {
            printForward(root, target, "right");
            Node right = predecessor(root.right, target);
            if(right != null) {
                printReturn(root, right, "right");
                return right;
            } else {
                printReturn(root, root, "right");
                return root;
            }
            //return (right != null) ? right : root;
        }
    }

    public void printForward(Node root, Node target, String direction) {
        //System.out.println(String.format("Target: %d, Root: %d,\t Moving: %s", target.val, root.val, direction));
    }

    public void printReturn(Node root, Node result, String direction) {
        if(result != null) {
            //System.out.println(String.format("Result: %d, Root: %d,\t Returning: %s", result.val, root.val, direction));
        } else {
            //System.out.println(String.format("Result: NULL, Root: %d,\t Returning: %s", root.val, direction));
        }
    }

    public void printAllSuccessors(Node root) {
        System.out.println("Successor of 1 : " + successorIterative(root, 1).val);
        System.out.println("Successor of 2 : " + successorIterative(root, 2).val);
        System.out.println("Successor of 3 : " + successorIterative(root, 3).val);
        System.out.println("Successor of 4 : " + successorIterative(root, 4).val);
        System.out.println("Successor of 5 : " + successorIterative(root, 5).val);
        System.out.println("Successor of 6 : " + successorIterative(root, 6).val);
        // Null Node will be returned.
        System.out.println("Successor of 7 : " + successorIterative(root, 7));
    }

	public static void main(String[] args) {
		Node root = new Node(4);
		root.left = new Node(2);
		root.right = new Node(6);
		root.left.left = new Node(1);
		root.left.right = new Node(3);
		root.right.left = new Node(5);
		root.right.right = new Node(7);

        BSTSuccessorPredecessor bstSuccessorPredecessor = new BSTSuccessorPredecessor();
        bstSuccessorPredecessor.printAllSuccessors(root);
/*
		BSTSuccessorPredecessor bstSuccessorPredecessor = new BSTSuccessorPredecessor();
		Node successor1 = bstSuccessorPredecessor.successorRecursion(root, root.left);
		System.out.println(String.format("Successor of " + root.left.val + " is = " + successor1.val));
        System.out.println();
        Node predecessor1 = bstSuccessorPredecessor.predecessor(root, root.left);
        System.out.println(String.format("Predecessor of " + root.left.val + " is = " + predecessor1.val));
        System.out.println();
        Node successor2 = bstSuccessorPredecessor.successorRecursion(root, root);
        System.out.println(String.format("Successor of " + root.val + " is = " + successor2.val));
        System.out.println();
        Node predecessor2 = bstSuccessorPredecessor.predecessor(root, root);
        System.out.println(String.format("Predecessor of " + root.val + " is = " + predecessor2.val));
        System.out.println();
        */


    }

}
