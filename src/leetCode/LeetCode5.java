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
        String str = addDividers(s, '#');
        int strLen = str.length();

        int[] dp = new int[strLen];

        int center = 0;
        int maxRight = 0;

        int maxLen = 1;
        int begin = 0;
        char[] charArray = str.toCharArray();
        for (int i=0; i<strLen; i++){
            if (i < maxRight){
                int mirror = 2*center-i;
                dp[i] = Math.min(maxRight-i, dp[mirror]);
            }

            int left = i-(1 + dp[i]);
            int right = i+(1 + dp[i]);
            while (left >=0 && right < strLen && charArray[left]==charArray[right]){
                dp[i]++;
                left--;
                right++;
            }

            if (i + dp[i] > maxRight){
                maxRight = i + dp[i];
                center = i;
            }

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
