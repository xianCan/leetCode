package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xianCan
 * @date 2020/9/18 10:44
 *
 * 47. 全排列 II（中等）
 *
 *  给定一个可包含重复数字的序列，返回所有不重复的全排列。

    示例:

    输入: [1,1,2]
    输出:
    [
    [1,1,2],
    [1,2,1],
    [2,1,1]
    ]

 */
public class LeetCode47 {

    private int[] nums;
    private List<List<Integer>> res;
    private boolean[] visited;

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        this.nums = nums;
        this.res = new ArrayList<>();
        this.visited = new boolean[nums.length];
        helper(new LinkedList<>());
        return res;
    }

    private void helper(LinkedList<Integer> linkedList){
        if (linkedList.size() == nums.length){
            res.add(new ArrayList<>(linkedList));
            return;
        }

        for (int i=0; i<nums.length; i++){
            if (visited[i] || (i > 0 && nums[i]==nums[i-1] && !visited[i-1])){
                continue;
            }

            linkedList.add(nums[i]);
            visited[i] = true;
            helper(linkedList);
            linkedList.removeLast();
            visited[i] = false;

        }
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = new LeetCode47().permuteUnique(new int[]{1, 1, 2});
        for (List<Integer> list : lists){
            for (Integer i : list){
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
