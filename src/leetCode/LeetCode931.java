package leetCode;

/**
 * @author xianCan
 * @date 2021/8/17 20:23
 *
 * 931. 下降路径最小和（中等）
 *
 *  给你一个 n x n 的 方形 整数数组 matrix ，请你找出并返回通过 matrix 的下降路径 的 最小和 。

    下降路径 可以从第一行中的任何元素开始，并从每一行中选择一个元素。在下一行选择的元素和当前行所选元素最多相隔一列（即位于正下方或者沿对角线向左或者向右的第一个元素）。具体来说，位置 (row, col) 的下一个元素应当是 (row + 1, col - 1)、(row + 1, col) 或者 (row + 1, col + 1) 。

     

    示例 1：

    输入：matrix = [[2,1,3],[6,5,4],[7,8,9]]
    输出：13
    解释：下面是两条和最小的下降路径，用加粗+斜体标注：
    [[2,1,3],      [[2,1,3],
    [6,5,4],       [6,5,4],
    [7,8,9]]       [7,8,9]]
    示例 2：

    输入：matrix = [[-19,57],[-40,-5]]
    输出：-59
    解释：下面是一条和最小的下降路径，用加粗+斜体标注：
    [[-19,57],
    [-40,-5]]
    示例 3：

    输入：matrix = [[-48]]
    输出：-48
     

    提示：

    n == matrix.length
    n == matrix[i].length
    1 <= n <= 100
    -100 <= matrix[i][j] <= 100

 */
public class LeetCode931 {

    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][n];

        for (int i = n - 1; i >= 0; i--){
            for (int j = 0; j < n; j++){
                if (i == n - 1){
                    dp[i][j] = matrix[i][j];
                } else {
                    int l = j == 0 ? Integer.MAX_VALUE : dp[i + 1][j - 1];
                    int m = dp[i + 1][j];
                    int r = j == n - 1 ? Integer.MAX_VALUE : dp[i + 1][j + 1];
                    dp[i][j] = Math.min(l, Math.min(m, r)) + matrix[i][j];
                }
            }
        }

        int res = Integer.MAX_VALUE;
        for (int num : dp[0]){
            res = Math.min(res, num);
        }
        return res;
    }

    //空间复杂度：O(N)，滚动数组
    public int minFallingPathSum2(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[2][n];

        for (int i = n - 1; i >= 0; i--){
            for (int j = 0; j < n; j++){
                if (i == n - 1){
                    dp[i & 1][j] = matrix[i][j];
                } else {
                    int l = j == 0 ? Integer.MAX_VALUE : dp[(i + 1) & 1][j - 1];
                    int m = dp[(i + 1) & 1][j];
                    int r = j == n - 1 ? Integer.MAX_VALUE : dp[(i + 1) & 1][j + 1];
                    dp[i & 1][j] = Math.min(l, Math.min(m, r)) + matrix[i][j];
                }
            }
        }

        int res = Integer.MAX_VALUE;
        for (int num : dp[0]){
            res = Math.min(res, num);
        }
        return res;
    }
}
