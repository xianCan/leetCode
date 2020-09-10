package leetCode;

import java.util.*;

/**
 * @author xianCan
 * @date 2020/9/10 21:27
 *
 * 40. 组合总和 II（中等）
 *
 *  给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

    candidates 中的每个数字在每个组合中只能使用一次。

    说明：

    所有数字（包括目标数）都是正整数。
    解集不能包含重复的组合。 
    示例 1:

    输入: candidates = [10,1,2,7,6,1,5], target = 8,
    所求解集为:
    [
    [1, 7],
    [1, 2, 5],
    [2, 6],
    [1, 1, 6]
    ]
    示例 2:

    输入: candidates = [2,5,2,1,2], target = 5,
    所求解集为:
    [
      [1,2,2],
      [5]
    ]

 */
public class LeetCode40 {

    private int[] candidates;
    private int len;
    private List<List<Integer>> res;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        this.candidates = candidates;
        this.len = candidates.length;
        this.res = new ArrayList<>();
        help(0, target, new LinkedList<>());
        return res;
    }

    private void help(int start, int target, LinkedList<Integer> linkedList) {
        if (target == 0){
            res.add(new ArrayList<>(linkedList));
        }

        for (int i=start; i<len; i++){
            //大剪枝
            if (target - candidates[i] < 0){
                break;
            }

            //小剪枝：同一层相同数值的结点，从第 2 个开始，候选数更少，结果一定发生重复，因此跳过，用 continue
            if (i > start && candidates[i] == candidates[i-1]){
                continue;
            }

            linkedList.add(candidates[i]);
            help(i+1, target-candidates[i], linkedList);
            linkedList.removeLast();
        }
    }

    //去重笨办法
    private void helper(List<List<Integer>> res){
        Set<String> set = new HashSet<>();
        Iterator<List<Integer>> iterator = res.iterator();
        while (iterator.hasNext()){
            List<Integer> next = iterator.next();
            StringBuilder builder = new StringBuilder();
            for (Integer i : next){
                builder.append(i);
            }
            if (set.contains(builder.toString())){
                iterator.remove();
            } else {
                set.add(builder.toString());
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = new LeetCode40().combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8);
        for (List<Integer> list : lists){
            for (Integer i : list){
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

}
