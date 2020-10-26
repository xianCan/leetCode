package leetCode;

/**
 * @author xianCan
 * @date 2020/10/26 0:09
 *
 * 1365. 有多少小于当前数字的数字（简单）
 *
 *  给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。

    换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。

    以数组形式返回答案。

     

    示例 1：

    输入：nums = [8,1,2,2,3]
    输出：[4,0,1,1,3]
    解释：
    对于 nums[0]=8 存在四个比它小的数字：（1，2，2 和 3）。
    对于 nums[1]=1 不存在比它小的数字。
    对于 nums[2]=2 存在一个比它小的数字：（1）。
    对于 nums[3]=2 存在一个比它小的数字：（1）。
    对于 nums[4]=3 存在三个比它小的数字：（1，2 和 2）。
    示例 2：

    输入：nums = [6,5,4,8]
    输出：[2,1,0,3]
    示例 3：

    输入：nums = [7,7,7,7]
    输出：[0,0,0,0]
     

    提示：

    2 <= nums.length <= 500
    0 <= nums[i] <= 100

 */
public class LeetCode1365 {

    /**
     * 暴力就完事了
     * @param nums
     * @return
     */
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int len = nums.length;
        int[] ans = new int[len];

        for (int i=0; i<len; i++){
            int count=0;
            for (int j=0; j<len; j++){
                if (nums[i] > nums[j]){
                    count++;
                }
            }
            ans[i] = count;
        }

        return ans;
    }

    /**
     * 耍无赖的计数排序
     * @param nums
     * @return
     */
    public int[] smallerNumbersThanCurrent2(int[] nums){
        int[] cnt = new int[101];
        int len = nums.length;
        for (int num : nums){
            cnt[num]++;
        }
        for (int i=1; i<=100; i++){
            cnt[i] += cnt[i-1];
        }

        int[] ret = new int[len];
        for (int i=0; i<len; i++){
            ret[i] = nums[i] == 0 ? 0 : cnt[nums[i]-1];
        }

        return ret;
    }

    public static void main(String[] args) {
        int[] ints = new LeetCode1365().smallerNumbersThanCurrent(new int[]{7,7,7,7});
        System.out.println(ints);
    }
}
