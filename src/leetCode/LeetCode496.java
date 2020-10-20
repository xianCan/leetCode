package leetCode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author xianCan
 * @date 2020/10/19 23:00
 *
 * 496. 下一个更大元素 I（简单）
 *
 *  给定两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。找到 nums1 中每个元素在 nums2 中的下一个比其大的值。

    nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1 。

     

    示例 1:

    输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
    输出: [-1,3,-1]
    解释:
    对于num1中的数字4，你无法在第二个数组中找到下一个更大的数字，因此输出 -1。
    对于num1中的数字1，第二个数组中数字1右边的下一个较大数字是 3。
    对于num1中的数字2，第二个数组中没有下一个更大的数字，因此输出 -1。
    示例 2:

    输入: nums1 = [2,4], nums2 = [1,2,3,4].
    输出: [3,-1]
    解释:
        对于 num1 中的数字 2 ，第二个数组中的下一个较大数字是 3 。
    对于 num1 中的数字 4 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
     

    提示：

    nums1和nums2中所有元素是唯一的。
    nums1和nums2 的数组大小都不超过1000。

 */
public class LeetCode496 {

    /**
     * 常规方法
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        int len1 = nums1.length;
        int len2 = nums2.length;

        for (int i=0; i<len2; i++){
            map.put(nums2[i], i);
        }

        int[] ans = new int[len1];

        for (int i=0; i<len1; i++){
            Integer idx = map.get(nums1[i]);
            for (int j=idx+1; j<=len2; j++){
                if (j == len2){
                    ans[i] = -1;
                    break;
                } else if (nums2[j] > nums1[i]){
                    ans[i] = nums2[j];
                    break;
                }
            }
        }

        return ans;
    }

    /**
     * 单调栈
     */
    public int[] nextGreaterElement2(int[] nums1, int[] nums2){
        int[] ans = new int[nums1.length];
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();

        for (int i=nums2.length-1; i>=0; i--){
            while (!stack.isEmpty() && stack.peek() <= nums2[i]){
                stack.pop();
            }
            map.put(nums2[i], stack.empty() ? -1 : stack.peek());
            stack.push(nums2[i]);
        }

        for (int i=0; i<nums1.length; i++){
            ans[i] = map.get(nums1[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] ints = new LeetCode496().nextGreaterElement2(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2});
        System.out.println(ints);
    }
}
