package leetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author xianCan
 * @date 2021/4/29 22:20
 *
 * 403. 青蛙过河（困难）
 *
 *  一只青蛙想要过河。 假定河流被等分为若干个单元格，并且在每一个单元格内都有可能放有一块石子（也有可能没有）。 青蛙可以跳上石子，但是不可以跳入水中。

    给你石子的位置列表 stones（用单元格序号 升序 表示）， 请判定青蛙能否成功过河（即能否在最后一步跳至最后一块石子上）。

    开始时， 青蛙默认已站在第一块石子上，并可以假定它第一步只能跳跃一个单位（即只能从单元格 1 跳至单元格 2 ）。

    如果青蛙上一步跳跃了 k 个单位，那么它接下来的跳跃距离只能选择为 k - 1、k 或 k + 1 个单位。 另请注意，青蛙只能向前方（终点的方向）跳跃。

     

    示例 1：

    输入：stones = [0,1,3,5,6,8,12,17]
    输出：true
    解释：青蛙可以成功过河，按照如下方案跳跃：跳 1 个单位到第 2 块石子, 然后跳 2 个单位到第 3 块石子, 接着 跳 2 个单位到第 4 块石子, 然后跳 3 个单位到第 6 块石子, 跳 4 个单位到第 7 块石子, 最后，跳 5 个单位到第 8 个石子（即最后一块石子）。
    示例 2：

    输入：stones = [0,1,2,3,4,8,9,11]
    输出：false
    解释：这是因为第 5 和第 6 个石子之间的间距太大，没有可选的方案供青蛙跳跃过去。
     

    提示：

    2 <= stones.length <= 2000
    0 <= stones[i] <= 231 - 1
    stones[0] == 0

 */
public class LeetCode403 {

    public boolean canCross(int[] stones) {
        if (stones[1] != stones[0] + 1) return false; // 【单独处理】：第1次只能跳1步

        Set<Integer> set = new HashSet<>();
        for (int stone : stones){
            set.add(stone);
        }
        Map<Integer, Map<Integer, Boolean>> map = new HashMap<>();
        return helper(stones[1], 1, stones[stones.length - 1], map, set);
    }

    private boolean helper(int cur, int k, int end, Map<Integer, Map<Integer, Boolean>> map, Set<Integer> set) {
        if (map.containsKey(cur) && map.get(cur).containsKey(k)) return map.get(cur).get(k);

        boolean ans = false;
        if (cur == end){
            ans = true;
        } else {
            int[] steps = {k - 1, k, k + 1};
            for (int step: steps){
                int next = cur + step;
                if (step > 0 && set.contains(next) && helper(next, step, end, map, set)){
                    ans = true;
                    break;
                }
            }
        }

        Map<Integer, Boolean> stepMap = map.getOrDefault(cur, new HashMap<>());
        stepMap.put(k, ans);
        map.put(cur, stepMap);

        return ans;
    }

    public static void main(String[] args) {
        boolean b = new LeetCode403().canCross(new int[]{0,1,2,3,4,8,9,11});
        System.out.println(b);
    }

}
