package leetCode;

/**
 * @author xianCan
 * @date 2020/8/15 20:42
 *
 * 300. 最长上升子序列（中等）
 *
 *  给定一个无序的整数数组，找到其中最长上升子序列的长度。

    示例:

    输入: [10,9,2,5,3,7,101,18]
    输出: 4
    解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
    说明:

    可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
    你算法的时间复杂度应该为 O(n2) 。
    进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?

 */
public class LeetCode300 {
    /**
     * O(N^2)
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length==0) return 0;
        int length = nums.length;
        int[] dp = new int[length];
        dp[0] = 1;
        int res=1;

        for (int i=1; i<length; i++){
            int num = nums[i];
            int max = 1;
            for (int j=0; j<i; j++){
                if (num > nums[j]){
                    max = Math.max(max, dp[j] + 1);
                }
            }
            dp[i] = max;
            res = Math.max(max, res);
        }

        return res;
    }

    public static void main(String[] args) {
        int i = new LeetCode300().lengthOfLIS(new int[]{10,9,2,5,3,7,101,18});
        System.out.println(i);
    }
}
