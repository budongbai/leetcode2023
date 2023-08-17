package hard;

import java.util.Arrays;

public class NumberOfWaysOfCuttingAPizza {
    public static final int MOD = (int) 1e9 + 7;

    public int ways(String[] pizza, int k) {
        MatrixSum ms = new MatrixSum(pizza);
        int m = pizza.length, n = pizza[0].length();
        int[][][] memo = new int[k][m][n];
        for (int i = 0; i < k; i++)
            for (int j = 0; j < m; j++)
                Arrays.fill(memo[i][j], -1); // -1 表示没有计算过
        return dfs(k - 1, 0, 0, memo, ms, m, n);
    }

    private int dfs(int c, int i, int j, int[][][] memo, MatrixSum ms, int m, int n) {
        if (c == 0) // 递归边界：无法再切了
            return ms.query(i, j, m, n) > 0 ? 1 : 0;
        if (memo[c][i][j] != -1) // 之前计算过
            return memo[c][i][j];
        int res = 0;
        for (int j2 = j; j2 < n; j2++) // 垂直切
            if (ms.query(i, j, m, j2) > 0) // 有苹果
                res = (res + dfs(c - 1, i, j2, memo, ms, m, n)) % MOD;
        for (int i2 = i; i2 < m; i2++) // 水平切
            if (ms.query(i, j, i2, n) > 0) // 有苹果
                res = (res + dfs(c - 1, i2, j, memo, ms, m, n)) % MOD;
        return memo[c][i][j] = res; // 记忆化
    }
}

// 二维前缀和模板（'A' 的 ASCII 码最低位为 1，'.' 的 ASCII 码最低位为 0）
class MatrixSum {
    private final int[][] sum;

    public MatrixSum(String[] matrix) {
        int m = matrix.length, n = matrix[0].length();
        sum = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum[i + 1][j + 1] = sum[i + 1][j] + sum[i][j + 1] - sum[i][j] + (matrix[i].charAt(j) & 1);
            }
        }
    }

    // 返回左上角在 (r1,c1) 右下角在 (r2-1,c2-1) 的子矩阵元素和（类似前缀和的左闭右开）
    public int query(int r1, int c1, int r2, int c2) {
        return sum[r2][c2] - sum[r2][c1] - sum[r1][c2] + sum[r1][c1];
    }
}
