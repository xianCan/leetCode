package leetCode;

/**
 * @author xianCan
 * @date 2020/8/23 15:59
 *
 * 201. 数字范围按位与（中等）
 *
 *  给定范围 [m, n]，其中 0 <= m <= n <= 2147483647，返回此范围内所有数字的按位与（包含 m, n 两端点）。

    示例 1: 

    输入: [5,7]
    输出: 4
    示例 2:

    输入: [0,1]
    输出: 0

 */
public class LeetCode201 {
    /**
     * 找公共前缀：位移
     *
     * 时间复杂度：O(log N)
     * 空间复杂度：O(1)
     * @param m
     * @param n
     * @return
     */
    public int rangeBitwiseAnd(int m, int n) {
        int shift = 0;
        // 找到公共前缀
        while (m < n) {
            m >>= 1;
            n >>= 1;
            ++shift;
        }
        return m << shift;
    }

    /**
     * 找公共前缀：Brian Kernighan 算法
     * @param m
     * @param n
     * @return
     */
    public int rangeBitwiseAnd2(int m, int n) {
        while (m < n){
            //抹去最右边的 1
            n = n & (n-1);
        }
        return n;
    }
}
