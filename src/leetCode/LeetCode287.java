package leetCode;

/**
 * @author xianCan
 * @date 2022/2/25 21:42
 *
 * 287. 寻找重复数（中等）
 *
 *  给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。

    假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。

    你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。

    示例 1：

    输入：nums = [1,3,4,2,2]
    输出：2
    示例 2：

    输入：nums = [3,1,3,4,2]
    输出：3
     
    提示：

    1 <= n <= 105
    nums.length == n + 1
    1 <= nums[i] <= n
    nums 中 只有一个整数 出现 两次或多次 ，其余整数均只出现 一次
     
    进阶：

    如何证明 nums 中至少存在一个重复的数字?
    你可以设计一个线性级时间复杂度 O(n) 的解决方案吗？

 */
public class LeetCode287 {

    public int findDuplicate(int[] nums) {
        int slow = nums[0], fast = nums[nums[0]];
        while (slow != fast){
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        slow = 0;
        while (slow != fast){
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }

    public static void main(String[] args) {
        int duplicate = new LeetCode287().findDuplicate(new int[]{1, 3, 4, 2, 2});
        System.out.println(duplicate);
    }
}
