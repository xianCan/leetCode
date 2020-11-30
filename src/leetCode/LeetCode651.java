package leetCode;

/**
 * @author xianCan
 * @date 2020/9/18 22:22
 *
 * 651、四键键盘（中等）
 *
 *  假设你有一个特殊的键盘，包含以下的按键：

    key 1：（A）：在屏幕上打印一个 A

    key 2：（Ctrl-A）：选中整个屏幕

    key 3：（Ctrl-C）：复制选中区域到缓冲区

    key 4：（Ctrl-V）：将缓冲区内容输出到上次输入的结束位置，并显示在屏幕上

    现在，你只可以按键N次（使用上述四种按键），请问屏幕上最多可以显示几个A？

    样例1：

    输入：N=3

    输出：3

    解释：我们最多可以在屏幕上显示3个A，通过如下顺序按键：A, A, A

    样例2：

    输入：N=7

    输出：N=9

    解释：我们最多可以在屏幕上显示9个A，通过如下顺序按键：A, A, A, Ctrl-A, Ctrl-C, Ctrl-V, Ctrl-V
 */
public class LeetCode651 {

    /**
     * 暴力递归
     * @param N
     * @return
     */
    public int maxA(int N){
        return helper(N, 0, 0);
    }

    private int helper(int N, int aNum, int copy){
        if (N <= 0){
            return aNum;
        }

        int choice = helper(N - 1, aNum + 1, copy);//打印一个A
        int paste = helper(N - 1, aNum + copy, copy);//粘贴
        int selectAndCopy = helper(N - 2, aNum, aNum);//全选+复制
        return Math.max(choice, Math.max(paste, selectAndCopy));
    }

    /**
     * 换一种思路的动态规划：
     *
     *  选择 还是那 4 个，但是这次我们只定义一个「状态」，也就是剩余的敲击次数 n
     *
     *  这种思路稍微有点复杂，但是效率高。继续走流程，「选择」还是那 4 个，但是这次我们只定义一个「状态」，也就是剩余的敲击次数 n。
        这个算法基于这样一个事实，最优按键序列一定只有两种情况：
        要么一直按 A：A,A,...A（当 N 比较小时）。
        要么是这么一个形式：A,A,...C-A,C-C,C-V,C-V,...C-V（当 N 比较大时）。
     * @return
     */
    public int maxA2(int N){
        //定义：dp[i] 表示 i 次操作后最多能显示多少个 A
        int[] dp = new int[N+1];
        dp[0] = 0;
        for (int i=1; i<=N; i++){
            // 按 A 键
            dp[i] = dp[i-1] + 1;
            for (int j=2; j<i; j++){
                // 全选 & 复制 dp[j-2]，连续粘贴 i - j 次
                // 屏幕上共 dp[j - 2] * (i - j + 1) 个 A
                dp[i] = Math.max(dp[i], dp[j-2] * (i-j+1));
            }
        }
        return dp[N];
    }

}
