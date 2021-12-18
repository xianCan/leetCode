package leetCode;

/**
 * @author xianCan
 * @date 2021/12/18 17:55
 *
 * 130. 被围绕的区域（中等）
 *
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
     

    示例 1：


    输入：board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
    输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
    解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
    示例 2：

    输入：board = [["X"]]
    输出：[["X"]]
     

    提示：

    m == board.length
    n == board[i].length
    1 <= m, n <= 200
    board[i][j] 为 'X' 或 'O'

 */
public class LeetCode130 {

    public void solve(char[][] board) {
        int m = board.length, n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isEdge(board, i, j) && board[i][j] == 'O'){
                    dfs(board, m, n, i, j);
                }
            }
        }

        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
                if (board[i][j] == 'Y'){
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void dfs(char[][] board, int m, int n, int x, int y){
        if (x < 0 || y < 0 || x >= m || y >= n){
            return;
        }
        if (board[x][y] != 'O'){
            return;
        }
        board[x][y] = 'Y';
        dfs(board, m, n, x - 1, y);
        dfs(board, m, n, x + 1, y);
        dfs(board, m, n, x, y - 1);
        dfs(board, m, n, x, y + 1);
    }

    private boolean isEdge(char[][] board, int r, int c){
        return r == 0|| r == board.length - 1 || c == 0 || c == board[0].length - 1;
    }

    public static void main(String[] args) {
        new LeetCode130().solve(new char[][]{{'O', 'O'}, {'O', 'O'}});
        System.out.println();
    }
}
