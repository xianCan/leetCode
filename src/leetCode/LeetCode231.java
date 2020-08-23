package leetCode;

/**
 * @author xianCan
 * @date 2020/8/23 16:45
 *
 * 231. 2的幂（简单）
 *
 *  给定一个整数，编写一个函数来判断它是否是 2 的幂次方。

    示例 1:

    输入: 1
    输出: true
    解释: 20 = 1
    示例 2:

    输入: 16
    输出: true
    解释: 24 = 16
    示例 3:

    输入: 218
    输出: false

 */
public class LeetCode231 {
    /**
     * 暴力
     * @param n
     * @return
     */
    public boolean isPowerOfTwo(int n) {
        if (n <= 0){
            return false;
        }
        while (n > 1){
            int tmp = n % 2;
            if (tmp != 0){
                return false;
            }
            n = n / 2;
        }
        return true;
    }

    /**
     * Brian Kernighan 算法
     * @param n
     * @return
     */
    public boolean isPowerOfTwo2(int n) {
        return n > 0 && (n & (n-1)) == 0;
    }
}
