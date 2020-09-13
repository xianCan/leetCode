package leetCode;

/**
 * @author xianCan
 * @date 2020/9/13 0:57
 *
 * 79. 单词搜索（中等）
 *
 *  给定一个二维网格和一个单词，找出该单词是否存在于网格中。

    单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

    示例:

    board =
    [
    ['A','B','C','E'],
    ['S','F','C','S'],
    ['A','D','E','E']
    ]

    给定 word = "ABCCED", 返回 true
    给定 word = "SEE", 返回 true
    给定 word = "ABCB", 返回 false
     

    提示：

    board 和 word 中只包含大写和小写英文字母。
    1 <= board.length <= 200
    1 <= board[i].length <= 200
    1 <= word.length <= 10^3

 */
public class LeetCode79 {

    private char[][] board;
    private boolean[][] visited;
    private String word;

    public boolean exist(char[][] board, String word) {
        if (board == null){
            return false;
        }
        this.visited = new boolean[board.length][board[0].length];
        this.board = board;
        this.word = word;

        for (int i=0; i<board.length; i++){
            for (int j=0; j<board[0].length; j++){
                if (dfs(i, j, 0)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int i, int j, int k) {
        if (board[i][j] != word.charAt(k)) {
            return false;
        } else if (k == word.length() - 1) {
            return true;
        }
        visited[i][j] = true;

        //上
        if (i-1 >= 0 && !visited[i-1][j] && dfs(i-1, j, k+1)){
            return true;
        }

        //下
        if (i+1 < board.length && !visited[i+1][j] && dfs(i+1, j, k+1)){
            return true;
        }

        //左
        if (j-1 >= 0 && !visited[i][j-1] && dfs(i, j-1, k+1)){
            return true;
        }

        //右
        if (j+1 < board[0].length && !visited[i][j+1] && dfs(i, j+1, k+1)){
            return true;
        }

        visited[i][j] = false;
        return false;
    }

}
