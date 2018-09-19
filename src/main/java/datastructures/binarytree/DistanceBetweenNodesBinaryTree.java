package datastructures.binarytree;

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
		private int findLevelForParticularNodeBST(Node root, int key, int currentLevel) {
			if (root == null) {
				return -1;
			} else if (root.key == key) {
				return currentLevel;
			}
			if(key < root.key) {
				return findLevelForParticularNodeBST(root.left, key, currentLevel + 1);
			} else {
				return findLevelForParticularNodeBST(root.right, key, currentLevel + 1);
			}
		}

        /* Time Complexity - O(N) */
		private Node findLeastCommonAncestor(Node root, int key1, int key2, int currentLevel, int[] keyLevelAndDistance) {
			if(root == null) {
				return null;
			}
            // System.out.println("Solving: root = " + root.key);
            if(root.key == key1) {
                keyLevelAndDistance[0] = currentLevel;
                /* targetFirstNode */
				return root;
			}
			if(root.key == key2) {
                keyLevelAndDistance[1] = currentLevel;
                /* targetSecondNode */
				return root;
			}

			Node anyTargetInLeftTree = findLeastCommonAncestor(root.left, key1, key2, 1+currentLevel, keyLevelAndDistance);
			Node anyTargetInRightTree = findLeastCommonAncestor(root.right, key1, key2, 1+currentLevel, keyLevelAndDistance);

			if(anyTargetInLeftTree != null && anyTargetInRightTree != null) {
                /* root is the LCA */
                keyLevelAndDistance[2] = keyLevelAndDistance[0] + keyLevelAndDistance[1] - (2 * currentLevel);
				return root;
			}

            /*
                Problem with this logic - If a node is match, all the children nodes are ignored.
            */
			return anyTargetInLeftTree != null ? anyTargetInLeftTree : anyTargetInRightTree;
		}

        /*


            Time Complexity - O(N)
        */
		public int findDistance(Node root, int key1, int key2) {

            int[] keyLevelAndDistance = new int[3];
			keyLevelAndDistance[0] = -1;
			keyLevelAndDistance[1] = -1;
            keyLevelAndDistance[2] = 0;

			Node LCA = findLeastCommonAncestor(root, key1, key2, 1, keyLevelAndDistance);

            if(LCA.key == key1) {
                return findLevelBinaryTree(LCA, key2, 0);
            } else if (LCA.key == key2) {
                return findLevelBinaryTree(LCA, key1, 0);
            } else if (keyLevelAndDistance[0] != -1 && keyLevelAndDistance[1] != -1) {
                return keyLevelAndDistance[2];
            } else {
                return -1;
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
