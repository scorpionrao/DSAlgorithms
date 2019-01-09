package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SerializeBinaryTree {

    private static class Node {

        int key;
        Node left;
        Node right;
        public Node(int key) {
            this.key = key;
            this.left = null;
            this.right = null;
        }
    }

    private static String serialize(Node root) {
        return serialize(root, "");
    }

    private static String serialize(Node root, String str) {
        if(root == null) {
            return str + "null,";
        }
        str = str + root.key + ",";
        str = serialize(root.left, str);
        str = serialize(root.right, str);
        return str;
    }


    private static Node deserialize(String str) {
        String[] array = str.split(",");
        List<String> list = new LinkedList<>(Arrays.asList(array));
        return deserialize(list);
    }

    private static Node deserialize(List<String> list) {
        if(list.get(0).equals("null")) {
            list.remove(0);
            return null;
        }
        Node node = new Node(Integer.valueOf(list.get(0)));
        list.remove(0);
        node.left = deserialize(list);
        node.right = deserialize(list);
        return node;
    }

    private static void evaluate(Node root, String expected) {
        System.out.println("NEW PROBLEM");
        print(root);
        System.out.println();
        String serialized = serialize(root);
        System.out.println("Expected : " + expected);
        System.out.println("Actual   : " + serialized);
        Node newRoot = deserialize(serialized);
        print(newRoot);
        System.out.println();
    }

    private static void print(Node root) {
        if(root == null) {
            return;
        }
        print(root.left);
        System.out.print(root.key + " ");
        print(root.right);
    }

    public static void main(String[] args) {

        Node root1 = new Node(1);
        root1.left = new Node(2);
        root1.right = new Node(3);
        root1.right.left = new Node(4);
        root1.right.right = new Node(5);
        evaluate(root1, "1,2,null,null,3,4,null,null,5,null,null,");

        Node root2 = new Node(1);
        root2.left = new Node(2);
        root2.right = new Node(5);
        root2.left.left = new Node(3);
        root2.left.right = new Node(4);
        evaluate(root2, "1,2,3,null,null,4,null,null,5,null,null,");
    }
}
