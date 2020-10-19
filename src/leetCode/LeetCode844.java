package leetCode;

/**
 * @author xianCan
 * @date 2020/10/19 22:22
 *
 * 844. 比较含退格的字符串（简单）
 *
 *  给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。

    注意：如果对空文本输入退格字符，文本继续为空。

     

    示例 1：

    输入：S = "ab#c", T = "ad#c"
    输出：true
    解释：S 和 T 都会变成 “ac”。
    示例 2：

    输入：S = "ab##", T = "c#d#"
    输出：true
    解释：S 和 T 都会变成 “”。
    示例 3：

    输入：S = "a##c", T = "#a#c"
    输出：true
    解释：S 和 T 都会变成 “c”。
    示例 4：

    输入：S = "a#c", T = "b"
    输出：false
    解释：S 会变成 “c”，但 T 仍然是 “b”。
     

    提示：

    1 <= S.length <= 200
    1 <= T.length <= 200
    S 和 T 只含有小写字母以及字符 '#'。
     

    进阶：

    你可以用 O(N) 的时间复杂度和 O(1) 的空间复杂度解决该问题吗？

 */
public class LeetCode844 {

    public boolean backspaceCompare(String S, String T) {
        StringBuilder builder1 = new StringBuilder();
        StringBuilder builder2 = new StringBuilder();

        for (int i=0; i<S.length(); i++){
            if (S.charAt(i) != '#'){
                builder1.append(S.charAt(i));
            } else if (builder1.length() > 0){
                builder1.deleteCharAt(builder1.length()-1);
            }
        }

        for (int i=0; i<T.length(); i++){
            if (T.charAt(i) != '#'){
                builder2.append(T.charAt(i));
            } else if (builder2.length() > 0){
                builder2.deleteCharAt(builder2.length()-1);
            }
        }

        return builder1.toString().equals(builder2.toString());
    }
}
