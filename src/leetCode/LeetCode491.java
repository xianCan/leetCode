package leetCode;

import java.util.*;

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
    private List<List<Integer>> res;

    /**
     * 暴力回溯
     * @param nums
     * @return
     */
    public List<List<Integer>> findSubsequences(int[] nums) {
        this.nums = nums;
        this.res = new ArrayList<>();
        recursion(0, new LinkedList<>());
        return res;
    }

    private void recursion(int start, LinkedList<Integer> path){
        if (path.size() > 1){
            res.add(new ArrayList<>(path));
        }

        //由于不能排序，因此以前的排序，通过一个标记数组来达到去重是不可用的

        //同层上使用过的元素不能再使用了，在同一层上通过一个Set来标记用过的元素
        Set<Integer> set = new HashSet<>();
        for (int i=start; i<nums.length; i++){
            if ((path.isEmpty() || nums[i] >= path.getLast()) && !set.contains(nums[i])){
                set.add(nums[i]);
                path.add(nums[i]);
                recursion(i+1, path);
                path.removeLast();
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> subsequences = new LeetCode491().findSubsequences(new int[]{7,4,6,7,7});
        System.out.println(subsequences);
    }
}
