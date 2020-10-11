package leetCode;

/**
 * @author xianCan
 * @date 2020/10/11 18:49
 *
 * 283. 移动零（简单）
 *
 *  给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

    示例:

    输入: [0,1,0,3,12]
    输出: [1,3,12,0,0]
    说明:

    必须在原数组上操作，不能拷贝额外的数组。
    尽量减少操作次数。

 */
public class LeetCode283 {

    public void moveZeroes(int[] nums) {
        int slow=0, fast=0, len=nums.length;

        while (fast < len){
            if (nums[fast] != 0){
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }

        for (; slow<len; slow++){
            nums[slow] = 0;
        }
    }

    public static void main(String[] args) {
        new LeetCode283().moveZeroes(new int[]{0, 1, 0, 3, 12});
    }
}
