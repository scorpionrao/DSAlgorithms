package datastructures.binarysearchtree;

import java.util.Arrays;

public class SortedArrayToBst {

    public static class Node {
        int key;
        Node left;
        Node right;
        public Node(int key) {
            this.key = key;
            this.left = null;
            this.right = null;
        }
    }

    private static Node convertToBst(int[] sortedArray) {
        if(sortedArray == null || sortedArray.length == 0) {
            return null;
        }
        return convertToBst(sortedArray, 0, sortedArray.length - 1);
    }

    /*
        Time complexity - O(N log N)
        Space complexity - O(N)
     */
    private static Node convertToBst(int[] sortedArray, int low, int high) {
        System.out.println(low + " - " + high);
        if(low > high) {
            return null;
        }
        int mid = (high + low) / 2;
        Node node = new Node(sortedArray[mid]);
        node.left = convertToBst(sortedArray, low, mid - 1);
        node.right = convertToBst(sortedArray, mid + 1, high);
        return node;
    }

    private static void inOrderTraversal(Node root) {
        if(root == null) {
            return;
        }
        inOrderTraversal(root.left);
        System.out.print(root.key + ", ");
        inOrderTraversal(root.right);
    }

    public static void main(String[] args) {
        int[] sortedArray = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(sortedArray));
        Node node = convertToBst(sortedArray);
        inOrderTraversal(node);
    }
}
