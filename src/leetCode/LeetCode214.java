package leetCode;

/**
 * @author xianCan
 * @date 2020/8/29 17:24
 *
 * 214. 最短回文串（困难）
 *
 *  给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串。

    示例 1:

    输入: "aacecaaa"
    输出: "aaacecaaa"
    示例 2:

    输入: "abcd"
    输出: "dcbabcd"

 */
public class LeetCode214 {

    /**
     * 直接马拉车算法
     * 时间复杂度：O(N)
     * 空间复杂度：O(N)
     * @param s
     * @return
     */
    public String shortestPalindrome(String s) {
        if (s==null || s.length()<2) return s;

        String str = helper(s, '#');
        int strLen = str.length();
        int[] dp = new int[strLen];
        int center=0, maxRight=0;
        int r=0;

        for (int i=0; i<strLen; i++){
            /**
             * 如果 i >= maxRight， 只能老老实实走中心扩展法
             * 如果 i < maxRight，可以使用回文串特性进行优化
             */
            if (i < maxRight){
                /**
                 * 核心代码：
                 *
                 * 定义 mirror 为 i 关于 center 的对称点
                 * 1、当 dp[mirror] < maxRight-i，有 p[i] = p[mirror] < maxRight - i
                 * 2、当 dp[mirror] = maxRight-i，有 p[i] 至少等于 maxRight - i， 然后需要从 maxRight 开始尝试中心扩展法
                 * 3、当 dp[mirror] > maxRight-i，有 p[i] = maxRight - i
                 *
                 * 综合三种情况，p[i] = min(p[mirror], mqxRight - i)，然后尝试中心扩散
                 */
                int mirror = 2*center-i;
                dp[i] = Math.min(dp[mirror], maxRight-i);
            }

            int left = i-dp[i]-1;
            int right = i+dp[i]+1;
            while (left>=0 && right<strLen && str.charAt(left)==str.charAt(right)){
                dp[i]++;
                left--;
                right++;
            }

            if (i+dp[i] > maxRight){
                center = i;
                maxRight = i+dp[i];
            }

            if (dp[i] == i){
                r = i;
            }
        }

        StringBuilder builder = new StringBuilder();
        for (int i=s.length()-1; i>=r; i--){
            builder.append(s.charAt(i));
        }
        builder.append(s);
        return builder.toString();
    }

    private String helper(String s, char div){
        StringBuilder builder = new StringBuilder();
        int length = s.length();
        for (int i=0; i<length; i++){
            builder.append(div);
            builder.append(s.charAt(i));
        }
        builder.append(div);
        return builder.toString();
    }

    public static void main(String[] args) {
        String s = new LeetCode214().shortestPalindrome("abcd");
        System.out.println(s);
    }
}
