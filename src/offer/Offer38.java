package offer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xianCan
 * @date 2021/6/22 21:40
 *
 * 剑指 Offer 38. 字符串的排列（中等）
 *
 *  输入一个字符串，打印出该字符串中字符的所有排列。

     

    你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。

     

    示例:

    输入：s = "abc"
    输出：["abc","acb","bac","bca","cab","cba"]
     

    限制：

    1 <= s 的长度 <= 8

 */
public class Offer38 {

    public String[] permutation(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        boolean[] used = new boolean[chars.length];
        List<String> ans = new ArrayList<>();
        helper(chars, used, new StringBuilder(), ans);
        return ans.toArray(new String[ans.size()]);
    }

    private void helper(char[] chars, boolean[] used, StringBuilder builder, List<String> ans){
        if (chars.length == builder.length()){
            ans.add(builder.toString());
        }

        for (int i = 0; i < chars.length; i++){
            if (i > 0 && chars[i] == chars[i - 1] && used[i - 1]){
                continue;
            }
            if (!used[i]){
                builder.append(chars[i]);
                used[i] = true;
                helper(chars, used, builder, ans);
                used[i] = false;
                builder.deleteCharAt(builder.length() - 1);
            }
        }
    }
}
