package leetCode;

/**
 * @author xianCan
 * @date 2020/11/7 0:11
 *
 * 327. 区间和的个数（困难）
 *
 *  给定一个整数数组 nums，返回区间和在 [lower, upper] 之间的个数，包含 lower 和 upper。
 *
    区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。

    说明:
    最直观的算法复杂度是 O(n2) ，请在此基础上优化你的算法。

    示例:

    输入: nums = [-2,5,-1], lower = -2, upper = 2,
    输出: 3
    解释: 3个区间分别是: [0,0], [2,2], [0,2]，它们表示的和分别为: -2, -1, 2。

 */
public class LeetCode327 {

    public int countRangeSum(int[] nums, int lower, int upper) {
        int res=0;
        int len = nums.length;
        long[] dp = new long[len];
        for (int i=0; i<len; i++){
            dp[i] = i > 0 ? dp[i-1] + nums[i] : nums[i];
        }

        for (int i=0; i<len; i++){
            for (int j=i; j<len; j++){
                long sum = i > 0 ? dp[j]-dp[i-1] : dp[j];
                if (sum >= lower && sum <= upper){
                    res++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int i = new LeetCode327().countRangeSum(new int[]{}, -17, 10);
        System.out.println(i);
    }
}
