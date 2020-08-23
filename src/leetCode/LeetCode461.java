package leetCode;

/**
 * @author xianCan
 * @date 2020/8/23 17:00
 *
 * 461. 汉明距离（简单）
 *
 *  两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。

    给出两个整数 x 和 y，计算它们之间的汉明距离。

    注意：
    0 ≤ x, y < 231.

    示例:

    输入: x = 1, y = 4

    输出: 2

    解释:
    1   (0 0 0 1)
    4   (0 1 0 0)
    ↑   ↑

    上面的箭头指出了对应二进制位不同的位置。

 */
public class LeetCode461 {

    /**
     * 暴力
     * @param x
     * @param y
     * @return
     */
    public int hammingDistance(int x, int y) {
        int tmp = x ^ y;
        int res=0;
        String str = Integer.toBinaryString(tmp);
        for (int i=0; i<str.length(); i++){
            char c = str.charAt(i);
            if (c == '1'){
                res++;
            }
        }
        return res;
    }

    /**
     * Brian Kernighan 算法
     * @param x
     * @param y
     * @return
     */
    public int hammingDistance2(int x, int y){
        int tmp = x ^ y;
        int res=0;
        while (tmp > 0){
            tmp = tmp & (tmp-1);
            res++;
        }
        return res;
    }
}
