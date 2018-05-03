package kickstart;

public class RecursiveBinarySearchTree {

    public static class Node {
        int data;
        Node left = null;
        Node right = null;

        public Node(int data) {
            this.data = data;
        }
    }

    public Node root;

    public Node insert(Node root, int data) {
        if(root == null) {
            root = new Node(data);
        } else if(root.data > data) {
            root.left = insert(root.left, data);
        } else {
            root.right = insert(root.right, data);
        }
        return root;
    }

    public boolean search(Node root, int data) {
        if(root == null) {
            return false;
        } else if(root.data == data) {
            return true;
        } else if(root.data > data) {
            return search(root.left, data);
        } else {
            return search(root.right, data);
        }
    }

    public void print(int indent, Node root) {
        if(root == null) {
            return;
        }
        for(int i = 0; i < indent; i++) {
            System.out.print("\t");
        }
        System.out.println(root.data);
        print(indent+1, root.left);
        print(indent+1, root.right);
    }

    public static void main(String[] args) {
        RecursiveBinarySearchTree bst = new RecursiveBinarySearchTree();
        bst.root = bst.insert(bst.root, 15);
        bst.root = bst.insert(bst.root, 10);
        bst.root = bst.insert(bst.root, 20);
        bst.root = bst.insert(bst.root, 25);

        System.out.println(bst.search(bst.root, 12));
        System.out.println(bst.search(bst.root, 15));
    }
}

