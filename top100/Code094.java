package top100;

import java.util.ArrayList;
import java.util.List;

public class Code094 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }
        process(root, res);
        return res;
    }

    public void process(TreeNode root, List<Integer> res) {
        if (root == null) return;
        process(root.left, res);
        res.add(root.val);
        process(root.right, res);
    }

    /**
     * 使用moriis遍历改写
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }
        process(root, res);
        return res;
    }
}
