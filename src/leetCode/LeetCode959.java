package leetCode;

/**
 * @author xianCan
 * @date 2021/1/25 21:16
 *
 * 959. 由斜杠划分区域（中等）
 *
 *  在由 1 x 1 方格组成的 N x N 网格 grid 中，每个 1 x 1 方块由 /、\ 或空格构成。这些字符会将方块划分为一些共边的区域。

    （请注意，反斜杠字符是转义的，因此 \ 用 "\\" 表示。）。

    返回区域的数目。

     

    示例 1：

    输入：
    [
      " /",
      "/ "
    ]
    输出：2
    解释：2x2 网格如下：

    示例 2：

    输入：
    [
      " /",
      "  "
    ]
    输出：1
    解释：2x2 网格如下：

    示例 3：

    输入：
    [
      "\\/",
      "/\\"
    ]
    输出：4
    解释：（回想一下，因为 \ 字符是转义的，所以 "\\/" 表示 \/，而 "/\\" 表示 /\。）
    2x2 网格如下：

    示例 4：

    输入：
    [
      "/\\",
      "\\/"
    ]
    输出：5
    解释：（回想一下，因为 \ 字符是转义的，所以 "/\\" 表示 /\，而 "\\/" 表示 \/。）
    2x2 网格如下：

    示例 5：

    输入：
    [
      "//",
      "/ "
    ]
    输出：3
    解释：2x2 网格如下：

     

    提示：

    1 <= grid.length == grid[0].length <= 30
    grid[i][j] 是 '/'、'\'、或 ' '。

 */
public class LeetCode959 {

    /**
     * 「斜杠」、「反斜杠」把单元格拆分成的 2 个三角形的形态，在做合并的时候需要分类讨论。根据「斜杠」、「反斜杠」分割的特点，我们把一个单元格分割成逻辑上的 4 个部分。如下图所示：
    我们须要遍历一次输入的二维网格 grid，在 单元格内 和 单元格间 进行合并。

    单元格内：
    如果是空格：合并 0、1、2、3；
    如果是斜杠：合并 0、3，合并 1、2；
    如果是反斜杠：合并 0、1，合并 2、3。

    单元格间：
    把每一个单元格拆分成 4 个小三角形以后，相邻的单元格须要合并，无须分类讨论。我们选择在遍历 grid 的每一个单元格的时候，分别「向右、向下」尝试合并
     */
    public int regionsBySlashes(String[] grid) {
        int N = grid.length;
        int size = 4 * N * N;

        UnionFind unionFind = new UnionFind(size);
        for (int i = 0; i < N; i++) {
            char[] row = grid[i].toCharArray();
            for (int j = 0; j < N; j++) {
                // 二维网格转换为一维表格
                int index = 4 * (i * N + j);
                char c = row[j];
                // 单元格内合并
                if (c == '/') {
                    // 合并 0、3，合并 1、2
                    unionFind.union(index, index + 3);
                    unionFind.union(index + 1, index + 2);
                } else if (c == '\\') {
                    // 合并 0、1，合并 2、3
                    unionFind.union(index, index + 1);
                    unionFind.union(index + 2, index + 3);
                } else {
                    unionFind.union(index, index + 1);
                    unionFind.union(index + 1, index + 2);
                    unionFind.union(index + 2, index + 3);
                }

                // 单元格间合并
                // 向右合并：1（当前）、3（右一列）
                if (j + 1 < N) {
                    unionFind.union(index + 1, 4 * (i * N + j + 1) + 3);
                }
                // 向下合并：2（当前）、0（下一行）
                if (i + 1 < N) {
                    unionFind.union(index + 2, 4 * ((i + 1) * N + j));
                }
            }
        }
        return unionFind.getCount();
    }

    class UnionFind {

        private int[] parent;

        private int count;

        public int getCount() {
            return count;
        }

        public UnionFind(int n) {
            this.count = n;
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            while (x != parent[x]) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }

            parent[rootX] = rootY;
            count--;
        }
    }

}
