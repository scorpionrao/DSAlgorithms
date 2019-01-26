package live;

import java.util.*;

public class LevelSum {

    /*

        Find the sum of each level of the following tree.

              4
            / | \
            1  5  10
            /  / \  \
            6  1   4  6


        Integers, tree is provided in-memory
        Input: N-array tree
        Output: List<Integer>

        Level Order traversal - BFS

        4(1)
        1(2), 5(2), 10(2)

        Time complexity: O(N), N=number of nodes
        Space complexity: O(W), W = max width at a particular level
    */


    public static class Node {
        int key;
        List<Node> children;
        Node(int key) {
            this.key = key;
            this.children = new ArrayList<>();
        }
    }

    public static class TreeNode {
        int key;
        TreeNode left;
        TreeNode right;
        TreeNode(int key) {
            this.key = key;
            this.left = null;
            this.right = null;
        }
    }


    private static List<Integer> getLevelSumIterative(Node root) {
        if(root == null) {
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        // Time Complexity - O(N) → Each node is ADDED O(1) and PROCESSED O(1) by queue ONCE
        Queue<Node> currentLevelQueue = new LinkedList<>();
        Queue<Node> nextLevelQueue = new LinkedList<>();
        currentLevelQueue.offer(root);
        int sum = 0;

        while(!currentLevelQueue.isEmpty()) {
            Node node = currentLevelQueue.poll();
            if(node != null) {
                sum = sum + node.key;
                nextLevelQueue.addAll(node.children);
            }
            if(currentLevelQueue.isEmpty()) {
                currentLevelQueue = nextLevelQueue;
                nextLevelQueue = new LinkedList<>();
                result.add(sum);
                sum = 0;
            }
        }
        return result;
    }

    private static List<Integer> getLevelSumIterativeOptimized(Node root) {
        if(root == null) {
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        int numOfCurrentLevelNodes = 1;
        int numOfNextLevelNodes = 0;
        int sum = 0;

        while(!queue.isEmpty()) {
            Node node = queue.poll();
            if(node != null) {
                sum = sum + node.key;
                queue.addAll(node.children);
                numOfNextLevelNodes = numOfNextLevelNodes + node.children.size();
            }
            numOfCurrentLevelNodes--;

            if(numOfCurrentLevelNodes == 0) {
                result.add(sum);
                sum = 0;

                numOfCurrentLevelNodes = numOfNextLevelNodes;
                numOfNextLevelNodes = 0;
            }
        }
        return result;
    }

    private static void getLevelFlipterativeOptimized(TreeNode root, int k) {
        if(root == null) {
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int countCurrentLevel = 1;
        int countNextLevel = 0;
        int level = 1;

        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            countCurrentLevel--;
            if(level % k == 0) {
                TreeNode temp = node.left;
                node.left = node.right;
                node.right = temp;
            }
            if(node.left != null) {
                queue.add(node.left);
                countNextLevel++;
            }
            if(node.right != null) {
                queue.add(node.right);
                countNextLevel++;
            }

            if(countCurrentLevel == 0) {
                level++;
                countCurrentLevel = countNextLevel;
                countNextLevel = 0;
            }
        }
    }


    private static List<Integer> getLevelSumRecursive(Node root) {
        if(root == null) {
            return new ArrayList<>();
        }
        Map<Integer, Integer> levelSumMap = new LinkedHashMap<>();
        getLevelSumRecursive(root, levelSumMap, 0);
        return new ArrayList<>(levelSumMap.values());
    }

    private static void getLevelSumRecursive(Node root, Map<Integer, Integer> levelSumMap, int currentLevel) {

        if(root == null) {
            return;
        }
        // PRE-PROCESS - BFS - Root sum to Leaf sum
        // POST-PROCESS - DFS - Leaf sum to Root sum
        if(levelSumMap.containsKey(currentLevel)) {
            levelSumMap.put(currentLevel, levelSumMap.get(currentLevel) + root.key);
        } else {
            levelSumMap.put(currentLevel, root.key);
        }
        for(Node child : root.children) {
            getLevelSumRecursive(child, levelSumMap, currentLevel+1);
        }
    }

    public static void main(String[] args) {
        /*
        Node root = new Node(4);
        Node level1Node1 = new Node(1);
        Node level1Node2 = new Node(5);
        Node level1Node3 = new Node(10);
        Node level2Node1 = new Node(6);
        Node level2Node2 = new Node(1);
        Node level2Node3 = new Node(4);
        Node level2Node4 = new Node(4);

        root.children.add(level1Node1);
        root.children.add(level1Node2);
        root.children.add(level1Node3);

        root.children.get(0).children.add(level2Node1);
        root.children.get(1).children.add(level2Node2);
        root.children.get(1).children.add(level2Node3);
        root.children.get(2).children.add(level2Node4);

        System.out.println(getLevelSumIterative(root).toString());
        System.out.println(getLevelSumIterativeOptimized(root).toString());
        System.out.println(getLevelSumRecursive(root).toString());
        */

        System.out.println(0^0);
        System.out.println(1^1);

        System.out.println(0^1);
        System.out.println(1^0);
    }
} // end of class
