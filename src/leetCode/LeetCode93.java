package leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xianCan
 * @date 2020/8/9 10:51
 *
 * 93. 复原IP地址（中等）
 *
 *  给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。

    有效的 IP 地址正好由四个整数（每个整数位于 0 到 255 之间组成），整数之间用 '.' 分隔。

    示例:

    输入: "25525511135"
    输出: ["255.255.11.135", "255.255.111.35"]

 */
public class LeetCode93 {

    /**
     * 暴力
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        if (s==null || s.length() < 4 || s.length() > 12) return result;
        int length = s.length();

        for (int i=1; i<=3 && i < length - 2; i++) {
            for (int j = i; j < i + 4 && j < length - 1; j++) {
                for (int k = j; k < j + 4 && k < length; k++) {
                    String s1 = s.substring(0, i);
                    String s2 = s.substring(i, j);
                    String s3 = s.substring(j, k);
                    String s4 = s.substring(k);
                    if(predicate(s1)&&predicate(s2)&&predicate(s3)&&predicate(s4)){
                        StringBuilder sb = new StringBuilder();
                        sb.append(s1); sb.append(".");
                        sb.append(s2); sb.append(".");
                        sb.append(s3); sb.append(".");
                        sb.append(s4);
                        result.add(sb.toString());
                    }
                }
            }
        }
        return result;
    }

    private boolean predicate(String s){
        if (s.length() == 0)return false;
        if (s.length() == 1)return true;
        if (s.length() > 3) return false;
        if (s.charAt(0) == '0') return false;
        if (Integer.parseInt(s) <= 255) return true;
        return false;
    }

    /**
     * 回溯
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses2(String s) {
        List<String> result = new ArrayList<>();
        if (s==null || s.length() < 4 || s.length() > 12) return result;
        int[] segments = new int[4];
        recursive(s,0,0, segments, result);
        return result;
    }

    private void recursive(String s, int segId, int segStart, int[] segments, List<String> result){
        if (segId == 4 && segStart == s.length()){
            StringBuilder builder = new StringBuilder();
            for (int i=0; i<4; i++){
                builder.append(segments[i]);
                if (i != 3){
                    builder.append(".");
                }
            }
            result.add(builder.toString());
            return;
        } else if (segStart == s.length() || segId == 4){
            return;
        }

        if (s.charAt(segStart) == '0'){
            segments[segId] = 0;
            recursive(s, segId+1, segStart+1, segments, result);
        }

        int addr=0;
        for (int segEnd=segStart; segEnd < s.length(); segEnd++){
            addr = addr * 10 + (s.charAt(segEnd) - '0');
            if (addr > 0 && addr <= 0xFF) {
                segments[segId] = addr;
                recursive(s, segId+1,segEnd+1, segments, result);
            } else {
                break;
            }
        }
    }



    public static void main(String[] args) {
        List<String> strings = new LeetCode93().restoreIpAddresses2("020000");
        for (String s : strings){
            System.out.println(s);
        }
    }
}
