package leetCode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author xianCan
 * @date 2020/12/4 20:08
 *
 * 659. 分割数组为连续子序列（中等）
 *
 *  给你一个按升序排序的整数数组 num（可能包含重复数字），请你将它们分割成一个或多个子序列，其中每个子序列都由连续整数组成且长度至少为 3 。

    如果可以完成上述分割，则返回 true ；否则，返回 false 。

     

    示例 1：

    输入: [1,2,3,3,4,5]
    输出: True
    解释:
    你可以分割出这样两个连续子序列 :
    1, 2, 3
    3, 4, 5
     

    示例 2：

    输入: [1,2,3,3,4,4,5,5]
    输出: True
    解释:
    你可以分割出这样两个连续子序列 :
    1, 2, 3, 4, 5
    3, 4, 5
     

    示例 3：

    输入: [1,2,3,4,4,5]
    输出: False
     

    提示：

    输入的数组长度范围为 [1, 10000]

 */
public class LeetCode659 {

    /**
     * 哈希表+最小堆
     *
     * 哈希表的键为子序列的最后一个数字，值为最小堆，用于存储所有的子序列长度，最小堆满足堆顶的元素是最小的，因此堆顶的元素即为最小的子序列长度
     * O(N log N)
     * @param nums
     * @return
     */
    public boolean isPossible(int[] nums) {
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int num : nums){
            if (!map.containsKey(num)){
                map.put(num, new PriorityQueue<>());
            }
            if (map.containsKey(num - 1)){
                Integer prevLen = map.get(num - 1).poll();
                if (map.get(num - 1).isEmpty()){
                    map.remove(num - 1);
                }
                map.get(num).offer(prevLen + 1);
            } else {
                map.get(num).offer(1);
            }
        }

        for (PriorityQueue<Integer> value : map.values()){
            if (value.peek() < 3){
                return false;
            }
        }

        return true;
    }

    /**
     * 贪心
     * O(N)
     * @param nums
     * @return
     */
    public boolean isPossible2(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        Map<Integer, Integer> endMap = new HashMap<>();

        for (int num : nums){
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        for (int num : nums){
            int count = countMap.getOrDefault(num, 0);
            if (count > 0){
                Integer prevEndCount = endMap.getOrDefault(num - 1, 0);
                if (prevEndCount > 0){
                    countMap.put(num, count - 1);
                    endMap.put(num - 1, prevEndCount - 1);
                    endMap.put(num, endMap.getOrDefault(num, 0) + 1);
                } else {
                    Integer count1 = countMap.getOrDefault(num + 1, 0);
                    Integer count2 = countMap.getOrDefault(num + 2, 0);
                    if (count1 > 0 && count2 > 0){
                        countMap.put(num, count - 1);
                        countMap.put(num + 1, count1 - 1);
                        countMap.put(num + 2, count2 - 1);
                        endMap.put(num + 2, endMap.getOrDefault(num + 2, 0) + 1);
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        boolean possible2 = new LeetCode659().isPossible2(new int[]{1, 2, 3, 3, 4, 5});
        System.out.println(possible2);
    }
}
