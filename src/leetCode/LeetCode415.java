package leetCode;

/**
 * @author xianCan
 * @date 2020/8/3 21:03
 *
 * 415. 字符串相加
 *
 *  给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。

    提示：

    num1 和num2 的长度都小于 5100
    num1 和num2 都只包含数字 0-9
    num1 和num2 都不包含任何前导零
    你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式

 */
public class LeetCode415 {

    public String addStrings(String num1, String num2) {
        if (num1== null && num2==null)return null;
        if (num1==null || "".equals(num1))return num2;
        if (num2==null || "".equals(num2))return num1;

        StringBuilder builder = new StringBuilder();
        int length1 = num1.length() - 1;
        int length2 = num2.length() - 1;

        int flag=0;
        while (length1 >=0 && length2 >=0){
            int i1 = num1.charAt(length1) - '0';
            int i2 = num2.charAt(length2) - '0';
            int tmp = i1+i2+flag;
            int cur = tmp % 10;
            flag = tmp / 10;
            builder.append(cur);
            length1--;
            length2--;
        }

        while (length1 >=0){
            int tmp = num1.charAt(length1) - '0' + flag;
            int cur = tmp % 10;
            flag = tmp / 10;
            builder.append(cur);
            length1--;
        }

        while (length2 >=0){
            int tmp = num2.charAt(length2) - '0' + flag;
            int cur = tmp % 10;
            flag = tmp / 10;
            builder.append(cur);
            length2--;
        }
        if (flag != 0){
            builder.append(flag);
        }

        return builder.reverse().toString();
    }

    public static void main(String[] args) {
        String s = new LeetCode415().addStrings("111123456789987654321","987654321123456789");
        System.out.println(s);
    }
}
