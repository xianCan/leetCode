package leetCode.leetCode_3;

import java.util.HashSet;
import java.util.Set;

/**
 * @authod xianCan
 * @date 2018/12/14 11:06
 *
 * 3.无重复字符的最长子串
 *
 * 题目描述：
 *     给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */
public class LeetCode3 {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || "".equals(s)) return 0;
        char[] chars = s.toCharArray();
        Set<Character> set = new HashSet<>();
        int result = 0;
        for (int l=0, r=0; r < chars.length; ){
            if (!set.contains(chars[r])){
                set.add(chars[r++]);
                result = r - l > result ? r - l : result;
            } else {
                set.remove(chars[l++]);
            }
        }
        return result;
    }
}