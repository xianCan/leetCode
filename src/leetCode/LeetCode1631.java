package leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xianCan
 * @date 2021/1/29 22:21
 *
 * 1631. 最小体力消耗路径（中等）
 *
 *  你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights ，其中 heights[row][col] 表示格子 (row, col) 的高度。一开始你在最左上角的格子 (0, 0) ，且你希望去最右下角的格子 (rows-1, columns-1) （注意下标从 0 开始编号）。你每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费 体力 最小的一条路径。

    一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。

    请你返回从左上角走到右下角的最小 体力消耗值 。

     

    示例 1：



    输入：heights = [[1,2,2],[3,8,2],[5,3,5]]
    输出：2
    解释：路径 [1,3,5,3,5] 连续格子的差值绝对值最大为 2 。
    这条路径比路径 [1,2,2,2,5] 更优，因为另一条路径差值最大值为 3 。
    示例 2：



    输入：heights = [[1,2,3],[3,8,4],[5,3,5]]
    输出：1
    解释：路径 [1,2,3,4,5] 的相邻格子差值绝对值最大为 1 ，比路径 [1,3,5,3,5] 更优。
    示例 3：


    输入：heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
    输出：0
    解释：上图所示路径不需要消耗任何体力。
     

    提示：

    rows == heights.length
    columns == heights[i].length
    1 <= rows, columns <= 100
    1 <= heights[i][j] <= 106

 */
public class LeetCode1631 {

    public int minimumEffortPath(int[][] heights) {
        int row = heights.length;
        int col = heights[0].length;

        List<int[]> edges = new ArrayList<>();

        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                int id = i * col + j;
                if (i > 0){
                    edges.add(new int[]{id - col, id, Math.abs(heights[i][j] - heights[i - 1][j])});
                }
                if (j > 0){
                    edges.add(new int[]{id - 1, id, Math.abs(heights[i][j] - heights[i][j - 1])});
                }
            }
        }

        edges.sort((o1, o2) -> Integer.compare(o1[2], o2[2]));

        UnionFind unionFind = new UnionFind(row * col);
        int ans = 0;
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1], v = edge[2];
            unionFind.union(x, y);
            if (unionFind.connected(0, row * col - 1)) {
                ans = v;
                break;
            }
        }
        return ans;
    }

    class UnionFind{
        private int[] parent;
        private int count;

        public UnionFind(int n){
            this.parent = new int[n + 1];
            this.count = n;
            for (int i = 1; i <= n; i++){
                parent[i] = i;
            }
        }

        public int getCount(){
            return this.count;
        }

        public int find(int x){
            if (x != parent[x]){
                parent[x] = find(parent[x]);
            }

            return parent[x];
        }

        public boolean union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY){
                return false;
            }

            parent[rootX] = rootY;
            count--;
            return true;
        }

        public boolean connected(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            return rootX == rootY;
        }
    }
}
