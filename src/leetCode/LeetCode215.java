package leetCode;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * @author xianCan
 * @date 2020/9/7 21:19
 *
 * 215. 数组中的第K个最大元素（中等）
 *
 *  在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

    示例 1:

    输入: [3,2,1,5,6,4] 和 k = 2
    输出: 5
    示例 2:

    输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
    输出: 4
    说明:

    你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。

 */
public class LeetCode215 {

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int num : nums){
            if (k == queue.size()){
                if (num > queue.peek()){
                    queue.poll();
                    queue.offer(num);
                }
            } else {
                queue.offer(num);
            }
        }
        return queue.peek();
    }

    public int findKthLargest2(int[] nums, int k){
        int len = nums.length;
        int targetIndex = len - k;
        int left = 0, right = len - 1;
        while (true){
            int i = partition(nums, left, right);
            if (i == targetIndex){
                return nums[i];
            } else if (i < targetIndex){
                left = i + 1;
            } else {
                right = i-1;
            }
        }
    }

    private int partition(int[] nums, int left, int right){
        if (right > left){
            int random = left + new Random().nextInt(right-left);
            swap(nums, right, random);
        }

        int i = left;
        int pivot = nums[right];
        for (int j=left; j<right; j++){
            if (nums[j] < pivot){
                swap(nums, i, j);
                i++;
            }
        }
        swap(nums,i, right);
        return i;
    }

    private void swap(int[] nums, int idx1, int idx2){
        int tmp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = tmp;
    }

    public static void main(String[] args) {
        int kthLargest = new LeetCode215().findKthLargest(new int[]{3,2,1,5,6,4}, 2);
        System.out.println(kthLargest);
    }
}
