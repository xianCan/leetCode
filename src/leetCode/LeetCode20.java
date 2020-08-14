package leetCode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @authod xianCan
 * @date 2018/12/21 16:05
 *
 * 20. 有效的括号（简单）
 *
 * 题目描述：
 *     给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *     左括号必须用相同类型的右括号闭合。
 *     左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * 示例 1:
 * 输入: "()"
 * 输出: true
 *
 * 示例 2:
 * 输入: "()[]{}"
 * 输出: true
 *
 * 示例 3:
 * 输入: "(]"
 * 输出: false
 *
 * 示例 4:
 * 输入: "([)]"
 * 输出: false
 *
 * 示例 5:
 * 输入: "{[]}"
 * 输出: true
 */
public class LeetCode20 {

    private static Map<Character, Character> map;

    static {
        map = new HashMap<>();
        map.put(')','(');
        map.put(']','[');
        map.put('}','{');
    }


    public boolean isValid(String s) {
        if (s==null || "".equals(s)){
            return true;
        }

        Stack<Character> stack = new Stack<>();
        int length = s.length();
        for (int i=0; i<length; i++){
            char indC = s.charAt(i);
            Character character1 = map.get(indC);
            if (character1 == null){
                stack.push(indC);
            } else {
                Character character2 = stack.isEmpty() ? null : stack.pop();
                if (character1 != character2){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
