package top100;

import java.util.ArrayList;
import java.util.List;

public class Code230 {
    /**
     * 第一种思路：
     * 中序遍历
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest1(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        process(root, list);
        if (k <= 0 || k > list.size()) {
            return -1; // 或者抛出异常
        }
        return list.get(k - 1);
    }

    public void process(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        process(root, list);
        list.add(root.val);
        process(root.right, list);
    }

    /**
     * 改morris遍历
     *
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest2(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        process(root, list);
        if (k <= 0 || k > list.size()) {
            return -1; // 或者抛出异常
        }
        return list.get(k - 1);
    }
}
