package top100;

public class Code437 {

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
            ans += process(root.left, targetSum - root.val);
            ans += process(root.right, targetSum - root.val);
            return ans;
        }
    }
}
