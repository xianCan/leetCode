package leetCode;

/**
 * @author xianCan
 * @date 2020/9/18 20:00
 *
 * 72. 编辑距离（困难）
 *
 *  给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。

    你可以对一个单词进行如下三种操作：

    插入一个字符
    删除一个字符
    替换一个字符
     

    示例 1：

    输入：word1 = "horse", word2 = "ros"
    输出：3
    解释：
    horse -> rorse (将 'h' 替换为 'r')
    rorse -> rose (删除 'r')
    rose -> ros (删除 'e')
    示例 2：

    输入：word1 = "intention", word2 = "execution"
    输出：5
    解释：
    intention -> inention (删除 't')
    inention -> enention (将 'i' 替换为 'e')
    enention -> exention (将 'n' 替换为 'x')
    exention -> exection (将 'n' 替换为 'c')
    exection -> execution (插入 'u')

 */
public class LeetCode72 {

    private String word1;
    private String word2;

    /**
     * 暴力递归
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        this.word1 = word1;
        this.word2 = word2;
        return helper(word1.length()-1, word2.length()-1);
    }

    private int helper(int i, int j){
        if (i==-1) return j+1;
        if (j==-1) return i+1;

        if (word1.charAt(i) == word2.charAt(j)){
            return helper(i-1, j-1);
        } else {
            //删除
            int delete = helper(i - 1, j) + 1;
            //插入
            int insert = helper(i, j-1) + 1;
            //替换
            int replace = helper(i-1, j-1) + 1;
            return Math.min(delete, Math.min(insert, replace));
        }
    }

    /**
     * 动态规划
     * 如果需要记录具体的操作，则可以用一个包含value和choice的对象去取代int类型的dp数组
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance2(String word1, String word2){
        int m=word1.length(), n=word2.length();
        // 有一个字符串为空串
        if (n * m == 0) {
            return n + m;
        }

        int[][] dp = new int[m+1][n+1];

        for (int i=0; i<=m; i++){
            dp[i][0] = i;
        }
        for (int i=0; i<=n; i++){
            dp[0][i] = i;
        }

        for (int i=1; i<=m; i++){
            for (int j=1; j<=n; j++){
                if (word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = min(dp[i][j-1]+1, dp[i-1][j]+1, dp[i-1][j-1]+1);
                }
            }
        }
        return dp[m][n];
    }

    private int min(int a, int b, int c){
        return Math.min(a, Math.min(b, c));
    }

    public static void main(String[] args) {
        int i = new LeetCode72().minDistance("horse", "ros");
        System.out.println(i);
    }
}
