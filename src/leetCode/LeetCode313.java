package leetCode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author xianCan
 * @date 2021/8/9 20:03
 *
 * 313. 超级丑数（中等）
 *
 *  超级丑数 是一个正整数，并满足其所有质因数都出现在质数数组 primes 中。

    给你一个整数 n 和一个整数数组 primes ，返回第 n 个 超级丑数 。

    题目数据保证第 n 个 超级丑数 在 32-bit 带符号整数范围内。

     

    示例 1：

    输入：n = 12, primes = [2,7,13,19]
    输出：32
    解释：给定长度为 4 的质数数组 primes = [2,7,13,19]，前 12 个超级丑数序列为：[1,2,4,7,8,13,14,16,19,26,28,32] 。
    示例 2：

    输入：n = 1, primes = [2,3,5]
    输出：1
    解释：1 不含质因数，因此它的所有质因数都在质数数组 primes = [2,3,5] 中。
     
    提示：

    1 <= n <= 106
    1 <= primes.length <= 100
    2 <= primes[i] <= 1000
    题目数据 保证 primes[i] 是一个质数
    primes 中的所有值都 互不相同 ，且按 递增顺序 排列

 */
public class LeetCode313 {

    //优先级队列
    public int nthSuperUglyNumber(int n, int[] primes) {
        Set<Long> set = new HashSet<>();
        PriorityQueue<Long> queue = new PriorityQueue<>();
        set.add(1L);
        queue.offer(1L);
        int res = 0;
        for (int i = 0; i < n; i++){
            long poll = queue.poll();
            res = (int) poll;
            for (int prime : primes){
                long num = poll * prime;
                if (set.add(num)){
                    queue.offer(num);
                }
            }
        }
        return res;
    }

    //动态规划
    public int nthSuperUglyNumber2(int n, int[] primes) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int len = primes.length;
        //创建与数组 primes 相同长度的数组 pointers，表示下一个超级丑数是当前指针指向的超级丑数乘以对应的质因数。初始时，数组 pointers 的元素值都是 1。
        int[] idxs = new int[len];
        Arrays.fill(idxs, 1);

        for (int i = 2; i <= n; i++){
            int min = Integer.MAX_VALUE;
            int[] nums = new int[len];
            for (int j = 0; j < len; j++){
                nums[j] = dp[idxs[j]] * primes[j];
                min = Math.min(min, nums[j]);
            }
            dp[i] = min;
            for (int j = 0; j < len; j++){
                if (nums[j] == min){
                    idxs[j]++;
                }
            }
        }

        return dp[n];
    }
}
