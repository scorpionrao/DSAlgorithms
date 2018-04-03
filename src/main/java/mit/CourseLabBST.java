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
	
	public static void insert(Node root, int newVal, int minGap) {
		
		if(root == null) {
			return;
		}
		// InOrder Traversal
		// Process first
		if(Math.abs(root.val - newVal) < minGap) {
			return;
		}
		root.size++;
		// End of Process
		if(newVal < root.val) {
			if(root.leftChild != null) {
				insert(root.leftChild, newVal, minGap);
			} else {
				root.leftChild = new Node(newVal);
			}
		} else {
			if(root.rightChild != null) {
				insert(root.rightChild, newVal, minGap);
			} else {
				root.rightChild = new Node(newVal);
			}
		}
	}
	
	public static void calculateCountLessThan(Node root, int k) {
		
		int count = 0;
		
		while(root != null) {
			if(k < root.val) {
				root = root.leftChild;
			} else {
				count = count + 1;
				if(root.leftChild != null) {
					count = count + root.leftChild.size;
				}
				root = root.rightChild;
			}
		}
		System.out.println("Count of values less than " + k + " = " + count);
	}
	
	public static void main(String[] args) {
		Node root = new Node(5);
		insert(root, 3, 2);
		insert(root, 7, 2);
		preOrderTraversal(root);
		System.out.println();
		insert(root, 9, 2);
		preOrderTraversal(root);
		System.out.println();
		insert(root, 1, 2);
		preOrderTraversal(root);
		System.out.println();
		calculateCountLessThan(root, 10);
	}
}
