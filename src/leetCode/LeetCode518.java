package leetCode;

/**
 * @author xianCan
 * @date 2020/8/9 17:08
 *
 * 518. 零钱兑换 II（中等）
 *
 *  给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。 

    示例 1:

    输入: amount = 5, coins = [1, 2, 5]
    输出: 4
    解释: 有四种方式可以凑成总金额:
    5=5
    5=2+2+1
    5=2+1+1+1
    5=1+1+1+1+1
    示例 2:

    输入: amount = 3, coins = [2]
    输出: 0
    解释: 只用面额2的硬币不能凑成总金额3。
    示例 3:

    输入: amount = 10, coins = [10]
    输出: 1
     

    注意:

    你可以假设：

    0 <= amount (总金额) <= 5000
    1 <= coin (硬币面额) <= 5000
    硬币种类不超过 500 种
    结果符合 32 位符号整数

 */
public class LeetCode518 {

    /**
     * 有一个背包，最大容量为 amount，有一系列物品 coins，每个物品的重量为 coins[i]，每个物品的数量无限。请问有多少种方法，能够把背包恰好装满？
     这个问题和我们前面讲过的两个背包问题，有一个最大的区别就是，每个物品的数量是无限的，这也就是传说中的「完全背包问题」
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        int length = coins.length;
        int[][] dp = new int[length+1][amount+1];

        for (int i=0; i<=length; i++){
            dp[i][0] = 1;
        }

        for (int i=1; i<=length; i++){
            for (int j=1; j<=amount; j++){
                if (j - coins[i-1] < 0){
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = dp[i-1][j] + dp[i][j - coins[i-1]];
                }
            }
        }
        return dp[length][amount];
    }

    public int change2(int amount, int[] coins) {
        int length = coins.length;
        int[] dp = new int[amount+1];
        dp[0] = 1;

        for (int i=0; i<length; i++){
            for (int j=1; j<=amount; j++){
                if (j - coins[i] >= 0){
                    dp[j] = dp[j] + dp[j - coins[i]];
                }
            }
        }
        return dp[amount];
    }



    public static void main(String[] args) {
        int change = new LeetCode518().change2(5, new int[]{1,2,5});
        System.out.println(change);
    }
}
