package leetCode;

/**
 * @author xianCan
 * @date 2020/8/8 12:39
 *
 * 416. 分割等和子集（中等）
 *
 *  给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。

    注意:

    每个数组中的元素不会超过 100
    数组的大小不会超过 200
    示例 1:

    输入: [1, 5, 11, 5]

    输出: true

    解释: 数组可以分割成 [1, 5, 5] 和 [11].
     

    示例 2:

    输入: [1, 2, 3, 5]

    输出: false

    解释: 数组不能分割成两个元素和相等的子集.

 */
public class LeetCode416 {

    /**
     *  首先回忆一下背包问题大致的描述是什么：
     *
        给你一个可装载重量为 W 的背包和 N 个物品，每个物品有重量和价值两个属性。其中第 i 个物品的重量为 wt[i]，价值为 val[i]，现在让你用这个背包装物品，最多能装的价值是多少？

        那么对于这个问题，我们可以先对集合求和，得出 sum，把问题转化为背包问题：

        给一个可装载重量为 sum / 2 的背包和 N 个物品，每个物品的重量为 nums[i]。现在让你装物品，是否存在一种装法，能够恰好将背包装满？
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        int sum=0;
        for (int num : nums){
            sum += num;
        }
        if (sum % 2 != 0) return false;
        sum = sum / 2;
        int length = nums.length;

        boolean[][] dp = new boolean[length+1][sum+1];
        //base case
        //暗含了另一个base case dp[0][i] = false
        for (int i=0; i<dp.length; i++){
            dp[i][0] = true;
        }

        for (int i=1; i<=length; i++){
            for (int j=1; j<=sum; j++){
                if (j - nums[i-1] < 0){
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i-1]];
                }
            }
        }
        return dp[length][sum];
    }

    /**
     *  压缩空间
     *
     *  注意到 dp[i][j] 都是通过上一行 dp[i-1][..] 转移过来的，之前的数据都不会再使用了。
        所以，我们可以进行状态压缩，将二维 dp 数组压缩为一维，节约空间复杂度：
     * @param nums
     * @return
     */
    public boolean canPartition2(int[] nums) {
        int sum=0;
        for (int num : nums){
            sum += num;
        }
        if (sum % 2 != 0) return false;
        sum = sum / 2;
        int length = nums.length;

        boolean[] dp = new boolean[sum+1];
        //base case
        dp[0] = true;

        for (int i=0; i<length; i++){
            //唯一需要注意的是 j 应该从后往前反向遍历，因为每个物品（或者说数字）只能用一次，以免之前的结果影响其他的结果。
            for (int j=sum; j>=0; j--){
                if (j - nums[i] >= 0){
                    dp[j] = dp[j] || dp[j-nums[i]];
                }
            }
        }
        return dp[sum];
    }
}
