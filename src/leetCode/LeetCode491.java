package leetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xianCan
 * @date 2020/8/25 22:25
 *
 * 491.递增子序列（中等）
 *
 *  给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。

    示例:

    输入: [4, 6, 7, 7]
    输出: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
    说明:

    给定数组的长度不会超过15。
    数组中的整数范围是 [-100,100]。
    给定数组中可能包含重复数字，相等的数字应该被视为递增的一种情况。

 */
public class LeetCode491 {

    private int[] nums;
    private List<List<Integer>> res = new ArrayList<>();
    private LinkedList<Integer> temp = new LinkedList<>();

    /**
     * 暴力回溯
     * @param nums
     * @return
     */
    public List<List<Integer>> findSubsequences(int[] nums) {
        this.nums = nums;
        recursion(0, Integer.MIN_VALUE);
        return res;
    }

    private void recursion(int cur, int last){
        if (cur == nums.length){
            if (temp.size() > 1){
                res.add(new LinkedList<>(temp));
            }
            return;
        }

        if (nums[cur] >= last){
            temp.add(nums[cur]);
            recursion(cur + 1, nums[cur]);
            temp.removeLast();
        }
        if (nums[cur] != last){
            recursion(cur + 1, last);
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> subsequences = new LeetCode491().findSubsequences(new int[]{1,2,3,4,5,6,7,8,9,10,1,1,1,1,1});

        System.out.println(subsequences);
    }
}
