package live;

/*
 * To execute Java, please define "static void main" on a class
 * named ClosestXdestinations.
 *
 * If you need more classes, simply define them inline.
 */
public class LCA {

    private static class Node {

        char key;
        Node leftChild;
        Node rightChild;

        Node(char key) {
            this.key = key;
        }

        void print(Node root) {
            if(root == null) {
                return;
            }
            print(root.leftChild);
            System.out.print(root.key + " -> ");
            print(root.rightChild);
        }
    }


    /*
        Write a function that takes in a tree root, two nodes, and returns the
        lowest common ancestor (LCA) of the two nodes. The LCA is defined as the
        deepest node in the tree that contains both nodes in its subtree. For example,
        `findLCA(root=a, node1=d, node2=e)` should return `b` for the tree below. You
        can assume the tree is a binary tree.
                 a
             b       c
           d    e  f    g
     */

    /*
        Two base cases
        Post order traversal

        Process: If both nodes exist, LCA; Else, pass the only node upward.
     */
    public static Node findLCA(Node root, Node node1, Node node2) {

        if(node1 == null || node2 == null) {
            throw new IllegalArgumentException();
        }

        if(root == null) {
            return null;
        }

        if(root.key == node1.key || root.key == node2.key) {
            return root;
        }

        Node left = findLCA(root.leftChild, node1, node2);
        Node right = findLCA(root.rightChild, node1, node2);

        if(left != null && right != null) {
            // LCA found
            return root;
        }

        return (left != null) ? left : right;
    }

    public static void main(String[] args) {

        Node root = new Node('a');
        root.leftChild = new Node('b');
        root.rightChild = new Node('c');
        root.leftChild.leftChild = new Node('d');
        root.leftChild.rightChild = new Node('e');
        root.rightChild.leftChild = new Node('f');
        root.rightChild.rightChild = new Node('g');
        root.print(root);

        // Test 1
        Node result1 = findLCA(root,
                root.leftChild.leftChild,
                root.leftChild.rightChild);
        System.out.println("Expected: b, " + result1.key);
        System.out.println();
        // Test 2 - Null input
        Node result2 = findLCA(null,
                root.leftChild.leftChild,
                root.leftChild.rightChild);
        System.out.println("Expected: null, " + result2);
        // Test 3
        Node result3 = findLCA(root,
                root.leftChild.leftChild,
                root.rightChild.rightChild);
        System.out.println("Expected: a, " + result3.key);

        // Test 4
        Node result4 = findLCA(root,
                null,
                root.rightChild.rightChild);
        System.out.println("Expected: null, " + result4);

        // Test 5
        Node result5 = findLCA(root,
                root.rightChild.rightChild, null);
        System.out.println("Expected: null, " + result5);

        // Test 6
        Node result6 = findLCA(root,
                root.leftChild,
                root.leftChild.leftChild);
        System.out.println("Expected: b, " + result6.key);

        // Test 7
        Node result7 = findLCA(root,
                root.rightChild,
                root.leftChild.leftChild);
        System.out.println("Expected: a, " + result7.key);

        // Test 8
        Node result8 = findLCA(root,
                root,
                root.rightChild);
        System.out.println("Expected: a, " + result8.key);
    }

}
