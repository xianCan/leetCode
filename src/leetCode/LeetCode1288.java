package leetCode;

import java.util.Arrays;

/**
 * @author xianCan
 * @date 2020/10/22 21:37
 *
 * 1288. 删除被覆盖区间（中等）
 *
 *  给你一个区间列表，请你删除列表中被其他区间所覆盖的区间。

    只有当 c <= a 且 b <= d 时，我们才认为区间 [a,b) 被区间 [c,d) 覆盖。

    在完成所有删除操作后，请你返回列表中剩余区间的数目。

     

    示例：

    输入：intervals = [[1,4],[3,6],[2,8]]
    输出：2
    解释：区间 [3,6] 被区间 [2,8] 覆盖，所以它被删除了。
     

    提示：​​​​​​

    1 <= intervals.length <= 1000
    0 <= intervals[i][0] < intervals[i][1] <= 10^5
    对于所有的 i != j：intervals[i] != intervals[j]

 */
public class LeetCode1288 {

    public int removeCoveredIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        //排序：按照起点升序排序，起点相同，按照终点降序排序
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]){
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });

        int end, preEnd=0;
        int ans=0;
        for (int[] interval : intervals){
            end = interval[1];

            if (end > preEnd){
                ans++;
                preEnd = end;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int i = new LeetCode1288().removeCoveredIntervals(new int[][]{{1, 4}, {3, 6}, {2, 8}});
        System.out.println(i);
    }
}
