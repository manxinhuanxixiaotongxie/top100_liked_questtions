package top100;

import java.util.Map;

public class Code105 {
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
