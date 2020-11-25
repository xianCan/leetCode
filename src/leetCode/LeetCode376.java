package leetCode;

/**
 * @author xianCan
 * @date 2020/11/25 20:14
 *
 * 376. 摆动序列（中等）
 *
 *  如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为摆动序列。第一个差（如果存在的话）可能是正数或负数。少于两个元素的序列也是摆动序列。

    例如， [1,7,4,9,2,5] 是一个摆动序列，因为差值 (6,-3,5,-7,3) 是正负交替出现的。相反, [1,4,7,2,5] 和 [1,7,4,5,5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。

    给定一个整数序列，返回作为摆动序列的最长子序列的长度。 通过从原始序列中删除一些（也可以不删除）元素来获得子序列，剩下的元素保持其原始顺序。

    示例 1:

    输入: [1,7,4,9,2,5]
    输出: 6
    解释: 整个序列均为摆动序列。
    示例 2:

    输入: [1,17,5,10,13,15,10,5,16,8]
    输出: 7
    解释: 这个序列包含几个长度为 7 摆动序列，其中一个可为[1,17,10,13,10,16,8]。
    示例 3:

    输入: [1,2,3,4,5,6,7,8,9]
    输出: 2
    进阶:
    你能否用 O(n) 时间复杂度完成此题?

 */
public class LeetCode376 {

    /**
     * O(N^2)
     * @param nums
     * @return
     */
    public int wiggleMaxLength(int[] nums) {
        if (nums == null){
            return 0;
        } else if (nums.length <= 1){
            return nums.length;
        }

        int len = nums.length;
        int ans=0, flag, tmp;

        for (int i=0; i<len; i++){
            tmp=1;
            flag=0;
            for (int j=i+1; j<len; j++){
                if (flag>=0 && nums[j] < nums[j-1]){
                    tmp++;
                    flag = nums[j] - nums[j-1];
                } else if (flag<=0 && nums[j] > nums[j-1]){
                    tmp++;
                    flag = nums[j] - nums[j-1];
                }
            }
            ans = Math.max(ans, tmp);
        }

        return ans;
    }

    /**
     * 动态规划思想
     * O(N)
     * @param nums
     * @return
     */
    public int wiggleMaxLength2(int[] nums){
        if (nums == null){
            return 0;
        } else if (nums.length <= 1){
            return nums.length;
        }
        int up=1, down=1;
        for (int i=1; i<nums.length; i++){
            if (nums[i] > nums[i-1]){
                up = down + 1;
            } else if (nums[i] < nums[i-1]){
                down = up + 1;
            }
        }
        return Math.max(up, down);
    }

    /**
     * 贪心的思想
     * O(N)
     * @param nums
     * @return
     */
    public int wiggleMaxLength3(int[] nums){
        if (nums == null){
            return 0;
        } else if (nums.length <= 1){
            return nums.length;
        }
        int ans=1, preDiff=0, curDiff;
        for (int i=1; i<nums.length; i++){
            curDiff = nums[i] - nums[i-1];

            if ((preDiff <= 0 && curDiff > 0) || (preDiff >= 0 && curDiff < 0)){
                ans++;
                preDiff = curDiff;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int i = new LeetCode376().wiggleMaxLength3(new int[]{1,10,17,14,16});
        System.out.println(i);
    }
}
