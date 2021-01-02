package leetCode;

import java.util.Arrays;

/**
 * @author xianCan
 * @date 2020/12/3 20:56
 *
 * 1005. K 次取反后最大化的数组和（简单）
 *
 *  给定一个整数数组 A，我们只能用以下方法修改该数组：我们选择某个索引 i 并将 A[i] 替换为 -A[i]，然后总共重复这个过程 K 次。（我们可以多次选择同一个索引 i。）

    以这种方式修改数组后，返回数组可能的最大和。

     

    示例 1：

    输入：A = [4,2,3], K = 1
    输出：5
    解释：选择索引 (1,) ，然后 A 变为 [4,-2,3]。
    示例 2：

    输入：A = [3,-1,0,2], K = 3
    输出：6
    解释：选择索引 (1, 2, 2) ，然后 A 变为 [3,1,0,2]。
    示例 3：

    输入：A = [2,-3,-1,5,-4], K = 2
    输出：13
    解释：选择索引 (1, 4) ，然后 A 变为 [2,3,-1,5,4]。
     

    提示：

    1 <= A.length <= 10000
    1 <= K <= 10000
    -100 <= A[i] <= 100

 */
public class LeetCode1005 {

    public int largestSumAfterKNegations(int[] A, int K) {
        Arrays.sort(A);
        int i=0;
        while (K > 0){
            //先反转，再判断是否比下一个数要大，是的话就i++
            A[i] = -A[i];
            if (i < A.length-1 && A[i] > A[i + 1]){
                i++;
            }
            K--;
        }

        int ans=0;
        for (int num : A){
            ans += num;
        }
        return ans;
    }

    /**
     * 先按照绝对值排序，然后取反
     * @param A
     * @param K
     * @return
     */
    public int largestSumAfterKNegations2(int[] A, int K) {
        int len = A.length;
        Integer[] nums = new Integer[len];
        for (int i = 0; i < len; i++){
            nums[i] = A[i];
        }
        Arrays.sort(nums, (o1, o2) -> Integer.compare(Math.abs(o2), Math.abs(o1)));
        for (int i = 0; i < len; i++){
            if (nums[i] < 0 && K > 0){
                nums[i] = -nums[i];
                K--;
            }
        }

        if (K % 2 == 1) nums[len - 1] = - nums[len - 1];
        int ans=0;
        for (int num : nums){
            ans += num;
        }
        return ans;
    }

    public static void main(String[] args) {
        int i = new LeetCode1005().largestSumAfterKNegations2(new int[]{2,-3,-1,5,-4}, 2);
        System.out.println(i);
    }
}
