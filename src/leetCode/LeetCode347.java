package leetCode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * @author xianCan
 * @date 2020/9/7 21:06
 *
 * 347. 前 K 个高频元素（中等）
 *
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。

    示例 1:

    输入: nums = [1,1,1,2,2,3], k = 2
    输出: [1,2]
    示例 2:

    输入: nums = [1], k = 1
    输出: [1]
     

    提示：

    你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
    你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
    题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
    你可以按任意顺序返回答案。

 *
 */
public class LeetCode347 {

    /**
     * 暴力，也能过...
     * 时间复杂度：在排序那里O(N log N)
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums){
            map.put(num, map.getOrDefault(num, 0)+1);
        }

        List<Integer> list = map.entrySet().stream().sorted((o1, o2) ->
                o2.getValue() - o1.getValue()).map(Map.Entry::getKey).collect(Collectors.toList());

        int[] res = new int[k];
        for(int i=0; i<k; i++){
            res[i] = list.get(i);
        }
        return res;
    }

    /**
     * 小根堆
     * 时间复杂度：O(N)
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums){
            map.put(num, map.getOrDefault(num, 0)+1);
        }

        //优先级队列
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

        for (Map.Entry<Integer, Integer> entry : map.entrySet()){
            int key = entry.getKey(), value = entry.getValue();
            if (k == queue.size()){
                if (value > queue.peek()[1]){
                    queue.poll();
                    queue.offer(new int[]{key, value});
                }
            } else {
                queue.offer(new int[]{key, value});
            }
        }

        int[] res = new int[k];
        for(int i=0; i<k; i++){
            res[i] = queue.poll()[0];
        }
        return res;
    }
}
