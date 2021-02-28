package leetCode;

/**
 * @author xianCan
 * @date 2021/2/28 15:32
 *
 * 995. K 连续位的最小翻转次数（困难）
 *
 *  在仅包含 0 和 1 的数组 A 中，一次 K 位翻转包括选择一个长度为 K 的（连续）子数组，同时将子数组中的每个 0 更改为 1，而每个 1 更改为 0。

    返回所需的 K 位翻转的最小次数，以便数组没有值为 0 的元素。如果不可能，返回 -1。

     

    示例 1：

    输入：A = [0,1,0], K = 1
    输出：2
    解释：先翻转 A[0]，然后翻转 A[2]。
    示例 2：

    输入：A = [1,1,0], K = 2
    输出：-1
    解释：无论我们怎样翻转大小为 2 的子数组，我们都不能使数组变为 [1,1,1]。
    示例 3：

    输入：A = [0,0,0,1,0,1,1,0], K = 3
    输出：3
    解释：
    翻转 A[0],A[1],A[2]: A变成 [1,1,1,1,0,1,1,0]
    翻转 A[4],A[5],A[6]: A变成 [1,1,1,1,1,0,0,0]
    翻转 A[5],A[6],A[7]: A变成 [1,1,1,1,1,1,1,1]
     

    提示：

    1 <= A.length <= 30000
    1 <= K <= A.length

 */
public class LeetCode995 {

    /**
     *
         令A[-1]为0，则根据递推公式，

         diff[0] = A[0]的翻转次数 - A[-1]的翻转次数
         diff[1] = A[1]的翻转次数 - A[0]的翻转次数
         diff[2] = A[2]的翻转次数 - A[1]的翻转次数
         diff[3] = A[3]的翻转次数 - A[2]的翻转次数
         ...
         diff[i] = A[i]的翻转次数 - A[i-1]的翻转次数
         将以上式子累加得到

         sum(diff[0..i]) = A[i]， 即所谓的差分数组的前缀和就是A[i]的翻转次数。

         差分数组的+1，-1操作可以理解为从**电平触发**转换为**边沿触发**，从而通过一个前缀和变量来优化暴力解法中的内层循环。

         但在写具体的代码的时候，还需要搞清楚差分数组与原数组的对应关系。由于还有一个A[-1]的存在，可以将diff数组整体往后移一位，因此是diff[i+1]++, diff[i+k]--
     * @param A
     * @param K
     * @return
     */
    public int minKBitFlips(int[] A, int K) {
        int n = A.length;
        int[] diff = new int[n + 1];
        int ans = 0, revCnt = 0;
        for (int i = 0; i < n; ++i) {
            revCnt += diff[i];
            if ((A[i] + revCnt) % 2 == 0) {
                if (i + K > n) {
                    return -1;
                }
                ++diff[i + 1];
                --diff[i + K];
                ++ans;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int i = new LeetCode995().minKBitFlips(new int[]{0,0,0,1,0,1,1,0}, 3);
        System.out.println(i);
    }
}
