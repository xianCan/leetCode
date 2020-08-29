package leetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xianCan
 * @date 2020/8/29 21:36
 *
 * 131. 分割回文串（中等）
 *
 *  给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。

    返回 s 所有可能的分割方案。

    示例:

    输入: "aab"
    输出:
    [
    ["aa","b"],
    ["a","a","b"]
    ]

 */
public class LeetCode131 {

    private List<List<String>> res = new ArrayList<>();
    private int len;
    private boolean[][] dp;

    /**
     * 暴力回溯 + 备忘录（相当于动态规划）
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        len = s.length();
        // 预处理
        // 状态：dp[i][j] 表示 s[i][j] 是否是回文
        dp = new boolean[len][len];
        // 状态转移方程：在 s[i] == s[j] 的时候，dp[i][j] 参考 dp[i + 1][j - 1]
        for (int right = 0; right < len; right++) {
            // 注意：left <= right 取等号表示 1 个字符的时候也需要判断
            for (int left = 0; left <= right; left++) {
                if (s.charAt(left) == s.charAt(right) && (right - left <= 2 || dp[left + 1][right - 1])) {
                    dp[left][right] = true;
                }
            }
        }
        recursion(0, s, new LinkedList<>());
        return res;
    }

    private void recursion(int start, String s, LinkedList<String> linkedList){
        if (start == len){
            res.add(new LinkedList<>(linkedList));
            return;
        }

        for (int i=start; i<len; i++){
            if (!dp[start][i]){
                continue;
            }
            linkedList.add(s.substring(start, i+1));
            recursion(i+1, s, linkedList);
            linkedList.removeLast();
        }
    }

    public static void main(String[] args) {
        List<List<String>> aab = new LeetCode131().partition("abbab");
        for (List<String> list : aab){
            for (String str : list){
                System.out.print(str + " ");
            }
            System.out.println();
        }
    }
}
