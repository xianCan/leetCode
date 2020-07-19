package leetCode;

import java.util.LinkedList;

/**
 * @author xianCan
 * @date 2020/7/19 22:42
 *
 * 312. 戳气球
 *
 *  有 n 个气球，编号为0 到 n-1，每个气球上都标有一个数字，这些数字存在数组 nums 中。

    现在要求你戳破所有的气球。如果你戳破气球 i ，就可以获得 nums[left] * nums[i] * nums[right] 个硬币。 这里的 left 和 right 代表和 i 相邻的两个气球的序号。注意当你戳破了气球 i 后，气球 left 和气球 right 就变成了相邻的气球。

    求所能获得硬币的最大数量。

    说明:

    你可以假设 nums[-1] = nums[n] = 1，但注意它们不是真实存在的所以并不能被戳破。
    0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
    示例:

    输入: [3,1,5,8]
    输出: 167
    解释: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
         coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167

 */
public class LeetCode312 {

    private int maxRes = 0;

    /**
     * 暴力回溯，超时...
     * @param nums
     * @return
     */
    public int maxCoins(int[] nums) {
        if (nums == null || nums.length==0) return maxRes;
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i:nums){
            linkedList.add(i);
        }
        int res = 0;
        recursive(linkedList, res);
        return maxRes;
    }

    private void recursive(LinkedList<Integer> linkedList, int res){
        if (linkedList.size() == 0){
            maxRes = Math.max(res, maxRes);
        }
        for (int i=0; i<linkedList.size(); i++){
            int left = i==0? 1 : linkedList.get(i-1);
            int right = i==linkedList.size()-1 ? 1 : linkedList.get(i+1);
            Integer integer = linkedList.remove(i);
            recursive(linkedList, res + left*integer*right);
            linkedList.add(i, integer);
        }
    }

    /**
     * 动态规划
     *
     *  想一下递归式，比如现在只剩1号，戳1号后，最后答案为 戳完前面的3号+戳完后面5，8号+戳1号，因为是最后戳1号，所以戳1号的值为 start 1号 end，
     *  戳前面和后面递归调用即可（比如，此时前面的区间的最后一个气球戳破就能算，因为这个区间的start为1，end为最后戳破的那个气球）。一下就有思路了。
     *  原来得逆向戳气球，假设最后一次戳气球是x，这时因为只剩x没有邻居可以直接算出值为x，前面和后面的空缺再递归调用戳气球就可以了！
     *  其实反思过来动态规划本身就是逆向的思维，从最后一步往前推。
     * @param nums
     * @return
     */
    public int maxCoins2(int[] nums) {
        if (nums == null || nums.length==0) return 0;
        int[] newNums = new int[nums.length+2];
        newNums[0]=1;newNums[newNums.length-1]=1;
        for (int i=0; i<nums.length; i++){
            newNums[i+1] = nums[i];
        }

        int[][] dp = new int[newNums.length][newNums.length];
        return recursive2(newNums, 0, newNums.length-1, dp);
    }

    private int recursive2(int[] nums, int start, int end, int[][] dp){
        if (dp[start][end]!=0)return dp[start][end];
        int max=0;
        for (int i=start+1; i<end; i++){
            max = Math.max(nums[i]*nums[start]*nums[end]+recursive2(nums, start, i, dp)+recursive2(nums, i, end, dp), max);
        }
        dp[start][end] = max;
        return max;
    }

    public static void main(String[] args) {
        //
        int i = new LeetCode312().maxCoins2(new int[]{7, 9, 8, 0, 7, 1, 3, 5, 5, 2, 3, 3});
        System.out.println(i);
    }
}
