package leetCode;

/**
 * @author xianCan
 * @date 2020/8/30 0:04
 *
 * 557. 反转字符串中的单词 III
 *
 *  给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。

    示例：

    输入："Let's take LeetCode contest"
    输出："s'teL ekat edoCteeL tsetnoc"
     

    提示：

    在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。

 */
public class LeetCode557 {

    public String reverseWords(String s) {
        if (s==null || s.length()==0) return "";
        String[] strArr = s.split(" ");
        StringBuilder res = new StringBuilder();
        for (String str : strArr){
            StringBuilder builder = new StringBuilder();
            builder.append(str);
            builder.reverse();

            res.append(builder).append(" ");
        }
        return res.toString().trim();
    }

    public static void main(String[] args) {
        String s = new LeetCode557().reverseWords("Let's take LeetCode contest");
        System.out.println(s);
    }
}
