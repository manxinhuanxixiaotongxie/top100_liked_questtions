package top100;

/**
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的
 * 路径 的数目。
 *
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 *
 */
public class Code437 {

    /**
     * 要拆分成两个子问题：
     *
     * 1.以当前节点为起点 找到路径和等于targetSum的路径数
     *
     * 2.枚举每个节点作为起点
     *
     * @param root
     * @param targetSum
     * @return
     */
    public int pathSum(TreeNode root, long targetSum) {
        if (root == null) {
            return 0;
        }
        int ans = 0;
        ans += process(root, targetSum);
        ans += pathSum(root.left, targetSum);
        ans += pathSum(root.right, targetSum);
        return ans;
    }

    public int process(TreeNode root, long targetSum) {
        if (root == null) {
            return 0;
        } else {
            int ans = 0;
            if (root.val == targetSum) {
                ans++;
            }
//            ans += process(root.left, targetSum);
//            ans += process(root.right, targetSum);
            ans += process(root.left, targetSum - root.val);
            ans += process(root.right, targetSum - root.val);
            return ans;
        }
    }
}
