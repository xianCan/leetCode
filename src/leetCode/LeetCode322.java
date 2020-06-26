package leetCode;

/**
 * @author xianCan
 * @date 2020/6/26 11:20
 *
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
    示例 1:

    输入: coins = [1, 2, 5], amount = 11
    输出: 3
    解释: 11 = 5 + 5 + 1
    示例 2:

    输入: coins = [2], amount = 3
    输出: -1
 

    说明:
    你可以认为每种硬币的数量是无限的。
 */
public class LeetCode322 {

    /**
     * 动态规划：本质是N叉数的遍历
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        if (amount < 0) return -1;
        int[] costs = new int[amount + 1];
        //base case
        costs[0] = 0;
        //初始化，将amount+1的值赋给除0外的下标
        for (int i=1; i<costs.length; i++){
            costs[i] = amount + 1;
        }
        for (int i=1; i<=amount; i++){
            for (int coin : coins){
                if (i - coin >= 0){
                    if (costs[i-coin] < amount + 1){
                        costs[i] = costs[i-coin] + 1;
                    }
                }
            }
        }
        return costs[amount] == amount+1 ? -1 : costs[amount];
    }

    public static void main(String[] args) {
        int[] coins = new int[]{1, 2, 5};
        int i = new LeetCode322().coinChange(coins, 11);
        System.out.println(i);
    }
}
