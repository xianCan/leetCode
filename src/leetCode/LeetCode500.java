package leetCode;

import java.util.*;

/**
 * @author xianCan
 * @date 2021/10/31 15:13
 *
 * 500. 键盘行（简单）
 *
 *  给你一个字符串数组 words ，只返回可以使用在 美式键盘 同一行的字母打印出来的单词。键盘如下图所示。

    美式键盘 中：

    第一行由字符 "qwertyuiop" 组成。
    第二行由字符 "asdfghjkl" 组成。
    第三行由字符 "zxcvbnm" 组成。

    示例 1：

    输入：words = ["Hello","Alaska","Dad","Peace"]
    输出：["Alaska","Dad"]
    示例 2：

    输入：words = ["omk"]
    输出：[]
    示例 3：

    输入：words = ["adsdf","sfd"]
    输出：["adsdf","sfd"]
     

    提示：

    1 <= words.length <= 20
    1 <= words[i].length <= 100
    words[i] 由英文字母（小写和大写字母）组成

 */
public class LeetCode500 {

    private static Set<Character> set1 = new HashSet<>();
    private static Set<Character> set2 = new HashSet<>();
    private static Set<Character> set3 = new HashSet<>();

    static {
        set1.addAll(Arrays.asList('q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P'));
        set2.addAll(Arrays.asList('a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L'));
        set3.addAll(Arrays.asList('z', 'x', 'c', 'v', 'b', 'n', 'm', 'Z', 'X', 'C', 'V', 'B', 'N', 'M'));
    }

    public String[] findWords(String[] words) {
        List<String> list = new ArrayList<>();
        for (String word : words){
            if (function(word)){
                list.add(word);
            }
        }
        String[] ans = new String[list.size()];
        for (int i = 0; i < list.size(); i++){
            ans[i] = list.get(i);
        }
        return ans;
    }

    private boolean function(String word){
        char fir = word.charAt(0);
        Set<Character> set;
        if (set1.contains(fir)){
            set = set1;
        } else if (set2.contains(fir)){
            set = set2;
        } else {
            set = set3;
        }

        for (int i = 1; i < word.length(); i++){
            if (!set.contains(word.charAt(i))){
                return false;
            }
        }
        return true;
    }
}
