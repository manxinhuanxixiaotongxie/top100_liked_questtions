package top100;


import java.util.List;

/**
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * <p>
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 *
 */
public class Code114 {
    // 常规解法 求先序遍历
    public void flatten1(TreeNode root) {
        if (root == null) return;
        List<TreeNode> list = new java.util.ArrayList<>();
        // 先序遍历
        process(root, list);
        // 将list转化为链表
        for (int i = 0; i < list.size() - 1; i++) {
            TreeNode current = list.get(i);
            current.left = null; // 左子树置空
            current.right = list.get(i + 1); // 右子树指向下一个节点
        }
        // 最后一个节点的右子树也要置空
        list.get(list.size() - 1).left = null;
        list.get(list.size() - 1).right = null;
    }

    public void process(TreeNode root, List<TreeNode> list) {
        if (root == null) return;
        // 先序遍历
        list.add(root);
        process(root.left, list);
        process(root.right, list);
    }

    /**
     * morris遍历
     *
     * @param root
     */
    public void flatten2(TreeNode root) {
        // 使用morris遍历改写
        TreeNode cur = root;
        TreeNode pre = null;
        while (cur != null) {
            if (cur.left == null) {
                if (pre != null) pre.left = cur;
                pre = cur;
                cur = cur.right;
            } else {
                TreeNode cueLeftNode = cur.left;
                while (cueLeftNode.right != null && cueLeftNode.right != cur) {
                    cueLeftNode = cueLeftNode.right;
                }
                if (cueLeftNode.right == null) {
                    // 当前节点是第一次的来到
                    // 右侧为空
                    cueLeftNode.right = cur;
                    if (pre != null) {
                        pre.left = cur;
                    }
                    pre = cur;
                    cur = cur.left;
                } else {
                    // 不为空
                    cueLeftNode.right = null;
                    cur = cur.right;
                }
            }
        }
        cur = root;
        TreeNode next;
        while (cur != null) {
            next = cur.left;
            cur.left = null;
            cur.right = next;
            cur = next;
        }
    }

    /**
     * morris遍历 直接用right指针一步到位
     *
     * @param root
     */
    /**
     * morris遍历 说明：无法直接用right指针一步到位
     * 原因：串联链表时 pre.right=cur 会覆盖某个祖先节点的原始right
     * 而Morris遍历第二次回到该祖先时需要用 cur.right 找原始右子树，导致冲突
     * 因此只能用left指针先串联，再第二步转成right（同flatten2）
     */
    public void flatten3(TreeNode root) {
        // 同flatten2，两步走是Morris做法的唯一正确方式
        flatten2(root);
    }
}
