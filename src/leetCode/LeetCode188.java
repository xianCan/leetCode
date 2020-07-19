package leetCode;

/**
 * @author xianCan
 * @date 2020/7/19 20:08
 *
 * 188. 买卖股票的最佳时机 IV
 *
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。

    设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。

    注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

    示例 1:

    输入: [2,4,1], k = 2
    输出: 2
    解释: 在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
    示例 2:

    输入: [3,2,6,5,0,3], k = 2
    输出: 7
    解释: 在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
         随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。

 */
public class LeetCode188{

    /**
     * 这种方式虽然可行，但是leetCode上会显示超出内存，因此在交易次数大于 n/2 时，相当于交易次数无限制
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit(int k, int[] prices) {
        int res = 0;
        if (k==0 || prices==null || prices.length==0) return res;
        int n = prices.length;
        if (k > n/2){
            return maxProfit(prices);
        }

        int[][][] dp = new int[n][k+1][2];
        for (int i=0; i<n; i++){
            for (int j=0; j<=k; j++){
                if (i==0){
                    dp[0][j][0] = 0;
                    dp[0][j][1] = - prices[0];
                    continue;
                }
                if (j==0){
                    dp[i][0][0] = 0;
                    dp[i][0][1] = Integer.MIN_VALUE;
                    continue;
                }
                dp[i][j][0] = Math.max(dp[i-1][j][0], dp[i-1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i-1][j-1][0] - prices[i]);
                res = Math.max(res, dp[i][j][0]);
            }
        }
        return res;
    }

    public int maxProfit(int[] prices) {
        int n = prices.length;

        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int i=0; i< n; i++){
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, temp - prices[i]);
        }
        return dp_i_0;
    }

    public static void main(String[] args) {
        int i = new LeetCode188().maxProfit(2, new int[]{3,3,5,0,0,3,1,4});
        System.out.println(i);
    }
}
