package leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xianCan
 * @date 2020/11/4 20:34
 *
 * 57. 插入区间（困难）
 *
 *  给出一个无重叠的 ，按照区间起始端点排序的区间列表。

    在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。

     

    示例 1：

    输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
    输出：[[1,5],[6,9]]
    示例 2：

    输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
    输出：[[1,2],[3,10],[12,16]]
    解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。

 */
public class LeetCode57 {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> ans = new ArrayList<>();
        int left=newInterval[0], right=newInterval[1];
        int idx=0;
        for (int[] interval : intervals){
            if (interval[1] < newInterval[0]){
                ans.add(interval);
                idx++;
            } else if(interval[0] > newInterval[1]){
                ans.add(interval);
            } else {
                left = Math.min(left, interval[0]);
                right = Math.max(right, interval[1]);
            }
        }

        ans.add(idx, new int[]{left, right});
        int[][] res = new int[ans.size()][2];
        for (int i=0; i<ans.size(); i++){
            res[i] = ans.get(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] insert = new LeetCode57().insert(new int[][]{{1,2},{3,5},{6,7},{8,10},{12,16}}, new int[]{-8, -4});
        System.out.println(insert);
    }
}
