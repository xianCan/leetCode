package leetCode;

/**
 * @author xianCan
 * @date 2020/11/28 21:57
 *
 * 493. 翻转对（困难）
 *
 *  给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。

    你需要返回给定数组中的重要翻转对的数量。

    示例 1:

    输入: [1,3,2,3,1]
    输出: 2
    示例 2:

    输入: [2,4,3,5,1]
    输出: 3
    注意:

    给定数组的长度不会超过50000。
    输入数组中的所有数字都在32位整数的表示范围内。

 */
public class LeetCode493 {

    /**
     * 暴力
     * @param nums
     * @return
     */
    public int reversePairs(int[] nums) {
        int ans=0;
        for (int i=0; i<nums.length; i++){
            for (int j=i+1; j < nums.length; j++){
                if (nums[i] > 2 * nums[j]){
                    ans++;
                }
            }
        }
        return ans;
    }


    public int reversePairs2(int[] nums) {
        if (nums == null || nums.length < 2)return 0;
        return helper(nums, 0, nums.length-1);
    }

    private int helper(int[] nums, int left, int right) {
        if (left == right){
            return 0;
        }
        int mid = (left + right) / 2;
        int r1 = helper(nums, left, mid);
        int r2 = helper(nums, mid+1, right);
        int res = r1 + r2;

        int i=left, j=mid+1;
        while (i <= mid){
            while (j <= right && (long) nums[i] > 2 * (long) nums[j]){
                j++;
            }
            res += j-mid-1;
            i++;
        }

        int[] sorted = new int[right-left+1];
        int p1=left, p2=mid+1, p=0;
        while (p1 <= mid || p2 <= right){
            if (p1 > mid){
                sorted[p++] = nums[p2++];
            } else if (p2 > right){
                sorted[p++] = nums[p1++];
            } else {
                if (nums[p1] < nums[p2]){
                    sorted[p++] = nums[p1++];
                } else {
                    sorted[p++] = nums[p2++];
                }
            }
        }

        for (int k=0; k < sorted.length; k++){
            nums[left+k] = sorted[k];
        }

        return res;
    }
}
