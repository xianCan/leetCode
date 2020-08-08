package leetCode;

/**
 * @author xianCan
 * @date 2020/8/8 10:30
 *
 * 0 1 背包问题
 */
public class Package_0_1 {

    /**
     * 自底向上
     * @param W
     * @param N
     * @param wt
     * @param val
     * @return
     */
    public int maxValue(int W, int N, int[] wt, int[] val){
        int[][] dp = new int[N+1][W+1];

        for (int i=1; i<=N; i++){
            for (int w=1; w<=W; w++){
                if (w-wt[i-1] < 0){
                    dp[i][w] = dp[i-1][w];
                } else {
                    dp[i][w] = Math.max(dp[i-1][w], dp[i-1][w-wt[i-1]] + val[i-1]);
                }
            }
        }
        return dp[N][W];
    }

    /**
     * 自顶向下
     * @param W
     * @param N
     * @param wt
     * @param val
     * @return
     */
    public int maxValue2(int W, int N, int[] wt, int[] val){
        Integer[][] dp = new Integer[N+1][W+1];
        return recursive(W, N, wt, val, dp);
    }

    public int recursive(int W, int N, int[] wt, int[] val, Integer[][] dp){
        //base case
        if (W==0 || N==0)return 0;
        if (dp[N][W] != null)return dp[N][W];

        int res;
        if (W - wt[N-1] < 0){
            res = recursive(W, N-1, wt, val, dp);
        } else {
            res = Math.max(recursive(W, N-1, wt, val, dp), val[N-1]+ recursive(W - wt[N-1], N-1, wt, val, dp));
        }
        dp[N][W] = res;
        return res;
    }
}
