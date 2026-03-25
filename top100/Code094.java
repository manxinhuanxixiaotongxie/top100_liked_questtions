package top100;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
 *
 */
public class Code094 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }
        process(root, res);
        return res;
    }

    /**
     * 采用递归的方式
     *
     * @param root
     * @param res
     */
    public void process(TreeNode root, List<Integer> res) {
        if (root == null) return;
        process(root.left, res);
        res.add(root.val);
        process(root.right, res);
    }

    /**
     * 使用Morris遍历改写
     * <p>
     * morris过程：
     * 有左树：
     * 1.找到左树的的最右侧节点 将该节点的右指针指向当前节点
     * 2.当前节点来到左树
     * 没有左树：
     * 当前节点来到右树
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }
        // 使用Morris遍历改写
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) {
                res.add(cur.val);
                cur = cur.right;
            } else {
                // 有左树
                TreeNode curLeftNode = cur.left;
                while (curLeftNode.right != null && curLeftNode.right != cur) {
                    curLeftNode = curLeftNode.right;
                }
                if (curLeftNode.right == null) {
                    curLeftNode.right = cur;
                    cur = cur.left;
                } else {
                    // 说明之前已经访问过了
                    res.add(cur.val);
                    curLeftNode.right = null;
                    cur = cur.right;
                }
            }
        }
        return res;
    }
}
