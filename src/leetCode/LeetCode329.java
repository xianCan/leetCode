package leetCode;

/**
 * @author xianCan
 * @date 2020/7/26 11:02
 *
 * 329. 矩阵中的最长递增路径（困难）
 *
 *  给定一个整数矩阵，找出最长递增路径的长度。

    对于每个单元格，你可以往上，下，左，右四个方向移动。 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。

    示例 1:

    输入: nums =
    [
    [9,9,4],
    [6,6,8],
    [2,1,1]
    ]
    输出: 4
    解释: 最长递增路径为 [1, 2, 6, 9]。
    示例 2:

    输入: nums =
    [
    [3,4,5],
    [3,2,6],
    [2,2,1]
    ]
    输出: 4
    解释: 最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。

 */
public class LeetCode329 {

    private int[][] steps = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
    private int row, column;

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length==0 || matrix[0].length==0)
            return 0;
        row = matrix.length;
        column = matrix[0].length;
        Integer[][] memo = new Integer[row][column];

        int res = 0;
        for (int i=0; i < row; i++){
            for (int j=0; j < column; j++){
                res = Math.max(res, dfs(matrix, i, j, memo));
            }
        }
        return res;
    }

    private int dfs(int[][] matrix, int i, int j, Integer[][] memo){
        if (memo[i][j] != null){
            return memo[i][j];
        }
        int res=1;//初始化为1，因为从当前字母开始算（包含当前字母），最短路径为1
        for (int[] step : steps){
            int newRow = i  + step[0], newColumn = j + step[1];
            if (newRow >= 0 && newRow < row && newColumn >= 0 && newColumn < column && matrix[newRow][newColumn] > matrix[i][j]){
                res = Math.max(res, dfs(matrix, newRow, newColumn, memo) + 1);
            }
        }
        memo[i][j] = res;
        return res;
    }

    public static void main(String[] args) {
        int i = new LeetCode329().longestIncreasingPath(new int[][]{{3, 4, 5}, {3, 2, 6}, {2, 2, 1}});
        System.out.println(i);
    }
}
