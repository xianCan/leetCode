package leetCode;

/**
 * @author xianCan
 * @date 2020/9/12 11:53
 *
 * 494. 目标和（中等）
 *
 *  给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。

    返回可以使最终数组和为目标数 S 的所有添加符号的方法数。

    示例：

    输入：nums: [1, 1, 1, 1, 1], S: 3
    输出：5
    解释：

    -1+1+1+1+1 = 3
    +1-1+1+1+1 = 3
    +1+1-1+1+1 = 3
    +1+1+1-1+1 = 3
    +1+1+1+1-1 = 3

    一共有5种方法让最终目标和为3。
     

    提示：

    数组非空，且长度不会超过 20 。
    初始的数组的和不会超过 1000 。
    保证返回的最终结果能被 32 位整数存下。

 */
public class LeetCode494 {

    private int[] nums;
    private int len;
    private int res;

    /**
     * 暴力递归，也能过...
     * @param nums
     * @param S
     * @return
     */
    public int findTargetSumWays(int[] nums, int S) {
        this.nums = nums;
        this.res = 0;
        this.len = nums.length;
        helper(0, S);
        return res;
    }

    private void helper(int start, int S){
        if (S == 0 && start == len){
            res++;
            return;
        }

        if (start < len){
            helper(start+1, S-nums[start]);
            helper(start+1, S+nums[start]);
        }
    }

    /**
     * dp[i][j]数组的定义：数组nums中 0 - i 的元素进行加减可以得到 j 的方法数量
     * 状态转移方程：dp[ i ][ j ] = dp[ i - 1 ][ j - nums[ i ] ] + dp[ i - 1 ][ j + nums[ i ] ]
     * @param nums
     * @param S
     * @return
     */
    public int findTargetSumWays2(int[] nums, int S) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 绝对值范围超过了sum的绝对值范围则无法得到
        if (Math.abs(S) > Math.abs(sum)) return 0;

        int len = nums.length;
        // - 0 +
        int t = sum * 2 + 1;
        int[][] dp = new int[len][t];
        // 初始化
        if (nums[0] == 0) {
            dp[0][sum] = 2;
        } else {
            dp[0][sum + nums[0]] = 1;
            dp[0][sum - nums[0]] = 1;
        }

        for (int i = 1; i < len; i++) {
            for (int j = 0; j < t; j++) {
                // 边界
                int l = (j - nums[i]) >= 0 ? j - nums[i] : 0;
                int r = (j + nums[i]) < t ? j + nums[i] : 0;
                dp[i][j] = dp[i - 1][l] + dp[i - 1][r];
            }
        }
        return dp[len - 1][sum + S];
    }


    public static void main(String[] args) {
        int targetSumWays = new LeetCode494().findTargetSumWays2(new int[]{1, 1, 1, 1, 1}, 3);
        System.out.println(targetSumWays);
    }
}
