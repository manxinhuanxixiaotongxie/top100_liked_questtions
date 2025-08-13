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

    }
}
