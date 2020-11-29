package leetCode;

import java.util.Arrays;

/**
 * @author xianCan
 * @date 2020/11/29 0:08
 *
 * 976. 三角形的最大周长（简单）
 *
 *  给定由一些正数（代表长度）组成的数组 A，返回由其中三个长度组成的、面积不为零的三角形的最大周长。

    如果不能形成任何面积不为零的三角形，返回 0。

     

    示例 1：

    输入：[2,1,2]
    输出：5
    示例 2：

    输入：[1,2,1]
    输出：0
    示例 3：

    输入：[3,2,3,4]
    输出：10
    示例 4：

    输入：[3,6,2,3]
    输出：8
     

    提示：

    3 <= A.length <= 10000
    1 <= A[i] <= 10^6

 */
public class LeetCode976 {

    /**
     * 傻逼暴力
     * @param A
     * @return
     */
    public int largestPerimeter(int[] A) {
        Arrays.sort(A);
        int len = A.length;
        for (int i=len-1; i >= 0; i--){
            for (int j=i-1; j >= 0; j--){
                for (int k=j-1; k >= 0; k--){
                    if (predicate(A[i], A[j], A[k])){
                        return A[i]+A[j]+A[k];
                    }
                }
            }
        }
        return 0;
    }

    private boolean predicate(int a, int b, int c){
        return (a+b)>c && (a+c)>b && (b+c)>a;
    }

    /**
     * 贪心
     * @param A
     * @return
     */
    public int largestPerimeter2(int[] A) {
        Arrays.sort(A);
        for (int i=A.length-1; i >= 2; i--){
            if (A[i-2] + A[i-1] > A[i]){
                return A[i-2] + A[i-1] + A[i];
            }
        }
        return 0;
    }
}
