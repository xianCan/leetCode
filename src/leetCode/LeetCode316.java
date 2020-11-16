package leetCode;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @author xianCan
 * @date 2020/11/15 20:06
 *
 * 316. 去除重复字母（中等）
 *
 *  给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。

    注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters 相同

     

    示例 1：

    输入：s = "bcabc"
    输出："abc"
    示例 2：

    输入：s = "cbacdcbc"
    输出："acdb"
     

    提示：

    1 <= s.length <= 104
    s 由小写英文字母组成

 */
public class LeetCode316 {

    /**
     * 用栈来存储最终返回的字符串，并维持字符串的最小字典序。每遇到一个字符，如果这个字符不存在于栈中，就需要将该字符压入栈中。
     * 但在压入之前，需要先将之后还会出现，并且字典序比当前字符小的栈顶字符移除，然后再将当前字符压入。
     * @param s
     * @return
     */
    public String removeDuplicateLetters(String s) {
        int[] nums = new int[26];
        for (int i=0; i<s.length(); i++){
            nums[s.charAt(i) - 'a'] = i;
        }

        Stack<Character> stack = new Stack<>();
        Set<Character> set = new HashSet<>();

        for (int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            if (!set.contains(ch)){
                while (!stack.isEmpty() && ch < stack.peek() && nums[stack.peek() - 'a'] > i){
                    set.remove(stack.pop());
                }
                stack.push(ch);
                set.add(ch);
            }
        }

        StringBuilder builder = new StringBuilder();
        for (Character c : stack){
            builder.append(c);
        }

        return builder.toString();
    }

    public static void main(String[] args) {
        String s = new LeetCode316().removeDuplicateLetters("cbacdcbc");
        System.out.println(s);
    }
}
