package leetCode;

/**
 * @author xianCan
 * @date 2020/10/25 0:19
 *
 * 845. 数组中的最长山脉（中等）
 *
 *  我们把数组 A 中符合下列属性的任意连续子数组 B 称为 “山脉”：

    B.length >= 3
    存在 0 < i < B.length - 1 使得 B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
    （注意：B 可以是 A 的任意子数组，包括整个数组 A。）

    给出一个整数数组 A，返回最长 “山脉” 的长度。

    如果不含有 “山脉” 则返回 0。

     

    示例 1：

    输入：[2,1,4,7,3,2,5]
    输出：5
    解释：最长的 “山脉” 是 [1,4,7,3,2]，长度为 5。
    示例 2：

    输入：[2,2,2]
    输出：0
    解释：不含 “山脉”。
     

    提示：

    0 <= A.length <= 10000
    0 <= A[i] <= 10000

 */
public class LeetCode845 {

    /**
     * 双指针
     * @param A
     * @return
     */
    public int longestMountain(int[] A) {
        if (A==null || A.length < 3) return 0;

        int len = A.length;
        int res=0;
        for (int i=1; i<len-1; i++){
            if (A[i] > A[i-1] && A[i] > A[i+1]){
                int l=i-1;
                int r=i+1;
                while (l > 0 && A[l-1] < A[l]){
                    l--;
                }
                while (r < len-1 && A[r] > A[r+1]){
                    r++;
                }
                res = Math.max(res, r-l+1);
            }
        }
        return res;
    }

    /**
     *  动态规划：
     *  思路就是遍历分别以1 ~ n-2为山顶时,得出山脉的最大长度。当以某个索引为山顶时，此时的山脉长度应该是该索引左边连续递减的个数+右边递减个数+1。
        所以我们先做预处理，计算所有点左边、右边连续递减个数。而这一步又能使用动态规划进行优化。
        例如left[i]代表i左边有left[i]个数连续递减，那么left[i + 1] = A[i + 1] > A[i] ? left[i] + 1 : 0
     * @param A
     * @return
     */
    public int longestMountain2(int[] A){
        if (A==null || A.length < 3) return 0;

        int len = A.length;
        int[] left = new int[len];
        int[] right = new int[len];

        for (int i=1; i < len; i++){
            if (A[i] > A[i-1]){
                left[i] = left[i-1] + 1;
            }
        }

        for (int i=len-2; i>=0; i--){
            if (A[i] > A[i+1]){
                right[i] = right[i+1] + 1;
            }
        }

        int ans=0;
        for (int i=1; i<len-1; i++){
            if (left[i] > 0 && right[i] > 0){
                ans = Math.max(ans, left[i] + right[i] + 1);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int i = new LeetCode845().longestMountain2(new int[]{2,1,4,7,3,2,5});
        System.out.println(i);
    }
}
