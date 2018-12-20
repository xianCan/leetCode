package leetCode;

/**
 * @authod xianCan
 * @date 2018/12/19 16:07
 *
 * 题目描述：
 *     编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1:
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 *
 * 示例 2:
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 *
 * 说明:
 * 所有输入只包含小写字母 a-z 。
 */
public class LeetCode14 {
    /**
     * 外循环遍历第一个字符串，内循环则从后面的字符串中拿出第i个和第一个字符串比较
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        char ch;
        for (int i=0;i<strs[0].length();i++){
            ch = strs[0].charAt(i);
            for (int j=1;j<strs.length;j++){
                if (i>strs[j].length() || ch != strs[j].charAt(i)){
                    flag = true;
                    break;
                }
            }
            if (flag)
                break;
            else sb.append(ch);
        }

        return sb.toString();
    }
}
