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
        Recursion:
            If target < root, successor is in left tree or root.
            If target >= root, successor is in right tree.
	 */
	public Node successorRecursion(Node root, Node target) {

        if (root == null) {
			  return null;
        }

        if(target.val < root.val) {
            //printForward(root, target, "left");
            Node left = successorRecursion(root.left, target);
            if(left != null) {
                //printReturn(root, left, "left");
                return left;
            } else {
                //printReturn(root, root, "root");
                return root;
            }
        } else {
            //printForward(root, target, "right");
            Node result = successorRecursion(root.right, target);
            //printReturn(root, result, "right");
            return result;
        }
    }

    /*
        Case 1 - If target node has right child, find min of right child.
        Case 2 - If target node has no right child, find latest ancestor from which we branched left.
     */
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
        if(root == null) {
            return;
        }
        printAllSuccessors(root.left);
        Node successor = successorIterative(root, root.val);
        if(successor != null) {
            System.out.println("Successor of " + root.val + " : " + successor.val);
        }
        printAllSuccessors(root.right);
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
    }

}
