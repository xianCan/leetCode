package leetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xianCan
 * @date 2020/8/26 22:00
 *
 * 17. 电话号码的字母组合（中等）
 *
 *  给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。

    给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。

    示例:

    输入："23"
    输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
    说明:
    尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。

 */
public class LeetCode17 {

    private List<String> res = new ArrayList<>();
    private Map<Character, String> map = new HashMap<>();
    private String digits;

    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0){
            return res;
        }
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        this.digits = digits;
        recursion(0, new StringBuilder());
        return res;
    }

    private void recursion(int cur, StringBuilder builder){
        if (cur == digits.length()){
            res.add(builder.toString());
            return;
        }
        for (char c : map.get(digits.charAt(cur)).toCharArray()){
            builder.append(c);
            recursion(cur+1, builder);
            builder.deleteCharAt(builder.length()-1);
        }
    }

    public static void main(String[] args) {
        List<String> strings = new LeetCode17().letterCombinations("223");
        for (String str : strings){
            System.out.println(str);
        }
        int i = 'a';
        System.out.println(i);
    }
}
