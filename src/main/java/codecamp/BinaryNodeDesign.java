package codecamp;

public class BinaryNodeDesign {

    private static class BinaryTreeNode {

        BinaryTreeNode leftChild;
        BinaryTreeNode rightChild;
        BinaryTreeNode parent;
        boolean isLocked;

        public boolean isLocked() {
            return isLocked;
        }

        public boolean lock() {
            isLocked = isSubtreeUnlocked(this) && isParentTreeUnlocked(parent);
            return isLocked();
        }

        public boolean unlock() {
            isLocked = !(isSubtreeUnlocked(this) && isParentTreeUnlocked(parent));
            return isLocked();
        }

        private boolean isSubtreeUnlocked(BinaryTreeNode binaryTreeNode) {
            if(binaryTreeNode == null) {
                return true;
            }
            return !binaryTreeNode.isLocked()
                    && isSubtreeUnlocked(binaryTreeNode.leftChild)
                    && isSubtreeUnlocked(binaryTreeNode.rightChild);
        }

        private boolean isParentTreeUnlocked(BinaryTreeNode binaryTreeNode) {
            if(binaryTreeNode == null) {
                return true;
            }
            return !binaryTreeNode.isLocked() && isParentTreeUnlocked(binaryTreeNode);
        }
    }
}
