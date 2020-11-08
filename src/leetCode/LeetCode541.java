package leetCode;

/**
 * @author xianCan
 * @date 2020/11/8 20:22
 *
 * 541. 反转字符串 II（简单）
 *
 *  给定一个字符串 s 和一个整数 k，你需要对从字符串开头算起的每隔 2k 个字符的前 k 个字符进行反转。

    如果剩余字符少于 k 个，则将剩余字符全部反转。
    如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
     

    示例:

    输入: s = "abcdefg", k = 2
    输出: "bacdfeg"
     

    提示：

    该字符串只包含小写英文字母。
    给定字符串的长度和 k 在 [1, 10000] 范围内。

 */
public class LeetCode541 {

    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        int count = len%k==0 ? len/k : len/k+1;
        for (int i=1; i <= count; i++){
            if (i % 2 == 1){
                int last = i * k - 1;
                reverse(chars, (i-1)*k, last>=len ? len-1:last);
            }
        }
        return new String(chars);
    }

    private void reverse(char[] chars, int i, int j){
        while (i < j){
            char tmp = chars[i];
            chars[i] = chars[j];
            chars[j] = tmp;
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        String s = new LeetCode541().reverseStr("abcd", 2);
        System.out.println(s);
    }
}
