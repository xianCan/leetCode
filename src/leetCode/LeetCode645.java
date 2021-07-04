package leetCode;

/**
 * @author xianCan
 * @date 2021/7/4 11:43
 *
 * 645. 错误的集合（简单）
 *
 *  集合 s 包含从 1 到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个数字复制了成了集合里面的另外一个数字的值，导致集合 丢失了一个数字 并且 有一个数字重复 。

    给定一个数组 nums 代表了集合 S 发生错误后的结果。

    请你找出重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。

     

    示例 1：

    输入：nums = [1,2,2,4]
    输出：[2,3]
    示例 2：

    输入：nums = [1,1]
    输出：[1,2]
     

    提示：

    2 <= nums.length <= 104
    1 <= nums[i] <= 104

 */
public class LeetCode645 {

    public int[] findErrorNums(int[] nums) {
        int[] ans = new int[2];
        boolean[] exist = new boolean[nums.length + 1];
        for (int num : nums){
            if (exist[num]){
                ans[0] = num;
            }
            exist[num] = true;
        }
        for (int i = 1; i < exist.length; i++){
            if (!exist[i]){
                ans[1] = i;
            }
        }
        return ans;
    }
}
