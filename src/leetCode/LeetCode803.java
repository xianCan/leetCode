package leetCode;

/**
 * @author xianCan
 * @date 2021/1/16 20:00
 *
 * 803. 打砖块（困难）
 *
 *  有一个 m x n 的二元网格，其中 1 表示砖块，0 表示空白。砖块 稳定（不会掉落）的前提是：

    一块砖直接连接到网格的顶部，或者
    至少有一块相邻（4 个方向之一）砖块 稳定 不会掉落时
    给你一个数组 hits ，这是需要依次消除砖块的位置。每当消除 hits[i] = (rowi, coli) 位置上的砖块时，对应位置的砖块（若存在）会消失，然后其他的砖块可能因为这一消除操作而掉落。一旦砖块掉落，它会立即从网格中消失（即，它不会落在其他稳定的砖块上）。

    返回一个数组 result ，其中 result[i] 表示第 i 次消除操作对应掉落的砖块数目。

    注意，消除可能指向是没有砖块的空白位置，如果发生这种情况，则没有砖块掉落。

    示例 1：

    输入：grid = [[1,0,0,0],[1,1,1,0]], hits = [[1,0]]
    输出：[2]
    解释：
    网格开始为：
    [[1,0,0,0]，
    [1,1,1,0]]
    消除 (1,0) 处加粗的砖块，得到网格：
    [[1,0,0,0]
    [0,1,1,0]]
    两个加粗的砖不再稳定，因为它们不再与顶部相连，也不再与另一个稳定的砖相邻，因此它们将掉落。得到网格：
    [[1,0,0,0],
    [0,0,0,0]]
    因此，结果为 [2] 。
    示例 2：

    输入：grid = [[1,0,0,0],[1,1,0,0]], hits = [[1,1],[1,0]]
    输出：[0,0]
    解释：
    网格开始为：
    [[1,0,0,0],
    [1,1,0,0]]
    消除 (1,1) 处加粗的砖块，得到网格：
    [[1,0,0,0],
    [1,0,0,0]]
    剩下的砖都很稳定，所以不会掉落。网格保持不变：
    [[1,0,0,0],
    [1,0,0,0]]
    接下来消除 (1,0) 处加粗的砖块，得到网格：
    [[1,0,0,0],
    [0,0,0,0]]
    剩下的砖块仍然是稳定的，所以不会有砖块掉落。
    因此，结果为 [0,0] 。
     

    提示：

    m == grid.length
    n == grid[i].length
    1 <= m, n <= 200
    grid[i][j] 为 0 或 1
    1 <= hits.length <= 4 * 104
    hits[i].length == 2
    0 <= xi <= m - 1
    0 <= yi <= n - 1
    所有 (xi, yi) 互不相同

 */
public class LeetCode803 {

    /**
     * dfs
     *
     * 一、将二元网格中要消除位置的砖块置为-1，用于逆向添加；
       二、深度优先遍历，将与顶部连通的砖块置2；
       三、逆序添加要消除的砖块
           1、如果该砖块相邻位置有砖块与顶部连通或者是顶部砖块，则深度优先遍历该砖块，得到与其相邻的砖块数，即为消除该砖块将掉落的砖块数目；
           2、如果该砖块相邻位置没有砖块与顶部连通，也不是顶部砖块，则消除不会掉落砖块。

     * @param grid
     * @param hits
     * @return
     */
    public int[] hitBricks(int[][] grid, int[][] hits) {
        int len = hits.length;
        for (int[] hit : hits){
            grid[hit[0]][hit[1]]--;
        }

        for (int i = 0; i < grid[0].length; i++){
            dfs(0, i, grid);
        }


        int[] ans = new int[len];
        for (int i = hits.length - 1; i >= 0; i--){
            grid[hits[i][0]][hits[i][1]]++;
            if (grid[hits[i][0]][hits[i][1]] == 1 && isConnectTop(hits[i][0], hits[i][1], grid)) {
                ans[i] = dfs(hits[i][0], hits[i][1], grid) - 1;
            }
        }

        return ans;
    }

