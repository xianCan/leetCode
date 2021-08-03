package leetCode;

import java.util.Arrays;

/**
 * @author xianCan
 * @date 2021/8/3 20:50
 *
 * 581. 最短无序连续子数组（中等）
 *
 *  给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。

    请你找出符合题意的 最短 子数组，并输出它的长度。

     

    示例 1：

    输入：nums = [2,6,4,8,10,9,15]
    输出：5
    解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
    示例 2：

    输入：nums = [1,2,3,4]
    输出：0
    示例 3：

    输入：nums = [1]
    输出：0
     

    提示：

    1 <= nums.length <= 104
    -105 <= nums[i] <= 105
     

    进阶：你可以设计一个时间复杂度为 O(n) 的解决方案吗？

 */
public class LeetCode581 {

    //O(N log N)
    public int findUnsortedSubarray(int[] nums) {
        int[] copyArrays = Arrays.copyOf(nums, nums.length);
        Arrays.sort(copyArrays);

        int left = -1, right = -1;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] != copyArrays[i]){
                if (left == -1){
                    left = i;
                }
                right = i;
            }
        }

        return left != -1 ? right - left + 1 : 0;
    }

    //O(N)
    public int findUnsortedSubarray2(int[] nums) {
        int n = nums.length;
        int maxn = Integer.MIN_VALUE, right = -1;
        for (int i = 0; i < n; i++){
            if (maxn > nums[i]){
                right = i;
            } else {
                maxn = nums[i];
            }
        }

        int minn = Integer.MAX_VALUE, left = -1;
        for (int i = n - 1; i >= 0; i--) {
            if (minn < nums[i]) {
                left = i;
            } else {
                minn = nums[i];
            }
        }
        return right == -1 ? 0 : right - left + 1;
    }
}
