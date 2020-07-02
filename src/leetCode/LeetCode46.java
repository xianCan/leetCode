package leetCode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author xianCan
 * @date 2020/7/2 20:35
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。

    示例:

    输入: [1,2,3]
    输出:
    [
    [1,2,3],
    [1,3,2],
    [2,1,3],
    [2,3,1],
    [3,1,2],
    [3,2,1]
    ]
 */
public class LeetCode46 {

    /**
     * 解题方法：
     *     本题是典型的回溯算法题目，具体框架如下
     *
     * result = []
     * function backtrack(路径, 选择列表):
     *     if 满足结束条件:
     *          result.add(路径)
     *          return
     *     for 选择 in 做选择
     *          做选择
     *          backtrack(路径, 选择列表)
     *          撤销选择
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        if (nums == null || nums.length == 0){
            return result;
        }
        LinkedList<Integer> track = new LinkedList<>();
        recursive(track, nums, result);
        return result;
    }

    private void recursive(LinkedList<Integer> track, int[] nums, List<List<Integer>> result){
        if (track.size() == nums.length){
            result.add(new LinkedList<>(track));
            return;
        }

        for (int i : nums){
            if (!track.contains(i)){
                track.add(i);
                recursive(track, nums, result);
                track.removeLast();
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        List<List<Integer>> permute = new LeetCode46().permute(nums);
        System.out.println(permute);
    }
}
