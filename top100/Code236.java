package top100;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *
 */
public class Code236 {
    // 二叉树的最低公共祖先

    /**
     * 思路：使用二叉树的递归方式 找到两个节点的最近公共祖先
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        return process(root, p, q).ancestor;
    }

    public Info process(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return new Info(false, false, null);
        }
        Info leftInfo = process(root.left, p, q);
        Info rightInfo = process(root.right, p, q);
        boolean findP = leftInfo.findP || rightInfo.findP || root == p;
        boolean findQ = leftInfo.findQ || rightInfo.findQ || root == q;
        TreeNode ancestor = null;
        if (leftInfo.ancestor != null) {
            ancestor = leftInfo.ancestor;
        } else if (rightInfo.ancestor != null) {
            ancestor = rightInfo.ancestor;
        } else if (findP && findQ) {
            ancestor = root;
        }
        return new Info(findP, findQ, ancestor);
    }


    class Info {
        boolean findP;
        boolean findQ;
        TreeNode ancestor;

        public Info(boolean findP, boolean findQ, TreeNode ancestor) {
            this.findP = findP;
            this.findQ = findQ;
            this.ancestor = ancestor;
        }
    }

    /**
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        // 构建当前节点到父节点的映射的关系
        Map<TreeNode, TreeNode> fatherMap = new HashMap<>();
        process2(root, fatherMap);
        // 构建好父子的映射关系之后 沿着p或者q的某一个的路径向上查找
        // 假设使用p的位置向上查找
        TreeNode temp = p;
        Set<TreeNode> set = new HashSet<>();
        while (temp != null) {
            set.add(temp);
            temp = fatherMap.get(temp);
        }
        // 找到某一个方向的值之后沿着另外一个节点向上查找
        temp = q;
        while (temp != null && !set.contains(temp)) {
            temp = fatherMap.get(temp);
        }
        return set.contains(temp) ? temp : null;
    }

    public void process2(TreeNode root, Map<TreeNode, TreeNode> fatherMap) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            return;
        }
        if (root.left != null) {
            fatherMap.put(root.left, root);
            process2(root.left, fatherMap);
        }
        if (root.right != null) {
            fatherMap.put(root.right, root);
            process2(root.right, fatherMap);
        }
    }

}
