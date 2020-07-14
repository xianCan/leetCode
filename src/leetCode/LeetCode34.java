package leetCode;

/**
 * @author xianCan
 * @date 2020/7/14 21:57
 *
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 *
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。

    你的算法时间复杂度必须是 O(log n) 级别。

    如果数组中不存在目标值，返回 [-1, -1]。

    示例 1:

    输入: nums = [5,7,7,8,8,10], target = 8
    输出: [3,4]
    示例 2:

    输入: nums = [5,7,7,8,8,10], target = 6
    输出: [-1,-1]

 */
public class LeetCode34 {

    /**
     * 二分有三种形式：
     * 如果右边界取数组长度，那么 while 循环里面写 left < right
     * 如果右边界取数组长度-1，那么 while 循环里面写 left <= right
     *
     * 1、寻找其中一个相等的即可：
     *      nums[mid] == target 时，直接return mid的下标值
     *
     * 2、寻找左边界：
     *      nums[mid] == target 时，不要立即返回mid值，而是 right = mid - 1，让right不断往左边推
     *      最后检测left 越界的情况：left >= nums.length || nums[left] != target 时 return -1，其他return left
     *
     * 3、寻找右边界
     *      nums[mid] == target 时，不要立即返回mid值，而是 left = mid + 1，让left不断往右边推
     *      最后检测right 越界的情况：right < 0 || nums[right] != target 时 return -1，其他return right
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        int left = 0, right = nums.length - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (nums[mid] == target){
                //别返回，锁定左侧边界
                right = mid - 1;
            } else if (nums[mid] < target){
                left = mid + 1;
            } else if (nums[mid] > target){
                right = mid - 1;
            }
        }
        // 最后要检查 left 越界的情况
        result[0] = (left >= nums.length || nums[left] != target) ? -1 : left;
        left = 0;
        right = nums.length - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (nums[mid] == target){
                // 别返回，锁定右侧边界
                left = mid + 1;
            } else if (nums[mid] < target){
                left = mid + 1;
            } else if (nums[mid] > target){
                right = mid - 1;
            }
        }
        // 最后要检查 right 越界的情况
        result[1] = (right < 0 || nums[right] != target) ? -1 : right;
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5,6,7,7,8,8,10};
        int[] ints = new LeetCode34().searchRange(nums, 6);
        System.out.println(ints);
    }
}
