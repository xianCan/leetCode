package leetCode;

/**
 * @author xianCan
 * @date 2020/8/15 20:13
 *
 * 53. 最大子序和（简单）
 *
 *  给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

    示例:

    输入: [-2,1,-3,4,-1,2,1,-5,4]
    输出: 6
    解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
    进阶:

    如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。

 */
public class LeetCode53 {

    /**
     *  对于这类子数组问题，我们就要重新定义 dp 数组的含义：
     *  以 nums[i] 为结尾的「最大子数组和」为 dp[i]
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if (nums==null || nums.length==0) return 0;
        int n=nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];

        for (int i=1; i<n; i++){
            dp[i] = Math.max(nums[i], dp[i-1]+nums[i]);
        }

        int res = Integer.MIN_VALUE;
        for (int i=0; i<n ;i++){
            res= Math.max(res, dp[i]);
        }

        return res;
    }

    /**
     * 贪心：加上当前数字，如果小于0，则丢掉开始，重新赋0
     * @param nums
     * @return
     */
    public int maxSubArray2(int[] nums){
        if (nums==null || nums.length==0) return 0;
        int ans=Integer.MIN_VALUE, sum=0;
        for (int num : nums){
            sum += num;
            if (sum > ans){
                ans = sum;
            }
            if (sum < 0){
                sum = 0;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int i = new LeetCode53().maxSubArray2(new int[]{-2,1,-3,4,-1,2,1,-5,4});
        System.out.println(i);
    }
}
