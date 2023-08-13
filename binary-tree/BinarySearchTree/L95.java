package BinarySearchTree;

import budongbai.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/unique-binary-search-trees-ii/">95. 不同的二叉搜索树 II</a>
 * <p>
 * 中等
 * 给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。
 */
public class L95 {
    /**
     * 首先需要明确二叉搜索树的定义，二叉搜索树，左子树的节点值都小于根节点，右子树的节点值都大于根节点
     *
     * 以i为根节点，其左子树 即 [1, i - 1] 组成，右子树 [i+1, n]组成。递归分别生成子树，添加到根节点上。
     *           i
     *       /      \
     * [1, i-1]   [i+1, n]
     */

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        return backtrack(1, n);
    }

    private List<TreeNode> backtrack(int start, int end) {
        List<TreeNode> allTrees = new ArrayList<>();

        if (start > end) {
            allTrees.add(null);
            return allTrees;
        }

        for (int i = start; i <= end; i++) {

            // 左子树集合
            List<TreeNode> left = backtrack(start, i - 1);
            // 右子树集合
            List<TreeNode> right = backtrack(i + 1, end);

            // 遍历左右子树集合，分别挑一个左子树、一个右子树，组成一棵完整的二叉搜索树。
            for (TreeNode leftNode : left) {
                for (TreeNode rightNode : right) {
                    TreeNode cur = new TreeNode(i);
                    cur.left = leftNode;
                    cur.right = rightNode;
                    allTrees.add(cur);
                }
            }
        }
        return allTrees;
    }

}