    private int dfs(int x, int y, int[][] grid){
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] != 1){
            return 0;
        }

        grid[x][y] = 2;
        return dfs(x - 1, y, grid) + dfs(x + 1, y, grid) + dfs(x, y - 1, grid) + dfs(x, y + 1, grid) + 1;
    }

    private int[] dx = {0, 0, 1, -1};
    private int[] dy = {1, -1, 0, 0};
    private boolean isConnectTop(int x, int y, int[][] grid){
        if (x == 0){
            return true;
        }

        for (int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= grid.length || ny < 0 || ny >= grid[0].length || grid[nx][ny] != 2){
                continue;
            }
            return true;
        }

        return false;
    }

    public static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    /**
     * 并查集
     * @param grid
     * @param hits
     * @return
     */
    public int[] hitBricks2(int[][] grid, int[][] hits) {
        // 第 1 步：把 grid 中的砖头全部击碎
        for (int[] hit : hits){
            grid[hit[0]][hit[1]]--;
        }

        //第 2 步：建图，把砖块和砖块的连接关系输入并查集，size 表示二维网格的大小，也表示虚拟的「屋顶」在并查集中的编号
        int rows = grid.length;
        int cols = grid[0].length;
        int size = rows * cols;
        UnionFind unionFind = new UnionFind(size + 1);

        // 将下标为 0 的这一行的砖块与「屋顶」相连
        for (int j = 0; j < cols; j++){
            if (grid[0][j] == 1){
                unionFind.union(j, size);
            }
        }

        // 其余网格，如果是砖块向上、向左看一下，如果也是砖块，在并查集中进行合并
        for (int i = 1; i < rows; i++){
            for (int j = 0; j < cols; j++){
                if (grid[i][j] == 1){
                    if (grid[i - 1][j] == 1){
                        unionFind.union((i-1) * cols + j, i * cols + j);
                    }
                    if (j > 0 && grid[i][j - 1] == 1){
                        unionFind.union(i * cols + j -1, i * cols + j);
                    }
                }
            }
        }

        // 第 3 步：按照 hits 的逆序，在 copy 中补回砖块，把每一次因为补回砖块而与屋顶相连的砖块的增量记录到 res 数组中
        int[] ans = new int[hits.length];
        for (int i = hits.length - 1; i >= 0; i--){
            int x = hits[i][0];
            int y = hits[i][1];

            if (grid[x][y] == -1){
                continue;
            }

            int origin = unionFind.getSize(size);

            if (x == 0){
                unionFind.union(y, size);
            }

            for (int[] direction : DIRECTIONS){
                int newX = x + direction[0];
                int newY = y + direction[1];

                if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && grid[newX][newY] == 1){
                    unionFind.union(x * cols + y, newX * cols + newY);
                }
            }

            // 补回之后与屋顶相连的砖块数
            int current = unionFind.getSize(size);
            // 减去的 1 是逆向补回的砖块（正向移除的砖块），与 0 比较大小，是因为存在一种情况，添加当前砖块，不会使得与屋顶连接的砖块数更多
            ans[i] = Math.max(0, current - origin - 1);

            // 真正补上这个砖块
            grid[x][y] = 1;
        }


        return ans;
    }

    private class UnionFind{
        /**
         * 当前结点的父亲结点
         */
        private int[] parent;
        /**
         * 以当前结点为根结点的子树的结点总数
         */
        private int[] size;

        public UnionFind(int n){
            this.parent = new int[n];
            this.size = new int[n];
            for (int i = 0; i < n; i++){
                parent[i] = i;
                size[i] = 1;
            }
        }

        /**
         * 路径压缩
         * @param x
         * @return
         */
        public int find(int x){
            if (x != parent[x]){
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY){
                return;
            }

            parent[rootX] = rootY;
        }

        public int getSize(int x){
            return size[find(x)];
        }
    }
}
