package leetCode;

/**
 * @author xianCan
 * @date 2020/10/16 19:06
 *
 * 977. 有序数组的平方（简单）
 *
 *  给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。

    示例 1：

    输入：[-4,-1,0,3,10]
    输出：[0,1,9,16,100]
    示例 2：

    输入：[-7,-3,2,3,11]
    输出：[4,9,9,49,121]
     

    提示：

    1 <= A.length <= 10000
    -10000 <= A[i] <= 10000
    A 已按非递减顺序排序。

 */
public class LeetCode977 {

    public int[] sortedSquares(int[] A) {
        if (A==null)return null;
        int len = A.length, p=0;
        while (p < len){
            if (A[p] < 0){
                p++;
            } else {
                break;
            }
        }

        int[] res = new int[len];
        int left=p-1, right=p, idx=0;
        while (left >= 0 && right < len){
            if (Math.abs(A[left]) < A[right]){
                res[idx++] = A[left] * A[left];
                left--;
            } else {
                res[idx++] = A[right] * A[right];
                right++;
            }
        }

        while (left >= 0){
            res[idx++] = A[left] * A[left];
            left--;
        }

        while (right < len){
            res[idx++] = A[right] * A[right];
            right++;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] ints = new LeetCode977().sortedSquares(new int[]{-7,-6, 0, 1});
        for (int i : ints){
            System.out.print(i + " ");
        }
    }
}
