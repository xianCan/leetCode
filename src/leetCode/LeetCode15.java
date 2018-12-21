package leetCode;

import java.util.*;

/**
 * @authod xianCan
 * @date 2018/12/20 17:42
 *
 * 题目描述：
 *     给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 *
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 */
public class LeetCode15 {
    public List<List<Integer>> threeSum(int[] nums) {
        //保存结果
        List<List<Integer>> result = new ArrayList<>();
        if (nums==null || nums.length<3) return result;
        //利用set来去除重复的数组
        Set<List<Integer>> set = new HashSet<>();
        //对数组进行排序，才能用二分查找
        Arrays.sort(nums);
        for (int i=0;i<nums.length-2;i++){
            //left和right当作两个指针
            int left=i+1,right=nums.length-1;
            while (left<right){
                int tempSum = nums[i]+nums[left]+nums[right];
                if (tempSum==0){
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[left]);
                    temp.add(nums[right]);
                    set.add(temp);
                    //跳过与当前数字重复的数字
                    int j=nums[left],k=nums[right];
                    while (j==nums[left]){
                        left++;
                        if (left>=right)
                            break;
                    }
                    while (k==nums[right]){
                        right--;
                        if (left>=right)
                            break;
                    }
                }else if (tempSum<0){
                    left++;
                }else {
                    right--;
                }
            }
        }
        for (List<Integer> list:set){
            result.add(list);
        }
        return result;
    }
}
