package leetCode;

import java.util.*;

/**
 * @author xianCan
 * @date 2021/1/11 21:03
 *
 * 1202. 交换字符串中的元素（中等）
 *
 *  给你一个字符串 s，以及该字符串中的一些「索引对」数组 pairs，其中 pairs[i] = [a, b] 表示字符串中的两个索引（编号从 0 开始）。

    你可以 任意多次交换 在 pairs 中任意一对索引处的字符。

    返回在经过若干次交换后，s 可以变成的按字典序最小的字符串。

     

    示例 1:

    输入：s = "dcab", pairs = [[0,3],[1,2]]
    输出："bacd"
    解释：
    交换 s[0] 和 s[3], s = "bcad"
    交换 s[1] 和 s[2], s = "bacd"
    示例 2：

    输入：s = "dcab", pairs = [[0,3],[1,2],[0,2]]
    输出："abcd"
    解释：
    交换 s[0] 和 s[3], s = "bcad"
    交换 s[0] 和 s[2], s = "acbd"
    交换 s[1] 和 s[2], s = "abcd"
    示例 3：

    输入：s = "cba", pairs = [[0,1],[1,2]]
    输出："abc"
    解释：
    交换 s[0] 和 s[1], s = "bca"
    交换 s[1] 和 s[2], s = "bac"
    交换 s[0] 和 s[1], s = "abc"
     

    提示：

    1 <= s.length <= 10^5
    0 <= pairs.length <= 10^5
    0 <= pairs[i][0], pairs[i][1] < s.length
    s 中只含有小写英文字母

 */
public class LeetCode1202 {

    /**
     * 并查集
     * @param s
     * @param pairs
     * @return
     */
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int length = s.length();
        //记录每个值的父节点值，初始状态默认每个值的父节点值都是他自己，祖宗节点也是他自己
        int[] parent = new int[length];
        for (int i = 0; i < length; i++)
            parent[i] = i;

        //pair中指向的两个值是可以自由交换的，所以他们是一阵营的，也就是祖宗是同一个。
        for (List<Integer> pair : pairs) {
            int ancestry0 = find(pair.get(0), parent);
            int ancestry1 = find(pair.get(1), parent);
            //ancestry0和ancestry1用哪一个成为他们的祖宗都是可以的
            parent[ancestry1] = ancestry0;
        }

        Map<Integer, Queue<Character>> map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            //具有同一祖宗的，说明他们是一阵营的，把他们放到同一队列中
            int ancestry = find(i, parent);
            map.computeIfAbsent(ancestry, k -> new PriorityQueue<>()).offer(s.charAt(i));
        }

        //最后在进行合并
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            //找到i所在的队列，然后元素出队（这里的队列使用的是PriorityQueue，
            //其实就是个最小堆，每次出来的都是队列中最小的值）
            Queue queue = map.get(find(i, parent));
            stringBuilder.append(queue.poll());
        }
        return stringBuilder.toString();
    }

    //查找祖宗节点，只有当前的值等于他父节点值的时候才是祖宗节点。
    private int find(int i, int[] parent) {
        if (parent[i] != i) {
            //如果不是祖宗节点就继续往上查找
            parent[i] = find(parent[i], parent);
        }
        return parent[i];
    }

    /**
     * 并查集
     */
    class UnionFind{
        private int[] parent;

        public UnionFind(int N){
            parent = new int[N + 1];
            for (int i=1; i <= N; i++){
                parent[i] = i;
            }
        }

        public void union(int x, int y){
            int xRoot = find(x);
            int yRoot = find(y);

            //在同一个连通分量上
            if (xRoot == yRoot) return;

            //x是y的父节点
            parent[yRoot] = xRoot;
        }

        public int find(int x){
            if (parent[x] != x){
                return find(parent[x]);
            }
            return parent[x];
        }

        public boolean same(int x, int y){
            return find(x) == find(y);
        }
    }

}
