package leetCode;

/**
 * @author xianCan
 * @date 2021/8/14 10:21
 *
 * 233. 数字 1 的个数（困难）
 *
 *  给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。

    示例 1：

    输入：n = 13
    输出：6
    示例 2：

    输入：n = 0
    输出：0
     

    提示：

    0 <= n <= 2 * 109

 */
public class LeetCode233 {

    //计算十位时的1
    //2304  0000 - 2219  000 - 229  230
    //2314  0000 - 2314  000 - 234  235
    //2324  0000 - 2319  000 - 239  240
    public int countDigitOne(int n) {
        int res = 0, mul = 1;
        int high = n / 10, cur = n % 10, low = 0;
        while (high > 0 || cur > 0){
            if (cur == 0){
                res += high * mul;
            } else if (cur == 1){
                res += high * mul + low + 1;
            } else {
                res += (high + 1) * mul;
            }
            low += cur * mul;
            cur = high % 10;
            high /= 10;
            mul *= 10;
        }

        return res;
    }
}
