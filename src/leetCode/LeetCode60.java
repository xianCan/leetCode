package leetCode;

import java.util.LinkedList;

/**
 * @author xianCan
 * @date 2020/9/5 10:12
 *
 * 60. 第k个排列（中等）
 *
 *  给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。

    按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：

    "123"
    "132"
    "213"
    "231"
    "312"
    "321"
    给定 n 和 k，返回第 k 个排列。

    说明：

    给定 n 的范围是 [1, 9]。
    给定 k 的范围是[1,  n!]。
    示例 1:

    输入: n = 3, k = 3
    输出: "213"
    示例 2:

    输入: n = 4, k = 9
    输出: "2314"

 */
public class LeetCode60 {

    /**
     * 时间复杂度：O(N^2)
     * 空间复杂度：O(N)
     * @param n
     * @param k
     * @return
     */
    public String getPermutation(int n, int k) {
        int[] factorial = new int[n];
        factorial[0]=1;
        for (int i=1; i<n; i++){
            factorial[i] = factorial[i-1] * i;
        }

        StringBuilder res = new StringBuilder();
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i=1; i<=n; i++){
            linkedList.add(i);
        }
        for (int i=1; i<=n; i++){
            int order = k % factorial[n-i] == 0 ? k / factorial[n-i] - 1 : k / factorial[n-i];
            res.append(linkedList.remove(order));
            k = k % factorial[n-i] == 0 ? factorial[n-i] : k % factorial[n-i];
        }
        return res.toString();
    }


    public static void main(String[] args) {
        String permutation = new LeetCode60().getPermutation(6, 1);
        System.out.println(permutation);
    }
}
