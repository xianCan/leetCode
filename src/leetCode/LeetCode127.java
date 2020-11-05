package leetCode;

import java.util.*;

/**
 * @author xianCan
 * @date 2020/11/5 20:54
 *
 * 127. 单词接龙（中等）
 *
 *  给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：

    每次转换只能改变一个字母。
    转换过程中的中间单词必须是字典中的单词。
    说明:

    如果不存在这样的转换序列，返回 0。
    所有单词具有相同的长度。
    所有单词只由小写字母组成。
    字典中不存在重复的单词。
    你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
    示例 1:

    输入:
    beginWord = "hit",
    endWord = "cog",
    wordList = ["hot","dot","dog","lot","log","cog"]

    输出: 5

    解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
    返回它的长度 5。
    示例 2:

    输入:
    beginWord = "hit"
    endWord = "cog"
    wordList = ["hot","dot","dog","lot","log"]

    输出: 0

    解释: endWord "cog" 不在字典中，所以无法进行转换。

 */
public class LeetCode127 {

    private Set<String> wordSet;
    private Set<String> visited;
    private Queue<String> queue;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //第 1 步：先将 wordList 放到哈希表里，便于判断某个单词是否在 wordList 里
        wordSet = new HashSet<>(wordList);
        visited = new HashSet<>();
        if (!wordSet.contains(endWord)){
            return 0;
        }
        wordSet.remove(beginWord);

        // 第 2 步：图的广度优先遍历，必须使用队列和表示是否访问过的 visited 哈希表
        queue = new LinkedList<>();
        queue.offer(beginWord);
        visited.add(beginWord);

        // 第 3 步：开始广度优先遍历，包含起点，因此初始化的时候步数为 1
        int step=1;
        while (!queue.isEmpty()){
            int curSize = queue.size();
            while (curSize > 0){
                String word = queue.poll();
                // 如果 currentWord 能够修改 1 个字符与 endWord 相同，则返回 step + 1
                if (predicate(word, endWord)){
                    return step+1;
                }
                curSize--;
            }
            step++;
        }
        return 0;
    }

    private boolean predicate(String word, String endWord) {
        char[] chars = word.toCharArray();
        for (int i=0; i<chars.length; i++){
            char originChar = chars[i];
            for (char j='a'; j <='z'; j++){
                if (j==originChar){
                    continue;
                }
                chars[i] = j;
                String nextWord = String.valueOf(chars);
                if (wordSet.contains(nextWord)){
                    if (nextWord.equals(endWord)){
                        return true;
                    }
                    if (!visited.contains(nextWord)){
                        queue.add(nextWord);
                        visited.add(nextWord);
                    }
                }
            }
            chars[i] = originChar;
        }
        return false;
    }

    public static void main(String[] args) {
        int i = new LeetCode127().ladderLength("hit", "cog", Arrays.asList(new String[]{"hot","dot","dog","lot","log","cog"}));
        System.out.println(i);
    }
}
