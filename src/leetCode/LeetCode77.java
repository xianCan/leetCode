package leetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xianCan
 * @date 2020/9/8 21:04
 *
 * 77. 组合（中等）
 *
 *  给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。

    示例:

    输入: n = 4, k = 2
    输出:
    [
    [2,4],
    [3,4],
    [2,3],
    [1,2],
    [1,3],
    [1,4],
    ]

 */
public class LeetCode77 {

    private int n;
    private int k;
    private int[] nums;
    private List<List<Integer>> res;

    public List<List<Integer>> combine(int n, int k) {
        this.n = n;
        this.k = k;
        this.nums = new int[n];
        this.res = new ArrayList<>();
        for (int i=1; i<=n; i++){
            nums[i-1] = i;
        }
        helper(0, new LinkedList<>());
        return res;
    }

    private void helper(int start, LinkedList<Integer> linkedList){
        int size = linkedList.size();
        if (size == k){
            res.add(new LinkedList<>(linkedList));
            return;
        }

        //回溯+剪枝
        for (int i=start; i<=n-(k-size); i++){
            linkedList.add(nums[i]);
            helper(i+1, linkedList);
            linkedList.removeLast();
        }
    }
}
