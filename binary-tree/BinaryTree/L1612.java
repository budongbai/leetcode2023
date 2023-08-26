package BinaryTree;

/**
 * <a href="https://leetcode.cn/problems/check-if-two-expression-trees-are-equivalent/">1612. 检查两棵二叉表达式树是否等价</a>
 */
public class L1612 {
    public boolean checkEquivalence(Node root1, Node root2) {
        int[] charNum1 = new int[26];
        int[] charNum2 = new int[26];
        sum(root1, charNum1, 1);
        sum(root2, charNum2, 1);
        for (int i = 0; i < charNum1.length; i++) {
            int num1 = charNum1[i];
            int num2 = charNum2[i];
            if (num1 != num2) {
                return false;
            }
        }
        return true;
    }

    public void sum(Node root, int[] charNum, int plus) {
        if (root == null) {
            return;
        }


        if (root.val != '+' && root.val != '-') {
            charNum[root.val - 'a'] = charNum[root.val - 'a'] + plus;
        } else if (root.val == '+') {
            plus = 1;
        } else {
            plus = -1;
        }


        sum(root.left, charNum, plus);
        sum(root.right, charNum, plus);
    }


    public static class Node {
        char val;
        Node left;
        Node right;

        Node() {
            this.val = ' ';
        }

        Node(char val) {
            this.val = val;
        }

        Node(char val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}

