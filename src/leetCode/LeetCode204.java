package leetCode;

/**
 * @author xianCan
 * @date 2020/12/3 22:22
 *
 * 204. 计数质数（简单）
 *
 *  统计所有小于非负整数 n 的质数的数量。

    示例 1：

    输入：n = 10
    输出：4
    解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
    示例 2：

    输入：n = 0
    输出：0
    示例 3：

    输入：n = 1
    输出：0
     

    提示：

    0 <= n <= 5 * 106

 */
public class LeetCode204 {

    //暴力法，不可取

    /**
     * 埃氏法：如果 x 是质数，那么大于 x 的 x 的倍数 2x,3x,… 一定不是质数，因此我们可以从这里入手
     * @param n
     * @return
     */
    public int countPrimes(int n) {
        boolean[] notPrimes = new boolean[n];
        int ans = 0;

        for (int i=2; i < n; i++){
            if (!notPrimes[i]){
                ans++;
            }
            //
            for (int j=i; j < n; j += i){
                notPrimes[j] = true;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int i = new LeetCode204().countPrimes(5000000);
        System.out.println(i);
    }
}
