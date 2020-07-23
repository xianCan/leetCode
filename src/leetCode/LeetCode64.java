package leetCode;

/**
 * @author xianCan
 * @date 2020/7/23 20:54
 *
 * 64. 最小路径和
 *
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。

    说明：每次只能向下或者向右移动一步。

    示例:

    输入:
    [
      [1,3,1],
    [1,5,1],
    [4,2,1]
    ]
    输出: 7
    解释: 因为路径 1→3→1→1→1 的总和最小。

 */
public class LeetCode64 {

    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        int rows = grid.length, columns = grid[0].length;
        int[][] dp = new int[rows][columns];
        dp[0][0] = grid[0][0];

        for (int i = 1; i < rows; i++){
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }

        for (int j = 1; j < columns; j++){
            dp[0][j] = dp[0][j-1] + grid[0][j];
        }

        for (int i=1; i< rows; i++){
            for (int j=1; j<columns; j++){
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }

        return dp[rows-1][columns-1];
    }

    public static void main(String[] args) {
        int[][] a = new int[3][3];
        a[0][0] = 1;
        a[0][1] = 3;
        a[0][2] = 1;
        a[1][0] = 1;
        a[1][1] = 5;
        a[1][2] = 1;
        a[2][0] = 4;
        a[2][1] = 2;
        a[2][2] = 1;
        int i = new LeetCode64().minPathSum(a);
        System.out.println(i);
    }
}
