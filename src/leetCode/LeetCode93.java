package leetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xianCan
 * @date 2020/8/9 10:51
 *
 * 93. 复原IP地址（中等）
 *
 *  给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 *

    有效的 IP 地址正好由四个整数（每个整数位于 0 到 255 之间组成），整数之间用 '.' 分隔。

    示例:

    输入: "25525511135"
    输出: ["255.255.11.135", "255.255.111.35"]

 */
public class LeetCode93 {

    private List<String> ans;
    private String s;

    /**
     * 暴力
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        this.ans = new ArrayList<>();
        this.s = s;
        if (s.length() < 4 || s.length() > 12)return ans;
        recursive(0, new LinkedList<>());
        return ans;
    }

    private void recursive(int start, LinkedList<String> path){
        if (start==s.length() && path.size()==4){
            ans.add(String.join(".", path));
            return;
        }

        for (int i=start; i<s.length(); i++){
            if (i-start > 2){
                break;
            }
            if (predicate(start, i)){
                path.add(s.substring(start, i+1));
                recursive(i+1, path);
                path.removeLast();
            }
        }
    }

    private boolean predicate(int left, int right){
        if (s.charAt(left)=='0' && right==left){
            return true;
        } else if (s.charAt(left)=='0'){
            return false;
        } else if (Integer.parseInt(s.substring(left, right+1)) <= 255){
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        List<String> strings = new LeetCode93().restoreIpAddresses("000000");
        for (String s : strings){
            System.out.println(s);
        }
    }
}
