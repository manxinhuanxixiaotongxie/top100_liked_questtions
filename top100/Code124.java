package top100;

/**
 * 二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 * <p>
 * 路径和 是路径中各节点值的总和。
 * <p>
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 *
 */
public class Code124 {
    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        return process(root).maxValue;
    }


    public Info process(TreeNode root) {
        // 有负数
        // 如果是空树的话
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            return new Info(root.val, root.val);
        }
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);
        int maxSide = root.val;
        int maxValue = root.val;
        if (leftInfo != null && rightInfo != null) {
            maxSide = Math.max(maxSide, Math.max(leftInfo.maxSide, rightInfo.maxSide) + root.val);
            maxValue = Math.max(maxValue, Math.max(leftInfo.maxValue, rightInfo.maxValue));
            maxValue = Math.max(maxValue, Math.max(leftInfo.maxSide, rightInfo.maxSide) + root.val);
            maxValue = Math.max(maxValue, leftInfo.maxSide + rightInfo.maxSide + root.val);
        } else if (leftInfo != null) {
            maxSide = Math.max(maxSide, leftInfo.maxSide + root.val);
            maxValue = Math.max(maxValue, leftInfo.maxValue);
            maxValue = Math.max(maxValue, leftInfo.maxSide + root.val);
        } else if (rightInfo != null) {
            maxSide = Math.max(maxSide, rightInfo.maxSide + root.val);
            maxValue = Math.max(maxValue, rightInfo.maxValue);
            maxValue = Math.max(maxValue, rightInfo.maxSide + root.val);
        }
        return new Info(maxSide, maxValue);
    }

    class Info {
        // 单边最大值
        int maxSide;
        // 最大值
        int maxValue;

        Info(int maxSide, int maxValue) {
            this.maxSide = maxSide;
            this.maxValue = maxValue;
        }
    }
}
