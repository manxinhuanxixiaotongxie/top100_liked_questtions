package top100;

/**
 * 二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列
 * 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 * <p>
 * 路径和 是路径中各节点值的总和。
 * <p>
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 *
 * 提示：
 *
 * 树中节点数目范围是 [1, 3 * 10^4]
 * -1000 <= Node.val <= 1000
 *
 */
public class Code124 {
    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        return process(root).maxValue;
    }


    public Info process(TreeNode root) {
        // 空树不能随意指定最大值，因为存在负数情况 可能会影响计算
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            return new Info(root.val, root.val);
        }
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);
        int fromNodeMaxValue = root.val;
        int maxValue = root.val;
        if (leftInfo != null && rightInfo != null) {
            // 从当前节点出发只有一种可能性，当前节点的值加上左树与右树的单边最大值
            fromNodeMaxValue = Math.max(fromNodeMaxValue,Math.max(leftInfo.fromNodeMaxValue,
                    rightInfo.fromNodeMaxValue) + root.val);
            // 整棵树最大值 存在几种可能性 （1）在左树 （2）在右树 （3）当前节点+左树单边最大值 （4）当前节点+右树单边最大值 (5)左数单边最大值 + 当前节点值 + 右树单边最大值
            // 情况（1）（2）
            maxValue = Math.max(fromNodeMaxValue,Math.max(leftInfo.maxValue, rightInfo.maxValue));
            // 情况（3）（4）
            maxValue = Math.max(maxValue, Math.max(leftInfo.fromNodeMaxValue, rightInfo.fromNodeMaxValue)
                    + root.val);
            // 情况（5）
            maxValue = Math.max(maxValue, leftInfo.fromNodeMaxValue + rightInfo.fromNodeMaxValue + root.val);
        } else if (leftInfo != null) {
            // 左树不为空 右树为空
            // 单边节点值只有一种可能 当前节点值+左树单边最大值
            fromNodeMaxValue = Math.max(fromNodeMaxValue,leftInfo.fromNodeMaxValue + root.val);
            // 最大值 （1）左树最大值 （2）左树单边最大值 + 当前节点值
            // 情况（1）
            maxValue = Math.max(maxValue,Math.max(fromNodeMaxValue,leftInfo.maxValue));
            // 情况（2）
            maxValue = Math.max(maxValue, leftInfo.fromNodeMaxValue + root.val);
        } else if (rightInfo != null) {
            // 右树不为空 单边最大值 只有一种可能 当前节点值+右树单边最大值
            fromNodeMaxValue = Math.max(fromNodeMaxValue,rightInfo.fromNodeMaxValue + root.val);
            // 整棵树最大值 （1）右树的最大值  （2）当前节点 + 右树单边最大值
            maxValue = Math.max(maxValue,rightInfo.maxValue);
            maxValue = Math.max(maxValue, rightInfo.fromNodeMaxValue + root.val);
        }
        return new Info(fromNodeMaxValue, maxValue);
    }

    class Info {
        // 从当前节点出发能够获取的最大值
        int fromNodeMaxValue;
        // 整棵树的最大值
        int maxValue;

        Info(int maxSide, int maxValue) {
            this.fromNodeMaxValue = maxSide;
            this.maxValue = maxValue;
        }
    }
}
