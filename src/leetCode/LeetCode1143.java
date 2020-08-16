package leetCode;

/**
 * @author xianCan
 * @date 2020/8/16 16:16
 *
 * 1143. 最长公共子序列（中等）（Longest Common Subsequence, LCS）
 *
 *  给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。

    一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
    例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。

    若这两个字符串没有公共子序列，则返回 0。

    示例 1:

    输入：text1 = "abcde", text2 = "ace"
    输出：3
    解释：最长公共子序列是 "ace"，它的长度为 3。
    示例 2:

    输入：text1 = "abc", text2 = "abc"
    输出：3
    解释：最长公共子序列是 "abc"，它的长度为 3。
    示例 3:

    输入：text1 = "abc", text2 = "def"
    输出：0
    解释：两个字符串没有公共子序列，返回 0。
     

    提示:

    1 <= text1.length <= 1000
    1 <= text2.length <= 1000
    输入的字符串只含有小写英文字符。

 */
public class LeetCode1143 {
    /**
     * 自底向上
     * 定义dptable： dp[i][j] 的含义是：对于 s1[1..i] 和 s2[1..j] ， 它们的 LCS ⻓度是 dp[i][j]
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        if (text1==null || text2==null || text1.length()==0 || text2.length()==0)return 0;
        int length1 = text1.length();
        int length2 = text2.length();
        int[][] dp = new int[length1+1][length2+1];


        for (int i=1; i<=length1; i++){
            for (int j=1; j<=length2; j++){
                if (text1.charAt(i-1) == text2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[length1][length2];
    }

    /**
     * 自顶向下
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence2(String text1, String text2){
        if (text1==null || text2==null || text1.length()==0 || text2.length()==0)return 0;
        int length1 = text1.length();
        int length2 = text2.length();
        Integer[][] dp = new Integer[length1+1][length2+1];
        return recursion(text1, text2, length1, length2, dp);
    }

    private int recursion(String text1, String text2, int i, int j, Integer[][] dp) {
        if (i==0 || j==0)
            return 0;
        if (dp[i][j] != null){
            return dp[i][j];
        }
        int res;
        if (text1.charAt(i-1) == text2.charAt(j-1)){
            res =  recursion(text1, text2, i-1, j-1, dp) + 1;
        } else {
            res = Math.max(recursion(text1, text2, i-1, j, dp), recursion(text1, text2, i, j-1, dp));
        }
        dp[i][j]  = res;
        return res;
    }

    public static void main(String[] args) {
        int i = new LeetCode1143().longestCommonSubsequence2("abcde", "ace");
        System.out.println(i);
    }

}
