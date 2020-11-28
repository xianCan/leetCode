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
        Arrays.sort(nums);
        int len = nums.length;
        //a=nums[i], b=nums[left], c=nums[right]
        for (int i=0; i < len; i++){
            //第一个数大于 0，后面的数都比它大，肯定不成立了
            if (nums[i] > 0){
                return result;
            }
            //第一个数去重
            if (i > 0 && nums[i]==nums[i-1]){
                continue;
            }
            //c对应最右边界
            int left=i+1, right=len-1;

            while (left < right){
                if (nums[i]+nums[left]+nums[right] == 0){
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 现在要增加 left，减小 right，但是不能重复，比如: [-2, -1, -1, -1, 3, 3, 3], i = 0, left = 1, right = 6, [-2, -1, 3] 的答案加入后，需要排除重复的 -1 和 3
                    //因为已经存了一个结果，无论如何先要进行加减操作
                    left++;
                    right--;
                    while (left < right && nums[left] == nums[left-1]){
                        left++;
                    }
                    while (left < right && nums[right] == nums[right+1]){
                        right++;
                    }
                } else if (nums[i]+nums[left]+nums[right] < 0){
                    left++;
                } else if (nums[i]+nums[left]+nums[right] > 0){
                    right--;
                }
            }
        }
        return result;
    }

    /**
     * 如果要的是不重复的下标
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length < 3){
            return ans;
        }

        int len = nums.length;
        //key:nums的值，value:所有nums的下标
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i=0; i<len; i++){
            if (!map.containsKey(nums[i])){
                map.put(nums[i], new ArrayList<>());
            }
            map.get(nums[i]).add(i);
        }

        for (int i=0; i < len; i++){
            for (int j=i+1; j < len; j++){
                List<Integer> list = map.get(-nums[i] - nums[j]);
                if (list != null){
                    for (Integer idx : list){
                        if (idx > j){
                            ans.add(Arrays.asList(i, j, idx));
                        }
                    }
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        //-1, 0, 1, 2, -1, -4
        List<List<Integer>> lists = new LeetCode15().threeSum2(new int[]{-1, 0, 1, 2, -1, -4});
        System.out.println(lists);
    }
}
