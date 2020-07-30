package leetCode;

/**
 * @author xianCan
 * @date 2020/7/30 23:13
 *
 * 343. 整数拆分
 *
 *  给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。

    示例 1:

    输入: 2
    输出: 1
    解释: 2 = 1 + 1, 1 × 1 = 1。
    示例 2:

    输入: 10
    输出: 36
    解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
    说明: 你可以假设 n 不小于 2 且不大于 58。

 *
 *
 */
public class LeetCode343 {

    public int integerBreak(int n) {
        if (n < 2) return 0;
        Integer[] dp = new Integer[n+1];
        dp[0] = 0;
        dp[1] = 0;
        return recursive(n, dp);
    }

    private int recursive(int n, Integer[] dp) {
        if (dp[n] != null)
            return dp[n];

        int res=0;
        for (int i=1; i<=n; i++){
            res = Math.max(res, Math.max(i * (n-i), i * recursive(n-i, dp)));
        }
        dp[n] = res;
        return res;
    }

    public static void main(String[] args) {
        int i = new LeetCode343().integerBreak(10);
        System.out.println(i);
    }
}
