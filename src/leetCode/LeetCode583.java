package leetCode;

/**
 * @author xianCan
 * @date 2021/4/2 17:08
 *
 * 583. 两个字符串的删除操作（中等）
 *
 * 给定两个单词 word1 和 word2，找到使得 word1 和 word2 相同所需的最小步数，每步可以删除任意一个字符串中的一个字符。

     

    示例：

    输入: "sea", "eat"
    输出: 2
    解释: 第一步将"sea"变为"ea"，第二步将"eat"变为"ea"
     

    提示：

    给定单词的长度不超过500。
    给定单词中的字符只含有小写字母。

 */
public class LeetCode583 {

    public int minDistance(String word1, String word2) {
        //没有判空
        int len1 = word1.length(), len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 1; i <= len1; i++){
            for (int j = 1; j <= len2; j++){
                if (word1.charAt(i - 1) == word2.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return len1 + len2 - 2 * dp[len1][len2];
    }
}
