package leetCode;

/**
 * @author xianCan
 * @date 2020/8/24 21:59
 *
 * 459. 重复的子字符串（简单）
 *
 *  给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。

    示例 1:

    输入: "abab"

    输出: True

    解释: 可由子字符串 "ab" 重复两次构成。
    示例 2:

    输入: "aba"

    输出: False
    示例 3:

    输入: "abcabcabcabc"

    输出: True

    解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)

 */
public class LeetCode459 {
    /**
     * 方法一：枚举
     *
     * 时间复杂度：O(N^2)
     * 空间复杂度：O(1)
     *
     * 如果一个长度为 n 的字符串 s 可以由它的一个长度为 n' 的子串 s' 重复多次构成，那么：
            n 一定是 n' 的倍数；
            s'一定是 s  的前缀；
            对于任意的 i ∈ [n′,n)，有 s[i] = s[i-n']。
       也就是说，s 中长度为 n' 的前缀就是 s'，并且在这之后的每一个位置上的字符 s[i]，都需要与它之前的第 n'个字符 s[i-n'] 相同。

       因此，我们可以从小到大枚举 n'，并对字符串 ss 进行遍历，进行上述的判断。注意到一个小优化是，因为子串至少需要重复一次，所以 n'

       不会大于 n 的一半，我们只需要在 [1, n/2] 的范围内枚举 n' 即可。
     * @param s
     * @return
     */
    public boolean repeatedSubstringPattern(String s) {
        int length = s.length();
        for (int i=1; i * 2 <= length; i++){
            if (length % i == 0){
                boolean match = true;
                for (int j=i; j < length; j++){
                    if (s.charAt(j) != s.charAt(j-i)){
                        match = false;
                        break;
                    }
                }
                if (match){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 方法二：作弊写法
     *
     * 可以把字符串 s 写成 s′s′⋯s′s′
     *
     * 如果我们移除字符串 s 的前 n'个字符（即一个完整的 s′），再将这些字符保持顺序添加到剩余字符串的末尾，那么得到的字符串仍然是 s。
     * 由于 1≤ n′ < n，那么如果将两个 s 连在一起，并移除第一个和最后一个字符，那么得到的字符串一定包含 s，即 s 是它的一个子串。
     *
     * 因此我们可以考虑这种方法：我们将两个 s 连在一起，并移除第一个和最后一个字符。如果 s 是该字符串的子串，那么 s 就满足题目要求。
     *
     * @param s
     * @return
     */
    public boolean repeatedSubstringPattern2(String s) {
        return (s+s).indexOf(s, 1) != s.length();
    }

    /**
     * 方法三：KMP算法
     *
     *
     * @param s
     * @return
     */
    public boolean repeatedSubstringPattern3(String s){
        return false;
    }
}
