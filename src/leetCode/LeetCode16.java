package leetCode;

import com.sun.media.sound.RIFFInvalidDataException;

import java.util.Arrays;

/**
 * @authod xianCan
 * @date 2018/12/21 10:32
 *
 * 题目描述：
 *     给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 *
 * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 */
public class LeetCode16 {
    /**
     * 先排序，再双指针
     * @param nums 数组
     * @param target 目标值
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int diff=Integer.MAX_VALUE,result=0;
        for (int i=0;i<nums.length-2;i++){
            int left=i+1,right=nums.length-1;
            while (left<right){
                int temp = nums[i]+nums[left]+nums[right];
                int abs = Math.abs(target - temp);
                if (abs<diff) {
                    result = temp;
                    diff = abs;
                }
                //如果出现等于target的情况，直接返回
                if (abs==0)return result;
                if (target>temp) left++;
                else right--;
            }
        }
        return result;
    }
}
