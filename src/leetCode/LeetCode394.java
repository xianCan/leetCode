package leetCode;

import java.util.Stack;

/**
 * @author xianCan
 * @date 2022/3/12 21:55
 *
 * 394. 字符串解码（中等）
 *
 *  给定一个经过编码的字符串，返回它解码后的字符串。

    编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。

    你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。

    此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。

     

    示例 1：

    输入：s = "3[a]2[bc]"
    输出："aaabcbc"
    示例 2：

    输入：s = "3[a2[c]]"
    输出："accaccacc"
    示例 3：

    输入：s = "2[abc]3[cd]ef"
    输出："abcabccdcdcdef"
    示例 4：

    输入：s = "abc3[cd]xyz"
    输出："abccdcdcdxyz"
     

    提示：

    1 <= s.length <= 30
    s 由小写英文字母、数字和方括号 '[]' 组成
    s 保证是一个 有效 的输入。
    s 中所有整数的取值范围为 [1, 300] 

 */
public class LeetCode394 {

    public String decodeString(String s) {
        StringBuilder builder = new StringBuilder();
        Stack<Integer> multiStack = new Stack<>();
        Stack<String> stack = new Stack<>();

        int multi = 0;
        for (char ch : s.toCharArray()){
            if (ch == '['){
                multiStack.push(multi);
                stack.push(builder.toString());
                multi = 0;
                builder = new StringBuilder();
            } else if (ch == ']'){
                StringBuilder tmp = new StringBuilder();
                Integer curMulti = multiStack.pop();
                for (int i = 0; i < curMulti; i++){
                    tmp.append(builder);
                }
                builder = new StringBuilder(stack.pop() + tmp);
            } else if (ch >= '0' && ch <= '9'){
                multi = multi * 10 + Integer.parseInt(ch + "");
            } else {
                builder.append(ch);
            }
        }

        return builder.toString();
    }
}
