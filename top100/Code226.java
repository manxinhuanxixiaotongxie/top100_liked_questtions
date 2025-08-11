package top100;

public class Code226 {
    public TreeNode invertTree(TreeNode root) {
        return process2(root);
    }

    /**
     * 这个递归为什么会有问题
     *
     * @param root
     * @return
     */
    public TreeNode process(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 运行到这一步root.left已经被修改
        root.left = invertTree(root.right);
        // 此时的root.left已经不是原来的root.left了
        root.right = invertTree(root.left);
        return root;
    }

    public TreeNode process2(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = invertTree(root.right);
        TreeNode right = invertTree(root.left);
        root.left = left;
        root.right = right;
        return root;
    }
}
