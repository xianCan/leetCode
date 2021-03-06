package leetCode;

/**
 * @author xianCan
 * @date 2020/10/25 11:41
 *
 * 941. 有效的山脉数组（简单）
 *
 *  给定一个整数数组 A，如果它是有效的山脉数组就返回 true，否则返回 false。

    让我们回顾一下，如果 A 满足下述条件，那么它是一个山脉数组：

    A.length >= 3
    在 0 < i < A.length - 1 条件下，存在 i 使得：
    A[0] < A[1] < ... A[i-1] < A[i]
    A[i] > A[i+1] > ... > A[A.length - 1]
     
    示例 1：

    输入：[2,1]
    输出：false
    示例 2：

    输入：[3,5,5]
    输出：false
    示例 3：

    输入：[0,3,2,1]
    输出：true
     

    提示：

    0 <= A.length <= 10000
    0 <= A[i] <= 10000 

 */
public class LeetCode941 {

    public boolean validMountainArray(int[] A) {
        if (A==null || A.length < 3) return false;
        int N = A.length;
        int i = 0;

        // walk up
        while (i+1 < N && A[i] < A[i+1])
            i++;

        // peak can't be first or last
        if (i == 0 || i == N-1)
            return false;

        // walk down
        while (i+1 < N && A[i] > A[i+1])
            i++;

        return i == N-1;
    }

    public static void main(String[] args) {
        boolean b = new LeetCode941().validMountainArray(new int[]{0, 2, 3, 4,5,4,1,1});
        System.out.println(b);
    }
}
