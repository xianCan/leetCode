package leetCode;

/**
 * @author xianCan
 * @date 2019/6/3 15:46
 *
 * 5.最长回文子串
 *
 * 给定一个字符串s，找到s中最长的回文子串。你可以假定s的最大长度为1000.
 *
 * 示例 1：
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 *
 * 示例 2：
 * 输入: "cbbd"
 * 输出: "bb"
 *
 */
public class LC5_TheLongestSubstring {

    /**
     * 动态规划的方法
     *
     * 解题思路：
     * 一个字符串 s 可转换为数组 dp[i][j] ，i 为最长回文子串起始位置，j 为截止位置。
     * 如果 s 长度为1，则结果为 s
     * dp[1][1] 为回文，如果 s[0]==s[2]，则 dp[0][2] 为回文
     * 以此类推，dp[i][j] 为回文，如果 s[i-1]==s[j+1]，则 dp[i-1][j+1] 为回文
     * @param s 字符串
     * @return
     */
    public String longestPalidromeDP(String s){
        //1.如果s为空或者长度为1，直接返回
        if (s == null || s.length() < 2){
            return s;
        }
        //2.设置起始位置和子串长度
        int begin = 0;
        int longLen = 1;

        //3.给长度为1和2的子串初始化
        int[][] dp = new int[s.length()][s.length()];
        for (int i = 0; i< s.length(); i++){
            dp[i][i] = 1;
            if (i < s.length() - 1 && s.charAt(i) == s.charAt(i+1)){
                dp[i][i+1] = 1;
                begin = i;
                longLen = 2;
            }
        }

        //4.从长度为3开始，遍历所有可能
        for (int maxLen = 3; maxLen <= s.length(); maxLen++){
            for (int i = 0; i<=s.length() - maxLen; i++){
                int j = i + maxLen - 1;
                if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1] == 1){
                    dp[i][j] = 1;
                    begin = i;
                    longLen = maxLen;
                }
            }
        }
        return s.substring(begin, begin + longLen);
    }

    /**
     *     自己的思路：求一个字符串的最长回文子串，我们可以将以每个字符为首的子串都遍历一遍，判断是否为回文，
     * 如果是回文，再判断最大长度的回文子串。算法简单，但是算法复杂度太高，O（n^3）
     * @param s 字符串
     * @return
     */
    public String longestPalidrome(String s){
        if (s.isEmpty()) return "";
        if (s.length() == 1) return s;
        int start = 0, maxlength = 1;//记录最大回文子串的起始位置以及长度
        for (int i=0; i<s.length(); i++){
            for (int j=i+1; j<s.length(); j++){//从当前位置的下一个开始算
                int temp1,temp2;
                for (temp1=i, temp2=j; temp1<temp2; temp1++,temp2--){
                    if (s.charAt(temp1) != s.charAt(temp2))
                        break;
                }
                if (temp1>=temp2 && j-i+1>maxlength){//这里要注意条件为temp1>=temp2，因为如果是偶数个字符，相邻的两个经上一步会出现大于的情况
                    maxlength = j-i+1;
                    start = i;
                }
            }
        }
        return s.substring(start, maxlength);//利用string中的substr函数来返回相应的子串,第一个参数是起始位置，第二个参数是字符个数
    }
}
