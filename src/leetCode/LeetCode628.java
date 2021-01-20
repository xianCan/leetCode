package leetCode;

import java.util.Arrays;

/**
 * @author xianCan
 * @date 2021/1/20 20:56
 *
 * 628. 三个数的最大乘积（简单）
 *
 *  给定一个整型数组，在数组中找出由三个数组成的最大乘积，并输出这个乘积。

    示例 1:

    输入: [1,2,3]
    输出: 6
    示例 2:

    输入: [1,2,3,4]
    输出: 24
    注意:

    给定的整型数组长度范围是[3,104]，数组中所有的元素范围是[-1000, 1000]。
    输入的数组中任意三个数的乘积不会超出32位有符号整数的范围。

 */
public class LeetCode628 {

    /**
     * 排序
     * @param nums
     * @return
     */
    public int maximumProduct(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);
        int res1 = nums[0] * nums[1] * nums[len - 1];
        int res2 = nums[len - 2] * nums[len - 3] * nums[len - 1];
        return Math.max(res1, res2);
    }

    /**
     * 线性扫描
     * @param nums
     * @return
     */
    public int maximumProduct2(int[] nums) {
        // 最小的和第二小的
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        // 最大的、第二大的和第三大的
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;

        for (int x : nums) {
            if (x < min1) {
                min2 = min1;
                min1 = x;
            } else if (x < min2) {
                min2 = x;
            }

            if (x > max1) {
                max3 = max2;
                max2 = max1;
                max1 = x;
            } else if (x > max2) {
                max3 = max2;
                max2 = x;
            } else if (x > max3) {
                max3 = x;
            }
        }

        return Math.max(min1 * min2 * max1, max1 * max2 * max3);
    }

    public static void main(String[] args) {
        int i = new LeetCode628().maximumProduct(new int[]{-1, -2, 0, 4});
        System.out.println(i);
    }
}
