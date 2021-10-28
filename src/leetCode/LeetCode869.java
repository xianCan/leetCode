package leetCode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author xianCan
 * @date 2021/10/28 21:28
 *
 * 869. 重新排序得到 2 的幂（中等）
 *
 *  给定正整数 N ，我们按任何顺序（包括原始顺序）将数字重新排序，注意其前导数字不能为零。

    如果我们可以通过上述方式得到 2 的幂，返回 true；否则，返回 false。

     

    示例 1：

    输入：1
    输出：true
    示例 2：

    输入：10
    输出：false
    示例 3：

    输入：16
    输出：true
    示例 4：

    输入：24
    输出：false
    示例 5：

    输入：46
    输出：true
     

    提示：

    1 <= N <= 10^9

 */
public class LeetCode869 {

    static Set<Integer> set = new HashSet<>();

    static {
        for (int i = 1; i <= 1e+9; i <<= 1){
            set.add(i);
        }
    }

    public boolean reorderedPowerOf2(int n) {
        int[] cnts = new int[10];
        while (n > 0){
            cnts[n % 10]++;
            n /= 10;
        }
        int[] curs;
        for (Integer integer : set){
            curs = new int[10];
            while (integer > 0){
                curs[integer % 10]++;
                integer /= 10;
            }

            boolean f = true;
            for (int i = 0; i < 10; i++){
                if (cnts[i] != curs[i]){
                    f = false;
                    break;
                }
            }
            if (f){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        boolean b = new LeetCode869().reorderedPowerOf2(1);
        System.out.println(b);
    }
}
