package leetCode;

/**
 * @author xianCan
 * @date 2021/3/16 21:56
 *
 * 718. 最长重复子数组（中等）
 *
 *  给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。

    示例：

    输入：
    A: [1,2,3,2,1]
    B: [3,2,1,4,7]
    输出：3
    解释：
    长度最长的公共子数组是 [3, 2, 1] 。
     

    提示：

    1 <= len(A), len(B) <= 1000
    0 <= A[i], B[i] < 100

 */
public class LeetCode718 {

    public int findLength(int[] A, int[] B) {
        int lenA = A.length, lenB = B.length, ans = 0;
        int[][] dp = new int[lenA][lenB];
        for (int i = 0; i < lenA; i++){
            for (int j = 0; j < lenB; j++){
                if (A[i] == B[j] && i > 0 && j > 0 && A[i - 1] == B[j - 1]){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    ans = Math.max(dp[i][j], ans);
                } else if (A[i] == B[j]){
                    dp[i][j] = 1;
                    ans = Math.max(dp[i][j], ans);
                }
            }
        }

        return ans;
    }
}
