package offer;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @author xianCan
 * @date 2021/1/17 17:14
 *
 * 剑指 Offer 59 - I. 滑动窗口的最大值（简单）
 *
 *  给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。

    示例:

    输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
    输出: [3,3,5,5,6,7]
    解释:

    滑动窗口的位置                最大值
    ---------------               -----
    [1  3  -1] -3  5  3  6  7       3
    1 [3  -1  -3] 5  3  6  7       3
    1  3 [-1  -3  5] 3  6  7       5
    1  3  -1 [-3  5  3] 6  7       5
    1  3  -1  -3 [5  3  6] 7       6
    1  3  -1  -3  5 [3  6  7]      7
     

    提示：

    你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。

    注意：本题与主站 239 题相同：https://leetcode-cn.com/problems/sliding-window-maximum/
    注意：本题不对时间复杂度有要求

 */
public class Offer59I {

    /**
     * 优先级队列
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n == 0 || k == 0){
            return new int[]{};
        }
        int[] res = new int[n - k + 1];
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> nums[o2] - nums[o1]);

        for (int i = 0; i < k; i++){
            queue.offer(i);
        }
        res[0] = nums[queue.peek()];

        int idx = 1;
        for (int i = k; i < n; i++){
            while (!queue.isEmpty() && queue.peek() <= (i - k)){
                queue.poll();
            }
            queue.offer(i);
            res[idx++] = nums[queue.peek()];
        }

        return res;
    }

    /**
     * 双向队列
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow2(int[] nums, int k){
        int n = nums.length;
        if (n == 0 || k == 0){
            return new int[]{};
        }
        int[] res = new int[n - k + 1];

        Deque<Integer> queue = new LinkedList<>();
        for (int i = 0; i < k; i++){
            while (!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]){
                queue.pollLast();
            }
            queue.offerLast(i);
        }

        res[0] = nums[queue.peekFirst()];

        int idx = 1;
        for (int i = k; i < n; i++){
            while (!queue.isEmpty() && queue.peekFirst() <= (i - k)){
                queue.pollFirst();
            }
            while (!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]){
                queue.pollLast();
            }
            queue.offerLast(i);
            res[idx++] = nums[queue.peekFirst()];
        }

        return res;
     }

    public static void main(String[] args) {
        int[] ints = new Offer59I().maxSlidingWindow2(new int[]{1,3,-1,-3,5,3,6,7}, 3);
        System.out.println(ints);
    }
}
