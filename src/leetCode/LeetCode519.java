package leetCode;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author xianCan
 * @date 2021/11/27 15:24
 *
 * 519. 随机翻转矩阵（中等）
 *
 *  给你一个 m x n 的二元矩阵 matrix ，且所有值被初始化为 0 。请你设计一个算法，随机选取一个满足 matrix[i][j] == 0 的下标 (i, j) ，并将它的值变为 1 。所有满足 matrix[i][j] == 0 的下标 (i, j) 被选取的概率应当均等。

    尽量最少调用内置的随机函数，并且优化时间和空间复杂度。

    实现 Solution 类：

    Solution(int m, int n) 使用二元矩阵的大小 m 和 n 初始化该对象
    int[] flip() 返回一个满足 matrix[i][j] == 0 的随机下标 [i, j] ，并将其对应格子中的值变为 1
    void reset() 将矩阵中所有的值重置为 0
     

    示例：

    输入
    ["Solution", "flip", "flip", "flip", "reset", "flip"]
    [[3, 1], [], [], [], [], []]
    输出
    [null, [1, 0], [2, 0], [0, 0], null, [2, 0]]

    解释
    Solution solution = new Solution(3, 1);
    solution.flip();  // 返回 [1, 0]，此时返回 [0,0]、[1,0] 和 [2,0] 的概率应当相同
    solution.flip();  // 返回 [2, 0]，因为 [1,0] 已经返回过了，此时返回 [2,0] 和 [0,0] 的概率应当相同
    solution.flip();  // 返回 [0, 0]，根据前面已经返回过的下标，此时只能返回 [0,0]
    solution.reset(); // 所有值都重置为 0 ，并可以再次选择下标返回
    solution.flip();  // 返回 [2, 0]，此时返回 [0,0]、[1,0] 和 [2,0] 的概率应当相同
     

    提示：

    1 <= m, n <= 104
    每次调用flip 时，矩阵中至少存在一个值为 0 的格子。
    最多调用 1000 次 flip 和 reset 方法。

 */
public class LeetCode519 {

    class Solution {

        Map<Integer, Integer> map;
        int m, n, total;
        Random random;

        public Solution(int m, int n) {
            map = new HashMap<>();
            random = new Random();
            this.m = m;
            this.n = n;
            total = m * n;
        }

        public int[] flip() {
            int ran = random.nextInt(total--);
            int idx = map.getOrDefault(ran, ran);
            map.put(ran, map.getOrDefault(total, total));
            return new int[]{idx / n, idx % n};
        }

        public void reset() {
            map.clear();
            total = m * n;
        }
    }
}
