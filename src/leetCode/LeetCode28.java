package leetCode;

/**
 * @author xianCan
 * @date 2021/4/20 21:11
 *
 * 28. 实现 strStr()（简单）
 *
 *  实现 strStr() 函数。

    给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回  -1 。

     

    说明：

    当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。

    对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与 C 语言的 strstr() 以及 Java 的 indexOf() 定义相符。

     

    示例 1：

    输入：haystack = "hello", needle = "ll"
    输出：2
    示例 2：

    输入：haystack = "aaaaa", needle = "bba"
    输出：-1
    示例 3：

    输入：haystack = "", needle = ""
    输出：0
     

    提示：

    0 <= haystack.length, needle.length <= 5 * 104
    haystack 和 needle 仅由小写英文字符组成

 */
public class LeetCode28 {

    /**
     * 暴力
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        int n = haystack.length(), m = needle.length();
        for (int i = 0; i + m <= n; i++) {
            boolean flag = true;
            for (int j = 0; j < m; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return i;
            }
        }
        return -1;
    }

    /**
     * KMP
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr2(String haystack, String needle) {
        if (needle.length() == 0){
            return 0;
        }
        int[] prefix = getPrefix(needle);
        //j代表needle已匹配长度
        int j = 0;
        for (int i = 0; i < haystack.length(); i++){
            if (j > 0 && haystack.charAt(i) != needle.charAt(j)){
                j = prefix[j - 1];
            }
            if (haystack.charAt(i) == needle.charAt(j)){
                j++;
            }
            if (j == needle.length()){
                return i - j + 1;
            }
        }
        return -1;
    }

    private int[] getPrefix(String needle){
        int[] prefix = new int[needle.length()];
        prefix[0] = 0;
        //j代表前缀的长度
        int j = 0;

        //i代表后缀的长度
        for (int i = 1; i < needle.length(); i++){
            while (j > 0 && needle.charAt(i) != needle.charAt(j)){
                j = prefix[j - 1];
            }
            if (needle.charAt(i) == needle.charAt(j)){
                j++;
            }
            prefix[i] = j;
        }

        return prefix;
    }
}
