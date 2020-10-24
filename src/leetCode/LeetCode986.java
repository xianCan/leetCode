package leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xianCan
 * @date 2020/10/22 23:29
 *
 * 986. 区间列表的交集（中等）
 *
 *  给定两个由一些 闭区间 组成的列表，每个区间列表都是成对不相交的，并且已经排序。

    返回这两个区间列表的交集。

    （形式上，闭区间 [a, b]（其中 a <= b）表示实数 x 的集合，而 a <= x <= b。两个闭区间的交集是一组实数，要么为空集，要么为闭区间。例如，[1, 3] 和 [2, 4] 的交集为 [2, 3]。）

    示例：

    输入：A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
    输出：[[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
     

    提示：

    0 <= A.length < 1000
    0 <= B.length < 1000
    0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9

 */
public class LeetCode986 {

    /**
     * 归并区间
     * @param A
     * @param B
     * @return
     */
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        if (A == null || B == null || A.length == 0 || B.length == 0) return new int[0][2];

        int len1=A.length, len2=B.length, i=0, j=0;

        List<int[]> ans = new ArrayList<>();
        while (i < len1 && j < len2){
            int left = Math.max(A[i][0], B[j][0]);
            int right = Math.min(A[i][1], B[j][1]);
            if (left <= right){
                ans.add(new int[]{left, right});
            }

            if (A[i][1] < B[j][1]){
                i++;
            } else {
                j++;
            }
        }

        return ans.toArray(new int[ans.size()][]);
    }

    public static void main(String[] args) {
        int[][] A = new int[][]{{5,10}};
        int[][] B = new int[][]{{3,10}};
        /*int[][] A = new int[][]{{0,2},{5,10},{13,23},{24,25}};
        int[][] B = new int[][]{{1,5},{8,12},{15,24},{25,26}};*/
        int[][] ints = new LeetCode986().intervalIntersection(A, B);
        System.out.println(ints);
    }
}
