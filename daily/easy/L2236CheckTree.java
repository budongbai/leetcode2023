package easy;

import budongbai.common.TreeNode;

public class L2236CheckTree {
    public boolean checkTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        int sum = 0;
        if (root.left != null) {
            sum += root.left.val;
        }
        if (root.right != null) {
            sum += root.right.val;
        }
        if ((root.left != null || root.right != null) && sum != root.val) {
            return false;
        }
        return checkTree(root.left) && checkTree(root.right);
    }
}
