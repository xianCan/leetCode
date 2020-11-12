package leetCode;

/**
 * @author xianCan
 * @date 2020/11/12 19:41
 *
 * 922. 按奇偶排序数组 II（简单）
 *
 *  给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。

    对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。

    你可以返回任何满足上述条件的数组作为答案。

     

    示例：

    输入：[4,2,5,7]
    输出：[4,5,2,7]
    解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
     

    提示：

    2 <= A.length <= 20000
    A.length % 2 == 0
    0 <= A[i] <= 1000

 */
public class LeetCode922 {

    public int[] sortArrayByParityII(int[] A) {
        if (A==null)return null;
        int len = A.length;
        int[] ans = new int[len];

        int idx=0;
        for (int num : A){
            if (num % 2 == 0){
                ans[idx] = num;
                idx += 2;
            }
        }

        idx=1;
        for (int num : A){
            if (num % 2 == 1){
                ans[idx] = num;
                idx += 2;
            }
        }
        return ans;
    }

    public int[] sortArrayByParityII2(int[] A) {
        if (A==null)return null;
        int len = A.length;
        int p=0, q=0;
        for (int i=0; i<len; i++){
            if (i % 2 == 0){
                while (p < len && A[p] %2==1){
                    p++;
                }
                swap(A, i, p);
                p++;
            } else {
                while (q < len && A[q] %2==0){
                    q++;
                }
                swap(A, i, q);
                q++;
            }
        }
        return A;
    }

    private void swap(int[] A, int p, int q){
        int tmp = A[p];
        A[p] = A[q];
        A[q] = tmp;
    }
}
