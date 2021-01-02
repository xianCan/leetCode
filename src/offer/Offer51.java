package offer;

/**
 * @author xianCan
 * @date 2020/11/28 23:49
 *
 * 剑指 Offer 51. 数组中的逆序对（困难）
 *
 *  在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。

    示例 1:

    输入: [7,5,6,4]
    输出: 5
     

    限制：

    0 <= 数组长度 <= 50000

 */
public class Offer51 {

    /**
     * 归并排序，O(N log N)
     * @param nums
     * @return
     */
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length < 2){
            return 0;
        }
        return helper(nums, 0, nums.length - 1);
    }

    private int helper(int[] nums, int left, int right){
        if (left == right){
            return 0;
        }

        int mid = (left + right) / 2;
        int res1 = helper(nums, left, mid);
        int res2 = helper(nums, mid + 1, right);
        int res = res1 + res2;

        int[] sortNum = new int[right - left + 1];
        int i = left, j = mid + 1, idx = 0;
        while (i <= mid || j <= right){
            if (i > mid){
                //下标已越过第一个数组右边界，直接取第二个数组
                sortNum[idx++] = nums[j++];
            } else if (j > right){
                //下标已越过第二个数组右边界，直接取第二个数组
                sortNum[idx++] = nums[i++];
            } else if (nums[j] < nums[i]){
                //nums[j] < nums[i]，则 nums[j] 小于 nums[i] 的所有数，此时进行逆序对的统计
                sortNum[idx++] = nums[j++];
                res += mid - i + 1;
            } else {
                sortNum[idx++] = nums[i++];
            }
        }

        for (int k = 0; k < right - left + 1; k++){
            nums[k + left] = sortNum[k];
        }

        return res;
    }

    public static void main(String[] args) {
        int i = new Offer51().reversePairs(new int[]{5});
        System.out.println(i);
    }
}
