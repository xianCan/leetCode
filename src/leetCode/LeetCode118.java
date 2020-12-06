package leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xianCan
 * @date 2020/12/6 9:44
 *
 * 118. 杨辉三角（简单）
 *
 *  给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。

    在杨辉三角中，每个数是它左上方和右上方的数的和。

    示例:

    输入: 5
    输出:
    [
    [1],
    [1,1],
    [1,2,1],
    [1,3,3,1],
    [1,4,6,4,1]
    ]

 */
public class LeetCode118 {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();

        for (int i=0; i < numRows; i++){
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j <= i; j++){
                if (j == 0 || j == i){
                    list.add(1);
                } else {
                    List<Integer> tmp = ans.get(i - 1);
                    list.add(tmp.get(j - 1) + tmp.get(j));
                }
            }
            ans.add(list);
        }

        return ans;
    }

    public static void main(String[] args) {
        List<List<Integer>> generate = new LeetCode118().generate(5);
        System.out.println(generate);
    }
}
