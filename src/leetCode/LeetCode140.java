package leetCode;

import java.util.*;

/**
 * @author xianCan
 * @date 2020/11/1 0:17
 *
 * 140. 单词拆分 II（困难）
 *
 *  给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。

    说明：

    分隔时可以重复使用字典中的单词。
    你可以假设字典中没有重复的单词。
    示例 1：

    输入:
    s = "catsanddog"
    wordDict = ["cat", "cats", "and", "sand", "dog"]
    输出:
    [
      "cats and dog",
      "cat sand dog"
    ]
    示例 2：

    输入:
    s = "pineapplepenapple"
    wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
    输出:
    [
      "pine apple pen apple",
      "pineapple pen apple",
      "pine applepen apple"
    ]
    解释: 注意你可以重复使用字典中的单词。
    示例 3：

    输入:
    s = "catsandog"
    wordDict = ["cats", "dog", "sand", "and", "cat"]
    输出:
    []

 */
public class LeetCode140 {

    private Set<String> wordSet;
    private String s;
    private Map<Integer, List<List<String>>> memo;

    /**
     * 记忆化回溯
     * @param s
     * @param wordDict
     * @return
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        this.memo = new HashMap<>();
        this.wordSet = new HashSet<>(wordDict);
        this.s = s;
        List<String> ans = new ArrayList<>();
        List<List<String>> listList = recursive(0);
        for (List<String> list : listList){
            ans.add(String.join(" ", list));
        }
        return ans;
    }

    private List<List<String>> recursive(int start){
        if (!memo.containsKey(start)){
            List<List<String>> res = new ArrayList<>();
            if (start == s.length()){
                res.add(new LinkedList<>());
            }
            for (int i=start+1; i<=s.length(); i++){
                String word = s.substring(start, i);
                if (wordSet.contains(word)){
                    List<List<String>> listList = recursive(i);
                    for (List<String> list : listList){
                        LinkedList<String> wordBreak = new LinkedList<>(list);
                        wordBreak.offerFirst(word);
                        res.add(wordBreak);
                    }
                }
            }
            memo.put(start, res);
        }
        return memo.get(start);
    }

    public static void main(String[] args) {
        List<String> list = new LeetCode140().wordBreak("catsanddog", Arrays.asList(new String[]{"cat", "cats", "and", "sand", "dog"}));
        System.out.println(list);
    }
}
