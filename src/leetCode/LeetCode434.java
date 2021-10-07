package leetCode;

/**
 * @author xianCan
 * @date 2021/10/7 7:59
 *
 * 434. 字符串中的单词数（简单）
 *
 *  统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。

    请注意，你可以假定字符串里不包括任何不可打印的字符。

    示例:

    输入: "Hello, my name is John"
    输出: 5
    解释: 这里的单词是指连续的不是空格的字符，所以 "Hello," 算作 1 个单词。

 */
public class LeetCode434 {

    public int countSegments(String s) {
        String[] strings = s.split(" ");
        int ans = 0;
        for (String str : strings){
            if (!" ".equals(str)){
                ans++;
            }
        }
        return ans;
    }
}
