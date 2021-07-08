package leetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xianCan
 * @date 2021/7/8 20:54
 *
 * 930. 和相同的二元子数组（中等）
 *
 *  给你一个二元数组 nums ，和一个整数 goal ，请你统计并返回有多少个和为 goal 的 非空 子数组。

    子数组 是数组的一段连续部分。

     

    示例 1：

    输入：nums = [1,0,1,0,1], goal = 2
    输出：4
    解释：
    有 4 个满足题目要求的子数组：[1,0,1]、[1,0,1,0]、[0,1,0,1]、[1,0,1]
    示例 2：

    输入：nums = [0,0,0,0,0], goal = 0
    输出：15
     

    提示：

    1 <= nums.length <= 3 * 104
    nums[i] 不是 0 就是 1
    0 <= goal <= nums.length

 */
public class LeetCode930 {

    public int numSubarraysWithSum(int[] nums, int goal) {
        Map<Integer, Integer> map = new HashMap<>();
        //垫下底
        map.put(0, 1);
        int sum = 0, res = 0;

        for (int num : nums){
            sum += num;
            //当前前缀和已知，判断是否含有 presum - k的前缀和，那么我们就知道某一区间的和为 k 了。
            map.put(sum, map.getOrDefault(sum, 0) + 1);
            //更新
            res += map.getOrDefault(sum - goal, 0);
        }

        return res;
    }
}
