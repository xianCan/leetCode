package leetCode;

import java.util.List;

/**
 * @author xianCan
 * @date 2021/8/17 20:15
 *
 * 120. 三角形最小路径和（中等）
 *
 *  给定一个三角形 triangle ，找出自顶向下的最小路径和。

    每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。

     

    示例 1：

    输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
    输出：11
    解释：如下面简图所示：
    2
    3 4
    6 5 7
    4 1 8 3
    自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
    示例 2：

    输入：triangle = [[-10]]
    输出：-10
     

    提示：

    1 <= triangle.length <= 200
    triangle[0].length == 1
    triangle[i].length == triangle[i - 1].length + 1
    -104 <= triangle[i][j] <= 104
     

    进阶：

    你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题吗？

 */
public class LeetCode120 {

    public int minimumTotal(List<List<Integer>> triangle) {
        int row = triangle.size();
        int[][] dp = new int[row][row];
        for (int i = row - 1; i >= 0; i--){
            for (int j = 0; j < triangle.get(i).size(); j++){
                if (i == row - 1){
                    dp[i][j] = triangle.get(i).get(j);
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
                }
            }
        }

        return dp[0][0];
    }

    //空间复杂度：O(N)，滚动数组
    public int minimumTotal2(List<List<Integer>> triangle) {
        int row = triangle.size();
        int[][] dp = new int[2][row];
        for (int i = row - 1; i >= 0; i--){
            for (int j = 0; j < triangle.get(i).size(); j++){
                if (i == row - 1){
                    dp[i & 1][j] = triangle.get(i).get(j);
                } else {
                    dp[i & 1][j] = Math.min(dp[(i + 1) & 1][j], dp[(i + 1) & 1][j + 1]) + triangle.get(i).get(j);
                }
            }
        }

        return dp[0][0];
    }

}
