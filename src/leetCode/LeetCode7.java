package leetCode;

/**
 * @authod xianCan
 * @date 2018/12/17 17:59
 *
 * 题目描述：
 *     给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * 示例 1:
 * 输入: 123
 * 输出: 321
 *
 * 示例 2:
 * 输入: -123
 * 输出: -321
 *
 * 示例 3:
 * 输入: 120
 * 输出: 21
 *
 * 注意:
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 */
public class LeetCode7 {

    /**
     * 从字符串角度出发
     * @param x 需要反转的整数
     * @return
     */
    public int reverseStr(int x) {
        String s = x+"";
        char[] chars = s.toCharArray();
        String intStr="";
        int border=0;
        if (chars[0] == '-'){
            intStr +=chars[0];
            border=1;
        }
        for(int i=chars.length-1;i>=border;i--){
            intStr += chars[i];
        }
        long l=Long.parseLong(intStr);
        return l>Integer.MAX_VALUE || l<Integer.MIN_VALUE ? 0 : (int) l;
    }

    /**
     * 从数字角度出发
     * @param x 需要反转的整数
     * @return
     */
    public int reverseInt(int x){
        int rev = 0;
        while (x != 0){
            int newRev = rev * 10 + x % 10;
            //如果反转计算得不到原来的结果，代表已经溢出整型最大值
            if ((newRev - x % 10) / 10 != rev) return 0;
            rev = newRev;
            x /= 10;
        }
        return rev;
    }
}
