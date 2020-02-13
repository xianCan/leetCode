package leetCode.leetCode_9;

/**
 * @authod xianCan
 * @date 2018/12/19 9:18
 *
 * 题目描述：
 *     判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 示例 1:
 * 输入: 121
 * 输出: true
 *
 * 示例 2:
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 *
 * 示例 3:
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 */
public class LeetCode9 {
    /**
     * 转换为字符串的方式解决
     * @param x 要检验的数字
     * @return
     */
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        String s = x + "", a = "";
        char[] chars = s.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            a += chars[i];
        }
        long i =0;
        try{
            i = Long.parseLong(a);
        }catch (Exception e){
            return false;
        }
        return i==x;
    }

    /**
     * 不用字符串的解决办法：将第7题的整数反转用起来
     * @param x 要检验的数字
     * @return
     */
    public boolean isPalindromeNotUserString(int x) {
        if (x < 0) return false;
        int sum=0, n=x;
        while (n!=0){
            sum = sum*10 + n%10;
            n /= 10;
        }
        return sum==x;
    }
}
