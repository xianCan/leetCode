package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xianCan
 * @date 2020/11/9 19:42
 *
 * 90. 子集 II（中等）
 *
 *  给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。

    说明：解集不能包含重复的子集。

    示例:

    输入: [1,2,2]
    输出:
    [
    [2],
    [1],
    [1,2,2],
    [2,2],
    [1,2],
    []
    ]

 */
public class LeetCode90 {

    private int[] nums;
    private List<List<Integer>> ans;

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        this.nums = nums;
        this.ans = new ArrayList<>();
        recursive(0, new LinkedList<>());
        return ans;
    }

    private void recursive(int start, LinkedList<Integer> path){
        ans.add(new ArrayList<>(path));

        for (int i=start; i<nums.length; i++){
            if (i > start && nums[i] == nums[i-1]){
                continue;
            }
            path.add(nums[i]);
            recursive(i+1, path);
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = new LeetCode90().subsetsWithDup(new int[]{1, 2, 2});
        System.out.println(lists);
    }
}
