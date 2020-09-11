package leetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xianCan
 * @date 2020/9/11 23:08
 *
 * 377. 组合总和 Ⅳ（中等）
 *
 *  给定一个由正整数组成且不存在重复数字的数组，找出和为给定目标正整数的组合的个数。

    示例:

    nums = [1, 2, 3]
    target = 4

    所有可能的组合为：
    (1, 1, 1, 1)
    (1, 1, 2)
    (1, 2, 1)
    (1, 3)
    (2, 1, 1)
    (2, 2)
    (3, 1)

    请注意，顺序不同的序列被视作不同的组合。

    因此输出为 7。
    进阶：
    如果给定的数组中含有负数会怎么样？
    问题会产生什么变化？
    我们需要在题目中添加什么限制来允许负数的出现？

 */
public class LeetCode377 {

    private int[] nums;
    private int len;
    private Map<Integer, Integer> memo;

    /**
     * 自顶向下
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4(int[] nums, int target) {
        this.nums = nums;
        this.len = nums.length;
        this.memo = new HashMap<>();
        return helper(target);
    }

    private int helper(int target){
        if (target == 0){
            return 1;
        }
        if (memo.containsKey(target)){
            return memo.get(target);
        }

        int res=0;
        for (int i=0; i<len; i++){
            if (target - nums[i] < 0){
                continue;
            }
            res += helper(target - nums[i]);
        }
        memo.put(target, res);
        return res;
    }

    /**
     * 自底向上
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum42(int[] nums, int target) {
        int[] dp = new int[target+1];
        dp[0] = 1;

        for (int i=1; i<=target; i++){
            int temp = 0;
            for (int num : nums){
                if (i - num >= 0){
                    temp += dp[i - num];
                }
            }
            dp[i] = temp;
        }
        return dp[target];
    }

    public static void main(String[] args) {
        int i = new LeetCode377().combinationSum42(new int[]{1, 2, 3}, 35);
        System.out.println(i);
    }
}
