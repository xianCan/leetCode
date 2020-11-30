package leetCode;

import java.util.Arrays;

/**
 * @author xianCan
 * @date 2020/11/26 20:18
 *
 * 164. 最大间距（困难）
 *
 *  给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。

    如果数组元素个数小于 2，则返回 0。

    示例 1:

    输入: [3,6,9,1]
    输出: 3
    解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。
    示例 2:

    输入: [10]
    输出: 0
    解释: 数组元素个数小于 2，因此返回 0。
    说明:

    你可以假设数组中所有元素都是非负整数，且数值在 32 位有符号整数范围内。
    请尝试在线性时间复杂度和空间复杂度的条件下解决此问题。

 */
public class LeetCode164 {

    /**
     * 暴力
     * @param nums
     * @return
     */
    public int maximumGap(int[] nums) {
        if (nums==null || nums.length < 2)return 0;
        Arrays.sort(nums);

        int ans=0;
        for (int i=1; i<nums.length; i++){
            if (nums[i] - nums[i-1] > ans){
                ans = nums[i] - nums[i-1];
            }
        }
        return ans;
    }

    /**
     * 桶排序
     *
     *  最优解：时间复杂度O(N)，空间O(N)

        那我们开始说这种方法：

        1）遍历所有数，找到最小值和最大值：min和max

        2）设数组长度为n，我们准备n+1个桶

        3）把max放进最后一个桶里，min放到第一个桶里

        4）每一个桶都负责放一个范围内的数字，负责的范围大小是(max-min)/n。

        重点来啦：因为有n+1个桶，有n个数字，我们就发现了一个问题：必定会有空的桶

        为什么我们一定要有空的桶呢？

        这样我们就可以做到：桶内的相邻数字的差，一定没有不同桶之间的数字的差大

        有了这个结论我们可以做什么呢？

        其实找相邻桶和桶之间的差就好啦，桶内的那些情况根本不用关心

        想到这里，我们发现桶里根本不用关心到底有几个数，他们的差是多少，只要记录每个桶的最大值最小值即可。

        最后一点小问题啦：对于一个数num，他应该放在哪个桶，最好推个公式吧？ 它应该被放在(num-min)*len/(max-min)这个桶里。也不难推。
     */
    public int maximumGap2(int[] nums){
        if (nums==null || nums.length<2)return 0;

        int max=Integer.MIN_VALUE, min=Integer.MAX_VALUE;
        for (int num : nums){
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        if (max == min){
            return 0;
        }

        int len = nums.length;
        // 分配桶的长度和个数是桶排序的关键
        // 在 n 个数下，形成的两两相邻区间是 n - 1 个，比如 [2,4,6,8] 这里
        // 有 4 个数，但是只有 3 个区间，[2,4], [4,6], [6,8]
        // 因此，桶长度 = 区间总长度 / 区间总个数 = (max - min) / (nums.length - 1)
        int bucketSize = Math.max(1, (max - min) / (len- 1));

        // 上面得到了桶的长度，我们就可以以此来确定桶的个数
        // 桶个数 = 区间长度 / 桶长度
        // 这里考虑到实现的方便，多加了一个桶，为什么？
        // 还是举上面的例子，[2,4,6,8], 桶的长度 = (8 - 2) / (4 - 1) = 2
        //                           桶的个数 = (8 - 2) / 2 = 3
        // 已知一个元素，需要定位到桶的时候，一般是 (当前元素 - 最小值) / 桶长度
        // 这里其实利用了整数除不尽向下取整的性质
        // 但是上面的例子，如果当前元素是 8 的话 (8 - 2) / 2 = 3，对应到 3 号桶
        //              如果当前元素是 2 的话 (2 - 2) / 2 = 0，对应到 0 号桶
        // 你会发现我们有 0,1,2,3 号桶，实际用到的桶是 4 个，而不是 3 个
        // 透过例子应该很好理解，但是如果要说根本原因，其实是开闭区间的问题
        // 这里其实 0,1,2 号桶对应的区间是 [2,4),[4,6),[6,8)
        // 那 8 怎么办？多加一个桶呗，3 号桶对应区间 [8,10)
        int bucketLen = (max - min) / bucketSize + 1;

        //设置多一个桶，这样至少有一个空桶，最大间隔肯定出现在相邻的数字
        Integer[] maxs = new Integer[bucketLen+1];
        Integer[] mins = new Integer[bucketLen+1];
        for (int num : nums){
            //int loc = (num-min) * len / (max-min);//核心：求出每个数被放置的位置，容易溢出
            int loc = (num - min) / bucketSize;
            maxs[loc] = maxs[loc]!=null ? Math.max(maxs[loc], num) : num;
            mins[loc] = mins[loc]!=null ? Math.min(mins[loc], num) : num;
        }

        int ans=Integer.MIN_VALUE;
        Integer lastMax = null;
        for (int i=0; i<bucketLen; i++){
            if (mins[i] != null){
                //记录第一个非空的lastMax
                if (lastMax == null){
                    lastMax = maxs[i];
                } else {
                    ans = Math.max(ans, mins[i] - lastMax);
                    lastMax = maxs[i];
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int i = new LeetCode164().maximumGap2(new int[]{1,1,1,1,1,5,5,5,5,5});
        System.out.println(i);
    }
}

