package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xianCan
 * @date 2020/11/28 17:40
 *
 * 18. 四数之和（中等）
 *
 *  给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。

    注意：

    答案中不可以包含重复的四元组。

    示例：

    给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。

    满足要求的四元组集合为：
    [
    [-1,  0, 0, 1],
    [-2, -1, 1, 2],
    [-2,  0, 0, 2]
    ]

 */
public class LeetCode18 {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans  = new ArrayList<>();
        if (nums == null || nums.length < 4) return ans;

        Arrays.sort(nums);
        int len = nums.length;

        for (int i=0; i<len; i++){

            if (i > 0 && nums[i]==nums[i-1]){
                continue;
            }

            for (int j=i+1; j<len; j++){

                if (j > i+1 && nums[j]==nums[j-1]){
                    continue;
                }

                int tmp = target - nums[i] - nums[j];
                int left=j+1, right=len-1;
                while (left < right){
                    if (nums[left] + nums[right] == tmp){
                        ans.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        left++;
                        right--;
                        while (left < right && nums[left] == nums[left-1]){
                            left++;
                        }
                        while (left < right && nums[right] == nums[right+1]){
                            right--;
                        }
                    } else if (nums[left] + nums[right] < tmp){
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = new LeetCode18().fourSum(new int[]{0,4,-5,2,-2,4,2,-1,4}, 12);
        lists.forEach(System.out::println);
    }
}
