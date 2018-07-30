package mit;

public class CourseLabBST {
	
	public static class Node {
		int val;
		Node leftChild;
		Node rightChild;
		int size;
		public Node(int val) {
			this.val = val;
			this.size = 1;
		}
	}
	
	public static void preOrderTraversal(Node root) {
		if(root == null) {
			return;
		}
		preOrderTraversal(root.leftChild);
		System.out.print(root.val + "->" + root.size + " ");
		preOrderTraversal(root.rightChild);
	}
	
	public static void insertRecursion(Node root, int newVal, int minGap) {
		
		if(root == null) {
			return;
		}

		// Process first
		if(Math.abs(root.val - newVal) < minGap) {
			return;
		}

        // fantastic implementation for a stream
		root.size++;
		// End of Process
		if(newVal < root.val) {
			if(root.leftChild != null) {
				insertRecursion(root.leftChild, newVal, minGap);
			} else {
				root.leftChild = new Node(newVal);
			}
		} else {
			if(root.rightChild != null) {
				insertRecursion(root.rightChild, newVal, minGap);
			} else {
				root.rightChild = new Node(newVal);
			}
		}
	}

	public static void calculateCountLessThanRecursionVoid(Node root, int k) {
		int[] count = new int[1];
		calculateCountLessThanRecursion(root, k, count);
		System.out.println("Recursion: Count of values less than " + k + " = " + count[0]);
	}

	private static void calculateCountLessThanRecursion(Node root, int k, int[] count) {
		if(root == null || root.val > k) {return;}
		calculateCountLessThanRecursion(root.leftChild, k, count);
		count[0]++;
		calculateCountLessThanRecursion(root.rightChild, k, count);
	}

    public static int calculateCountLessThanRecursionInt(Node root, int k) {
        if(root == null || root.val > k) {return 0;}
        return 1 +
                calculateCountLessThanRecursionInt(root.leftChild, k) +
                calculateCountLessThanRecursionInt(root.rightChild, k);
    }

    /* ASSUMPTION - Each node stores the size */
	public static void calculateCountLessThanIterative(Node root, int target) {
		
		int count = 0;
		
		while(root != null) {
            /* examine all left nodes before adding '1' for current node */
			if(target < root.val) {
				root = root.leftChild;
			} else {

                /* Target >= root */

                /* Blindly add left child size */
                if(root.leftChild != null) {
                    count = count + root.leftChild.size;
                }

                /* ADD '1' for root node */
				count = count + 1;

				root = root.rightChild;
			}
		}
		System.out.println("Iterative: Count of values less than " + target + " = " + count);
	}

    public static void evaluate(Node root, int target) {
        calculateCountLessThanIterative(root, target);
        calculateCountLessThanRecursionVoid(root, target);
        System.out.println("Recursion: Count of values less than " + target + " = " + calculateCountLessThanRecursionInt(root, target));
    }
	
	public static void main(String[] args) {
		Node root = new Node(5);
		insertRecursion(root, 3, 2);
		insertRecursion(root, 7, 2);
		preOrderTraversal(root);
		System.out.println();
		insertRecursion(root, 9, 2);
		preOrderTraversal(root);
		System.out.println();
		insertRecursion(root, 1, 2);
		preOrderTraversal(root);
		System.out.println();
        evaluate(root, 7);
	}
}