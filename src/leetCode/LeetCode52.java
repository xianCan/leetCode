package leetCode;

/**
 * @author xianCan
 * @date 2020/10/17 11:24
 *
 * 52. N皇后 II（困难）
 *
 *  n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。

    上图为 8 皇后问题的一种解法。

    给定一个整数 n，返回 n 皇后不同的解决方案的数量。

    示例:

    输入: 4
    输出: 2
    解释: 4 皇后问题存在如下两个不同的解法。
    [
     [".Q..",  // 解法 1
      "...Q",
      "Q...",
      "..Q."],

     ["..Q.",  // 解法 2
      "Q...",
      "...Q",
      ".Q.."]
    ]
     

    提示：

    皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一或 N-1 步，可进可退。（引用自 百度百科 - 皇后 ）
    通过次数38,628提交次数47,686

 */
public class LeetCode52 {

    private int[][] tmp;
    private int ans=0;

    public int totalNQueens(int n) {
        tmp = new int[n][n];
        recursive(0);
        return ans;
    }

    private void recursive(int row){
        if (row == tmp.length){
            ans++;
            return;
        }
        for (int col=0; col<tmp.length; col++){
            if (predicate(row, col)){
                tmp[row][col] = 1;
                recursive(row+1);
                tmp[row][col] = 0;
            }
        }
    }

    private boolean predicate(int row, int col){
        int n = tmp.length;
        for (int i=row; i>=0; i--){
            if (tmp[i][col] == 1){
                return false;
            }
        }

        for (int i=row, j=col; i>=0 && j>=0; i--, j--){
            if (tmp[i][j] == 1){
                return false;
            }
        }

        for (int i=row, j=col; i>=0 && j<n; i--, j++){
            if (tmp[i][j] == 1){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int i = new LeetCode52().totalNQueens(8);
        System.out.println(i);
    }
}
