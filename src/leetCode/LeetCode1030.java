package leetCode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author xianCan
 * @date 2020/11/17 19:43
 *
 * 1030. 距离顺序排列矩阵单元格（简单）
 *
 *  给出 R 行 C 列的矩阵，其中的单元格的整数坐标为 (r, c)，满足 0 <= r < R 且 0 <= c < C。

    另外，我们在该矩阵中给出了一个坐标为 (r0, c0) 的单元格。

    返回矩阵中的所有单元格的坐标，并按到 (r0, c0) 的距离从最小到最大的顺序排，其中，两单元格(r1, c1) 和 (r2, c2) 之间的距离是曼哈顿距离，|r1 - r2| + |c1 - c2|。（你可以按任何满足此条件的顺序返回答案。）

     

    示例 1：

    输入：R = 1, C = 2, r0 = 0, c0 = 0
    输出：[[0,0],[0,1]]
    解释：从 (r0, c0) 到其他单元格的距离为：[0,1]
    示例 2：

    输入：R = 2, C = 2, r0 = 0, c0 = 1
    输出：[[0,1],[0,0],[1,1],[1,0]]
    解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2]
    [[0,1],[1,1],[0,0],[1,0]] 也会被视作正确答案。
    示例 3：

    输入：R = 2, C = 3, r0 = 1, c0 = 2
    输出：[[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
    解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2,2,3]
    其他满足题目要求的答案也会被视为正确，例如 [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]]。
     

    提示：

    1 <= R <= 100
    1 <= C <= 100
    0 <= r0 < R
    0 <= c0 < C

 */
public class LeetCode1030 {

    /**
     * 直接排序
     */
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int[][] ans = new int[R*C][2];
        int idx=0;
        for (int i=0; i<R; i++){
            for (int j=0; j<C; j++){
                ans[idx++] = new int[]{i, j};
            }
        }

        Arrays.sort(ans, (o1, o2) -> {
            int dis1 = getDistance(o1[0], o1[1], r0, c0);
            int dis2 = getDistance(o2[0], o2[1], r0, c0);
            return Integer.compare(dis1, dis2);
        });

        return ans;
    }

    private int getDistance(int r, int c, int r0, int c0){
        return Math.abs(r-r0) + Math.abs(c-c0);
    }

    /**
     * 广度优先遍历
     * @return
     */
    public int[][] allCellsDistOrder2(int R, int C, int r0, int c0) {
        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int[][] ans = new int[R*C][2];
        boolean[][] used = new boolean[R][C];

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{r0, c0});
        used[r0][c0] = true;
        int idx = 0;

        while (!queue.isEmpty()){
            int size = queue.size();
            while (size > 0){
                int[] ints = queue.poll();
                ans[idx++] = ints;
                for (int[] dir : dirs){
                    int x = ints[0] + dir[0];
                    int y = ints[1] + dir[1];
                    if (x >= 0 && x < R && y >= 0 && y < C && !used[x][y]){
                        queue.offer(new int[]{x, y});
                        used[x][y] = true;
                    }
                }
                size--;
            }
        }

        return ans;
    }

    /**
     * 方法三：桶排序
     */

    public static void main(String[] args) {
        int[][] ints = new LeetCode1030().allCellsDistOrder2(2, 3, 1, 2);
        System.out.println(ints);
    }
}
