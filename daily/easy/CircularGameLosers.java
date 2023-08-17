package easy;

public class CircularGameLosers {
    public int[] circularGameLosers(int n, int k) {
        boolean[] vis = new boolean[n];
        int m = n; // 没接过球的个数
        // 第i个人第2次接球为结束
        for (int i = 0, d = k; !vis[i]; d += k, m--) {
            vis[i] = true;
            i = (i + d) % n;
        }
        int[] res = new int[m];
        // 遍历访问记录，没有接过球的人是输家
        for (int i = 0, j = 0; i < n; i++) {
            if (!vis[i]) {
                res[j++] = i + 1;
            }
        }
        return res;
    }

}
