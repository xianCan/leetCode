package leetCode;

/**
 * @author xianCan
 * @date 2020/11/7 11:30
 *
 * 209. 长度最小的子数组（中等）
 *
 *  给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，并返回其长度。如果不存在符合条件的子数组，返回 0。

    示例：

    输入：s = 7, nums = [2,3,1,2,4,3]
    输出：2
    解释：子数组 [4,3] 是该条件下的长度最小的子数组。
     

    进阶：

    如果你已经完成了 O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。

 */
public class LeetCode209 {

    public int minSubArrayLen(int s, int[] nums) {
        if (nums==null || nums.length==0) return 0;
        int sum=0, left=0, res=Integer.MAX_VALUE;
        for (int right=0; right<nums.length; right++){
            sum += nums[right];
            while (sum >= s){
                res = Math.min(res, right-left+1);
                sum -= nums[left++];
            }
        }
        return res==Integer.MAX_VALUE ? 0 : res;
    }

    public static void main(String[] args) {
        int i = new LeetCode209().minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3});
        System.out.println(i);
    }
}
