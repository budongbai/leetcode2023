package budongbai.daily.easy;

/**
 * <a href="https://leetcode.cn/problems/matrix-diagonal-sum/description/">1572. 矩阵对角线元素的和</a>
 * <p>
 * 给你一个正方形矩阵 mat，请你返回矩阵对角线元素的和。
 * <p>
 * 请你返回在矩阵主对角线上的元素和副对角线上且不在主对角线上元素的和。
 *
 * 题解link https://leetcode.cn/problems/matrix-diagonal-sum/solutions/2382850/xiao-bai-ti-jie-ju-zhen-dui-jiao-xian-yu-3rse/
 */
public class MatrixDiagonalSum {
    /**
     * 对角线元素
     *  i == j || i + j == n - 1
     */
    public int diagonalSum(int[][] mat) {
        int n = mat.length;

        boolean flag = n % 2 == 1;
        int mid = n / 2;
        int res = 0;
        for (int i = 0; i < n; i++) {
            // 矩阵长度为奇数，说明中心点会被两条对角线同时经过，这种情况只计算一次。
            if (flag && i == mid) {
                res += mat[i][i];
            } else {
                res += mat[i][i] + mat[i][n - i - 1];
            }
        }
        return res;
    }
}
