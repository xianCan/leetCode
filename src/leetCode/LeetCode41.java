package leetCode;

/**
 * @author xianCan
 * @date 2022/3/12 16:20
 *
 * 41. 缺失的第一个正数（困难）
 *
 *  给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。

    请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
     

    示例 1：

    输入：nums = [1,2,0]
    输出：3
    示例 2：

    输入：nums = [3,4,-1,1]
    输出：2
    示例 3：

    输入：nums = [7,8,9,11,12]
    输出：1
     

    提示：

    1 <= nums.length <= 5 * 105
    -231 <= nums[i] <= 231 - 1

 */
public class LeetCode41 {

    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        // 1. 先处理数组中的负值，排除干扰
        for (int i = 0; i < n; i++){
            if (nums[i] <= 0){
                nums[i] = n + 1;
            }
        }
        // 2. 统计包含的1~n，在其映射的下标作标记（设为负数）
        for (int i = 0; i < n; i++){
            int num = Math.abs(nums[i]);
            if (num > 0 && num <= n && nums[num - 1] > 0){
                nums[num - 1] = - nums[num - 1];
            }
        }
        // 3. 获得目标值
        for (int i = 0; i < n; i++){
            if (nums[i] > 0){
                return i + 1;
            }
        }

        return n + 1;
    }
}
