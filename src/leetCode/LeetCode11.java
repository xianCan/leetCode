package leetCode;

/**
 * @authod xianCan
 * @date 2018/12/19 9:54
 *
 * 题目描述：
 *     给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 *
 * 示例:
 * 输入: [1,8,6,2,5,4,8,3,7]
 * 输出: 49
 */
public class LeetCode11 {
    /**
     * 双指针法：头尾两个指针往中间移动，谁的值小就谁往中间移动
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int p=0,q=height.length-1,result=0,temp,minHeight;
        while (p!=q){
            minHeight = height[p]<height[q]?height[p++]:height[q--];
            temp = (q-p+1) * minHeight;
            if (temp>result)
                result=temp;
        }
        return result;
    }
}
