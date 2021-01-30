package leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xianCan
 * @date 2021/1/30 17:47
 *
 * 778. 水位上升的泳池中游泳（困难）
 *
 *  在一个 N x N 的坐标方格 grid 中，每一个方格的值 grid[i][j] 表示在位置 (i,j) 的平台高度。

    现在开始下雨了。当时间为 t 时，此时雨水导致水池中任意位置的水位为 t 。你可以从一个平台游向四周相邻的任意一个平台，但是前提是此时水位必须同时淹没这两个平台。假定你可以瞬间移动无限距离，也就是默认在方格内部游动是不耗时的。当然，在你游泳的时候你必须待在坐标方格里面。

    你从坐标方格的左上平台 (0，0) 出发。最少耗时多久你才能到达坐标方格的右下平台 (N-1, N-1)？

     

    示例 1:

    输入: [[0,2],[1,3]]
    输出: 3
    解释:
    时间为0时，你位于坐标方格的位置为 (0, 0)。
    此时你不能游向任意方向，因为四个相邻方向平台的高度都大于当前时间为 0 时的水位。

    等时间到达 3 时，你才可以游向平台 (1, 1). 因为此时的水位是 3，坐标方格中的平台没有比水位 3 更高的，所以你可以游向坐标方格中的任意位置
    示例2:

    输入: [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
    输出: 16
    解释:
    0  1  2  3  4
    24 23 22 21  5
    12 13 14 15 16
    11 17 18 19 20
    10  9  8  7  6

    最终的路线用加粗进行了标记。
    我们必须等到时间为 16，此时才能保证平台 (0, 0) 和 (4, 4) 是连通的
     

    提示:

    2 <= N <= 50.
    grid[i][j] 是 [0, ..., N*N - 1] 的排列。

 */
public class LeetCode778 {

    public int swimInWater(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;

        List<int[]> edges = new ArrayList<>();

        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                int id = i * col + j;
                //上一行
                if (i > 0){
                    edges.add(new int[]{id - col, id, Math.max(grid[i-1][j], grid[i][j])});
                }
                //前一列
                if (j > 0){
                    edges.add(new int[]{id - 1, id, Math.max(grid[i][j], grid[i][j-1])});
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

    public static void main(String[] args) {
        //[[0,2],[1,3]]
        int i = new LeetCode778().swimInWater(new int[][]{{0,2},{1,3}});
        //int i = new LeetCode778().swimInWater(new int[][]{{0,1,2,3,4},{24,23,22,21,5},{12,13,14,15,16},{11,17,18,19,20},{10,9,8,7,6}});
        System.out.println(i);
    }

    class UnionFind{
        private int[] parent;

        public UnionFind(int n){
            this.parent = new int[n + 1];
            for (int i = 1; i <= n; i++){
                parent[i] = i;
            }
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
            return true;
        }

        public boolean connected(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            return rootX == rootY;
        }
    }
}
