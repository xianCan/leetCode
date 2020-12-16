package leetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xianCan
 * @date 2020/12/16 21:26
 *
 * 290. 单词规律（简单）
 *
 *  给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。

    这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。

    示例1:

    输入: pattern = "abba", str = "dog cat cat dog"
    输出: true
    示例 2:

    输入:pattern = "abba", str = "dog cat cat fish"
    输出: false
    示例 3:

    输入: pattern = "aaaa", str = "dog cat cat dog"
    输出: false
    示例 4:

    输入: pattern = "abba", str = "dog dog dog dog"
    输出: false
    说明:
    你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。    

 */
public class LeetCode290 {

    public boolean wordPattern(String pattern, String s) {
        Map<Character, String> map1 = new HashMap<>();
        Map<String, Character> map2 = new HashMap<>();
        int len = pattern.length();
        String[] split = s.split(" ");
        if (len != split.length){
            return false;
        }

        for (int i = 0; i < len; i++){
            char ch = pattern.charAt(i);
            String str = split[i];
            if (!map1.containsKey(ch) && !map2.containsKey(str)){
                map1.put(ch, str);
                map2.put(str, ch);
            } else if (!map1.containsKey(ch) || !map2.containsKey(str)){
                return false;
            } else {
                String str2 = map1.get(ch);
                Character ch2 = map2.get(str);
                if (ch != ch2 || !str.equals(str2)){
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        boolean b = new LeetCode290().wordPattern("abbac", "dog cat cat dog d");
        System.out.println(b);
    }
}
