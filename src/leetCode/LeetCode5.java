package leetCode;

/**
 * @author xianCan
 * @date 2020/8/29 12:18
 *
 * 5. 最长回文子串（中等）
 *
 *  给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

    示例 1：

    输入: "babad"
    输出: "bab"
    注意: "aba" 也是一个有效答案。
    示例 2：

    输入: "cbbd"
    输出: "bb"

 */
public class LeetCode5 {

    /**
     * 中心扩展法
     * 时间复杂度：O(N^2)
     * 空间复杂度：O(1)
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start=0, end=0;
        for(int i=0; i<s.length(); i++){
            int len = Math.max(helper(s, i, i), helper(s, i, i+1));
            if (len > end-start){
                start = i-(len-1)/2;
                end = i+len/2;
            }
        }
        return s.substring(start, end+1);
    }

    private int helper(String s, int left, int right){
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }
        return right-left-1;
    }

    /**
     * 马拉车算法
     * 时间复杂度：O(N)
     * 空间复杂度：O(N)
     * @param s
     * @return
     */
    public String longestPalindrome2(String s){
        int len = s.length();
        if (len < 2) return s;
        //将奇偶字符串统一处理为奇字符串
        String str = addDividers(s, '#');
        int strLen = str.length();

        int[] dp = new int[strLen];//dp[i] 数组表示以 i 为中心时的最长回文半径

        int center = 0;//最右回文串的中心点
        int maxRight = 0;//最右回文串的右下标

        int maxLen = 1;//当前遍历的中心最大扩散步数，其值等于原始字符串的最长回文子串的长度
        int begin = 0;//原始字符串的最长回文子串的起始位置，与 maxLen 必须同时更新
        char[] charArray = str.toCharArray();
        for (int i=0; i<strLen; i++){
            /**
             * 如果 i >= maxRight， 只能老老实实走中心扩展法
             * 如果 i < maxRight，可以使用回文串特性进行优化
             */
            if (i < maxRight){
                int mirror = 2*center-i;
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
                dp[i] = Math.min(maxRight-i, dp[mirror]);
            }

            //中心扩展法：下一次尝试扩散的左右起点，能扩散的步数直接加到 p[i] 中
            int left = i-(1 + dp[i]);
            int right = i+(1 + dp[i]);
            while (left >=0 && right < strLen && charArray[left]==charArray[right]){
                dp[i]++;
                left--;
                right++;
            }

            //维护maxRight和center
            //根据 maxRight 的定义，它是遍历过的 i 的 i + p[i] 的最大者
            // 如果 maxRight 的值越大，进入上面 i < maxRight 的判断的可能性就越大，这样就可以重复利用之前判断过的回文信息了
            if (i + dp[i] > maxRight){
                maxRight = i + dp[i];
                center = i;
            }

            // 记录最长回文子串的长度和相应它在原始字符串中的起点，不用再去遍历多一次
            if (dp[i] > maxLen){
                maxLen = dp[i];
                begin = (i-maxLen)/2;
            }
        }
        return s.substring(begin, begin+maxLen);
    }

    /**
     * 统一处理形成奇数字符串
     * @param s
     * @param divider
     * @return
     */
    private String addDividers(String s, char divider){
        char[] charArray = s.toCharArray();
        int len = s.length();
        StringBuilder builder = new StringBuilder();
        for (int i=0; i<len; i++){
            builder.append(divider);
            builder.append(charArray[i]);
        }
        builder.append(divider);
        return builder.toString();
    }

    public static void main(String[] args) {
        String s = new LeetCode5().longestPalindrome2("abac");
        System.out.println(s);
    }
}
