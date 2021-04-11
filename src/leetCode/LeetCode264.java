package leetCode;

/**
 * @author xianCan
 * @date 2021/4/11 21:31
 *
 * 264. 丑数 II（中等）
 *
 *  给你一个整数 n ，请你找出并返回第 n 个 丑数 。

    丑数 就是只包含质因数 2、3 和/或 5 的正整数。

     

    示例 1：

    输入：n = 10
    输出：12
    解释：[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。
    示例 2：

    输入：n = 1
    输出：1
    解释：1 通常被视为丑数。
     

    提示：

    1 <= n <= 1690

 */
public class LeetCode264 {

    public int nthUglyNumber(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;

        int x2 = 1, x3 = 1, x5 = 1;
        for (int i = 2; i <= n; i++){
            int num1 = dp[x2] * 2, num2 = dp[x3] * 3, num3 = dp[x5] * 5;
            dp[i] = Math.min(num1, Math.min(num2, num3));
            if (dp[i] == num1){
                x2++;
            }
            if (dp[i] == num2){
                x3++;
            }
            if (dp[i] == num3){
                x5++;
            }
        }

        return dp[n];
    }
}
