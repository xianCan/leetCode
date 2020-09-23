package leetCode;

import java.util.Stack;

/**
 * @author xianCan
 * @date 2020/9/23 22:45
 *
 * 921. 使括号有效的最少添加（中等）
 *
 *  给定一个由 '(' 和 ')' 括号组成的字符串 S，我们需要添加最少的括号（ '(' 或是 ')'，可以在任何位置），以使得到的括号字符串有效。

    从形式上讲，只有满足下面几点之一，括号字符串才是有效的：

    它是一个空字符串，或者
    它可以被写成 AB （A 与 B 连接）, 其中 A 和 B 都是有效字符串，或者
    它可以被写作 (A)，其中 A 是有效字符串。
    给定一个括号字符串，返回为使结果字符串有效而必须添加的最少括号数。

     

    示例 1：

    输入："())"
    输出：1
    示例 2：

    输入："((("
    输出：3
    示例 3：

    输入："()"
    输出：0
    示例 4：

    输入："()))(("
    输出：4
     

    提示：

    S.length <= 1000
    S 只包含 '(' 和 ')' 字符。

 */
public class LeetCode921 {
    /**
     * 递归
     * @param S
     * @return
     */
    public int minAddToMakeValid(String S) {
        if (S == null) return 0;
        Stack<Character> stack = new Stack<>();
        int length = S.length();
        for (int i=0; i<length; i++){
            char c = S.charAt(i);
            if (!stack.isEmpty() && stack.peek() == '(' && c == ')'){
                stack.pop();
            } else {
                stack.push(c);
            }
        }

        return stack.size();
    }

    /**
     * 括号匹配
     * @param S
     * @return
     */
    public int minAddToMakeValid2(String S) {
        if (S == null) return 0;
        int res=0;//需要插入的数目
        int need=0;//需要匹配的数目
        int length = S.length();
        for (int i=0; i<length; i++){
            char c = S.charAt(i);
            if (c == '('){
                need++;
            } else if (c == ')'){
                need--;
                if (need==-1){ //代表多出一个(，因此需要插入一个),同时need清0
                    res++;
                    need=0;
                }
            }
        }

        return res+need;
    }

    public static void main(String[] args) {
        int i = new LeetCode921().minAddToMakeValid2("()))((");
        System.out.println(i);
    }
}
