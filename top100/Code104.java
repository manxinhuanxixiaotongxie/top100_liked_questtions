package top100;

public class Code104 {
    public int maxDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return process(root);
    }

    public int process(TreeNode root) {
        if (root == null) return 0;
        int left = process(root.left);
        int right = process(root.right);
        return Math.max(left, right) + 1;
    }

    /**
     * 使用morris遍历改写
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int max = 0;
        TreeNode cur = root;
        int curHeight = 0;
        while (cur != null) {
            if (cur.left == null) {
                // 没有左树，当前节点深度+1，更新max，再向右走
                curHeight++;
                max = Math.max(max, curHeight);
                cur = cur.right;
            } else {
                // 有左树，找左子树最右节点
                TreeNode curLeftNode = cur.left;
                int leftHeight = 1;
                while (curLeftNode.right != null && curLeftNode.right != cur) {
                    leftHeight++;
                    curLeftNode = curLeftNode.right;
                }
                if (curLeftNode.right == null) {
                    // 第一次到达，建立Morris连接，向左走，深度+1
                    curLeftNode.right = cur;
                    cur = cur.left;
                    curHeight++;
                } else {
                    // 第二次到达，断开连接，用curHeight更新max，恢复深度后向右走
                    curLeftNode.right = null;
                    max = Math.max(max, curHeight);
                    curHeight -= leftHeight;
                    cur = cur.right;
                }
            }
        }

        return max;
    }
}
