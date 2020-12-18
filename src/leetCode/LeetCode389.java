package leetCode;

/**
 * @author xianCan
 * @date 2020/12/18 23:21
 *
 * 389. 找不同（简单）
 *
 *  给定两个字符串 s 和 t，它们只包含小写字母。

    字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。

    请找出在 t 中被添加的字母。

     

    示例 1：

    输入：s = "abcd", t = "abcde"
    输出："e"
    解释：'e' 是那个被添加的字母。
    示例 2：

    输入：s = "", t = "y"
    输出："y"
    示例 3：

    输入：s = "a", t = "aa"
    输出："a"
    示例 4：

    输入：s = "ae", t = "aea"
    输出："a"
     

    提示：

    0 <= s.length <= 1000
    t.length == s.length + 1
    s 和 t 只包含小写字母

 */
public class LeetCode389 {

    public char findTheDifference(String s, String t) {
        int[] counts = new int[26];
        for (int i=0; i < s.length(); i++){
            counts[s.charAt(i) - 'a']++;
        }

        for (int i=0; i < t.length(); i++){
            char ch = t.charAt(i);
            if (counts[ch - 'a'] == 0){
                return ch;
            } else {
                counts[ch - 'a']--;
            }
        }

        throw new IllegalArgumentException("输入的参数不正确！");
    }
}
