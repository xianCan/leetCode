package leetCode;

/**
 * @authod xianCan
 * @date 2018/12/19 15:41
 *
 * 题目描述：
 *     给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。与12题对应
 */
public class LeetCode13 {
    public int romanToInt(String s) {
        int n = s.length();
        int result = 0;
        for (int i=0;i<n;i++){
            switch (s.charAt(i)){
                case 'I': result += 1;break;
                case 'V': result += 5;break;
                case 'X': result += 10;break;
                case 'L': result += 50;break;
                case 'C': result += 100;break;
                case 'D': result += 500;break;
                case 'M': result += 1000;break;
                default:break;
            }
            //如果出现IV、IX、XL、XC、CD、CM情况，则把前面多加的以双倍减去
            if (i!=0){
                if(((s.charAt(i)=='V')||(s.charAt(i)=='X'))&&(s.charAt(i-1)=='I'))
                    result = result-1*2;
                if(((s.charAt(i)=='L')||(s.charAt(i)=='C'))&&(s.charAt(i-1)=='X'))
                    result = result-10*2;
                if(((s.charAt(i)=='D')||(s.charAt(i)=='M'))&&(s.charAt(i-1)=='C'))
                    result = result-100*2;
            }
        }
        return result;
    }
}
