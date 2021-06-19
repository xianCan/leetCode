package offer;

/**
 * @author xianCan
 * @date 2021/6/19 10:25
 *
 * 剑指 Offer 58 - II. 左旋转字符串（简单）
 *
 *  字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。

     

    示例 1：

    输入: s = "abcdefg", k = 2
    输出: "cdefgab"
    示例 2：

    输入: s = "lrloseumgh", k = 6
    输出: "umghlrlose"
     

    限制：

    1 <= k < s.length <= 10000

 */
public class Offer58 {

    public String reverseLeftWords(String s, int n) {
        StringBuilder builder = new StringBuilder();
        for (int i = n; i < s.length(); i++){
            builder.append(s.charAt(i));
        }

        for (int i = 0; i < n; i++){
            builder.append(s.charAt(i));
        }

        return builder.toString();
    }

}
