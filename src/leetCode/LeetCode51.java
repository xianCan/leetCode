package leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xianCan
 * @date 2020/7/2 21:21
 *
 *
 *  n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。

    给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。

    每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。

    示例:

    输入: 4
    输出: [
    [".Q..",  // 解法 1
    "...Q",
    "Q...",
    "..Q."],

    ["..Q.",  // 解法 2
    "Q...",
    "...Q",
    ".Q.."]
    ]
    解释: 4 皇后问题存在两个不同的解法。
 
    提示：皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。
          当然，她横、竖、斜都可走一到七步，可进可退。
 */
public class LeetCode51 {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        if (n < 0){
            return result;
        }
        char[][] first = new char[n][n];
        for (int i=0; i<n; i++){
            for (int j=0; j<n; j++){
                first[i][j] = '.';
            }
        }
        recursive(first, 0, result);
        return result;
    }

    private void recursive(char[][] first, int row, List<List<String>> result){
        if (row == first.length){
            List<String> res = new ArrayList<>();
            for (char[] chars : first){
                String str = "";
                for (char c : chars){
                    str = str + c;
                }
                res.add(str);
            }
            result.add(res);
            //结束条件
            return;
        }

        for (int col=0; col<first[0].length; col++){
            if (canSolve(first, row, col)){
                first[row][col] = 'Q';
                recursive(first, row+1, result);
                first[row][col] = '.';
            }

        }
    }

    private boolean canSolve(char[][] first, int row, int col){
        //判断左斜上方
        for (int i=row-1, j=col-1; i >= 0 && j >= 0; i--, j--){
            if (first[i][j] == 'Q'){
                return false;
            }
        }
        //判断右斜上方
        for (int i=row-1, j=col+1; i >=0 && j < first[0].length; i--, j++){
            if (first[i][j] == 'Q'){
                return false;
            }
        }
        //判断正上方
        for (int i=row-1; i >= 0; i--){
            if (first[i][col] == 'Q'){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        List<List<String>> lists = new LeetCode51().solveNQueens(4);
        System.out.println(lists);
    }
}
