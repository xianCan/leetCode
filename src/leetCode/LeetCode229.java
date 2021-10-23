package leetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xianCan
 * @date 2021/10/23 19:31
 *
 * 229. 求众数 II（中等）
 *
 * 给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。

    示例 1：

    输入：[3,2,3]
    输出：[3]
    示例 2：

    输入：nums = [1]
    输出：[1]
    示例 3：

    输入：[1,1,1,3,3,2,2,2]
    输出：[1,2]
     

    提示：

    1 <= nums.length <= 5 * 104
    -109 <= nums[i] <= 109
     

    进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1)的算法解决此问题。

 */
public class LeetCode229 {

    public List<Integer> majorityElement(int[] nums) {
        int len = nums.length, cnt = len / 3;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<Integer> ans = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()){
            if (entry.getValue() > cnt){
                ans.add(entry.getKey());
            }
        }
        return ans;
    }
}
