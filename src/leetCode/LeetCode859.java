package leetCode;

/**
 * @author xianCan
 * @date 2021/11/23 21:25
 *
 * 859. 亲密字符串（简单）
 *
 *  给你两个字符串 s 和 goal ，只要我们可以通过交换 s 中的两个字母得到与 goal 相等的结果，就返回 true ；否则返回 false 。

    交换字母的定义是：取两个下标 i 和 j （下标从 0 开始）且满足 i != j ，接着交换 s[i] 和 s[j] 处的字符。

    例如，在 "abcd" 中交换下标 0 和下标 2 的元素可以生成 "cbad" 。
     

    示例 1：

    输入：s = "ab", goal = "ba"
    输出：true
    解释：你可以交换 s[0] = 'a' 和 s[1] = 'b' 生成 "ba"，此时 s 和 goal 相等。
    示例 2：

    输入：s = "ab", goal = "ab"
    输出：false
    解释：你只能交换 s[0] = 'a' 和 s[1] = 'b' 生成 "ba"，此时 s 和 goal 不相等。
    示例 3：

    输入：s = "aa", goal = "aa"
    输出：true
    解释：你可以交换 s[0] = 'a' 和 s[1] = 'a' 生成 "aa"，此时 s 和 goal 相等。
    示例 4：

    输入：s = "aaaaaaabc", goal = "aaaaaaacb"
    输出：true
     

    提示：

    1 <= s.length, goal.length <= 2 * 104
    s 和 goal 由小写英文字母组成

 */
public class LeetCode859 {

    public boolean buddyStrings(String s, String goal) {
        int len1 = s.length(), len2 = goal.length();
        if (len1 <= 1 || len2 <= 1 || len1 != len2){
            return false;
        }
        int[] nums = new int[26];
        char[] chars = s.toCharArray();
        Integer p = null, q = null;
        for (int i = 0; i < len1; i++){
            nums[chars[i] - 'a']++;
            if (chars[i] != goal.charAt(i)){
                if (p == null){
                    p = i;
                } else if (q == null){
                    q = i;
                } else {
                    break;
                }
            }
        }

        if (p == null && q == null){
            for (int num : nums){
                if (num >= 2){
                    return true;
                }
            }
            return false;
        } else if(p == null || q == null){
            return false;
        }

        char tmp = chars[p];
        chars[p] = chars[q];
        chars[q] = tmp;

        for (int i = 0; i < len1; i++){
            if (chars[i] != goal.charAt(i)){
                return false;
            }
        }
        return true;
    }
}
