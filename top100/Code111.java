package top100;

/**
 * 二叉树的最小深度
 * 给定一个二叉树，找出其最小深度。
 * <p>
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * <p>
 * 说明：叶子节点是指没有子节点的节点。
 */
public class Code111 {
    public int minDepth(TreeNode root) {
        return process(root);
    }

    /**
     * 递归方式
     *
     * @return
     */
    public int process(TreeNode root) {
        if (root == null) return 0;
        // 这行代码可以不要 这已经是叶子节点了 要也没关系
//        if (root.left == null && root.right == null) return 1;
        // 这两个判断条件是不能去掉的 比如一个链表形的二叉树 是只能计算单边的
        // 这个题目有情况要讨论
        // 1.无左树 有右树 2.有左树 无右树 3.无左树 无右树 4.有左树 有右树
        if (root.left == null) return process(root.right) + 1;
        if (root.right == null) return process(root.left) + 1;
        int left = process(root.left);
        int right = process(root.right);
        return Math.min(left, right) + 1;
    }

    /**
     * 使用morris遍历的进行改写
     *
     * @param root
     * @return
     */
    public int minDepth2(TreeNode root) {
        if (root == null) return 0;
        TreeNode cur = root;
        int min = Integer.MAX_VALUE;
        int curHeight = 0;
        while (cur != null) {
            if (cur.left == null) {
                curHeight++;
                // 只有叶子节点才更新
                if (cur.right == null) min = Math.min(min, curHeight);
                cur = cur.right;
            } else {
                // 有左树
                TreeNode curLeftNode = cur.left;
                int leftHeight = 1;
                while (curLeftNode.right != null && curLeftNode.right != cur) {
                    leftHeight++;
                    curLeftNode = curLeftNode.right;
                }
                if (curLeftNode.right == null) {
                    // 重构节点
                    curLeftNode.right = cur;
                    cur = cur.left;
                    curHeight++;
                } else {
                    // 不为空 说明已经访问过了
                    // 第二次到达这个节点 说明左树已经访问完了
                    if (curLeftNode.left == null) {
                        min = Math.min(min, curHeight);
                    }
                    // 恢复树结构
                    curLeftNode.right = null;
                    cur = cur.right;
                    curHeight -= leftHeight;
                }
            }
        }
        return min;
    }

    /**
     * 加强对方法1的理解
     * 二叉树的递归套路
     *
     * @param root
     * @return
     */
    public int minDepth3(TreeNode root) {
        if (root == null) return 0;
        return process2(root);
    }

    public Integer process2(TreeNode root) {
        if (root == null) {
            return null;
        }
        Integer left = process2(root.left);
        Integer right = process2(root.right);
        if (left == null && right == null) {
            return 1;
        } else if (left == null) {
            // 右树不为空
            return right + 1;
        } else if (right == null) {
            return left + 1;
        } else {
            return Math.min(left, right) + 1;
        }
    }


}
