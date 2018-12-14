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

    public static void main(String[] args){
        int[] nums1 = new int[]{1,2,3};
        int[] nums2 = new int[]{};
        LeetCode4 l = new LeetCode4();
        double v = l.findMedianSortedArrays(nums1, nums2);
        System.out.println(v);
    }
}
