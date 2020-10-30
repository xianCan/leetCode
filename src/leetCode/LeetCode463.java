package leetCode;

/**
 * @author xianCan
 * @date 2020/10/30 10:35
 *
 * 463. 岛屿的周长（简单）
 *
 *  给定一个包含 0 和 1 的二维网格地图，其中 1 表示陆地 0 表示水域。

    网格中的格子水平和垂直方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。

    岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。

     

    示例 :

    输入:
    [[0,1,0,0],
    [1,1,1,0],
    [0,1,0,0],
    [1,1,0,0]]

    输出: 16

    解释: 它的周长是下面图片中的 16 个黄色的边：

 */
public class LeetCode463 {

    public int islandPerimeter(int[][] grid) {
        int sum=0;
        int cover=0;
        for (int i=0; i<grid.length; i++){
            for (int j=0; j<grid[0].length; j++){
                if (grid[i][j] == 1){
                    sum++;
                    if (i-1 >=0 && grid[i-1][j] == 1) cover++;
                    if (j-1 >=0 && grid[i][j-1] == 1) cover++;
                }
            }
        }
        return sum*4 - cover*2;
    }

    /**
     * dfs
     * @param grid
     * @return
     */
    public int islandPerimeter2(int[][] grid) {
        for (int i=0; i<grid.length; i++){
            for (int j=0; j<grid[0].length; j++){
                if (grid[i][j] == 1){
                    return dfs(grid, i, j);
                }
            }
        }
        return 0;
    }

    int dfs(int[][] grid, int r, int c){
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length){
            return 1;
        }
        if (grid[r][c] == 0){
            return 1;
        }
        if (grid[r][c] != 1){
            return 0;
        }
        grid[r][c] = 2;
        return dfs(grid, r-1, c) +
                dfs(grid, r+1, c)+
                dfs(grid, r, c-1)+
                dfs(grid, r, c+1);
    }
}
