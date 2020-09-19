package leetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xianCan
 * @date 2020/9/20 0:06
 *
 * 78. 子集（中等）
 *
 *  给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。

    说明：解集不能包含重复的子集。

    示例:

    输入: nums = [1,2,3]
    输出:
    [
    [3],
      [1],
      [2],
      [1,2,3],
      [1,3],
      [2,3],
      [1,2],
      []
    ]

 */
public class LeetCode78 {

    private int[] nums;
    private List<List<Integer>> res;

    public List<List<Integer>> subsets(int[] nums) {
        this.nums = nums;
        this.res = new ArrayList<>();
        helper(0, new LinkedList<>());
        return res;
    }

    private void helper(int start, LinkedList<Integer> linkedList){
        res.add(new ArrayList<>(linkedList));
        for (int i=start; i<nums.length; i++){
            linkedList.add(nums[i]);
            helper(i+1, linkedList);
            linkedList.removeLast();
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> subsets = new LeetCode78().subsets(new int[]{1, 2, 3});
        for (List<Integer> list : subsets){
            for (Integer i : list){
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

}
