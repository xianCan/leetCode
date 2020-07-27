package leetCode;

/**
 * @author xianCan
 * @date 2020/7/27 20:48
 *
 * 392. 判断子序列
 *
 *  给定字符串 s 和 t ，判断 s 是否为 t 的子序列。

    你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。

    字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。

    示例 1:
    s = "abc", t = "ahbgdc"

    返回 true.

    示例 2:
    s = "axc", t = "ahbgdc"

    返回 false.

    后续挑战 :

    如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？

 */
public class LeetCode392 {

    public boolean isSubsequence(String s, String t) {
        if (s==null || t==null)return false;
        if (s.length() == 0) return true;
        char[] targets = s.toCharArray();
        char[] strings = t.toCharArray();

        int i=0;
        for (char str : strings){
            if (str==targets[i]){
                i++;
                if (i==targets.length)
                    return true;
            }
        }
        return false;
    }

    /**
     * 后续挑战，动态规划
     *
     *  考虑前面的双指针的做法，我们注意到我们有大量的时间用于在 t 中找到下一个匹配字符。

         这样我们可以预处理出对于 t 的每一个位置，从该位置开始往后每一个字符第一次出现的位置。

         我们可以使用动态规划的方法实现预处理，令 f[i][j] 表示字符串 tt 中从位置 i 开始往后字符 j 第一次出现的位置。在进行状态转移时，如果 t 中位置 i 的字符就是 j，那么 f[i][j]=i，否则 j 出现在位置 i+1 开始往后，即 f[i][j]=f[i+1][j]，因此我们要倒过来进行动态规划，从后往前枚举 i。

         这样我们可以写出状态转移方程：

         f[i][j]=   i,          t[i]=j
         f[i][j]= f[i+1][j],    t[i]!=j

         假定下标从 0 开始，那么 f[i][j]中有 0 ≤ i ≤ m−1 ，对于边界状态 f[m-1][..]，我们置 f[m][..] 为 m，让 f[m-1][..] 正常进行转移。这样如果 f[i][j]=m，则表示从位置 i 开始往后不存在字符 j。

         这样，我们可以利用 f 数组，每次 O(1) 地跳转到下一个位置，直到位置变为 m 或 s 中的每一个字符都匹配成功。

     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence2(String s, String t){
        int n = s.length(), m = t.length();

        int[][] f = new int[m + 1][26];
        for (int i = 0; i < 26; i++) {
            f[m][i] = m;
        }

        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < 26; j++) {
                if (t.charAt(i) == j + 'a')
                    f[i][j] = i;
                else
                    f[i][j] = f[i + 1][j];
            }
        }
        int add = 0;
        for (int i = 0; i < n; i++) {
            if (f[add][s.charAt(i) - 'a'] == m) {
                return false;
            }
            add = f[add][s.charAt(i) - 'a'] + 1;
        }
        return true;
    }
}
