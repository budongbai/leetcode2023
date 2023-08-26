package easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 228. 汇总区间
 * 给定一个  无重复元素 的 有序 整数数组 nums 。
 * <p>
 * 返回 恰好覆盖数组中所有数字 的 最小有序 区间范围列表 。也就是说，nums 的每个元素都恰好被某个区间范围所覆盖，并且不存在属于某个范围但不属于 nums 的数字 x 。
 * <p>
 * 列表中的每个区间范围 [a,b] 应该按如下格式输出：
 * <p>
 * "a->b" ，如果 a != b
 * "a" ，如果 a == b
 */
public class L228 {
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        int i = 0;
        while (i < nums.length) {
            int low = i;
            i++;
            while (i < nums.length && nums[i - 1] + 1 == nums[i]) {
                i++;
            }
            int high = i - 1;
            if (nums[low] == nums[high]) {
                res.add("" + nums[low]);
            } else {
                res.add(nums[low] + "->" + nums[high]);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new L228().summaryRanges(new int[]{0, 2, 3, 4, 6, 7, 9}));
    }
}
