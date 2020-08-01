package leetCode;

/**
 * @author xianCan
 * @date 2020/8/1 10:34
 *
 * 198. 打家劫舍
 *
 *  你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

    给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。

     

    示例 1：

    输入：[1,2,3,1]
    输出：4
    解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
         偷窃到的最高金额 = 1 + 3 = 4 。
    示例 2：

    输入：[2,7,9,3,1]
    输出：12
    解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
         偷窃到的最高金额 = 2 + 9 + 1 = 12 。
     

    提示：

    0 <= nums.length <= 100
    0 <= nums[i] <= 400

 */
public class LeetCode198 {

    /**
     * 自顶向下
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length==0)return 0;
        Integer[] dp=new Integer[nums.length+1];
        dp[0] = 0;
        return recursive(nums.length, nums, dp);
    }

    private int recursive(int num, int[] nums, Integer[] dp){
        if (num < 0) return 0;
        if (dp[num] != null){
            return dp[num];
        }
        int res= Math.max(nums[num-1] + recursive(num-2, nums, dp), recursive(num-1, nums, dp));
        dp[num] = res;
        return res;
    }

    /**
     * 自底向上
     * @param nums
     * @return
     */
    public int rob2(int[] nums){
        if (nums == null || nums.length==0)return 0;
        int length = nums.length;
        Integer[] dp=new Integer[length+1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i=2; i< length + 1; i++){
            dp[i] = Math.max(dp[i-2] + nums[i-1], dp[i-1]);
        }
        return dp[length];
    }

    public static void main(String[] args) {
        int rob = new LeetCode198().rob2(new int[]{2,7,9,3,1});
        System.out.println(rob);
    }
}
