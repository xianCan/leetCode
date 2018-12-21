package leetCode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @authod xianCan
 * @date 2018/12/21 16:05
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

    /**
     * 讨人厌的if解法
     * @param s
     * @return
     */
    @SuppressWarnings("all")
    public boolean isValid(String s) {
        if ("".equals(s))return true;
        if (s.length()%2==1)return false;
        Stack<Character> stack = new Stack<>();
        for (int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if ('('==c){
                stack.push(c);
            }else if ('['==c){
                stack.push(c);
            }else if ('{'==c){
                stack.push(c);
            }else if (')'==c){
                if(stack.isEmpty()){
                    return false;
                }else if (stack.peek()!='('){
                    return false;
                }else {
                    stack.pop();
                }
            }else if (']'==c){
                if(stack.isEmpty()){
                    return false;
                }else if (stack.peek()!='['){
                    return false;
                }else {
                    stack.pop();
                }
            }else if ('}'==c){
                if(stack.isEmpty()){
                    return false;
                }else if (stack.peek()!='{'){
                    return false;
                }else {
                    stack.pop();
                }
            }
        }
        if (stack.isEmpty())return true;
        return false;
    }

    /**
     * HashMap解法
     * @param s
     * @return
     */
    public boolean isValid2(String s) {
        if ("".equals(s))return true;
        if (s.length()%2==1)return false;
        Map<Character, Character> map = new HashMap<>();
        map.put(')','(');
        map.put(']','[');
        map.put('}','{');

        Stack<Character> stack = new Stack<>();
        for (int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if (map.containsKey(c)) {
                char topElement = stack.empty() ? '#' : stack.pop();
                if (topElement != map.get(c)) return false;
            } else stack.push(c);
        }
        return stack.isEmpty();
    }
}
