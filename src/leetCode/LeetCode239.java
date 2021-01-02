package leetCode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @author xianCan
 * @date 2021/1/2 11:34
 *
 * 239. 滑动窗口最大值（困难）
 *
 *  给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。

    返回滑动窗口中的最大值。

     

    示例 1：

    输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
    输出：[3,3,5,5,6,7]
    解释：
    滑动窗口的位置                最大值
    ---------------               -----
    [1  3  -1] -3  5  3  6  7       3
    1 [3  -1  -3] 5  3  6  7       3
    1  3 [-1  -3  5] 3  6  7       5
    1  3  -1 [-3  5  3] 6  7       5
    1  3  -1  -3 [5  3  6] 7       6
    1  3  -1  -3  5 [3  6  7]      7
    示例 2：

    输入：nums = [1], k = 1
    输出：[1]
    示例 3：

    输入：nums = [1,-1], k = 1
    输出：[1,-1]
    示例 4：

    输入：nums = [9,11], k = 2
    输出：[11]
    示例 5：

    输入：nums = [4,-2], k = 2
    输出：[4]
     

    提示：

    1 <= nums.length <= 105
    -104 <= nums[i] <= 104
    1 <= k <= nums.length

 */
public class LeetCode239 {

    /**
     * 优先级队列，O(N log N)
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow2(int[] nums, int k){
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] == o2[0]){
                return Integer.compare(o2[1], o1[1]);
            }
            return Integer.compare(o2[0], o1[0]);
        });
        int n = nums.length, idx = 1;
        int[] ans = new int[n - k + 1];

        for (int i = 0; i < k; i++){
            queue.offer(new int[]{nums[i], i});
        }
        ans[0] = queue.peek()[0];

        for (int i = k; i < n; i++){
            while (!queue.isEmpty() && queue.peek()[1] <= i - k){
                queue.poll();
            }
            queue.offer(new int[]{nums[i], i});
            ans[idx++] = queue.peek()[0];
        }
        return ans;
    }

    /**
     * 单调队列，O(N)
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> deque = new LinkedList<>();
        int n = nums.length, idx = 1;
        int[] ans = new int[n - k + 1];
        for (int i = 0; i < k; i++){
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]){
                deque.pollLast();
            }
            deque.offerLast(i);
        }

        ans[0] = nums[deque.peekFirst()];

        for (int i = k; i < n; i++){
            while (!deque.isEmpty() && deque.peekFirst() <= (i - k)){
                deque.pollFirst();
            }
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]){
                deque.pollLast();
            }
            deque.offerLast(i);
            ans[idx++] = nums[deque.peekFirst()];
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] ints = new LeetCode239().maxSlidingWindow2(new int[]{1,3,1,2,0,5}, 3);
        System.out.println(ints);
    }
}
