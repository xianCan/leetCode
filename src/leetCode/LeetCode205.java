package leetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xianCan
 * @date 2020/12/27 17:33
 *
 * 205. 同构字符串（简单）
 *
 *  给定两个字符串 s 和 t，判断它们是否是同构的。

    如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。

    所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。

    示例 1:

    输入: s = "egg", t = "add"
    输出: true
    示例 2:

    输入: s = "foo", t = "bar"
    输出: false
    示例 3:

    输入: s = "paper", t = "title"
    输出: true
    说明:
    你可以假设 s 和 t 具有相同的长度。

 */
public class LeetCode205 {

    public boolean isIsomorphic(String s, String t) {
        if (s == null && t == null){
            return true;
        } else if (s == null || t == null){
            return false;
        }
        int len1 = s.length(), len2 = t.length();
        if (len1 != len2){
            return false;
        }
        Map<Character, Character> reflectMap = new HashMap<>();
        int[] count1 = new int[128];
        int[] count2 = new int[128];
        for (int i=0; i < len1; i++){
            count1[s.charAt(i)]++;
        }

        for (int i=0; i < len1; i++){
            char ch1 = s.charAt(i);
            char ch2 = t.charAt(i);
            if (reflectMap.containsKey(ch1)){
                if (ch2 != reflectMap.get(ch1)){
                    return false;
                } else if (count2[ch2] <= 0){
                    return false;
                }
                count2[ch2]--;
            } else if (reflectMap.containsValue(ch2)){
                return false;
            } else {
                reflectMap.put(ch1, ch2);
                count2[ch2] = --count1[ch1];
            }
        }

        return true;
    }

    public boolean isIsomorphic2(String s, String t){
        Map<Character, Character> map1 = new HashMap<>();
        Map<Character, Character> map2 = new HashMap<>();
        int len = s.length();
        for (int i = 0; i < len; ++i) {
            char x = s.charAt(i), y = t.charAt(i);
            if ((map1.containsKey(x) && map1.get(x) != y) || (map2.containsKey(y) && map2.get(y) != x)) {
                return false;
            }
            map1.put(x, y);
            map2.put(y, x);
        }
        return true;
    }

    public static void main(String[] args) {
        boolean isomorphic = new LeetCode205().isIsomorphic("12", "34");
        System.out.println(isomorphic);
    }
}
