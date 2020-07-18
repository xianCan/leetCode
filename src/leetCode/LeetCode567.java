package leetCode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author xianCan
 * @date 2020/7/18 10:25
 *
 * 567. 字符串的排列
 *
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。

    换句话说，第一个字符串的排列之一是第二个字符串的子串。

    示例1:

    输入: s1 = "ab" s2 = "eidbaooo"
    输出: True
    解释: s2 包含 s1 的排列之一 ("ba").
     

    示例2:

    输入: s1= "ab" s2 = "eidboaoo"
    输出: False
     

    注意：

    输入的字符串只包含小写字母
    两个字符串的长度都在 [1, 10,000] 之间
 */
public class LeetCode567 {

    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> need = new HashMap<>(), window = new HashMap<>();
        for (int i=0; i<s1.length(); i++){
            char c = s1.charAt(i);
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left=0, right=0, valid=0;
        while (right < s2.length()){
            char c = s2.charAt(right);
            if (need.containsKey(c)){
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (Objects.equals(need.get(c), window.get(c))){
                    valid++;
                }
            }
            right++;

            if ((right- left) > s1.length()){
                char c1 = s2.charAt(left);
                if (need.containsKey(c1)){
                    if (Objects.equals(window.get(c1), need.get(c1))){
                        valid--;
                    }
                    window.put(c1, window.get(c1) - 1);
                }
                left++;
            }
            if (valid == need.size())
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        boolean b = new LeetCode567().checkInclusion("ab", "eidboaoo");
        System.out.println(b);
    }
}
