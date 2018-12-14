package leetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @authod xianCan
 * @date 2018/12/12 10:03
 *
 * 1.两数之和
 *
 * 题目描述：
 *     给定一个整数数组nums和一个目标值target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 */
public class LeetCode1 {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i=0;i<nums.length;i++){
            map.put(nums[i],i);
        }
        for (int i=0;i<nums.length;i++){
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i){
                return new int[]{i,map.get(complement)};
            }
        }
        return null;
    }
}
