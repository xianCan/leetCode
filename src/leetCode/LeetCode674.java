package leetCode;

/**
 * @author xianCan
 * @date 2021/1/24 17:01
 *
 * 674. 最长连续递增序列（简单）
 *
 *  给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度。

    连续递增的子序列 可以由两个下标 l 和 r（l < r）确定，如果对于每个 l <= i < r，都有 nums[i] < nums[i + 1] ，那么子序列 [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] 就是连续递增子序列。

     

    示例 1：

    输入：nums = [1,3,5,4,7]
    输出：3
    解释：最长连续递增序列是 [1,3,5], 长度为3。
    尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为 5 和 7 在原数组里被 4 隔开。
    示例 2：

    输入：nums = [2,2,2,2,2]
    输出：1
    解释：最长连续递增序列是 [2], 长度为1。
     

    提示：

    0 <= nums.length <= 104
    -109 <= nums[i] <= 109

 */
public class LeetCode674 {

    public int findLengthOfLCIS(int[] nums) {
        Integer left = null;
        int ans = 0;
        for (int i = 0; i < nums.length; i++){
            if (left == null){
                left = i;
                ans = Math.max(ans, i - left + 1);
            } else if (nums[i] > nums[i - 1]){
                ans = Math.max(ans, i - left + 1);
            } else {
                left = i;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int lengthOfLCIS = new LeetCode674().findLengthOfLCIS(new int[]{2, 2, 2, 2, 2});
        System.out.println(lengthOfLCIS);
    }
}
