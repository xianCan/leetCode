package leetCode;

/**
 * @author xianCan
 * @date 2020/7/18 19:53
 *
 * 121. 买卖股票的最佳时机
 *
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。

    如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。

    注意：你不能在买入股票前卖出股票。

     

    示例 1:

    输入: [7,1,5,3,6,4]
    输出: 5
    解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
    注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
    示例 2:

    输入: [7,6,4,3,1]
    输出: 0
    解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。

 */
public class LeetCode121 {

    /**
     * 动态规划
     *
     * i为天数，k为交易次数（一次买卖算一次交易），0代表空仓，1代表满仓
     * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1]+prices[i])
     *               max(reset, sell)
     *
     *  解释：今天我没有持有股票，有两种可能：
        要么是我昨天就没有持有，然后今天选择 rest，所以我今天还是没有持有；
        要么是我昨天持有股票，但是今天我 sell 了，所以我今天没有持有股票了。
     *
     * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0]-prices[i])
     *               max(reset, buy)
     *
     *  解释：今天我持有着股票，有两种可能：
        要么我昨天就持有着股票，然后今天选择 rest，所以我今天还持有着股票；
        要么我昨天本没有持有，但今天我选择 buy，所以今天我就持有股票了。
     *
     * base case
     *
     * dp[-1][k][0] = 0
     * 解释：因为 i 是从 0 开始的，所以 i = -1 意味着还没有开始，这时候的利润当然是 0 。
     *
     * dp[-1][k][1] = 负无穷
     * 解释：还没开始的时候，是不可能持有股票的，用负无穷表示这种不可能。
     *
     * dp[i][0][0] = 0
     * 解释：因为 k 是从 1 开始的，所以 k = 0 意味着根本不允许交易，这时候利润当然是 0 。
     *
     * dp[i][0][1] = 负无穷
     * 解释：不允许交易的情况下，是不可能持有股票的，用负无穷表示这种不可能。
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int i=0; i<n; i++){
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, -prices[i]);
        }
        return dp_i_0;
    }

    public static void main(String[] args) {
        int[] prices = new int[]{7,1,5,3,6,4};
        int i = new LeetCode121().maxProfit(prices);
        System.out.println(i);
    }
}
