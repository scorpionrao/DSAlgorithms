package codecamp;

public class ArithmeticExpAsBinaryTree {

    /*

            *
           / \
          +    +
         / \  / \
        3  2  4  5

        '+', '−', '∗', or '/'
     */

    private static class TreeNode {
        char ch;
        TreeNode left;
        TreeNode right;
        TreeNode(char ch) {
            this.ch = ch;
        }
    }

    public static int resolve(TreeNode root) {

        if(root.left == null && root.right == null) {
            return root.ch - '0';
        }

        int left = resolve(root.left);
        int right = resolve(root.right);
        switch(root.ch) {
            case '+':
                return left + right;
            case '-':
                return left - right;
            case '*':
                return left * right;
            case '/':
                return left / right;
        }
        throw new IllegalArgumentException("Illegal node");
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode('*');
        root.left = new TreeNode('+');
        root.left.left = new TreeNode('3');
        root.left.right = new TreeNode('2');
        root.right = new TreeNode('+');
        root.right.left = new TreeNode('4');
        root.right.right = new TreeNode('5');
        System.out.println(resolve(root));
    }
}
