package codecamp;

public class ConstructTree {

    private static class TreeNode {
        char val;
        TreeNode left;
        TreeNode right;

        TreeNode(char val) {
            this.val = val;
        }
    }

    static int preOrderIndex = 0;

    private static TreeNode constructTree(char[] inOrder, char[] preOrder) {
        return constructTree(inOrder, preOrder, 0, inOrder.length - 1);
    }

    private static TreeNode constructTree(char[] inOrder, char[] preOrder, int start, int end) {
        if(start > end) {
            return null;
        }

        TreeNode root = new TreeNode(preOrder[preOrderIndex++]);

        if(start == end) {
            return root;
        }

        int index = search(inOrder, start, end, root.val);
        root.left = constructTree(inOrder, preOrder, start, index - 1);
        root.right = constructTree(inOrder, preOrder, index + 1, end);
        return root;
    }

    private static int search(char[] inOrder, int start, int end, int val) {
        for(int i = start; i <= end; i++) {
            if(inOrder[i] == val) {
                return i;
            }
        }
        return -1;
    }

    private static void print(TreeNode root) {
        if(root == null) {
            return;
        }
        print(root.left);
        System.out.print(root.val + " ");
        print(root.right);
    }

    public static void main(String[] args) {
        char[] inOrder = {'D', 'B', 'E', 'A', 'F', 'C'};
        char[] preOrder = {'A', 'B', 'D', 'E', 'C', 'F'};
        TreeNode root = constructTree(inOrder, preOrder);
        print(root);
    }
}
