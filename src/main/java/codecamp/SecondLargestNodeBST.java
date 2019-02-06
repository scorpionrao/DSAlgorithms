package codecamp;

public class SecondLargestNodeBST {

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

    private static class Count {
        int count = 0;
    }

    private static TreeNode secondLargestNode(TreeNode root) {
        return secondLargestNode(root, new Count());
    }

    private static TreeNode secondLargestNode(TreeNode root, Count count) {
        if(root == null) {
            return null;
        }
        TreeNode left = secondLargestNode(root.right, count);
        if(left != null) {
            return left;
        }
        count.count++;
        if(count.count == 2) {
            return root;
        }
        return secondLargestNode(root.left, count);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        //root.right = new TreeNode(3);

        TreeNode secondLargestNode = secondLargestNode(root);
        System.out.println("Second Largest Node - " + secondLargestNode.key);
    }
}
