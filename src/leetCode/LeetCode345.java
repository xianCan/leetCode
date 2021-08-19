package leetCode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author xianCan
 * @date 2021/8/19 20:44
 *
 * 345. 反转字符串中的元音字母（简单）
 *
 *  给你一个字符串 s ，仅反转字符串中的所有元音字母，并返回结果字符串。

    元音字母包括 'a'、'e'、'i'、'o'、'u'，且可能以大小写两种形式出现。

     

    示例 1：

    输入：s = "hello"
    输出："holle"
    示例 2：

    输入：s = "leetcode"
    输出："leotcede"
     

    提示：

    1 <= s.length <= 3 * 105
    s 由 可打印的 ASCII 字符组成

 */
public class LeetCode345 {

    public String reverseVowels(String s) {
        Set<Character> set = new HashSet<>();
        set.addAll(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        char[] chars = s.toCharArray();
        int l = 0, r = chars.length - 1;
        while (l < r){
            boolean bool1 = set.contains(chars[l]);
            boolean bool2 = set.contains(chars[r]);
            if (bool1 && bool2){
                char tmp = chars[l];
                chars[l] = chars[r];
                chars[r] = tmp;
                l++;
                r--;
            }
            if (!bool1){
                l++;
            }
            if (!bool2){
                r--;
            }
        }
        return new String(chars);
    }
}
