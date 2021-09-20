package leetCode;

/**
 * @author xianCan
 * @date 2021/9/20 19:28
 *
 * 673. 最长递增子序列的个数（中等）
 *
 *  给定一个未排序的整数数组，找到最长递增子序列的个数。

    示例 1:

    输入: [1,3,5,4,7]
    输出: 2
    解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
    示例 2:

    输入: [2,2,2,2,2]
    输出: 5
    解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
    注意: 给定的数组长度不超过 2000 并且结果一定是32位有符号整数。

 */
public class LeetCode673 {

    public int findNumberOfLIS(int[] nums) {
        int n = nums.length, max = 1, ans = 0;
        int[] dp = new int[n], cnt = new int[n];

        for (int i = 0; i < n; i++){
            dp[i] = cnt[i] = 1;
            for (int j = 0; j < i; j++){
                if (nums[i] > nums[j]){
                    if (dp[i] < dp[j] + 1){
                        dp[i] = dp[j] + 1;
                        cnt[i] = cnt[j];
                    } else if (dp[i] == dp[j] + 1){
                        cnt[i] += cnt[j];
                    }
                }
            }
            max = Math.max(max, dp[i]);
        }
        for (int i = 0; i < n; i++){
            if (dp[i] == max){
                ans += cnt[i];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int numberOfLIS = new LeetCode673().findNumberOfLIS(new int[]{1,2,4,3,5,4,7,2});
        System.out.println(numberOfLIS);
    }
}
