package leetCode;

/**
 * @author xianCan
 * @date 2021/5/11 21:35
 *
 * 1734. 解码异或后的排列（中等）
 *
 *  给你一个整数数组 perm ，它是前 n 个正整数的排列，且 n 是个 奇数 。

    它被加密成另一个长度为 n - 1 的整数数组 encoded ，满足 encoded[i] = perm[i] XOR perm[i + 1] 。比方说，如果 perm = [1,3,2] ，那么 encoded = [2,1] 。

    给你 encoded 数组，请你返回原始数组 perm 。题目保证答案存在且唯一。

     

    示例 1：

    输入：encoded = [3,1]
    输出：[1,2,3]
    解释：如果 perm = [1,2,3] ，那么 encoded = [1 XOR 2,2 XOR 3] = [3,1]
    示例 2：

    输入：encoded = [6,5,4,6]
    输出：[2,4,1,5,3]
     

    提示：

    3 <= n < 105
    n 是奇数。
    encoded.length == n - 1

 */
public class LeetCode1734 {

    /**
     * 原数组是前n个正整数的排列。

     这样就可以轻易得到整个perm数组做异或的结果allXOR，剩下的问题就是怎么把perm[1]~perm[n-1]给消掉：把这些元素继续与allXOR做异或。

     既然encoded[i] = perm[i] ^ perm[i+1]，那么直接将encoded的元素做异或会使得每个perm[i]重复异或相，当于没操作。

     所以我们需要跳着，只使用encoded中的奇数下标元素。
     * @param encoded
     * @return
     */
    public int[] decode(int[] encoded) {
        int m = encoded.length;
        int n = m + 1, allXOR = 0;
        for (int i = 1; i <= n; i++) {
            allXOR ^= i;
        }
        int starter = allXOR;
        for (int i = 1; i < m; i += 2) {
            starter ^= encoded[i];
        }
        int[] decoded = new int[m + 1];
        decoded[0] = starter;
        for (int i = 1; i < n; i++) {
            decoded[i] = encoded[i-1] ^ decoded[i-1];
        }
        return decoded;
    }
}
