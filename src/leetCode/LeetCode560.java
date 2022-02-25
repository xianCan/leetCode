package leetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xianCan
 * @date 2022/2/24 23:27
 *
 * 560. 和为 K 的子数组（中等）
 *
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回该数组中和为 k 的连续子数组的个数。

    示例 1：

    输入：nums = [1,1,1], k = 2
    输出：2
    示例 2：

    输入：nums = [1,2,3], k = 3
    输出：2
     

    提示：

    1 <= nums.length <= 2 * 104
    -1000 <= nums[i] <= 1000
    -107 <= k <= 107

 */
public class LeetCode560 {

    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0, ans = 0;
        for (int num : nums){
            sum += num;
            if (map.containsKey(sum - k)){
                ans += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return ans;
    }
}
