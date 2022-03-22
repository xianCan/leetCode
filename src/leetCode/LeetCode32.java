package leetCode;

import java.util.Stack;

/**
 * 32. 最长有效括号（困难）
 *
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "(()"
 * 输出：2
 * 解释：最长有效括号子串是 "()"
 * 示例 2：
 *
 * 输入：s = ")()())"
 * 输出：4
 * 解释：最长有效括号子串是 "()()"
 * 示例 3：
 *
 * 输入：s = ""
 * 输出：0
 *  
 *
 * 提示：
 *
 * 0 <= s.length <= 3 * 104
 * s[i] 为 '(' 或 ')'
 *
 */
public class LeetCode32 {

    /**
     * 对于遇到的每个 ‘(’ ，我们将它的下标放入栈中
     * 对于遇到的每个 ‘)’ ，我们先弹出栈顶元素表示匹配了当前右括号：
     *    如果栈为空，说明当前的右括号为没有被匹配的右括号，我们将其下标放入栈中来更新我们之前提到的「最后一个没有被匹配的右括号的下标」
     *    如果栈不为空，当前右括号的下标减去栈顶元素即为「以该右括号为结尾的最长有效括号的长度」
     *
     */
    public int longestValidParentheses(String s) {
        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);

        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == '('){
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()){
                    stack.push(i);
                } else {
                    ans = Math.max(ans, i - stack.peek());
                }
            }
        }

        return ans;
    }
}
