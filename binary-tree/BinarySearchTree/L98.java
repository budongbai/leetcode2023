package BinarySearchTree;


import budongbai.common.TreeNode;

/**
 * <a href="https://leetcode.cn/problems/validate-binary-search-tree/">98. 验证二叉搜索树</a>
 * <p>
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 有效 二叉搜索树定义如下：
 * <p>
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 */
public class L98 {
    public boolean isValidBST(TreeNode root) {

        return isValidBinarySearchTree(root, null, null);
    }

    private boolean isValidBinarySearchTree(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) {
            return true;
        }

        if (min != null && root.val <= min.val) {
            return false;
        }

        if (max != null && root.val >= max.val) {
            return false;
        }
        // 节点的左子树只包含 小于 当前节点的数。
        return isValidBinarySearchTree(root.left, min, root)
                // 节点的右子树只包含 大于 当前节点的数。
                && isValidBinarySearchTree(root.right, root, max);
    }

    public static void main(String[] args) {
        TreeNode n5 = new TreeNode(5);
        TreeNode n1 = new TreeNode(1);
        TreeNode n4 = new TreeNode(4);
        TreeNode n3 = new TreeNode(3);
        TreeNode n6 = new TreeNode(6);

        n5.left = n1;
        n5.right = n4;
        n4.left = n3;
        n4.right = n6;

        System.out.println(new L98().isValidBST(n5));
    }
}
