package leetCode;

/**
 * @authod xianCan
 * @date 2018/12/19 10:28
 *
 * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
 *
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 *
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 *
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 *     I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 *     X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 *     C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 *
 * 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
 *
 * 示例 1:
 * 输入: 3
 * 输出: "III"
 *
 * 示例 2:
 * 输入: 4
 * 输出: "IV"
 *
 * 示例 3:
 * 输入: 9
 * 输出: "IX"
 *
 * 示例 4:
 * 输入: 58
 * 输出: "LVIII"
 * 解释: L = 50, V = 5, III = 3.
 *
 * 示例 5:
 * 输入: 1994
 * 输出: "MCMXCIV"
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 */
public class LeetCode12 {
    /**
     * 讨厌的if写法
     * @param num 要变换的数字
     * @return
     */
    @SuppressWarnings("all")
    public String intToRoman(int num) {
        if (num<1 || num>3999)return "数字越界，请输入1到3999范围内的整数";
        StringBuilder sb = new StringBuilder();
        int left=num;
        for (int i=1000;i>0;i=i/10){
            int p=left/i;
            left=left%i;
            if (i==1000){
                for (int j=0;j<p;j++){
                    sb.append("M");
                }
            } else if(i==100){
                if (p==9){
                    sb.append("CM");
                }else if (p==4){
                    sb.append("CD");
                }else if(p>=5){
                    sb.append("D");
                    for (int k=0;k<p-5;k++){
                        sb.append("C");
                    }
                }else if (p<5){
                    for (int l=0;l<p;l++){
                        sb.append("C");
                    }
                }
            } else if (i==10){
                if (p==9){
                    sb.append("XC");
                }else if (p==4){
                    sb.append("XL");
                }else if(p>=5){
                    sb.append("L");
                    for (int k=0;k<p-5;k++){
                        sb.append("X");
                    }
                }else if (p<5){
                    for (int m=0;m<p;m++){
                        sb.append("X");
                    }
                }
            } else if (i==1) {
                if (p == 9) {
                    sb.append("IX");
                } else if (p == 4) {
                    sb.append("IV");
                } else if (p >= 5) {
                    sb.append("V");
                    for (int n = 0; n < p - 5; n++) {
                        sb.append("I");
                    }
                } else if (p < 5) {
                    for (int o = 0; o < p; o++) {
                        sb.append("I");
                    }
                }
            }
        }
        return sb.toString();
    }
}
