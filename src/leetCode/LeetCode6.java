package leetCode;

import java.util.ArrayList;

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
        if (s==null)return "";
        //如果指定行数为1行，直接返回原来的字符串
        else if (numRows==1){
            return s;
        }
        ArrayList<StringBuffer> list = new ArrayList<>();
        for (int i=0;i<numRows;i++){
            list.add(new StringBuffer());
        }
        int p=0;
        boolean flag=false;
        for (char c:s.toCharArray()){
            list.get(p).append(c);
            if (p==0 || p==numRows-1)flag=!flag;
            p += flag ? 1:-1;
        }
        StringBuilder ret = new StringBuilder();
        for (StringBuffer sb : list) {
            ret.append(sb);
        }
        return ret.toString();
    }
}
