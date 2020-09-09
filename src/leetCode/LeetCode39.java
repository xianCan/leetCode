package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xianCan
 * @date 2020/9/9 21:13
 *
 * 39. 组合总和（中等）
 *
 *  给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

    candidates 中的数字可以无限制重复被选取。

    说明：

    所有数字（包括 target）都是正整数。
    解集不能包含重复的组合。 
    示例 1：

    输入：candidates = [2,3,6,7], target = 7,
    所求解集为：
    [
    [7],
    [2,2,3]
    ]
    示例 2：

    输入：candidates = [2,3,5], target = 8,
    所求解集为：
    [
      [2,2,2,2],
      [2,3,3],
      [3,5]
    ]
     

    提示：

    1 <= candidates.length <= 30
    1 <= candidates[i] <= 200
    candidate 中的每个元素都是独一无二的。
    1 <= target <= 500

 */
public class LeetCode39 {

    private int[] candidates;
    private int len;
    private List<List<Integer>> res;

    /**
     * 回溯+剪枝
     *
     * 利用数组元素的唯一性进行排序，然后target-candidates[i]小于0时，后续的元素不再进行遍历，从而剪枝
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        this.candidates = candidates;
        this.len = candidates.length;
        this.res = new ArrayList<>();
        helper(0, target, new LinkedList<>());
        return res;
    }

    private void helper(int start, int target, LinkedList<Integer> linkedList){
        if (target == 0){
            res.add(new ArrayList<>(linkedList));
        }

        for (int i=start; i<len; i++){
            if (target-candidates[i] < 0){
                break;
            }
            linkedList.add(candidates[i]);
            helper(i, target - candidates[i], linkedList);
            linkedList.removeLast();
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = new LeetCode39().combinationSum(new int[]{2, 3, 6, 7}, 7);
        for (List<Integer> list : lists){
            for (Integer i : list){
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
