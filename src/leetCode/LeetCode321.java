package leetCode;

/**
 * @author xianCan
 * @date 2020/11/15 20:43
 *
 * 321. 拼接最大数（困难）
 *
 *  给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。现在从这两个数组中选出 k (k <= m + n) 个数字拼接成一个新的数，要求从同一个数组中取出的数字保持其在原数组中的相对顺序。

    求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。

    说明: 请尽可能地优化你算法的时间和空间复杂度。

    示例 1:

    输入:
    nums1 = [3, 4, 6, 5]
    nums2 = [9, 1, 2, 5, 8, 3]
    k = 5
    输出:
    [9, 8, 6, 5, 3]
    示例 2:

    输入:
    nums1 = [6, 7]
    nums2 = [6, 0, 4]
    k = 5
    输出:
    [6, 7, 6, 0, 4]
    示例 3:

    输入:
    nums1 = [3, 9]
    nums2 = [8, 9]
    k = 3
    输出:
    [9, 8, 9]

 */
public class LeetCode321 {

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        if (nums1.length > nums2.length){
            return maxNumber(nums2, nums1, k);
        }
        if (k < 0 || k > nums1.length + nums2.length){
            throw new IllegalArgumentException();
        }

        int m = nums1.length;
        int n = nums2.length;
        int[] ans = new int[]{};

        for (int i=k>n?k-n:0; i<=m; i++){
            int j = k-i;
            int[] kth1 = getKth(nums1, i);
            int[] kth2 = getKth(nums2, j);
            int[] merge = new int[k];
            int cur = 0, p1 = 0, p2 = 0;
            while (cur < k) {
                if (compare(kth1, p1, kth2, p2)) { // 不能只比较当前值，如果当前值相等还需要比较后续哪个大
                    merge[cur++] = kth1[p1++];
                } else {
                    merge[cur++] = kth2[p2++];
                }
            }
            if (compare(merge, 0, ans, 0)){
                ans = merge;
            }
        }
        return ans;
    }

    /**
     * 单调栈
     * @param nums
     * @param k
     * @return
     */
    private int[] getKth(int[] nums, int k){
        if (k <= 0){
            return new int[]{};
        } else if (k >= nums.length){
            return nums;
        }

        int cur=0, rm=nums.length-k;
        int[] ans = new int[k];

        for (int num : nums){
            while (cur>0 && num > ans[cur-1] && rm > 0){
                cur--;
                rm--;
            }
            if (cur < k){
                ans[cur++] = num;
            } else {
                rm--;
            }
        }
        return ans;
    }

    private boolean compare(int[] nums1, int p1, int[] nums2, int p2) {
        if (p2 >= nums2.length) return true;
        if (p1 >= nums1.length) return false;
        if (nums1[p1] > nums2[p2]) return true;
        if (nums1[p1] < nums2[p2]) return false;
        return compare(nums1, p1 + 1, nums2, p2 + 1);
    }

    public static void main(String[] args) {
        //int[] ints = new LeetCode321().maxNumber(new int[]{3, 4, 6, 5}, new int[]{9, 1, 2, 5, 8, 3}, 5);
        //int[] ints = new LeetCode321().maxNumber(new int[]{6, 7}, new int[]{6, 0, 4}, 5);
        int[] ints = new LeetCode321().maxNumber(new int[]{3, 9}, new int[]{8, 9}, 3);
        System.out.println(ints);
    }
}
