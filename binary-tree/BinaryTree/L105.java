package BinaryTree;

import budongbai.common.TreeNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/">105. 从前序与中序遍历序列构造二叉树</a>
 * <p>
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 */
public class L105 {

    private Map<Integer, Integer> indexMap;

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        if (preorder.length == 0 || inorder.length == 0) {
            return null;
        }
        int n = preorder.length;

        indexMap = new HashMap<>(n << 1);

        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }

        return buildTree(preorder, inorder, 0, n - 1, 0, n - 1);

    }

    private TreeNode buildTree(int[] preorder, int[] inorder, int preLeft, int preRight, int inLeft, int inRight) {
        if (preLeft > preRight) {
            return null;
        }

        // 根节点 = preLeft
        // 找到中序遍历中根节点的下标
        int inRoot = indexMap.get(preorder[preLeft]);

        TreeNode root = new TreeNode(preorder[preLeft]);

        int size = inRoot - inLeft;

        root.left = buildTree(preorder, inorder, preLeft + 1, preLeft + size, inLeft, inRoot - 1);
        root.right = buildTree(preorder, inorder, preLeft + size + 1, preRight, inRoot + 1, inRight);
        return root;
    }


    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);

        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == preorder[0]) {
                root.left = buildTree(
                        Arrays.copyOfRange(preorder, 1, i + 1),
                        Arrays.copyOfRange(inorder, 0, i));
                root.right = buildTree(
                        Arrays.copyOfRange(preorder, i + 1, preorder.length),
                        Arrays.copyOfRange(inorder, i + 1, inorder.length));
            }
        }
        return root;
    }
}
