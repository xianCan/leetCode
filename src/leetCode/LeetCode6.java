package leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @authod xianCan
 * @date 2018/12/17 16:17
 *
 * 题目描述：
 *     将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 *
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 *
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 * 请你实现这个将字符串进行指定行数变换的函数：
 */
public class LeetCode6 {
    /**
     * Z形变换
     * @param s 需要变换的字符串
     * @param numRows 变换的总行数
     * @return
     */
    public String convert(String s, int numRows) {
        if(numRows <= 0 || "".equals(s)) return s;
        List<StringBuilder> builderList = new ArrayList<>(numRows);
        for (int i = 0; i < numRows; i++){
            builderList.add(new StringBuilder());
        }
        int flag = 2 * numRows - 2;
        for (int i = 0; i < s.length(); i++){
            int tempIndex = i % flag;
            int index = tempIndex < numRows ? tempIndex : flag - tempIndex;
            builderList.get(index).append(s.charAt(i));
        }
        StringBuilder result = new StringBuilder();
        for (StringBuilder builder : builderList){
            result.append(builder);
        }
        return result.toString();
    }
}
