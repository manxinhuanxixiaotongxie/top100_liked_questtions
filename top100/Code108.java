package top100;

public class Code108 {
    public TreeNode sortedArrayToBST(int[] nums) {
        return process(nums, 0, nums.length - 1);
    }

    public TreeNode process(int[] nums, int l, int r) {
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
