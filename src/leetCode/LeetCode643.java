package leetCode;

/**
 * @author xianCan
 * @date 2021/2/4 21:46
 *
 * 643. 子数组最大平均数 I（简单）
 *
 *  给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。

    示例：

    输入：[1,12,-5,-6,50,3], k = 4
    输出：12.75
    解释：最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
     

    提示：

    1 <= k <= n <= 30,000。
    所给数据范围 [-10,000，10,000]。

 */
public class LeetCode643 {

    public double findMaxAverage(int[] nums, int k) {
        int idx = 0, sum = 0;
        for (int i = 0; i < k; i++){
            sum += nums[i];
        }
        double ans = (double) sum / k;

        for (int i = k; i < nums.length;){
            sum -= nums[idx++];
            sum += nums[i++];

            ans = Math.max(ans, (double) sum / k);
        }

        return ans;
    }
}
