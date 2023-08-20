package BinaryTree;

import budongbai.common.TreeNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/construct-binary-tree-from-inorder-and-postorder-traversal/">106. 从中序与后序遍历序列构造二叉树s</a>
 * 给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，请你构造并返回这颗 二叉树 。
 */
public class L106 {

    private Map<Integer, Integer> indexMap;

    public TreeNode buildTree(int[] inorder, int[] postorder) {

        if (postorder.length == 0 || inorder.length == 0) {
            return null;
        }
        int n = postorder.length;

        indexMap = new HashMap<>(n << 1);

        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }

        return buildTree(inorder, postorder, 0, n - 1, 0, n - 1);

    }

    private TreeNode buildTree(int[] inorder, int[] postorder, int inLeft, int inRight, int postLeft, int postRight) {
        if (postLeft > postRight) {
            return null;
        }

        // 根节点 = postRight
        // 找到中序遍历中根节点的下标
        int inRoot = indexMap.get(postorder[postRight]);

        TreeNode root = new TreeNode(postorder[postRight]);

        int size = inRoot - inLeft;

        root.left = buildTree(inorder, postorder, inLeft, inRoot - 1, postLeft, postLeft + size - 1);
        root.right = buildTree(inorder, postorder, inRoot + 1, inRight, postLeft + size, postRight - 1);
        return root;
    }

    public TreeNode buildTree2(int[] inorder, int[] postorder) {

        if (postorder.length == 0 && inorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[postorder.length - 1]);
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == postorder[postorder.length - 1]) {
                root.left = buildTree2(
                        Arrays.copyOfRange(inorder, 0, i),
                        Arrays.copyOfRange(postorder, 0, i));

                root.right = buildTree2(
                        Arrays.copyOfRange(inorder, i + 1, inorder.length),
                        Arrays.copyOfRange(postorder, i, postorder.length - 1));
            }
        }
        return root;
    }
}
