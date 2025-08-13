package top100;

public class Code101 {
    public boolean isSymmetric(TreeNode root) {
        return process(root,root);
    }

    public boolean process(TreeNode left,TreeNode right) {
        if(left == null && right == null) {
            return true;
        }
        if(left != null && right == null) {
            return false;
        }
        if(left == null) {
            return false;
        }
        if(left.val != right.val) {
            return false;
        }

        return process(left.left,right.right) && process(left.right,right.left);
    }
}
