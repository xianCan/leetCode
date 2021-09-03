package interview;

import java.util.PriorityQueue;

/**
 * @author xianCan
 * @date 2021/9/3 21:25
 *
 * 面试题 17.14. 最小K个数（中等）
 *
 *  设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。

    示例：

    输入： arr = [1,3,5,7,2,4,6,8], k = 4
    输出： [1,2,3,4]
    提示：

    0 <= len(arr) <= 100000
    0 <= k <= min(100000, len(arr))

 */
public class Interview1714 {

    public int[] smallestK(int[] arr, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int num : arr){
            if (queue.size() < k){
                queue.offer(num);
            } else {
                queue.offer(num);
                queue.poll();
            }
        }
        int[] ans = new int[k];
        for (int i = 0; i < k; i++){
            ans[i] = queue.poll();
        }
        return ans;
    }
}
