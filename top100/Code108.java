package top100;

/**
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 平衡 二叉搜索树。
 */
public class Code108 {

    /**
     * 数组有序
     * <p>
     * 平衡二叉搜索树的特性 高度差不超过1  并且 节点左侧的最大值 必须小于当前节点
     * 节点右侧的最小值 必须大于当前节点
     * <p>
     * 在经典搜索二叉树中 是不能存在key相同的节点的
     * <p>
     * 那么这道题就可以利用这个特性 找到数组的中点 作为根节点
     *
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return process(nums, 0, nums.length - 1);
    }

    public TreeNode process(int[] nums, int l, int r) {

        if (l > r) return null;

        if (l == r) {
            return new TreeNode(nums[l]);
        }
        // 找到中点
        int mid = l + (r - l) / 2;
        TreeNode head = new TreeNode(nums[mid]);
        head.left = process(nums, l, mid - 1);
        head.right = process(nums, mid + 1, r);
        return head;
    }
}
