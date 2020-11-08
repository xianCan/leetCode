package offer;

/**
 * @author xianCan
 * @date 2020/11/8 20:55
 *
 * 剑指 Offer 05. 替换空格（简单）
 *
 *  请实现一个函数，把字符串 s 中的每个空格替换成"%20"。

    示例 1：

    输入：s = "We are happy."
    输出："We%20are%20happy."
     

    限制：

    0 <= s 的长度 <= 10000

 */
public class Offer05 {

    public String replaceSpace(String s) {
        int len=0;
        for (int i=0; i<s.length(); i++){
            len += s.charAt(i)==' ' ? 3 : 1;
        }

        char[] chars = new char[len];
        for (int i=0,j=0; i<s.length(); i++){
            char c = s.charAt(i);
            if (c == ' '){
                chars[j++] = '%';
                chars[j++] = '2';
                chars[j++] = '0';
            } else {
                chars[j++] = c;
            }
        }
        return new String(chars);
    }
}
