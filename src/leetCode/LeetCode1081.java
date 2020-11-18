package leetCode;

import java.util.Stack;

/**
 * @author xianCan
 * @date 2020/11/16 21:59
 *
 * 1081. 不同字符的最小子序列（中等）
 *
 *  返回字符串 text 中按字典序排列最小的子序列，该子序列包含 text 中所有不同字符一次。

    示例 1：

    输入："cdadabcc"
    输出："adbc"
    示例 2：

    输入："abcd"
    输出："abcd"
    示例 3：

    输入："ecbacba"
    输出："eacb"
    示例 4：

    输入："leetcode"
    输出："letcod"
     

    提示：

    1 <= text.length <= 1000
    text 由小写英文字母组成
     

    注意：本题目与 316 https://leetcode-cn.com/problems/remove-duplicate-letters/ 相同

 */
public class LeetCode1081 {

    /**
     * 用栈来存储最终返回的字符串，并维持字符串的最小字典序。每遇到一个字符，如果这个字符不存在于栈中，就需要将该字符压入栈中。
     * 但在压入之前，需要先将之后还会出现，并且字典序比当前字符小的栈顶字符移除，然后再将当前字符压入。
     * @param s
     * @return
     */
    public String smallestSubsequence(String s) {
        int[] ints = new int[26];
        for (int i=0; i<s.length(); i++){
            ints[s.charAt(i)-'a'] = i;
        }

        boolean[] bool = new boolean[26];
        Stack<Character> stack = new Stack<>();
        for (int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            if (!bool[ch-'a']){
                while (!stack.isEmpty() && ch < stack.peek() && i < ints[stack.peek()-'a']){
                    //维护bool数组
                    bool[stack.pop() - 'a'] = false;
                }
                stack.push(ch);
                bool[ch-'a'] = true;
            }
        }

        StringBuilder builder = new StringBuilder();
        for (Character ch : stack){
            builder.append(ch);
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        String s = new LeetCode1081().smallestSubsequence("leetcode");
        System.out.println(s);
    }
}
