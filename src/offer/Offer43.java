package offer;

/**
 * @author xianCan
 * @date 2021/8/14 10:29
 *
 * 剑指 Offer 43. 1～n 整数中 1 出现的次数（困难）
 *
 * 输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。

    例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。

     

    示例 1：

    输入：n = 12
    输出：5
    示例 2：

    输入：n = 13
    输出：6
     

    限制：

    1 <= n < 2^31
    注意：本题与主站 233 题相同：https://leetcode-cn.com/problems/number-of-digit-one/

 */
public class Offer43 {

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
