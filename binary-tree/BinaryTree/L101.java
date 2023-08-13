package BinaryTree;

import budongbai.common.TreeNode;

/**
 * <a href="https://leetcode.cn/problems/symmetric-tree/description/">101. 对称二叉树</a>
 *
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 */
public class L101 {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetric(root.left, root.right);
    }

    public boolean isSymmetric(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p != null && q != null) {
            return p.val == q.val && isSymmetric(p.left, q.right) && isSymmetric(p.right, q.left);
        } else {
            return false;
        }
    }
}
