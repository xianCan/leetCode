package leetCode;

/**
 * @author xianCan
 * @date 2020/9/12 20:28
 *
 * 474. 一和零（中等）
 *
 *  在计算机界中，我们总是追求用有限的资源获取最大的收益。

    现在，假设你分别支配着 m 个 0 和 n 个 1。另外，还有一个仅包含 0 和 1 字符串的数组。

    你的任务是使用给定的 m 个 0 和 n 个 1 ，找到能拼出存在于数组中的字符串的最大数量。每个 0 和 1 至多被使用一次。

    注意:

    给定 0 和 1 的数量都不会超过 100。
    给定字符串数组的长度不会超过 600。
    示例 1:

    输入: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
    输出: 4

    解释: 总共 4 个字符串可以通过 5 个 0 和 3 个 1 拼出，即 "10","0001","1","0" 。
    示例 2:

    输入: Array = {"10", "0", "1"}, m = 1, n = 1
    输出: 2

    解释: 你可以拼出 "10"，但之后就没有剩余数字了。更好的选择是拼出 "0" 和 "1" 。

 */
public class LeetCode474 {

    private String[] strs;
    private int len;

    /**
     * 暴力递归：超时...
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm(String[] strs, int m, int n) {
        this.strs = strs;
        this.len = strs.length;
        return helper(0, m, n);
    }


    private int helper(int start, int m, int n){
        int res=0;
        if (start == len){
            return res;
        }

        int sum0=0, sum1=0;
        String str = strs[start];
        for (int j=0; j<str.length(); j++){
            if ('0' == str.charAt(j)){
                sum0++;
            } else if ('1' == str.charAt(j)){
                sum1++;
            }
        }

        if (m - sum0 < 0 || n - sum1 < 0){
            res += helper(start+1, m, n);
        } else {
            res += Math.max(helper(start+1, m-sum0, n-sum1)+1, helper(start+1, m, n));
        }

        return res;
    }

    /**
     * 动态规划
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm2(String[] strs, int m, int n) {
        int len = strs.length;
        int[][] zerosones = new int[len][2];
        for (int i=0; i<len; i++){
            String str = strs[i];
            for (int j=0; j<str.length(); j++){
                zerosones[i][str.charAt(j)-'0']++;
            }
        }

        int[][][] dp = new int[m+1][n+1][len+1];

        /**
         * m，n表示匹配1和0的所需的个数，对于空字符串，m，n就为0了也可以匹配
         */
        for (int i=0; i<=m; i++){
            for (int j=0; j<=n; j++){
                for (int k=1; k<=len; k++){
                    if (i - zerosones[k-1][0] < 0 || j - zerosones[k-1][1] < 0){
                        dp[i][j][k] = dp[i][j][k-1];
                    } else {
                        dp[i][j][k] = Math.max(dp[i][j][k-1], dp[i - zerosones[k-1][0]][j - zerosones[k-1][1]][k-1] + 1);
                    }
                }
            }
        }

        return dp[m][n][len];
    }

    public static void main(String[] args) {
        int maxForm = new LeetCode474().findMaxForm2(new String[]{"10", "0", "1"}, 1, 1);
        System.out.println(maxForm);
    }
}
