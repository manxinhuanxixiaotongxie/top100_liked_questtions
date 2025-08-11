package top100;

public class Code104 {
    public int maxDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return process(root);
    }

    public int process(TreeNode root) {
        if (root == null) return 0;
        int left = process(root.left);
        int right = process(root.right);
        return Math.max(left, right) + 1;
    }

    /**
     * 使用morris遍历改写
     *
     * @param root
     * @return
     */
    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return process(root);
    }
}
