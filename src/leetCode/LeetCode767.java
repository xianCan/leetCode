package leetCode;

import java.util.PriorityQueue;

/**
 * @author xianCan
 * @date 2020/11/30 21:03
 *
 * 767. 重构字符串（中等）
 *
 *  给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。

    若可行，输出任意可行的结果。若不可行，返回空字符串。

    示例 1:

    输入: S = "aab"
    输出: "aba"
    示例 2:

    输入: S = "aaab"
    输出: ""
    注意:

    S 只包含小写字母并且长度在[1, 500]区间内。

 */
public class LeetCode767 {

    public String reorganizeString(String S) {
        int len = S.length(), maxCount=0;
        if (len < 2){
            return S;
        }
        int[] map = new int[26];
        for (int i=0; i<len; i++){
            map[S.charAt(i) - 'a']++;
            maxCount = Math.max(maxCount, map[S.charAt(i) - 'a']);
        }
        if (maxCount > (len + 1) / 2){
            return "";
        }

        PriorityQueue<Character> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(map[o2-'a'], map[o1-'a']));
        for (char i='a'; i<='z'; i++){
            if (map[i - 'a'] > 0){
                queue.offer(i);
            }
        }

        StringBuilder builder = new StringBuilder();
        while (queue.size() > 1){
            Character let1 = queue.poll();
            Character let2 = queue.poll();
            builder.append(let1);
            builder.append(let2);
            map[let1 - 'a']--;
            map[let2 - 'a']--;
            if (map[let1 - 'a'] > 0){
                queue.offer(let1);
            }
            if (map[let2 - 'a'] > 0){
                queue.offer(let2);
            }
        }
        if (queue.size() > 0){
            builder.append(queue.poll());
        }

        return builder.toString();
    }

    public static void main(String[] args) {
        //wawwivhwfrgontvvfggh
        String s = new LeetCode767().reorganizeString("aaabc");
        System.out.println(s);
    }
}
