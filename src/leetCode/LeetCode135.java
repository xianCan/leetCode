package leetCode;

import java.util.Arrays;

/**
 * @author xianCan
 * @date 2020/12/15 21:31
 *
 * 135. 分发糖果（困难）
 *
 *  老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。

    你需要按照以下要求，帮助老师给这些孩子分发糖果：

    每个孩子至少分配到 1 个糖果。
    相邻的孩子中，评分高的孩子必须获得更多的糖果。
    那么这样下来，老师至少需要准备多少颗糖果呢？

    示例 1:

    输入: [1,0,2]
    输出: 5
    解释: 你可以分别给这三个孩子分发 2、1、2 颗糖果。
    示例 2:

    输入: [1,2,2]
    输出: 4
    解释: 你可以分别给这三个孩子分发 1、2、1 颗糖果。
    第三个孩子只得到 1 颗糖果，这已满足上述两个条件。

 */
public class LeetCode135 {

    /**
     * 模拟
     * @param ratings
     * @return
     */
    public int candy(int[] ratings) {
        int len = ratings.length;
        int[] candys = new int[len];
        int startIdx = 0;
        Boolean switchFlag = null;

        for (int i=0; i < len; i++){
            if (switchFlag == null){
                candys[i] = 1;
                if (i < len -1 && ratings[i] < ratings[i+1]){
                    switchFlag = true;
                    startIdx = i;
                } else if (i < len -1 && ratings[i] > ratings[i+1]){
                    switchFlag = false;
                    startIdx = i;
                }
            } else if (switchFlag){
                candys[i] = candys[i-1] + 1;
                if (i < len -1 && ratings[i] > ratings[i+1]){
                    switchFlag = false;
                    startIdx = i;
                } else if (i < len - 1 && ratings[i] == ratings[i+1]){
                    switchFlag = null;
                    startIdx = i;
                }
            } else {
                int end = candys[startIdx] > candys[startIdx+1] + 1 ? startIdx+1 : startIdx;
                for (int j = i; j >= end; j--){
                    candys[j]++;
                }
                if (i < len -1 && ratings[i] < ratings[i+1]){
                    switchFlag = true;
                    startIdx = i;
                } else if (i < len - 1 && ratings[i] == ratings[i+1]){
                    switchFlag = null;
                    startIdx = i;
                }
            }
        }

        int ans=0;
        for (int candy : candys){
            ans += candy;
        }

        return ans;
    }

    /**
     * 贪心
     * 局部最优：只要右边评分比左边大，右边的孩子就多一个糖果
     * 全局最优：相邻的孩子中，评分高的右孩子获得比左孩子更多的糖果
     *
     * 先确定右边评分比左边大的情况（从前往后遍历），再确定左边孩子比右边大的情况（从后往前遍历）
     * @param ratings
     * @return
     */
    public int candy2(int[] ratings) {
        int len = ratings.length;
        int[] candys = new int[len];
        Arrays.fill(candys, 1);

        for (int i = 1; i < len; i++){
            if (ratings[i] > ratings[i-1]){
                candys[i] = candys[i-1] + 1;
            }
        }

        for (int i = len- 2; i >= 0; i--){
            if (ratings[i] > ratings[i + 1]){
                candys[i] = Math.max(candys[i], candys[i+1] + 1);
            }
        }

        int ans=0;
        for (int candy : candys){
            ans += candy;
        }
        return ans;
    }

    public static void main(String[] args) {
        int candy = new LeetCode135().candy2(new int[]{1, 2, 2});
        System.out.println(candy);
    }
}
