package leetCode;

import java.util.Stack;

/**
 * @author xianCan
 * @date 2020/11/15 0:26
 *
 * 402. 移掉K位数字（中等）
 *
 *  给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。

    注意:

    num 的长度小于 10002 且 ≥ k。
    num 不会包含任何前导零。
    示例 1 :

    输入: num = "1432219", k = 3
    输出: "1219"
    解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
    示例 2 :

    输入: num = "10200", k = 1
    输出: "200"
    解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
    示例 3 :

    输入: num = "10", k = 2
    输出: "0"
    解释: 从原数字移除所有的数字，剩余为空就是0。

 */
public class LeetCode402 {

    public String removeKdigits(String num, int k) {
        if (num.length() <= k){
            return "0";
        }

        Stack<Character> stack = new Stack<>();

        for (int i=0; i<num.length(); i++){
            char ch = num.charAt(i);
            while (!stack.isEmpty() && ch < stack.peek() && k > 0){
                stack.pop();
                k--;
            }
            if (ch == '0' && stack.isEmpty()){
                continue;
            }
            stack.push(ch);
        }

        while (k > 0){
            stack.pop();
            k--;
        }

        if (stack.isEmpty()){
            return "0";
        }

        StringBuilder builder = new StringBuilder();
        for (Character ch : stack){
            builder.append(ch);
        }

        return builder.toString();
    }
}
