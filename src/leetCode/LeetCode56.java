package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xianCan
 * @date 2020/10/22 21:52
 *
 * 56. 合并区间（中等）
 *
 *  给出一个区间的集合，请合并所有重叠的区间。

    示例 1:

    输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
    输出: [[1,6],[8,10],[15,18]]
    解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
    示例 2:

    输入: intervals = [[1,4],[4,5]]
    输出: [[1,5]]
    解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
    注意：输入类型已于2019年4月15日更改。 请重置默认代码定义以获取新方法签名。

     

    提示：

    intervals[i][0] <= intervals[i][1]

 */
public class LeetCode56 {

    /**
     * 区间问题：先排序，再判断区间左右边界
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0)return new int[0][2];
        List<int[]> ans = new ArrayList<>();

        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]){
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });

        int left = intervals[0][0];
        int right = intervals[0][1];

        for (int i=1; i<intervals.length; i++){
            int[] interval = intervals[i];
            if (right < interval[0]){
                ans.add(new int[]{left, right});
                left = interval[0];
                right = interval[1];
            } else {
                right = Math.max(right, interval[1]);
            }
        }
        ans.add(new int[]{left, right});
        return ans.toArray(new int[ans.size()][]);
    }
}
