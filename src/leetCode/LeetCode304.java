package leetCode;

/**
 * @author xianCan
 * @date 2021/3/2 21:20
 *
 * 304. 二维区域和检索 - 矩阵不可变（中等）
 *
 * 给定一个二维矩阵，计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2) 。

    上图子矩阵左上角 (row1, col1) = (2, 1) ，右下角(row2, col2) = (4, 3)，该子矩形内元素的总和为 8。

     

    示例：

    给定 matrix = [
    [3, 0, 1, 4, 2],
    [5, 6, 3, 2, 1],
    [1, 2, 0, 1, 5],
    [4, 1, 0, 1, 7],
    [1, 0, 3, 0, 5]
    ]

    sumRegion(2, 1, 4, 3) -> 8
    sumRegion(1, 1, 2, 2) -> 11
    sumRegion(1, 2, 2, 4) -> 12
     

    提示：

    你可以假设矩阵不可变。
    会多次调用 sumRegion 方法。
    你可以假设 row1 ≤ row2 且 col1 ≤ col2 。

 */
public class LeetCode304 {

    class NumMatrix {

        private int[][] sums;

        public NumMatrix(int[][] matrix) {
            int row = matrix.length;
            if (row > 0){
                int col = matrix[0].length;
                this.sums = new int[row + 1][col + 1];
                for (int i = 1; i <= row; i++){
                    for (int j = 1; j <= col; j++){
                        sums[i][j] = sums[i - 1][j] + sums[i][j - 1] + matrix[i - 1][j - 1] - sums[i - 1][j - 1];
                    }
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return sums[row2 + 1][col2 + 1] + sums[row1][col1] - sums[row1][col2 + 1] - sums[row2 + 1][col1];
        }
    }

}
