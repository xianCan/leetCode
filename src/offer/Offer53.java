package offer;

/**
 * @author xianCan
 * @date 2021/7/16 21:05
 *
 * 剑指 Offer 53 - I. 在排序数组中查找数字 I（简单）
 *
 * 统计一个数字在排序数组中出现的次数。

    示例 1:

    输入: nums = [5,7,7,8,8,10], target = 8
    输出: 2
    示例 2:

    输入: nums = [5,7,7,8,8,10], target = 6
    输出: 0
     

    限制：

    0 <= 数组长度 <= 50000

 */
public class Offer53 {

    public int search(int[] nums, int target) {
        int len = nums.length, l = 0, r = len - 1;
        while (l <= r){
            int mid = (l + r) / 2;
            if (target <= nums[mid]){
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        int left = l;
        l = 0;
        r = len - 1;
        while (l <= r){
            int mid = (l + r) / 2;
            if (target >= nums[mid]){
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        int right = r;
        if (left <= right && right < nums.length && nums[left] == target && nums[right] == target) {
            return right - left + 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        int search = new Offer53().search(new int[]{5, 7, 7, 8, 8, 10}, 9);
        System.out.println(search);
    }
}
