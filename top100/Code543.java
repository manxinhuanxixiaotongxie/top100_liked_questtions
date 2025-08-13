package top100;

public class Code543 {

    public int diameterOfBinaryTree(TreeNode root) {
        return process(root).maxSide - 1;
    }

    public Info process(TreeNode root) {
        if (root == null) {
            return new Info(0, 0);
        }
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);
        int fromRoot = Math.max(leftInfo.fromRoot, rightInfo.fromRoot) + 1;
        int maxSide = Math.max(leftInfo.maxSide, rightInfo.maxSide);
        maxSide = Math.max(maxSide, leftInfo.fromRoot + rightInfo.fromRoot + 1);
        return new Info(fromRoot, maxSide);
    }


    class Info {
        int fromRoot; // 从根节点到叶子节点的最大深度
        // 最大节点数
        int maxSide;

        Info(int fromRoot, int maxSide) {
            this.fromRoot = fromRoot;
            this.maxSide = maxSide;
        }
    }
}
