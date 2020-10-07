package leetCode;

/**
 * @author xianCan
 * @date 2020/10/7 11:04
 *
 * 75. 颜色分类（中等）
 *
 *  给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。

    此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。

    注意:
    不能使用代码库中的排序函数来解决这道题。

    示例:

    输入: [2,0,2,1,1,0]
    输出: [0,0,1,1,2,2]
    进阶：

    一个直观的解决方案是使用计数排序的两趟扫描算法。
    首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
    你能想出一个仅使用常数空间的一趟扫描算法吗？

 */
public class LeetCode75 {

    public void sortColors(int[] nums) {
        int left=0, mid=0;
        for (int i : nums){
            if (i==0){
                left++;
            } else if (i==1){
                mid++;
            }
        }

        for (int i=0; i<left; i++){
            nums[i]=0;
        }
        for (int i=left; i<mid+left; i++){
            nums[i]=1;
        }
        for (int i=mid+left; i<nums.length; i++){
            nums[i]=2;
        }
    }

    /**
     *   双指针
         两个指针分别指向 下一个0、2应该存放的位置
         遇0则交换 当前元素 和 p0空间的值，并 使得 p0指针 指向 下一个0应该存放的位置，遍历下一个元素
         遇2则交换 当前元素 和 p2空间的值，并 使得 p2指针 指向 下一个2应该存放的位置，继续遍历 交换后的当前元素
     * @param nums
     */
    public void sortColors2(int[] nums){
        if (nums == null || nums.length <= 1) {
            return;
        }
        int p0 = 0;
        int p2 = nums.length - 1;
        for (int i = p0; i <= p2; i++) {
            if (nums[i] == 0) {
                nums[i] = nums[p0];
                nums[p0++] = 0;
            } else if (nums[i] == 2) {
                nums[i--] = nums[p2];
                nums[p2--] = 2;
            }
        }
    }
}
