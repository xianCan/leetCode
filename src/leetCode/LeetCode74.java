package leetCode;

/**
 * @author xianCan
 * @date 2021/3/30 0:02
 *
 * 74. 搜索二维矩阵（中等）
 *
 *  编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：

    每行中的整数从左到右按升序排列。
    每行的第一个整数大于前一行的最后一个整数。
     

    示例 1：


    输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
    输出：true
    示例 2：


    输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
    输出：false
     

    提示：

    m == matrix.length
    n == matrix[i].length
    1 <= m, n <= 100
    -104 <= matrix[i][j], target <= 104

 */
public class LeetCode74 {

    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length, col = matrix[0].length;
        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                if (matrix[i][j] == target){
                    return true;
                } else if (i + 1 < row && matrix[i + 1][j] <= target){
                    break;
                } else if (j + 1 < col && matrix[i][j + 1] > target){
                    return false;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        boolean b = new LeetCode74().searchMatrix(new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,50}}, 10);
        System.out.println(b);
    }
}
