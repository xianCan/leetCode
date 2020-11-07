package leetCode;

/**
 * @author xianCan
 * @date 2020/11/7 15:41
 *
 * 59. 螺旋矩阵 II（中等）
 *
 *  给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。

    示例:

    输入: 3
    输出:
    [
    [ 1, 2, 3 ],
    [ 8, 9, 4 ],
    [ 7, 6, 5 ]
    ]

 */
public class LeetCode59 {

    private int num=0;
    private int[][] ans;

    public int[][] generateMatrix(int n) {
        if (n <= 0) return null;
        ans = new int[n][n];
        for (int i=0, len=n-1; i<n/2; i++, len--){
            helper(i, i, i, len);
        }
        if (n%2==1){
            ans[n/2][n/2] = n*n;
        }
        return ans;
    }

    private void helper(int i, int j, int start, int len){
        while (j < len){
            ans[i][j++] = ++num;
        }

        while (i < len){
            ans[i++][j] = ++num;
        }

        while (j > start){
            ans[i][j--] = ++num;
        }

        while (i > start){
            ans[i--][j] = ++num;
        }
    }

    public static void main(String[] args) {
        int[][] ints = new LeetCode59().generateMatrix(5);
        System.out.println(ints);
    }
}
