package leetCode;

/**
 * @authod xianCan
 * @date 2018/12/14 13:59
 *
 * 4.寻找两个有序数组的中位数
 *
 * 题目描述：
 *     给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *你可以假设 nums1 和 nums2 不会同时为空。
 *
 * 示例 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * 则中位数是 2.0
 *
 * 示例 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * 则中位数是 (2 + 3)/2 = 2.5
 */
public class LeetCode4 {
    /**
     * O(m+n)的时间复杂度
     * @param nums1 数组1
     * @param nums2 数组2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int mergeLength = nums1.length+nums2.length;
        int[] merge = new int[mergeLength];
        int cur1=0,cur2=0,i=0;
        while (cur1+cur2<mergeLength){
            int x = cur1<nums1.length?nums1[cur1]:Integer.MAX_VALUE;
            int y = cur2<nums2.length?nums2[cur2]:Integer.MAX_VALUE;
            if (x<y){
                merge[i++] = x;
                if (cur1+1<=nums1.length) cur1 = cur1+1;
            } else {
                merge[i++] = y;
                if (cur2+1<=nums2.length) cur2 = cur2+1;
            }
        }
        if (mergeLength%2==1){
            return new Double(merge[mergeLength/2]);
        }
        return new Double(((merge[mergeLength/2])+merge[mergeLength/2-1])/2.0);
    }

    /**
     * O(min(m, n))
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays2(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int totalLeft = (m + n + 1) / 2;

        for (int i=0; i<=m; i++){
            int j = totalLeft - i;

            int maxLeft1 = i==0 ? Integer.MIN_VALUE : nums1[i-1];
            int minRight1 = i==m ? Integer.MAX_VALUE : nums1[i];

            int maxLeft2 = j==0 ? Integer.MIN_VALUE : nums2[j-1];
            int minRight2 = j==n ? Integer.MAX_VALUE : nums2[j];

            if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1){
                if ( (m+n) % 2 == 1){
                    return (double) Math.max(maxLeft1, maxLeft2);
                } else {
                    return (Math.max(maxLeft1, maxLeft2) + Math.min(minRight1, minRight2)) / 2.0;
                }
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * O(log min(m, n))
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays3(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays3(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int left=0, right=m, totalLeft = (m+n+1) / 2;

        while (left <= right){
            int i = (left + right) / 2;
            int j = totalLeft - i;

            int maxLeft1 = i==0 ? Integer.MIN_VALUE : nums1[i-1];
            int minRight1 = i==m ? Integer.MAX_VALUE : nums1[i];

            int maxLeft2 = j==0 ? Integer.MIN_VALUE : nums2[j-1];
            int minRight2 = j==n ? Integer.MAX_VALUE : nums2[j];

            if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1){
                if ( (m+n) % 2 == 1){
                    return (double) Math.max(maxLeft1, maxLeft2);
                } else {
                    return (Math.max(maxLeft1, maxLeft2) + Math.min(minRight1, minRight2)) / 2.0;
                }
            } else if (maxLeft1 > minRight2){
                //i太靠右了，往左倾斜
                right = i - 1;
            } else {
                //i太靠左了，往右倾斜
                left = i + 1;
            }
        }

        throw new IllegalArgumentException();
    }

    public static void main(String[] args){
        int[] nums1 = new int[]{};
        int[] nums2 = new int[]{2,4,6,12,18,20};
        LeetCode4 l = new LeetCode4();
        double v = l.findMedianSortedArrays2(nums1, nums2);
        System.out.println(v);
    }
}
