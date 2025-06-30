package newer20;

/**
 * 给你一个 二叉树 的根结点 root，该二叉树由恰好 3 个结点组成：根结点、左子结点和右子结点。
 */
public class Code2236 {
    public boolean checkTree(TreeNode root) {
        return root.val == (root.left.val + root.right.val);
    }

    /**
     * 扩展： 如果有N个节点
     *
     * @param root
     * @return
     */
    public boolean checkTree2(TreeNode root) {
        return root.val == (root.left.val + root.right.val);
    }

    private boolean checkTree3(TreeNode root) {
        if (root == null) {
            return true;
        }
        int leftValue = root.left != null ? root.left.val : 0;
        int rightValue = root.right != null ? root.right.val : 0;
        return root.val == (leftValue + rightValue) && checkTree3(root.left) && checkTree3(root.right);
    }
}
