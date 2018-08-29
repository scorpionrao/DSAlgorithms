package mit;

public class DistanceBetweenNodesBinaryTree {
	
	public static class Node {
		int key;
		Node left;
		Node right;
		Node(int key) {
			this.key = key;
		}
	}

	public static class Tree {

		int key1Level;
		int key2Level;
		int dist = 0;

        /* Time Complexity - O(n) */
        private int findLevelBinaryTree(Node root, int key, int currentLevel) {
            if(root == null) {
                return -1;
            }
            if(root.key == key) {
                return currentLevel;
            }
            int leftTreeLevel = findLevelBinaryTree(root.left, key, 1 + currentLevel);
            int rightTreeLevel = findLevelBinaryTree(root.right, key, 1 + currentLevel);

            return (leftTreeLevel != -1) ? leftTreeLevel : rightTreeLevel;
        }

        /* Time Complexity - O(h) */
		private int findLevelBST(Node root, int key, int currentLevel) {
			if (root == null) {
				return -1;
			} else if (root.key == key) {
				return currentLevel;
			}
			if(key < root.key) {
				return findLevelBST(root.left, key, currentLevel + 1);
			} else {
				return findLevelBST(root.right, key, currentLevel + 1);
			}
		}

        /* Time Complexity - O(N) */
		private Node findLeastCommonAncestor(Node root, int key1, int key2, int currentLevel) {
			if(root == null) {
				return null;
			}
            // System.out.println("Solving: root = " + root.key);
            if(root.key == key1) {
				key1Level = currentLevel;
                /* targetFirstNode */
				return root;
			}
			if(root.key == key2) {
				key2Level = currentLevel;
                /* targetSecondNode */
				return root;
			}

			Node anyTargetInLeftTree = findLeastCommonAncestor(root.left, key1, key2, currentLevel + 1);
			Node anyTargetInRightTree = findLeastCommonAncestor(root.right, key1, key2, currentLevel + 1);

			if(anyTargetInLeftTree != null && anyTargetInRightTree != null) {
                /* root is the LCA */
				dist = key1Level + key2Level - (2 * currentLevel);
				return root;
			}

            /*
                Problem with this logic - If a node is match, all the children nodes are ignored.
            */
			return anyTargetInLeftTree != null ? anyTargetInLeftTree : anyTargetInRightTree;
		}

        /* Time Complexity - O(N) */
		public int findDistance(Node root, int key1, int key2) {

			// reset
			key1Level = -1;
			key2Level = -1;
			dist = 0;

			Node LCA = findLeastCommonAncestor(root, key1, key2, 1);

			if(key1Level != -1) {
				if(key2Level != -1) {
                    /*
                        Both keys exist.
                        Computed in findLeastCommonAncestor() method.
                     */
					return dist;
				} else {
                    /*
                        Key 1 (LCA) is ROOT and also ancestor of Key 2
                        Distance = Level of Key 2 in this tree.
                     */
					return findLevelBinaryTree(LCA, key2, 0);
				}
			} else {
				if(key2Level != -1) {
                    /*
                        Key 2 (LCA) is ROOT and also ancestor of Key 1
                        Distance = Level of Key 1 in this tree.
                     */
					return findLevelBinaryTree(LCA, key1, 0);
				} else {
                    /*
                        Both Key 1 and Key 2 do NOT exist
                     */
					return -1;
				}
			}
		}
	}

	public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.right.left.right = new Node(8);

        Tree tree = new Tree();
        System.out.println("Dist(4, 5): expected=2, actual=" + tree.findDistance(root, 4, 5));
        System.out.println("Dist(4, 6): expected=4, actual=" + tree.findDistance(root, 4, 6));
        System.out.println("Dist(3, 4): expected=3, actual=" + tree.findDistance(root, 3, 4));
        System.out.println("Dist(2, 4): expected=1, actual=" + tree.findDistance(root, 2, 4));
        System.out.println("Dist(8, 5): expected=5, actual=" + tree.findDistance(root, 8, 5));
	}

}
