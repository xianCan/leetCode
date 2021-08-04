package leetCode;

import java.util.Arrays;

/**
 * @author xianCan
 * @date 2021/8/4 19:50
 *
 * 611. 有效三角形的个数（中等）
 *
 *  给定一个包含非负整数的数组，你的任务是统计其中可以组成三角形三条边的三元组个数。

    示例 1:

    输入: [2,2,3,4]
    输出: 3
    解释:
    有效的组合是:
    2,3,4 (使用第一个 2)
    2,3,4 (使用第二个 2)
    2,2,3
    注意:

    数组长度不超过1000。
    数组里整数的范围为 [0, 1000]。

 */
public class LeetCode611 {

    //O(N ^ 2 log N)
    public int triangleNumber(int[] nums) {
        int len = nums.length, res = 0;
        Arrays.sort(nums);
        for (int i = 0; i < len; i++){
            for (int j = i + 1; j < len; j++){
                int left = j + 1, right = len - 1, target = nums[i] + nums[j] - 1;
                while (left <= right){
                    int mid = left + (right - left) / 2;
                    if (nums[mid] <= target){
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                res +=  left - j - 1;
            }
        }
        return res;
    }

    //O(N ^ 2)
    public int triangleNumber2(int[] nums){
        Arrays.sort(nums);
        int[] sums = new int[nums[nums.length - 1] + 1];
        for (int num : nums){
            sums[num]++;
        }
        for (int i = 1; i < sums.length; i++){
            sums[i] = sums[i] + sums[i - 1];
        }

        int res = 0;
        for (int i = nums.length - 1; i >= 2; i--) {
             //再固定次大的边
            for (int j = i - 1; j >= 1; j--) {
                 //快速计算出满足条件的另一条边的数量
                int minValue = nums[i] - nums[j];
                if (j > sums[minValue]) {
                    res += (j - sums[minValue]);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int i = new LeetCode611().triangleNumber2(new int[]{2, 2, 3, 4, 9, 10, 15});
        System.out.println(i);
    }
}
