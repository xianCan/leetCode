package leetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xianCan
 * @date 2021/9/9 21:29
 *
 * 68. 文本左右对齐（困难）
 *
 *  给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。

    你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。

    要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。

    文本的最后一行应为左对齐，且单词之间不插入额外的空格。

    说明:

    单词是指由非空格字符组成的字符序列。
    每个单词的长度大于 0，小于等于 maxWidth。
    输入单词数组 words 至少包含一个单词。
    示例:

    输入:
    words = ["This", "is", "an", "example", "of", "text", "justification."]
    maxWidth = 16
    输出:
    [
       "This    is    an",
       "example  of text",
       "justification.  "
    ]
    示例 2:

    输入:
    words = ["What","must","be","acknowledgment","shall","be"]
    maxWidth = 16
    输出:
    [
      "What   must   be",
      "acknowledgment  ",
      "shall be        "
    ]
    解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
         因为最后一行应为左对齐，而不是左右两端对齐。
    第二行同样为左对齐，这是因为这行只包含一个单词。
    示例 3:

    输入:
    words = ["Science","is","what","we","understand","well","enough","to","explain",
             "to","a","computer.","Art","is","everything","else","we","do"]
    maxWidth = 20
    输出:
    [
      "Science  is  what we",
    "understand      well",
      "enough to explain to",
      "a  computer.  Art is",
      "everything  else  we",
      "do                  "
    ]


 */
public class LeetCode68 {

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();
        int n = words.length, l = 0, r = 0, totalLen = 0, wordLen = 0;
        while (r < n){
            do {
                int tmp = totalLen + words[r].length() + (r - l > 0 ? 1 : 0);
                if (tmp <= maxWidth){
                    totalLen = tmp;
                    wordLen += words[r].length();
                    r++;
                } else {
                    break;
                }
            } while (totalLen <= maxWidth && r < n);
            List<Integer> splitLens = getSplitLen(maxWidth - wordLen, r - l - 1);
            StringBuilder builder = new StringBuilder();
            if (r >= n){
                for (int i = l; i < r; i++){
                    builder.append(words[i]);
                    if (builder.length() < maxWidth){
                        builder.append(" ");
                    }
                }
                for (int j = builder.length(); j < maxWidth; j++){
                    builder.append(" ");
                }
            } else {
                for (int i = l, j = 0; i < n; i++, j++){
                    builder.append(words[i]);
                    for (int k = 0; j < splitLens.size() && k < splitLens.get(j); k++){
                        builder.append(" ");
                    }
                }
            }
            ans.add(builder.toString());
            l = r;
            totalLen = 0;
            wordLen = 0;
        }
        return ans;
    }

    private List<Integer> getSplitLen(int splitLen, int n){
        LinkedList<Integer> ans = new LinkedList<>();
        while (splitLen > 0 && n > 1){
            int tmp = splitLen / n;
            ans.addFirst(tmp);
            splitLen -= tmp;
            n--;
        }
        ans.addFirst(splitLen);
        return ans;
    }

    public static void main(String[] args) {
        List<String> list = new LeetCode68().fullJustify(new String[]{"This", "is", "an", "example", "of", "text", "justification."}, 16);
        System.out.println(list);
    }
}
