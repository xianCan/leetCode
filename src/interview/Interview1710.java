package interview;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xianCan
 * @date 2021/7/9 20:13
 *
 * 面试题 17.10. 主要元素（简单）
 *
 *  数组中占比超过一半的元素称之为主要元素。给你一个 整数 数组，找出其中的主要元素。若没有，返回 -1 。请设计时间复杂度为 O(N) 、空间复杂度为 O(1) 的解决方案。

    示例 1：

    输入：[1,2,5,9,5,9,5,5,5]
    输出：5
    示例 2：

    输入：[3,2]
    输出：-1
    示例 3：

    输入：[2,2,1,1,1,2,2]
    输出：2

 */
public class Interview1710 {

    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums){
            map.put(num ,map.getOrDefault(num, 0) + 1);
        }

        int key = -1, val = -1;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()){
            if (entry.getValue() > val){
                key = entry.getKey();
                val = entry.getValue();
            }
        }

        return val * 2 > nums.length ? key : -1;
    }

    /**
     * 摩尔投票法
     *
     * Boyer-Moore 投票算法的基本思想是：在每一轮投票过程中，从数组中删除两个不同的元素，直到投票过程无法继续，此时数组为空或者数组中剩下的元素都相等。

        如果数组为空，则数组中不存在主要元素；

        如果数组中剩下的元素都相等，则数组中剩下的元素可能为主要元素。
     * @param nums
     * @return
     */
    public int majorityElement2(int[] nums) {
        int key = -1, cnt = 0;
        for (int num : nums){
            if (cnt == 0){
                key = num;
                cnt++;
            } else if (key == num){
                cnt++;
            } else {
                cnt--;
            }
        }

        cnt = 0;
        for (int num : nums){
            if (key == num){
                cnt++;
            }
        }

        return cnt * 2 > nums.length ? key : -1;
    }
}
