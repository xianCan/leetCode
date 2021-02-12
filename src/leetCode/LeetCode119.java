package leetCode;

import java.util.Arrays;
import java.util.List;

/**
 * @author xianCan
 * @date 2021/2/12 16:59
 *
 * 119. 杨辉三角 II（简单）
 *
 *  给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 *
    在杨辉三角中，每个数是它左上方和右上方的数的和。

    示例:

    输入: 3
    输出: [1,3,3,1]
    进阶：

    你可以优化你的算法到 O(k) 空间复杂度吗？

    1
    1 1
    1 2 1
    1 3 3 1
    1 4 6 4 1
    1 5 10 10 5 1
    1 6 15 20 15 6 1
    1 7 21 35 35 21 7 1

 */
public class LeetCode119 {

    public List<Integer> getRow(int rowIndex) {
        Integer[] nums = new Integer[rowIndex + 1];
        int row = 0;
        while (row <= rowIndex){
            for (int i = row; i >= 0; i--){
                if (i == 0 || i == row){
                    nums[i] = 1;
                } else {
                    nums[i] = nums[i] + nums[i - 1];
                }
            }
            row++;
        }

        return Arrays.asList(nums);
    }

    public static void main(String[] args) {
        List<Integer> row = new LeetCode119().getRow(4);
        System.out.println(row);
    }
}
