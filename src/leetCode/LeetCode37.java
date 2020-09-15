package leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xianCan
 * @date 2020/9/15 22:32
 *
 * 37. 解数独（困难）
 *
 *  编写一个程序，通过已填充的空格来解决数独问题。

    一个数独的解法需遵循如下规则：

    数字 1-9 在每一行只能出现一次。
    数字 1-9 在每一列只能出现一次。
    数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
    空白格用 '.' 表示。



    一个数独。



    答案被标成红色。

    Note:

    给定的数独序列只包含数字 1-9 和字符 '.' 。
    你可以假设给定的数独只有唯一解。
    给定数独永远是 9x9 形式的。

 */
public class LeetCode37 {

    private boolean[][] row = new boolean[9][9];
    private boolean[][] column = new boolean[9][9];
    private boolean[][][] block = new boolean[3][3][9];
    private boolean valid = false;
    private List<int[]> spaces = new ArrayList<>();

    public void solveSudoku(char[][] board) {
        for (int i=0; i<9; i++){
            for (int j=0; j<9; j++){
                if (board[i][j] == '.'){
                    spaces.add(new int[]{i, j});
                } else {
                    int num = board[i][j] - '0' - 1;
                    row[i][num] = column[j][num] = block[i/3][j/3][num] = true;
                }
            }
        }
        helper(board, 0);
    }

    private void helper(char[][] board, int start){
        if (start == spaces.size()){
            valid = true;
            return;
        }

        int[] space = spaces.get(start);
        int i=space[0], j=space[1];

        for (int num=0; num<9 && !valid; num++){
            if (!row[i][num] && !column[j][num] && !block[i/3][j/3][num]){
                row[i][num] = column[j][num] = block[i/3][j/3][num] = true;
                board[i][j] = (char) (num + '0' + 1);
                helper(board, start+1);
                row[i][num] = column[j][num] = block[i/3][j/3][num] = false;
            }
        }
    }
}
