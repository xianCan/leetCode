package leetCode;

import java.util.Stack;

/**
 * @author xianCan
 * @date 2022/2/24 23:07
 *
 * 84. 柱状图中最大的矩形（困难）
 *
 *  给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。

    求在该柱状图中，能够勾勒出来的矩形的最大面积。

     

    示例 1:



    输入：heights = [2,1,5,6,2,3]
    输出：10
    解释：最大的矩形为图中红色区域，面积为 10
    示例 2：



    输入： heights = [2,4]
    输出： 4
     

    提示：

    1 <= heights.length <=105
    0 <= heights[i] <= 104

 */
public class LeetCode84 {

    public int largestRectangleArea(int[] heights) {
        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        int[] newHeights = new int[heights.length + 2];
        for (int i = 1; i <= heights.length; i++){
            newHeights[i] = heights[i - 1];
        }
        for (int i = 0; i < newHeights.length; i++){
            while (!stack.isEmpty() && newHeights[i] < newHeights[stack.peek()]){
                Integer idx = stack.pop();
                int height = newHeights[idx];
                int length = i - stack.peek() - 1;
                ans = Math.max(ans, height * length);
            }
            stack.push(i);
        }
        return ans;
    }
}
