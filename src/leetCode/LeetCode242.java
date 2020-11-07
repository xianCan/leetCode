package leetCode;

/**
 * @author xianCan
 * @date 2020/11/7 19:57
 *
 * 242. 有效的字母异位词（简单）
 *
 *  给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。

    示例 1:

    输入: s = "anagram", t = "nagaram"
    输出: true
    示例 2:

    输入: s = "rat", t = "car"
    输出: false
    说明:
    你可以假设字符串只包含小写字母。

    进阶:
    如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？

 */
public class LeetCode242 {

    public boolean isAnagram(String s, String t) {
        int[] a1 = new int[26];

        for (int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            a1[ch-'a']++;
        }

        for (int i=0; i<t.length(); i++){
            char ch = t.charAt(i);
            a1[ch-'a']--;
        }

        for (int i=0; i<26; i++){
            if (a1[i] != 0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        boolean anagram = new LeetCode242().isAnagram("rat", "car");
        System.out.println(anagram);
    }
}
