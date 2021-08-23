package leetCode;

/**
 * @author xianCan
 * @date 2021/8/23 21:10
 *
 * 1646. 获取生成数组中的最大值（简单）
 *
 *  [1] = 1
    nums[(1 * 2) = 2] = nums[1] = 1
    nums[(1 * 2) + 1 = 3] = nums[1] + nums[2] = 1 + 1 = 2
    nums[(2 * 2) = 4] = nums[2] = 1
    nums[(2 * 2) + 1 = 5] = nums[2] + nums[3] = 1 + 2 = 3
    nums[(3 * 2) = 6] = nums[3] = 2
    nums[(3 * 2) + 1 = 7] = nums[3] + nums[4] = 2 + 1 = 3
    因此，nums = [0,1,1,2,1,3,2,3]，最大值 3
    示例 2：

    输入：n = 2
    输出：1
    解释：根据规则，nums[0]、nums[1] 和 nums[2] 之中的最大值是 1
    示例 3：

    输入：n = 3
    输出：2
    解释：根据规则，nums[0]、nums[1]、nums[2] 和 nums[3] 之中的最大值是 2
     
    提示：

    0 <= n <= 100

 */
public class LeetCode1646 {

    public int getMaximumGenerated(int n) {
        if (n == 0){
            return 0;
        }
        int[] nums = new int[n + 1];
        nums[0] = 0;
        nums[1] = 1;
        for (int i = 2; i <= n; i++){
            if (i % 2 == 0){
                nums[i] = nums[i / 2];
            } else {
                nums[i] = nums[i / 2] + nums[i / 2 + 1];
            }
        }

        int max = 0;
        for (int num : nums){
            max = Math.max(max, num);
        }
        return max;
    }
}
