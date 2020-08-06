package leetCode;

import java.util.*;

/**
 * @author xianCan
 * @date 2020/8/6 22:53
 *
 * 336. 回文对（困难）
 *
 *  给定一组 互不相同 的单词， 找出所有不同 的索引对(i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。

    示例 1：

    输入：["abcd","dcba","lls","s","sssll"]
    输出：[[0,1],[1,0],[3,2],[2,4]]
    解释：可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]
    示例 2：

    输入：["bat","tab","cat"]
    输出：[[0,1],[1,0]]
    解释：可拼接成的回文串为 ["battab","tabbat"]

 */
public class LeetCode336 {

    /**
     * 暴力，超时...
     * 时间复杂度O(N^2*M)
     * @param words
     * @return
     */
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        int length = words.length;
        for (int i=0; i<length; i++){
            for (int j=i+1; j<length; j++){
                if (isPalindrome(words[i] + words[j])){
                    res.add(Arrays.asList(i, j));
                }
                if (isPalindrome(words[j] + words[i])){
                    res.add(Arrays.asList(j, i));
                }
            }
        }
        return res;
    }

    private boolean isPalindrome(String str){
        int left=0, right=str.length()-1;
        while (left < right){
            if (str.charAt(left++) != str.charAt(right--))
                return false;
        }
        return true;
    }

    /**
     * 枚举前缀和后缀
     * 时间复杂度O(N*M^2)
     * @param words
     * @return
     */
    public List<List<Integer>> palindromePairs2(String[] words){
        List<List<Integer>> res = new ArrayList<>();
        int length = words.length;
        //存储反转字符串
        Map<String, Integer> map = new HashMap<>();
        for (int i=0; i<length; i++){
            map.put(new StringBuilder(words[i]).reverse().toString(), i);
        }

        for (int i=0; i<length; i++){
            String word = words[i];
            int len = word.length();

            //处理特殊情况
            if (isPalindrome(word, 0, len-1) && map.containsKey("") && !"".equals(word)) {
                res.add(Arrays.asList(map.get(""), i));
            }

            for (int j=0; j<len; j++){
                if (isPalindrome(word, j, len-1)){
                    Integer rev = map.get(word.substring(0, j));
                    if (rev != null && rev != i){
                        res.add(Arrays.asList(i, rev));
                    }
                }
                if (j != 0 && isPalindrome(word, 0, j-1)){
                    Integer rev = map.get(word.substring(j, len));
                    if (rev != null && rev != i){
                        res.add(Arrays.asList(rev, i));
                    }
                }
            }
        }
        return res;
    }

    private boolean isPalindrome(String str, int left, int right){
        while (left < right){
            if (str.charAt(left++) != str.charAt(right--))
                return false;
        }
        return true;
    }
}
