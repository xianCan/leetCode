package leetCode;

/**
 * @author xianCan
 * @date 2021/6/29 21:00
 *
 * 168. Excel表列名称（简单）
 *
 *  给你一个整数 columnNumber ，返回它在 Excel 表中相对应的列名称。

    例如：

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28
    ...
     

    示例 1：

    输入：columnNumber = 1
    输出："A"
    示例 2：

    输入：columnNumber = 28
    输出："AB"
    示例 3：

    输入：columnNumber = 701
    输出："ZY"
    示例 4：

    输入：columnNumber = 2147483647
    输出："FXSHRXW"
     

    提示：

    1 <= columnNumber <= 231 - 1

 */
public class LeetCode168 {

    public String convertToTitle(int columnNumber) {
        StringBuilder builder = new StringBuilder();
        while (columnNumber > 0){
            int num = columnNumber % 26;
            if (num > 0){
                builder.append((char)(num + 64));
                columnNumber /= 26;
            } else if (num == 0){
                builder.append('Z');
                columnNumber /= 27;
            }
        }
        builder.reverse();
        return builder.toString();
    }
}
