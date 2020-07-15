package leetCode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author xianCan
 * @date 2020/7/15 20:34
 *
 * 76. 最小覆盖子串
 *
 * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字符的最小子串。

    示例：

    输入: S = "ADOBECODEBANC", T = "ABC"
    输出: "BANC"
    说明：

    如果 S 中不存这样的子串，则返回空字符串 ""。
    如果 S 中存在这样的子串，我们保证它是唯一的答案。
 */
@SuppressWarnings("all")
public class LeetCode67 {

    /**
     * 滑动窗口
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        if (s == null || t == null || "".equals(s) || "".equals(t))
            return "";
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (int i=0; i < t.length(); i++){
            char c = t.charAt(i);
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        int left = 0, right = 0, valid = 0, start = 0, len = s.length() + 1;
        while (right < s.length()){
            char c = s.charAt(right);
            if (need.containsKey(c)){
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (Objects.equals(need.get(c), window.get(c))){
                    valid++;
                }
            }

            while (valid == need.size()){
                if (right - left < len){
                    start = left;
                    len = right - left;
                }
                char c1 = s.charAt(left);
                left++;
                if (need.containsKey(c1)){
                    if (Objects.equals(need.get(c1), window.get(c1))){
                        valid--;
                    }
                    window.put(c1, window.getOrDefault(c1, 0) - 1);
                }
            }
            right++;
        }
        return len == s.length() + 1 ? "":s.substring(start, start + len + 1);
    }

    /**
     * 节省一个map
     * @param s
     * @param t
     * @return
     */
    public String minWindow2(String s, String t) {
        if (s == null || t == null || "".equals(s) || "".equals(t))
            return "";
        Map<Character, Integer> need = new HashMap<>();
        for (int i=0; i < t.length(); i++){
            char c = t.charAt(i);
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        int left = 0, right = 0, valid = 0, start = 0, len = s.length() + 1;
        while (right < s.length()){
            char c = s.charAt(right);
            if (need.containsKey(c)){
                need.put(c, need.get(c) - 1);
                if (need.get(c) == 0){
                    valid++;
                }
            }

            while (valid == need.size()){
                if (right - left < len){
                    start = left;
                    len = right - left;
                }
                char c1 = s.charAt(left);
                left++;
                if (need.containsKey(c1)){
                    if (need.get(c1) == 0){
                        valid--;
                    }
                    need.put(c1, need.get(c1) + 1);
                }
            }
            right++;
        }
        return len == s.length() + 1 ? "":s.substring(start, start + len + 1);
    }

    public static void main(String[] args) {
        String s = new LeetCode67().minWindow2("ADOBECODEBANC", "ABC");
        System.out.println(s);
    }
}
