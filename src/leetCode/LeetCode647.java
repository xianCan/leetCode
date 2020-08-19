package leetCode;

/**
 * @author xianCan
 * @date 2020/8/19 21:37
 *
 * 647. 回文子串（中等）
 *
 *  给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。

    具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。

     

    示例 1：

    输入："abc"
    输出：3
    解释：三个回文子串: "a", "b", "c"
    示例 2：

    输入："aaa"
    输出：6
    解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
     

    提示：

    输入的字符串长度不会超过 1000 。

 */
public class LeetCode647 {

    /**
     * 暴力，时间复杂度：O(N^3)
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        int length = s.length();
        int res=0;
        for (int i=0; i<length; i++){
            for (int j=i+1; j<=length; j++){
                if (isSubString(s.substring(i, j))){
                    res++;
                }
            }
        }
        return res;
    }

    private boolean isSubString(String s){
        int left=0, right=s.length()-1;
        while (left < right){
            if (s.charAt(left++) != s.charAt(right--)){
                return false;
            }
        }
        return true;
    }

    /**
     * 以中心点扩充，区分单中心点和双中心点
     * @param s
     * @return
     */
    public int countSubstrings2(String s){
        int length = s.length();
        int res=0;
        for (int i=0; i<length; i++){
            res += helper(s, i, i, length);
            res += helper(s, i, i+1, length);
        }
        return res;
    }

    private int helper(String s, int l, int r, int length){
        int res=0;
        while (l>=0 && r<length && s.charAt(l) == s.charAt(r)){
            l--;
            r++;
            res++;
        }
        return res;
    }

    public static void main(String[] args) {
        int aaa = new LeetCode647().countSubstrings2("abc");
        System.out.println(aaa);
    }
}
