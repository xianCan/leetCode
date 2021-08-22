package leetCode;

import java.util.List;

/**
 * @author xianCan
 * @date 2021/8/22 16:20
 *
 * 1301. 最大得分的路径数目（困难）
 *
 *  给你一个正方形字符数组 board ，你从数组最右下方的字符 'S' 出发。

    你的目标是到达数组最左上角的字符 'E' ，数组剩余的部分为数字字符 1, 2, ..., 9 或者障碍 'X'。在每一步移动中，你可以向上、向左或者左上方移动，可以移动的前提是到达的格子没有障碍。

    一条路径的 「得分」 定义为：路径上所有数字的和。

    请你返回一个列表，包含两个整数：第一个整数是 「得分」 的最大值，第二个整数是得到最大得分的方案数，请把结果对 10^9 + 7 取余。

    如果没有任何路径可以到达终点，请返回 [0, 0] 。

     

    示例 1：

    输入：board = ["E23","2X2","12S"]
    输出：[7,1]
    示例 2：

    输入：board = ["E12","1X1","21S"]
    输出：[4,2]
    示例 3：

    输入：board = ["E11","XXX","11S"]
    输出：[0,0]
     

    提示：

    2 <= board.length == board[i].length <= 100

 */
public class LeetCode1301 {

    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size(), mod = 1000000007;
        char[][] cs = new char[n][n];
        for (int i = 0; i < n; i++){
            cs[i] = board.get(i).toCharArray();
        }

        int[][] maxNumDp = new int[n][n];
        int[][] maxPathDp = new int[n][n];

        for (int i = n -1; i >= 0; i--){
            for (int j = n - 1; j >= 0; j--){

                if (i == n - 1 && j == n - 1){
                    maxNumDp[i][j] = 0;
                    maxPathDp[i][j] = 1;
                    continue;
                }

                if (cs[i][j] == 'X'){
                    maxNumDp[i][j] = Integer.MIN_VALUE;
                    continue;
                }

                int val = (i == 0 && j == 0) ? 0 : cs[i][j] - '0';
                int u = Integer.MIN_VALUE, v = 0;

                if (i < n - 1 && j < n - 1){
                    int cur = maxNumDp[i + 1][j + 1] + val;
                    int cnt = maxPathDp[i + 1][j + 1];
                    if (cur > u){
                        u = cur;
                        v = cnt;
                    } else if (cur == u && cur > 0){
                        v += cnt;
                    }
                }

                if (i < n - 1){
                    int cur = maxNumDp[i + 1][j] + val;
                    int cnt = maxPathDp[i + 1][j];
                    if (cur > u){
                        u = cur;
                        v = cnt;
                    } else if (cur == u && cur > 0){
                        v += cnt;
                    }
                }

                if (j < n - 1){
                    int cur = maxNumDp[i][j + 1] + val;
                    int cnt = maxPathDp[i][j + 1];
                    if (cur > u){
                        u = cur;
                        v = cnt;
                    } else if (cur == u && cur > 0){
                        v += cnt;
                    }
                }
                maxNumDp[i][j] = u < 0 ? Integer.MIN_VALUE : u;
                maxPathDp[i][j] = v % mod;
            }
        }

        int[] ans = new int[2];
        ans[0] = maxNumDp[0][0] < 0 ? 0 : maxNumDp[0][0];
        ans[1] = maxNumDp[0][0] < 0 ? 0 : maxPathDp[0][0];
        return ans;
    }
}
