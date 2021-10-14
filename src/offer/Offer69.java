package offer;

/**
 * @author xianCan
 * @date 2021/10/14 21:33
 *
 * 剑指 Offer II 069. 山峰数组的顶部（简单）
 *
 * 符合下列属性的数组 arr 称为 山峰数组（山脉数组） ：

arr.length >= 3
存在 i（0 < i < arr.length - 1）使得：
arr[0] < arr[1] < ... arr[i-1] < arr[i]
arr[i] > arr[i+1] > ... > arr[arr.length - 1]
给定由整数组成的山峰数组 arr ，返回任何满足 arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 的下标 i ，即山峰顶部。

 

示例 1：

输入：arr = [0,1,0]
输出：1
示例 2：

输入：arr = [1,3,5,4,2]
输出：2
示例 3：

输入：arr = [0,10,5,2]
输出：1
示例 4：

输入：arr = [3,4,5,1]
输出：2
示例 5：

输入：arr = [24,69,100,99,79,78,67,36,26,19]
输出：2
 

提示：

3 <= arr.length <= 104
0 <= arr[i] <= 106
题目数据保证 arr 是一个山脉数组
 

进阶：很容易想到时间复杂度 O(n) 的解决方案，你可以设计一个 O(log(n)) 的解决方案吗？

 */
public class Offer69 {

    /**
     *   由于 arr 数值各不相同，因此峰顶元素左侧必然满足严格单调递增，峰顶元素右侧必然不满足。

         因此 以峰顶元素为分割点的 arr 数组，根据与 前一元素/后一元素 的大小关系，具有二段性：

         峰顶元素左侧满足 arr[i-1] < arr[i] 性质，右侧不满足
         峰顶元素右侧满足 arr[i] > arr[i+1] 性质，左侧不满足

     */
    public int peakIndexInMountainArray(int[] arr) {
        // 根据 arr[i-1] < arr[i] 在 [1,n-1] 范围内找值
        // 峰顶元素为符合条件的最靠近中心的元素
        int n = arr.length;
        int l = 1, r = n - 1;
        while (l < r) {
            int mid = (l + r + 1) / 2;
            if (arr[mid - 1] < arr[mid]) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return r;

    }
}
