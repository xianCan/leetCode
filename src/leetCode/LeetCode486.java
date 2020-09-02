package leetCode;

/**
 * @author xianCan
 * @date 2020/9/1 22:13
 *
 * 486. 预测赢家（中等）
 *
 *  给定一个表示分数的非负整数数组。 玩家 1 从数组任意一端拿取一个分数，随后玩家 2 继续从剩余数组任意一端拿取分数，然后玩家 1 拿，…… 。每次一个玩家只能拿取一个分数，分数被拿取之后不再可取。直到没有剩余分数可取时游戏结束。最终获得分数总和最多的玩家获胜。

    给定一个表示分数的数组，预测玩家1是否会成为赢家。你可以假设每个玩家的玩法都会使他的分数最大化。

     

    示例 1：

    输入：[1, 5, 2]
    输出：False
    解释：一开始，玩家1可以从1和2中进行选择。
    如果他选择 2（或者 1 ），那么玩家 2 可以从 1（或者 2 ）和 5 中进行选择。如果玩家 2 选择了 5 ，那么玩家 1 则只剩下 1（或者 2 ）可选。
    所以，玩家 1 的最终分数为 1 + 2 = 3，而玩家 2 为 5 。
    因此，玩家 1 永远不会成为赢家，返回 False 。
    示例 2：

    输入：[1, 5, 233, 7]
    输出：True
    解释：玩家 1 一开始选择 1 。然后玩家 2 必须从 5 和 7 中进行选择。无论玩家 2 选择了哪个，玩家 1 都可以选择 233 。
    最终，玩家 1（234 分）比玩家 2（12 分）获得更多的分数，所以返回 True，表示玩家 1 可以成为赢家。
     

    提示：

    1 <= 给定的数组长度 <= 20.
    数组里所有分数都为非负数且不会大于 10000000 。
    如果最终两个玩家的分数相等，那么玩家 1 仍为赢家。

 */
public class LeetCode486 {

    /**
     * 暴力递归
     * 时间复杂度：O(2^N)
     * @param nums
     * @return
     */
    public boolean PredictTheWinner(int[] nums) {
        if (nums == null || nums.length < 2 || nums.length %2 ==0) return true;
        return total(nums, 0, nums.length - 1) >= 0;
    }

    private int total(int[] nums, int start, int end) {
        if (start == end) {
            return nums[start];
        }
        int scoreStart = nums[start] - total(nums, start + 1, end);
        int scoreEnd = nums[end] - total(nums, start, end - 1);
        return Math.max(scoreStart, scoreEnd);
    }

    /**
     * 动态规划
     * dp[i][j]的定义：当前玩家在数组[i:j]中先手，赢过对方的分数。
     * @param nums
     * @return
     */
    public boolean PredictTheWinner2(int[] nums){
        int length = nums.length;
        if (nums.length < 2 || nums.length %2 ==0) return true;
        int[][] dp = new int[length][length];
        for (int i=0; i<length; i++){
            dp[i][i] = nums[i];
        }

        for (int i=length-2; i>=0; i--){
            for (int j=i+1; j<length; j++){
                int tmp1 = nums[i] - dp[i+1][j];
                int tmp2 = nums[j] - dp[i][j-1];
                dp[i][j] = Math.max(tmp1, tmp2);
            }
        }
        return dp[0][length-1] >= 0;
    }

    /**
     * 动态规划
     * @param nums
     * @return
     */
    public boolean PredictTheWinner3(int[] nums){
        int length = nums.length;
        if (nums.length < 2 || nums.length %2 ==0) return true;
        Tube[][] dp = new Tube[length][length];
        for (int i = 0; i < length; i++)
            for (int j = i; j < length; j++)
                dp[i][j] = new Tube(0, 0);
        for (int i=0; i<length; i++){
            dp[i][i].fir = nums[i];
            dp[i][i].sec = 0;
        }

        for (int i=length-2; i>=0; i--){
            for (int j=i+1; j<length; j++){
                /**
                 * 解释：我作为先手，面对 piles[i...j] 时，有两种选择：
                 # 要么我选择最左边的那一堆石头，然后面对 piles[i+1...j]
                 # 但是此时轮到对方，相当于我变成了后手；
                 # 要么我选择最右边的那一堆石头，然后面对 piles[i...j-1]
                 # 但是此时轮到对方，相当于我变成了后手。
                 */
                int left = nums[i] + dp[i+1][j].sec;
                int right = nums[j] + dp[i][j-1].sec;

                /**
                 * 解释：我作为后手，要等先手先选择，有两种情况：
                 # 如果先手选择了最左边那堆，给我剩下了 piles[i+1...j]
                 # 此时轮到我，我变成了先手；
                 # 如果先手选择了最右边那堆，给我剩下了 piles[i...j-1]
                 # 此时轮到我，我变成了先手。
                 */
                if (left > right){
                    dp[i][j].fir = left;
                    dp[i][j].sec = dp[i+1][j].fir;
                } else {
                    dp[i][j].fir = right;
                    dp[i][j].sec = dp[i][j-1].fir;
                }
            }
        }
        return dp[0][length-1].fir >= dp[0][length-1].sec;
    }


    class Tube{
        private int fir;
        private int sec;

        Tube(int fir, int sec){
            this.fir = fir;
            this.sec = sec;
        }
    }

    public static void main(String[] args) {
        boolean b = new LeetCode486().PredictTheWinner3(new int[]{2, 4, 55, 6, 8});
        System.out.println(b);
    }
}
