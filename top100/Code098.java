package top100;

public class Code098 {
    public boolean isValidBST(TreeNode root) {
        return process(root).isBST;
    }

    public Info process(TreeNode root) {
        if (root == null) {
            return null;
        }
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);

        int min;
        int max;
        boolean isBST = true;
        if (leftInfo == null && rightInfo == null) {
            min = root.val;
            max = root.val;
        }else if (leftInfo ==null) {
            // 左树空 右树不为空
            if (!rightInfo.isBST || rightInfo.min <= root.val) {
                isBST = false;
            }
            min = Math.min(rightInfo.min, root.val);
            max = Math.max(rightInfo.max, root.val);
        }else if (rightInfo == null) {
            // 左树不为空 右树为空
            if(!leftInfo.isBST || leftInfo.max >= root.val) {
                isBST = false;
            }
            min = Math.min(leftInfo.min, root.val);
            max = Math.max(leftInfo.max, root.val);
        }else {
            // 都不为空
            if (!leftInfo.isBST || !rightInfo.isBST || leftInfo.max >= root.val || rightInfo.min <= root.val) {
                isBST = false;
            }
            min = Math.min(leftInfo.min, Math.min(rightInfo.min, root.val));
            max = Math.max(leftInfo.max, Math.max(rightInfo.max, root.val));
        }
        return new Info(isBST, min, max);
    }

    /**
     * 二叉搜索树：
     * 左树的最大值小于当前节点
     * 右树的最小值大于当前节点
     *
     * 严格大 严格小
     * 左树是二叉搜索树
     * 右树是二叉搜索树
     */
    class Info{
        boolean isBST;
        int min;
        int max;

        Info(boolean isBST, int min, int max) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
        }
    }
}
