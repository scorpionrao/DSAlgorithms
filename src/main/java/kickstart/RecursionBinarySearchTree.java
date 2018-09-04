package kickstart;

import java.util.LinkedList;
import java.util.Queue;

public class RecursionBinarySearchTree {

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
            // always this is where new node gets created
            root = new Node(data);
        } else if(root.data > data) {
            // the very same root is sent and received
            root.left = insert(root.left, data);
        } else {
            // the very same root is sent and received
            root.right = insert(root.right, data);
        }
        // the very same root is passed and returned
        return root;
    }

    public Node delete(Node root, int data) {
        System.out.println(String.format("Root: %d, Data: %d", root.data, data));
        if(root == null) {
            // data not found
            return root;
        } else if (data < root.data) {
            root.left = delete(root.left, data);
        } else if (data > root.data) {
            root.right = delete(root.right, data);
        } else {
            // data is found
            if(root.left == null && root.right == null) {
                // case 1 - node is leaf
                root = null;
            } else if(root.left == null) {
                // case 2 - one child - only right child
                root = root.right;
            } else if(root.right == null) {
                // case 3 - one child - only left child
                root = root.left;
            } else {
                // case 4 - two child exist - min of right child OR max of left child
                Node node = findMin(root.right);
                root.data = node.data;
                root.right = delete(root.right, node.data);
            }
        }
        return root;
    }

    public Node search(Node root, int data) {
        if(root == null) {
            return root;
        } else if(root.data == data) {
            return root;
        } else if(root.data > data) {
            return search(root.left, data);
        } else {
            return search(root.right, data);
        }
    }

    public Node findMin(Node root) {
        if(root == null) {
            return root;
        } else if(root.left == null) {
            return root;
        } else {
            return findMin(root.left);
        }
    }

    public Node findMax(Node root) {
        if(root == null) {
            return root;
        } else if(root.right == null) {
            return root;
        } else {
            return findMax(root.right);
        }
    }

    public int findHeight(Node root) {
        if(root == null) {
            return -1;
        }
        return 1 + Math.max(findHeight(root.left), findHeight(root.right));
    }

    public void print(int indent, Node root, char ch) {
        if(root == null) {
            return;
        }
        for(int i = 0; i < indent; i++) {
            System.out.print("\t");
        }
        System.out.println(ch + ":" + root.data);
        print(indent+1, root.left, 'L');
        print(indent+1, root.right, 'R');
    }

    public void treeOrderTraversal(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if(node != null) {
                System.out.println(node.data);
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
    }

    public boolean isBST(Node root) {
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public boolean isBST(Node root, int min, int max) {
        /* End of branch */
        if(root == null) {
            return true;
        }
        return root.data > min
            && root.data < max
            && isBST(root.left, min, root.data-1)
            && isBST(root.right, root.data+1, max);
    }

    public Node getSuccessor(Node root, int data) {
        if(root == null) {
            return root;
        }
        Node current = search(root, data);
        if(current.right != null) {
            return findMin(current.right);
        } else {
            Node ancestor = root;
            Node successor = null;
            while(current.data != ancestor.data) {
                if (current.data < ancestor.data) {
                    successor = ancestor;   // deepest node for which current node is in left
                    ancestor = ancestor.left;
                } else {
                    ancestor = ancestor.right;
                }
            }
            return successor;
        }
    }

    public static void main(String[] args) {
        RecursionBinarySearchTree bst = new RecursionBinarySearchTree();
        bst.root = bst.insert(bst.root, 15);
        bst.root = bst.insert(bst.root, 10);
        bst.root = bst.insert(bst.root, 20);
        bst.root = bst.insert(bst.root, 25);
        bst.root = bst.insert(bst.root, 23);
        bst.print(0, bst.root, 'L');
        //bst.treeOrderTraversal(bst.root);
        /*
        System.out.println(bst.findMin(bst.root));
        System.out.println(bst.findMax(bst.root));
        System.out.println(bst.findHeight(bst.root));
        System.out.println(bst.search(bst.root, 15));
        */

        //System.out.println(bst.isBST(bst.root));

        //System.out.println(bst.search(bst.root, 12));
        //bst.root = bst.delete(bst.root, 15);
        //bst.print(0, bst.root, 'L');
        System.out.println(bst.getSuccessor(bst.root, 15).data);
    }
}

