package leetCode;

/**
 * @author xianCan
 * @date 2021/8/15 10:32
 *
 * 576. 出界的路径数（中等）
 *
 *  给你一个大小为 m x n 的网格和一个球。球的起始坐标为 [startRow, startColumn] 。你可以将球移到在四个方向上相邻的单元格内（可以穿过网格边界到达网格之外）。你 最多 可以移动 maxMove 次球。

    给你五个整数 m、n、maxMove、startRow 以及 startColumn ，找出并返回可以将球移出边界的路径数量。因为答案可能非常大，返回对 109 + 7 取余 后的结果。

     

    示例 1：


    输入：m = 2, n = 2, maxMove = 2, startRow = 0, startColumn = 0
    输出：6
    示例 2：


    输入：m = 1, n = 3, maxMove = 3, startRow = 0, startColumn = 1
    输出：12
     

    提示：

    1 <= m, n <= 50
    0 <= maxMove <= 50
    0 <= startRow < m
    0 <= startColumn < n
 */
public class LeetCode576 {

    private int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    //递归，超时
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        int res = 0;
        if (maxMove == 0){
            return res;
        }
        for (int[] dir : directions){
            int nRow = startRow + dir[0];
            int nCol = startColumn + dir[1];
            if (nRow < 0 || nRow >= m || nCol < 0 || nCol >= n){
                res += 1;
            } else {
                res += findPaths(m, n, maxMove - 1, nRow, nCol);
            }
        }

        return res % 1000000007;
    }


    //动态规划的状态由移动次数、行和列决定，定义 dp[i][j][k] 表示球移动 i 次之后位于坐标 (j,k) 的路径数量。
    public int findPaths2(int m, int n, int maxMove, int startRow, int startColumn) {
        final int MOD = 1000000007;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int outCounts = 0;
        int[][][] dp = new int[maxMove + 1][m][n];
        dp[0][startRow][startColumn] = 1;
        for (int i = 0; i < maxMove; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < n; k++) {
                    int count = dp[i][j][k];
                    if (count > 0) {
                        for (int[] direction : directions) {
                            int j1 = j + direction[0], k1 = k + direction[1];
                            if (j1 >= 0 && j1 < m && k1 >= 0 && k1 < n) {
                                dp[i + 1][j1][k1] = (dp[i + 1][j1][k1] + count) % MOD;
                            } else {
                                outCounts = (outCounts + count) % MOD;
                            }
                        }
                    }
                }
            }
        }
        return outCounts;
    }

    //动态规划
    public int findPaths3(int m, int n, int maxMove, int startRow, int startColumn){
        int mod = 1000000007;
        int[][][] dp = new int[m][n][maxMove + 1];

        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (i == 0) add(dp, i, j, maxMove);
                if (j == 0) add(dp, i, j, maxMove);
                if (i == m - 1) add(dp, i, j, maxMove);
                if (j == n - 1) add(dp, i, j, maxMove);
            }
        }

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int step = 1; step <= maxMove; step++){
            for (int i = 0; i < m; i++){
                for (int j = 0; j < n; j++){
                    for (int[] dir : directions){
                        int row = i + dir[0], col = j + dir[1];
                        if (row >= 0 && row < m && col >= 0 && col < n){
                            dp[i][j][step] += dp[row][col][step - 1];
                            dp[i][j][step] %= mod;
                        }
                    }
                }
            }
        }
        return dp[startRow][startColumn][maxMove];
    }

    private void add(int[][][] dp, int i, int j, int maxMove){
        for (int step = 1; step <= maxMove; step++){
            dp[i][j][step]++;
        }
    }

    public static void main(String[] args) {
        int dfs = new LeetCode576().findPaths(2, 2, 2, 0, 0);
        System.out.println(dfs);
    }
}
