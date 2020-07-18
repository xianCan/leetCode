package leetCode.leetCode_3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
        if (s == null || s.length() == 0) return 0;
        Map<Character, Integer> window = new HashMap<>();

        int left=0, right=0, len = 0;
        while (right < s.length()){
            char c = s.charAt(right);
            right++;
            window.put(c, window.getOrDefault(c, 0) + 1);

            while (window.get(c) > 1){
                char c1 = s.charAt(left);
                left++;
                window.put(c1, window.getOrDefault(c1, 0) -1);
            }
            len = Math.max(len, right - left);
        }
        return len;
    }

    public static void main(String[] args) {
        int bbbbb = new LeetCode3().lengthOfLongestSubstring("abcabcbb");
        System.out.println(bbbbb);
    }
}
