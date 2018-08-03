package mit;

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

            Successor: Right child OR left most child of right child

            successor(target, root) {
                if(target < root) {
                    return successor(target, root.leftChild) OR root
                } else {
                    return successor(target, root.rightChild)
                }
            }

            TARGET
          /       \
         /         \
        /           \
       L             R
        \           /
         \         /
         PRED   SUCC


	 */
	public Node successor(Node root, Node target) {

        if (root == null) {
			  return null;
        }

        if(target.val < root.val) {
            printForward(root, target, "left");
            Node left = successor(root.left, target);
            if(left != null) {
                printReturn(root, left, "left");
                return left;
            } else {
                printReturn(root, root, "left");
                return root;
            }
            // return (left != null) ? left : root;
        } else {
            printForward(root, target, "right");
            Node result = successor(root.right, target);
            printReturn(root, result, "right");
            return result;
        }
    }

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
        System.out.println(String.format("Target: %d, Root: %d,\t Moving: %s", target.val, root.val, direction));
    }

    public void printReturn(Node root, Node result, String direction) {
        if(result != null) {
            System.out.println(String.format("Result: %d, Root: %d,\t Returning: %s", result.val, root.val, direction));
        } else {
            System.out.println(String.format("Result: NULL, Root: %d,\t Returning: %s", root.val, direction));
        }
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
		Node successor1 = bstSuccessorPredecessor.successor(root, root.left);
		System.out.println(String.format("Successor of " + root.left.val + " is = " + successor1.val));
        System.out.println();
        Node predecessor1 = bstSuccessorPredecessor.predecessor(root, root.left);
        System.out.println(String.format("Predecessor of " + root.left.val + " is = " + predecessor1.val));
        System.out.println();
        Node successor2 = bstSuccessorPredecessor.successor(root, root);
        System.out.println(String.format("Successor of " + root.val + " is = " + successor2.val));
        System.out.println();
        Node predecessor2 = bstSuccessorPredecessor.predecessor(root, root);
        System.out.println(String.format("Predecessor of " + root.val + " is = " + predecessor2.val));
        System.out.println();
    }

}
