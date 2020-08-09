package leetCode;

/**
 * @author xianCan
 * @date 2020/7/31 21:55
 *
 * 面试题 08.03. 魔术索引
 *
 *  魔术索引。 在数组A[0...n-1]中，有所谓的魔术索引，满足条件A[i] = i。给定一个有序整数数组，编写一种方法找出魔术索引，若有的话，在数组A中找出一个魔术索引，如果没有，则返回-1。若有多个魔术索引，返回索引值最小的一个。

    示例1:

    输入：nums = [0, 2, 3, 4, 5]
    输出：0
    说明: 0下标的元素为0
    示例2:

    输入：nums = [1, 1, 1]
    输出：1
    说明:

    nums长度在[1, 1000000]之间
    此题为原书中的 Follow-up，即数组中可能包含重复元素的版本

 */
public class Interview0803 {

    /**
     * O(N)，加入跳跃表的思想
     * @param nums
     * @return
     */
    public int findMagicIndex(int[] nums) {
        if (nums==null || nums.length==0) return -1;
        int i=0;
        int length = nums.length;
        while (i < length){
            if (i == nums[i])
                return i;
            if (nums[i] > i)
                i = nums[i];
            else
                i++;
        }
        return -1;
    }

    public static void main(String[] args) {
        int magicIndex2 = new Interview0803().findMagicIndex(new int[]{3, 3, 3, 3, 5});
        System.out.println(magicIndex2);
    }
}