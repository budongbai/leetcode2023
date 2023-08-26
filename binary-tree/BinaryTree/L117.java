package BinaryTree;

import budongbai.common.Node;
import budongbai.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class L117 {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            Node last = null;
            while (size > 0 && !queue.isEmpty()) {
                Node cur = queue.poll();
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
                if (last != null) {
                    last.next = cur;
                }
                last = cur;
                size--;
            }
        }
        return root;
    }
}
