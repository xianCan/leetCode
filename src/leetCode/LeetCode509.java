package leetCode;

/**
 * @author xianCan
 * @date 2020/6/26 10:49
 *
 * 斐波那契数
 *
 * 形成的序列称为斐波那契数列。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：

    F(0) = 0,   F(1) = 1
    F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
    给定 N，计算 F(N)。

    示例 1：

    输入：2
    输出：1
    解释：F(2) = F(1) + F(0) = 1 + 0 = 1.
    示例 2：

    输入：3
    输出：2
    解释：F(3) = F(2) + F(1) = 1 + 1 = 2.
    示例 3：

    输入：4
    输出：3
    解释：F(4) = F(3) + F(2) = 2 + 1 = 3.
 */
public class LeetCode509 {

    /**
     * 最简单的递归方法，时间复杂度很高，2^N
     * @param N
     * @return
     */
    public int fib(int N) {
        if (N <= 0) return 0;
        if(N == 1 || N == 2) return 1;
        return fib(N-1) + fib(N -2);
    }

    /**
     * 带备忘录的递归方法
     * @param N
     * @return
     */
    public int main2(int N){
        if (N <= 0) return 0;
        int[] ints = new int[N + 1];
        ints[1] = 1; ints[2] = 1;
        return fib2(ints, N);
    }

    public int fib2(int[] ints, int N){
        if (ints[N] != 0)
            return ints[N];
        else {
            ints[N] =  fib2(ints, N-1) + fib2(ints, N-2);
            return ints[N];
        }
    }

    /**
     * 动态规划
     * @param N
     * @return
     */
    public int fib3(int N){
        if (N <= 0) return 0;
        int[] ints = new int[N + 1];
        ints[1] = 1; ints[2] = 1;
        for (int i=3; i<=N; i++){
            ints[i] = ints[i-1] + ints[i-2];
        }
        return ints[N];
    }

    /**
     * 由于fib数只与前两项相关，因此可以压缩空间复杂度为O(1)
     * @param N
     * @return
     */
    public int fib4(int N){
        if (N <= 0) return 0;
        int pre = 1, cur = 1;
        for (int i=3; i<=N; i++){
            int sum = pre + cur;
            pre = cur;
            cur = sum;
        }
        return cur;
    }

    public static void main(String[] args) {
        int i = new LeetCode509().fib4(10);
        System.out.println(i);
    }
}
