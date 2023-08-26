package BinaryTree;

import budongbai.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 113. 路径总和 II
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 * <p>
 * 叶子节点 是指没有子节点的节点。
 */
public class L113 {
    List<List<Integer>> res;
    List<Integer> path;

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        res = new ArrayList<>();
        path = new ArrayList<>();
        dfs(root, targetSum);
        return res;
    }

    private void dfs(TreeNode root, int targetSum) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        // 注意是叶子节点
        if (root.left == null
                && root.right == null
                && root.val == targetSum) {
            res.add(new ArrayList<>(path));
            // 因为直接return，走不到最后一行，这里需要补充一下移除
            path.remove(path.size() -1);
            return;
        }
        dfs(root.left, targetSum - root.val);
        dfs(root.right, targetSum - root.val);
        path.remove(path.size() - 1);
    }


}
