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
        Arrays.sort(nums);
        int length = nums.length;
        for (int i=0; i < length; i++){
            int left=i+1, right=length-1, target = 0-nums[i];
            while (left<right){
                int tmp = nums[left] + nums[right];
                if (target == tmp){
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while ((left+1 <= length-1) && nums[left] == nums[left+1]){
                        left++;
                    }
                    while ((right-1 >= 0) && nums[right-1] == nums[right]){
                        right--;
                    }
                    left++;
                    right--;
                } else if (target > tmp){
                    left++;
                } else if (target < tmp){
                    right--;
                }
            }
            while (i < length-1 && nums[i] == nums[i+1]){
                i++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = new LeetCode15().threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        System.out.println(lists);
    }
}
