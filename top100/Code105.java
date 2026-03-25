package top100;

import java.util.Map;

/**
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历，
 * inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 *
 */
public class Code105 {
    /**
     *
     * 给定现需遍历 以及中序遍历   构造二叉树
     * <p>
     * 思路：
     * 先序遍历的先后顺序是 头左右  中序遍历的顺序是   左头右
     * 那么的意味着  在现需遍历的节点 范围是left  right  只要在中序遍历中找到这个节点的位置 就可以认为 这个节点是头结点
     * <p>
     * 就能快速求出范围
     * <p>
     * 注意  这个题目是不能出现值相等的节点的 否则就无法确定在中序遍历中找到的节点是哪个节点了
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 加速用
        Map<Integer, Integer> indexMap = new java.util.HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        return process(indexMap, preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    public TreeNode process(Map<Integer, Integer> indexMap, int[] preorder, int l1, int r1, int[] inorder, int l2, int r2) {
        if (l1 > r1 || l2 > r2) {
            return null;
        }
        if (l1 == r1) {
            return new TreeNode(preorder[l1]);
        }
        TreeNode root = new TreeNode(preorder[l1]);
        // 在中序遍历找到l1出现的位置
        int index = indexMap.get(preorder[l1]);
        // 说明从l2到index-1是左子树
        // 从index+1到r2是右子树
        // 转换先序遍历的下标
        // 总共有多少个数l2 到index-1  范围有 index-1-l2 个数
        // 那么转化成先序遍历的左子树的范围就是l1 + 1 到 l1 + (index - l2 )
        // 右子树 l1 + (index - l2 + 1) 到 r1
        root.left = process(indexMap, preorder, l1 + 1, l1 + (index - l2), inorder, l2, index - 1);
        root.right = process(indexMap, preorder, l1 + (index - l2 + 1), r1, inorder, index + 1, r2);
        return root;
    }
}
