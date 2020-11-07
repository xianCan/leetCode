package leetCode;

/**
 * @author xianCan
 * @date 2020/11/7 10:40
 *
 * 35. 搜索插入位置（简单）
 *
 *  给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。

    你可以假设数组中无重复元素。

    示例 1:

    输入: [1,3,5,6], 5
    输出: 2
    示例 2:

    输入: [1,3,5,6], 2
    输出: 1
    示例 3:

    输入: [1,3,5,6], 7
    输出: 4
    示例 4:

    输入: [1,3,5,6], 0
    输出: 0

 */
public class LeetCode35 {

    /**
     * O(N)
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int i=0;
        while (i < nums.length){
            if (nums[i] >= target){
                return i;
            }
            i++;
        }
        return i;
    }

    /**
     * 二分
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert2(int[] nums, int target) {
        int l=0, r=nums.length-1;
        while (l <= r){
            int mid = l + (r-l)/2;
            if (nums[mid] == target){
                return mid;
            } else if (nums[mid] > target){
                r = mid-1;
            } else if (nums[mid] < target){
                l = mid+1;
            }
        }
        return l;
    }

    public int removeElement(int[] nums, int val) {
        int slow=0, fast=0;
        while (fast < nums.length){
            if (nums[fast] != val){
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }

    public static void main(String[] args) {
        int i = new LeetCode35().removeElement(new int[]{0,1,2,2,3,0,4,2}, 2);
        System.out.println(i);
    }
}
