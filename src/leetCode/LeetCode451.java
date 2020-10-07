package leetCode;

import java.util.PriorityQueue;

/**
 * @author xianCan
 * @date 2020/10/7 21:24
 *
 * 451. 根据字符出现频率排序（中等）
 *
 *  给定一个字符串，请将字符串里的字符按照出现的频率降序排列。

    示例 1:

    输入:
    "tree"

    输出:
    "eert"

    解释:
    'e'出现两次，'r'和't'都只出现一次。
    因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
    示例 2:

    输入:
    "cccaaa"

    输出:
    "cccaaa"

    解释:
    'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
    注意"cacaca"是不正确的，因为相同的字母必须放在一起。
    示例 3:

    输入:
    "Aabb"

    输出:
    "bbAa"

    解释:
    此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
    注意'A'和'a'被认为是两种不同的字符。


 */
public class LeetCode451 {

    private int[] freq;

    /**
     * 快排partition思想
     * @param s
     * @return
     */
    public String frequencySort(String s) {
        if (s == null || s.length() < 2) return s;
        char[] charArray = s.toCharArray();

        this.freq = new int[128];
        for (char c : charArray){
            this.freq[c]++;
        }

        quickSort(charArray, 0, s.length()-1);
        return new String(charArray);
    }

    private void quickSort(char[] charArray, int left, int right){
        if (left >= right || right-left < 2){
            return;
        }

        int pivot = charArray[right];
        int lt = left-1, i = left;
        int gt = right;

        while (i < gt){
            if (freq[charArray[i]] > freq[pivot]){
                lt++;
                swap(charArray, i, lt);
                i++;
            } else if (charArray[i] == pivot){
                i++;
            } else {
                gt--;
                swap(charArray, i, gt);
            }
        }
        swap(charArray, gt, right);
        quickSort(charArray, left, lt);
        quickSort(charArray, gt+1, right);
    }

    private void swap(char[] charArray, int idx1, int idx2){
        char tmp = charArray[idx1];
        charArray[idx1] = charArray[idx2];
        charArray[idx2] = tmp;
    }

    /**
     *
     * @param s
     * @return
     */
    public String frequencySort2(String s) {
        if (s == null || s.length() < 2) return s;

        int[] letters = new int[128];
        for (char c : s.toCharArray()){
            letters[c]++;
        }

        PriorityQueue<Character> heap = new PriorityQueue<>(128, (a, b) -> Integer.compare(letters[b], letters[a]));
        StringBuilder builder = new StringBuilder();

        for (int i=0; i < letters.length; i++){
            if (letters[i] != 0){
                heap.offer((char) i);
            }
        }

        while (!heap.isEmpty()){
            Character c = heap.poll();
            while (letters[c]-- > 0){
                builder.append(c);
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        String s = new LeetCode451().frequencySort("cacaca");
        System.out.println(s);
    }
}
