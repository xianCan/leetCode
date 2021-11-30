package leetCode;

/**
 * @author xianCan
 * @date 2021/11/30 21:31
 *
 * 1314. 矩阵区域和（中等）
 *
 *  给你一个 m x n 的矩阵 mat 和一个整数 k ，请你返回一个矩阵 answer ，其中每个 answer[i][j] 是所有满足下述条件的元素 mat[r][c] 的和： 

    i - k <= r <= i + k,
    j - k <= c <= j + k 且
    (r, c) 在矩阵内。
     

    示例 1：

    输入：mat = [[1,2,3],[4,5,6],[7,8,9]], k = 1
    输出：[[12,21,16],[27,45,33],[24,39,28]]
    示例 2：

    输入：mat = [[1,2,3],[4,5,6],[7,8,9]], k = 2
    输出：[[45,45,45],[45,45,45],[45,45,45]]
     

    提示：

    m == mat.length
    n == mat[i].length
    1 <= m, n, k <= 100
    1 <= mat[i][j] <= 100

 */
public class LeetCode1314 {

    //1 2 3
    //4 5 6
    //7 8 9
    public int[][] matrixBlockSum(int[][] mat, int K) {
        int n = mat.length,m = mat[0].length;
        int[][] dp = new int[n + 1][m + 1];
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1] + mat[i-1][j-1] - dp[i-1][j-1];
            }
        }

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                int x1 = i - K, y1 = j - K, x2 = i + K, y2 = j + K;
                if(x1 < 1) {
                    x1 = 1;
                }
                if(y1 < 1) {
                    y1 = 1;
                }
                if(x2 > n) {
                    x2 = n;
                }
                if(y2 > m) {
                    y2 = m;
                }
                mat[i - 1][j - 1] = dp[x2][y2] + dp[x1 - 1][y1 - 1] - dp[x1 - 1][y2] - dp[x2][y1 - 1];
            }
        }
        return mat;
    }

    public static void main(String[] args) {
        int[][] ints = new LeetCode1314().matrixBlockSum(new int[][]{{76,4,73},{21,8,56},{4,56,61},{70,32,38},{31,94,67}}, 1);
        System.out.println();
    }
}
