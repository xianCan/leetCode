package leetCode;

/**
 * @author xianCan
 * @date 2020/8/2 10:50
 *
 * 887. 鸡蛋掉落（困难）
 *
 *  你将获得 K 个鸡蛋，并可以使用一栋从 1 到 N  共有 N 层楼的建筑。

    每个蛋的功能都是一样的，如果一个蛋碎了，你就不能再把它掉下去。

    你知道存在楼层 F ，满足 0 <= F <= N 任何从高于 F 的楼层落下的鸡蛋都会碎，从 F 楼层或比它低的楼层落下的鸡蛋都不会破。

    每次移动，你可以取一个鸡蛋（如果你有完整的鸡蛋）并把它从任一楼层 X 扔下（满足 1 <= X <= N）。

    你的目标是确切地知道 F 的值是多少。

    无论 F 的初始值如何，你确定 F 的值的最小移动次数是多少？

     
    示例 1：

    输入：K = 1, N = 2
    输出：2
    解释：
    鸡蛋从 1 楼掉落。如果它碎了，我们肯定知道 F = 0 。
    否则，鸡蛋从 2 楼掉落。如果它碎了，我们肯定知道 F = 1 。
    如果它没碎，那么我们肯定知道 F = 2 。
    因此，在最坏的情况下我们需要移动 2 次以确定 F 是多少。
    示例 2：

    输入：K = 2, N = 6
    输出：3
    示例 3：

    输入：K = 3, N = 14
    输出：4
     

    提示：

    1 <= K <= 100
    1 <= N <= 10000

 */
public class LeetCode887 {

    /**
     * 普通动态规划：在6,2000的时候超时...
     * 时间复杂度O(K*N^2)，空间复杂度O(K*N)
     * @param K
     * @param N
     * @return
     */
    public int superEggDrop(int K, int N) {
        Integer[][] dp = new Integer[K+1][N+1];
        return recursive(K, N, dp);
    }

    private int recursive(int K, int N, Integer[][] dp){
        if (K==1) return N;
        if (N==0) return 0;
        if (dp[K][N] != null){
            return dp[K][N];
        }
        int res=Integer.MAX_VALUE / 2;
        for (int i=1; i <=N; i++){
            res = Math.min(res, 1+Math.max(recursive(K-1, i-1, dp), recursive(K, N-i, dp)));
        }
        dp[K][N] = res;
        return res;
    }

    /**
     * 利用二分的思维进行动态规划
     * 时间复杂度O(K*N*LogN)，空间复杂度O(K*N)
     * @param K
     * @param N
     * @return
     */
    public int superEggDrop2(int K, int N){
        Integer[][] dp = new Integer[K+1][N+1];
        return recursive2(K, N, dp);
    }

    private int recursive2(int K, int N, Integer[][] dp){
        if (K==1) return N;
        if (N==0) return 0;
        if (dp[K][N] != null){
            return dp[K][N];
        }
        int res=Integer.MAX_VALUE / 2;
        int left=1, right=N;
        while (left <= right){
            int mid = left + (right-left)/2;
            int broken = recursive2(K - 1, mid - 1, dp);//碎
            int notBroken = recursive2(K, N - mid, dp);
            if (broken > notBroken){
                right = mid - 1;
                res = Math.min(res, broken + 1);
            } else {
                left = mid + 1;
                res = Math.min(res, notBroken + 1);
            }
        }
        dp[K][N] = res;
        return res;
    }

    public static void main(String[] args) {
        int i = new LeetCode887().superEggDrop2(6, 2000);
        System.out.println(i);
    }
}
