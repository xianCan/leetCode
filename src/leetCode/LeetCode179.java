package leetCode;

import java.util.Arrays;

/**
 * @author xianCan
 * @date 2021/4/12 20:08
 *
 * 179. 最大数（中等）
 *
 *  给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。

    注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。

     

    示例 1：

    输入：nums = [10,2]
    输出："210"
    示例 2：

    输入：nums = [3,30,34,5,9]
    输出："9534330"
    示例 3：

    输入：nums = [1]
    输出："1"
    示例 4：

    输入：nums = [10]
    输出："10"
     

    提示：

    1 <= nums.length <= 100
    0 <= nums[i] <= 109

 */
public class LeetCode179 {

    public String largestNumber(int[] nums) {
        int len = nums.length;
        String[] strings = new String[len];

        for (int i = 0; i < len; i++){
            strings[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(strings, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));

        if(strings[0].equals("0")){
            return "0";
        }

        StringBuilder ans = new StringBuilder();
        for (String str : strings){
            ans.append(str);
        }
        return ans.toString();
    }
}
