package leetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xianCan
 * @date 2021/1/15 23:02
 *
 * 947. 移除最多的同行或同列石头（中等）
 *
 *  n 块石头放置在二维平面中的一些整数坐标点上。每个坐标点上最多只能有一块石头。

    如果一块石头的 同行或者同列 上有其他石头存在，那么就可以移除这块石头。

    给你一个长度为 n 的数组 stones ，其中 stones[i] = [xi, yi] 表示第 i 块石头的位置，返回 可以移除的石子 的最大数量。

     

    示例 1：

    输入：stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
    输出：5
    解释：一种移除 5 块石头的方法如下所示：
    1. 移除石头 [2,2] ，因为它和 [2,1] 同行。
    2. 移除石头 [2,1] ，因为它和 [0,1] 同列。
    3. 移除石头 [1,2] ，因为它和 [1,0] 同行。
    4. 移除石头 [1,0] ，因为它和 [0,0] 同列。
    5. 移除石头 [0,1] ，因为它和 [0,0] 同行。
    石头 [0,0] 不能移除，因为它没有与另一块石头同行/列。
    示例 2：

    输入：stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
    输出：3
    解释：一种移除 3 块石头的方法如下所示：
    1. 移除石头 [2,2] ，因为它和 [2,0] 同行。
    2. 移除石头 [2,0] ，因为它和 [0,0] 同列。
    3. 移除石头 [0,2] ，因为它和 [0,0] 同行。
    石头 [0,0] 和 [1,1] 不能移除，因为它们没有与另一块石头同行/列。
    示例 3：

    输入：stones = [[0,0]]
    输出：0
    解释：[0,0] 是平面上唯一一块石头，所以不可以移除它。
     

    提示：

    1 <= stones.length <= 1000
    0 <= xi, yi <= 104
    不会有两块石头放在同一个坐标点上

 */
public class LeetCode947 {

    private int[][] stones;
    private boolean[] visited;

    /**
     * dfs
     * @param stones
     * @return
     */
    public int removeStones(int[][] stones) {
        this.stones = stones;
        int num = 0;
        this.visited = new boolean[stones.length];

        for (int i=0; i < stones.length; i++){
            if (!visited[i]){
                num++;
                visited[i] = true;
                helper(stones[i][0], stones[i][1]);
            }
        }

        return stones.length - num;
    }

    private void helper(int x, int y){
        for (int i = 0; i < stones.length; i++){
            if (!visited[i] && (stones[i][0] == x || stones[i][1] == y)){
                visited[i] = true;
                helper(stones[i][0], stones[i][1]);
            }
        }
    }

    /**
     * 并查集
     * @param stones
     * @return
     */
    public int removeStones2(int[][] stones) {

        UnionFind unionFind = new UnionFind();

        for (int[] stone : stones){
            unionFind.union(stone[0] + 10001, stone[1]);
        }

        return stones.length - unionFind.count;
    }

    private class UnionFind {
        private Map<Integer, Integer> parent;
        private int count;

        public UnionFind(){
            this.parent = new HashMap<>();
            this.count = 0;
        }

        public int find(int x){
            if (!parent.containsKey(x)){
                parent.put(x, x);
                count++;
            }

            if (x != parent.get(x)) {
                parent.put(x, find(parent.get(x)));
            }
            return parent.get(x);
        }

        public void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY){
                return;
            }

            parent.put(rootX, rootY);
            count--;
        }
    }

    public static void main(String[] args) {
        //{0,0},{0,1},{1,0},{1,2},{2,1},{2,2}
        //{0,0},{0,2},{1,1},{2,0},{2,2}
        int i = new LeetCode947().removeStones2(new int[][]{{0,1},{1,0},{1,1}});
        System.out.println(i);
    }
}
