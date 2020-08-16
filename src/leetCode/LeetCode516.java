package leetCode;

/**
 * @author xianCan
 * @date 2020/8/16 17:02
 *
 * 516. 最长回文子序列（中等）
 *
 *  给定一个字符串 s ，找到其中最长的回文子序列，并返回该序列的长度。可以假设 s 的最大长度为 1000 。

    示例 1:
    输入:

    "bbbab"
    输出:

    4
    一个可能的最长回文子序列为 "bbbb"。

    示例 2:
    输入:

    "cbbd"
    输出:

    2
    一个可能的最长回文子序列为 "bb"。

    提示：

    1 <= s.length <= 1000
    s 只包含小写英文字母
 */
public class LeetCode516 {

    /**
     * dp数组的定义：在⼦串 s[i..j] 中，最⻓回⽂⼦序列 的⻓度为 dp[i][j]
     *
     * 求 dp[i][j] ，假设你知道了⼦问题 dp[i+1][j-1] 的结果（ s[i+1..j-1] 中最⻓回⽂⼦序列的⻓度），你是否能想办法算出 dp[i][j] 的值（ s[i..j] 中，最⻓回⽂⼦序列的⻓度）呢？
     * @param s
     * @return
     */
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length()==0) return 0;
        int length = s.length();
        int[][] dp = new int[length][length];
        //base case
        for (int i=0; i<length; i++){
            dp[i][i] = 1;
        }

        for (int i=length-1; i>=0; i--){
            for (int j=i+1; j<length; j++){
                if (s.charAt(i) == s.charAt(j)){
                    //如果相等，则在最长回文子序列中
                    dp[i][j] = dp[i+1][j-1] + 2;
                } else {
                    //如果不等，看取哪边会更长
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][length-1];
    }
}
