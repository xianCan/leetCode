package leetCode;

import java.util.*;

/**
 * @author xianCan
 * @date 2020/7/18 11:48
 *
 * 438. 找到字符串中所有字母异位词
 *
 * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。

    字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。

    说明：

    字母异位词指字母相同，但排列不同的字符串。
    不考虑答案输出的顺序。
    示例 1:

    输入:
    s: "cbaebabacd" p: "abc"

    输出:
    [0, 6]

    解释:
    起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
    起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
     示例 2:

    输入:
    s: "abab" p: "ab"

    输出:
    [0, 1, 2]

    解释:
    起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
    起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
    起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。

 */
@SuppressWarnings("all")
public class LeetCode438 {


    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s == null || "".equals(s)) return result;
        Map<Character, Integer> need = new HashMap<>(), window = new HashMap<>();
        for (int i=0; i<p.length(); i++){
            char c = p.charAt(i);
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0, valid = 0;
        while (right < s.length()){
            char c = s.charAt(right);
            if (need.containsKey(c)){
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (Objects.equals(need.get(c), window.get(c))){
                    valid++;
                }
            }
            right++;

            if (right - left > p.length()){
                char c1 = s.charAt(left);
                if (need.containsKey(c1)){
                    if (Objects.equals(need.get(c1), window.get(c1))){
                        valid--;
                    }
                    window.put(c1, window.get(c1) - 1);
                }
                left++;
            }

            if (valid == need.size()){
                result.add(left);
            }
        }
        return result;
    }

    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s == null || "".equals(s)) return result;
        int[] need = new int[26], window = new int[26];

        int left = 0, right = 0, total = p.length();
        for (char c : p.toCharArray()){
            need[c -'a']++;
        }

        while (right < s.length()){
            char c = s.charAt(right);
            if (need[c - 'a'] > 0){
                window[c - 'a'] ++;
                if (window[c - 'a'] <= need[c - 'a']){
                    total--;
                }
            }
            right++;

            while (total == 0){
                if (right-left == p.length()){
                    result.add(left);
                }
                char c1 = s.charAt(left);
                if (need[c1 - 'a'] > 0){
                    window[c1 - 'a']--;
                    if (window[c1 -'a'] < need[c1 - 'a']){
                        total++;
                    }
                }
                left++;
            }

        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> anagrams = new LeetCode438().findAnagrams2("cbaebabacd", "abc");
        for (Integer i : anagrams){
            System.out.println(i);
        }
    }
}
