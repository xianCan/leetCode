package leetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xianCan
 * @date 2020/10/22 21:25
 *
 * 763. 划分字母区间（中等）
 *
 *  字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。

    示例 1：

    输入：S = "ababcbacadefegdehijhklij"
    输出：[9,7,8]
    解释：
    划分结果为 "ababcbaca", "defegde", "hijhklij"。
    每个字母最多出现在一个片段中。
    像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
     

    提示：

    S的长度在[1, 500]之间。
    S只包含小写字母 'a' 到 'z' 。

 */
public class LeetCode763 {

    /**
     *    由于同一个字母只能出现在同一个片段，显然同一个字母的第一次出现的下标位置和最后一次出现的下标位置必须出现在同一个片段。
     * 因此需要遍历字符串，得到每个字母最后一次出现的下标位置。在得到每个字母最后一次出现的下标位置之后，可以使用贪心算法和双指
     * 针的方法将字符串划分为尽可能多的片段
     * @param S
     * @return
     */
    public List<Integer> partitionLabels(String S) {
        if (S == null || S.length() == 0)return null;
        //用map来存储每个字母的最后的索引
        Map<Character, Integer> map = new HashMap<>();
        int len = S.length();
        for (int i=0; i<len; i++){
            map.put(S.charAt(i), i);
        }

        List<Integer> ans = new ArrayList<>();
        int left=0, right=0;
        for (int i=0; i<len; i++){
            Integer idx = map.get(S.charAt(i));
            //取最大的索引
            right = Math.max(right, idx);
            //如果i遍历到最大索引，可以取一次区间
            if (i==right){
                ans.add(right-left+1);
                left = right+1;
                right = left;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        List<Integer> list = new LeetCode763().partitionLabels("ababcbacadefegdehijhklij");
        System.out.println(list);
    }
}
