package leetCode;

/**
 * @author xianCan
 * @date 2021/11/20 16:29
 *
 * 740. 删除并获得点数（中等）
 *
 *  给你一个整数数组 nums ，你可以对它进行一些操作。

    每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除 所有 等于 nums[i] - 1 和 nums[i] + 1 的元素。

    开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。

     

    示例 1：

    输入：nums = [3,4,2]
    输出：6
    解释：
    删除 4 获得 4 个点数，因此 3 也被删除。
    之后，删除 2 获得 2 个点数。总共获得 6 个点数。
    示例 2：

    输入：nums = [2,2,3,3,3,4]
    输出：9
    解释：
    删除 3 获得 3 个点数，接着要删除两个 2 和 4 。
    之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
    总共获得 9 个点数。
     

    提示：

    1 <= nums.length <= 2 * 104
    1 <= nums[i] <= 104
 */
public class LeetCode740 {

    public int deleteAndEarn(int[] nums) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int num : nums){
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        int[] nNums = new int[max - min + 1];
        for (int num : nums){
            nNums[num - min] += num;
        }
        return rob(nNums);
    }

    private int rob(int[] nums){
        int len = nums.length;
        int[] dp = new int[len + 1];
        dp[1] = nums[0];
        for (int i = 2; i <= len; i++){
            dp[i] = Math.max(dp[i - 2] + nums[i - 1], dp[i - 1]);
        }
        return dp[len];
    }
}
