package mit;

public class DistanceBetweenNodes {
	
	public static class Node {
		int key;
		Node left;
		Node right;
		Node(int key) {
			this.key = key;
		}
	}
	
	static int level1;
	static int level2;
	static int dist = 0;
	
	static int findLevel(Node root, int key, int currentLevel) {
        if (root == null)
            return -1;
        if (root.key == key)
            return currentLevel;  
        int leftLevel = findLevel(root.left, key, currentLevel + 1);
        return (leftLevel != -1) ? leftLevel : findLevel(root.right, key, currentLevel + 1);
    }
	
	public static Node findLCA(Node root, int key1, int key2, int level) {
		if(root == null) {
			return null;
		}
		if(root.key == key1) {
			level1 = level;
			return root;
		}
		if(root.key == key2) {
			level2 = level;
			return root;
		}
		
		Node leftLCA = findLCA(root.left, key1, key2, level + 1);
		Node rightLCA = findLCA(root.right, key1, key2, level + 1);
		
		if(leftLCA != null && rightLCA != null) {
			dist = (level1 + level2) - 2 * level;
			return root;
		}
		
		return leftLCA != null ? leftLCA : rightLCA;
	}
	
	public static int findDistance(Node root, int key1, int key2) {
		
		// reset
		level1 = -1;
		level2 = -1;
		dist = 0;
		
		Node LCA = findLCA(root, key1, key2, 1);
		
		if(level1 != -1) {
			if(level2 != -1) {
				return dist; // ROOT = ancestor
			} else {
				return findLevel(LCA, key2, 0); // LCA (key1) is ROOT and ancestor of key2
			}
		} else {
			if(level2 != -1) {
				return findLevel(LCA, key1, 0); // LCA (key2) is ROOT and ancestor of key1
			} else {
				return -1; // both key1 and key2 do NOT exist
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
        
        System.out.println("Dist(4, 5): expected=2, actual=" + findDistance(root, 4, 5));
        System.out.println("Dist(4, 6): expected=4, actual=" + findDistance(root, 4, 6));
        System.out.println("Dist(3, 4): expected=3, actual=" + findDistance(root, 3, 4));
        System.out.println("Dist(2, 4): expected=1, actual=" + findDistance(root, 2, 4));
        System.out.println("Dist(8, 5): expected=5, actual=" + findDistance(root, 8, 5));
	}

}
