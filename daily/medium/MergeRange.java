package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/merge-intervals/description/">56. 合并区间</a>
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 */
public class MergeRange {
    public int[][] merge(int[][] intervals) {
        List<int[]> res = new ArrayList<>();

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        for (int[] interval : intervals) {
            int left = interval[0], right = interval[1];
            // 首个区间 或 最后一个区间右元素小于当前区间左元素，即两个区间不重叠
            if (res.size() == 0 || res.get(res.size() - 1)[1] < left) {
                res.add(new int[]{left, right});
            } else {
                // 区间重叠，更新右元素
                res.get(res.size() - 1)[1] = Math.max(res.get(res.size() - 1)[1], right);
            }
        }
        return res.toArray(new int[][]{});
    }
}
