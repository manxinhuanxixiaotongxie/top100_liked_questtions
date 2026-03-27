package top100;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 小的元素（k 从 1 开始计数）。
 *
 */
public class Code230 {
    /**
     * 第一种思路：
     * 中序遍历
     *
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
        // 使用morris遍历进行改写
        TreeNode cur = root;
        while (cur != null) {
            // 中序遍历
            if (cur.left == null) {
                // 去右树
                if (--k == 0) {
                    return cur.val;
                }
                cur = cur.right;
            } else {
                TreeNode leftNode = cur.left;
                while (leftNode.right != null && leftNode.right != cur) {
                    leftNode = leftNode.right;
                }
                if (leftNode.right == null) {
                    leftNode.right = cur;
                    cur = cur.left;
                } else {
                    if (--k == 0) {
                        return cur.val;
                    }
                    leftNode.right = null;
                    cur = cur.right;
                }
            }
        }
        return -1; // 或者抛出异常
    }

    /**
     * morris遍历优雅的实现
     *
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest3(TreeNode root, int k) {
        // 使用morris遍历进行改写
        TreeNode cur = root;
        while (cur != null) {
            TreeNode left = cur.left;
            if (left != null) {
                // 有左树
                while (left.right != null && left.right != cur) {
                    left = left.right;
                }
                if (left.right == null) {
                    // 重构树结构
                    left.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    // 已经访问过了（第二次到达，中序访问点）
                    if (--k == 0) {
                        return cur.val;
                    }
                    // 恢复树结构，向右走
                    left.right = null;
                    cur = cur.right;
                    continue; // 跳过下面的--k，否则同一节点会被计数两次
                }
            }
            // 没有左树 直接访问右树（也是中序访问点）
            if (--k == 0) {
                return cur.val;
            }
            cur = cur.right;
        }
        return -1; // 或者抛出异常
    }
}
