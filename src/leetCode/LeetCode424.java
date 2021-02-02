package leetCode;

import java.util.Arrays;

/**
 * @author xianCan
 * @date 2021/2/2 21:24
 *
 * 424. 替换后的最长重复字符（中等）
 *
 *  给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换 k 次。在执行上述操作后，找到包含重复字母的最长子串的长度。

    注意：字符串长度 和 k 不会超过 104。

     

    示例 1：

    输入：s = "ABAB", k = 2
    输出：4
    解释：用两个'A'替换为两个'B',反之亦然。
    示例 2：

    输入：s = "AABABBA", k = 1
    输出：4
    解释：
    将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
    子串 "BBBB" 有最长重复字母, 答案为 4。

 */
public class LeetCode424 {

    /**
     *  我们维护一个数组 int[26] 来存储当前窗口中各个字母的出现次数，left 表示窗口的左边界，right 表示窗口右边界

        窗口扩张：left 不变，right++
        窗口滑动：left++，right++

        historyCharMax 保存滑动窗口内相同字母出现次数的 历史 最大值，通过判断窗口宽度 (right - left + 1) 是否大于 historyCharMax + K

     来决定窗口是否做滑动，否则窗口就扩张。

        这一题最关键的就是为什么需要记录历史窗口中最大的字符重复个数，而不是记录当前窗口的最大的字符重复个数。在我看来，由于这道题目要
     求解的是最长重复子串，如果当前窗口最大字符重复个数小于历史窗口的最大字符重复个数，完全可以将此窗口忽略掉，因为它必然不可能是最长重
     复子串。只有当历史窗口的最大字符重复个数更新时，其最大长度才会进行相应的更新。
     * @param s
     * @param k
     * @return
     */
    public int characterReplacement(String s, int k) {
        //nums记录的是【窗口里的】字符出现的次数，所以左窗口右移后，记得将移出去的元素个数-1
        int[] nums = new int[26];

        //历史窗口中出现次数最多字母的次数
        //因为maxn只是影响窗口的扩大，而窗口大小只会扩大不会缩小，所以即使曾经的出现次数最多的char被移除一个，maxn不准确了也不影响结果，因为此时maxn不会导致窗口扩大
        int maxn = 0;
        int len = s.length(), left = 0, right = 0;
        while (right < len){
            char ch = s.charAt(right);
            nums[ch - 'A']++;

            maxn = Math.max(maxn, nums[ch - 'A']);
            if (right - left + 1 - maxn > k){
                nums[s.charAt(left) - 'A']--;
                left++;
            }
            right++;
        }

        return right - left;
    }

    /**
     * 维护每个区间的最大值，时间复杂度高很多
     * @param s
     * @param k
     * @return
     */
    public int characterReplacement2(String s, int k) {
        //nums记录的是【窗口里的】字符出现的次数，所以左窗口右移后，记得将移出去的元素个数-1
        int[] nums = new int[26];

        int len = s.length(), left = 0, right = 0, res = 0;
        while (right < len){
            nums[s.charAt(right) - 'A']++;

            // 维护 maxCount 这里就得写成 while
            while (right - left > Arrays.stream(nums).max().getAsInt() + k){
                nums[s.charAt(left) - 'A']--;
                left++;
            }
            right++;
            res = Math.max(res, right - left);
        }

        return res;
    }

    public static void main(String[] args) {
        int abab = new LeetCode424().characterReplacement("ABAB", 2);
        System.out.println(abab);
    }
}
