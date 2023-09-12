package BinaryTree;

import budongbai.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 156. 上下翻转二叉树
 * 给你一个二叉树的根节点 root ，请你将此二叉树上下翻转，并返回新的根节点。
 * <p>
 * 你可以按下面的步骤翻转一棵二叉树：
 * <p>
 * 原来的左子节点变成新的根节点
 * 原来的根节点变成新的右子节点
 * 原来的右子节点变成新的左子节点
 * <p>
 * 上面的步骤逐层进行。题目数据保证每个右节点都有一个同级节点（即共享同一父节点的左节点）且不存在子节点。
 */
public class L156 {
    /**
     * 作者：Krahets
     * 链接：https://leetcode.cn/problems/binary-tree-upside-down/solutions/8900/binary-tree-upside-down-top-downdie-dai-fa-by-jin4/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public TreeNode upsideDownBinaryTree1(TreeNode root) {
        TreeNode parent = null, parentRight = null;
        while (root != null) {
            // 记录一下本次左右子树
            TreeNode rootLeft = root.left;
            TreeNode rootRight = root.right;
            // 更新
            root.left = parentRight; // 父右节点成为新的左节点
            root.right = parent; // 父节点成为新的右节点

            // 准备下一次迭代
            parent = root; // 当前节点 root 成为下次迭代的父节点 parent，
            root = rootLeft; // 原有左节点 rootLeft 成为下次迭代的当前节点 root
            parentRight = rootRight; // 原有右节点 rootRight 成为下次迭代的父右节点 parentRight
        }
        return parent;
    }

    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            return root;
        }

        // 左子树最左节点为根
        TreeNode newRoot = upsideDownBinaryTree(root.left);

        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;
        return newRoot;
    }


    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);

        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;

        System.out.println(n1.preOrder());
        System.out.println(new L156().upsideDownBinaryTree1(n1).preOrder());
    }

}
