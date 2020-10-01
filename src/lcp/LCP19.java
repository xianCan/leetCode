package lcp;

/**
 * @author xianCan
 * @date 2020/10/1 10:34
 *
 * LCP 19. 秋叶收藏集（中等）
 *
 *  小扣出去秋游，途中收集了一些红叶和黄叶，他利用这些叶子初步整理了一份秋叶收藏集 leaves， 字符串 leaves 仅包含小写字符 r 和 y， 其中字符 r 表示一片红叶，字符 y 表示一片黄叶。
    出于美观整齐的考虑，小扣想要将收藏集中树叶的排列调整成「红、黄、红」三部分。每部分树叶数量可以不相等，但均需大于等于 1。每次调整操作，小扣可以将一片红叶替换成黄叶或者将一片黄叶替换成红叶。请问小扣最少需要多少次调整操作才能将秋叶收藏集调整完毕。

    示例 1：

    输入：leaves = "rrryyyrryyyrr"

    输出：2

    解释：调整两次，将中间的两片红叶替换成黄叶，得到 "rrryyyyyyyyrr"

    示例 2：

    输入：leaves = "ryr"

    输出：0

    解释：已符合要求，不需要额外操作

    提示：

    3 <= leaves.length <= 10^5
    leaves 中只包含字符 'r' 和字符 'y'


 */
public class LCP19 {
    /**
     *
     * 动态规划

     使用 3 个 dp 数组记录状态

     dp[i][0] 代表从头开始全部修改成红色（纯红）需要修改几次
     dp[i][1] 代表从头开始是红色，然后现在是黄色（红黄），需要修改几次
     dp[i][2] 代表从头开始是红色，然后变成黄色，又变成红色（红黄红），需要修改几次
     根据 i 是红是黄，判断转移情况

     dp[i][0] 就很简单，如果是黄的，就比之前加一
     dp[i][1] 可以从上一个纯红状态变化过来，也可以从上一个本身状态变化过来
     dp[i][2] 可以从上一个红黄状态变化过来，也可以从上一个本身状态变化过来

     * @param leaves
     * @return
     */
    public int minimumOperations(String leaves) {
        if (leaves==null || leaves.length()==0){
            return 0;
        }
        int len = leaves.length();
        char[] chars = leaves.toCharArray();
        /**
            状态数组，state[i][j]中：
                i表示终止下标
                j表示：0为左半边，1为中间部分，2为右半边
            state[i][j] 表示 从0到i需要调整的叶子数
         */
        int[][] dp = new int[len][3];
        /**
            记录 已知状态数组元素：
                1、第一个叶子，必须是左半部分，所以只需判断是不是 黄色叶子 即可
                2、第一个叶子，必须是左半部分，所以 state[0][1] 和 state[0][2] 都是无效的
                3、第二个叶子，可以是左半部分，也可以是中间部分，但是不能是右半部分(每个区间必须有叶子)，
                    因此 state[1][2]是无效的
         */
        dp[0][0] = chars[0] == 'y' ? 1 : 0;
        dp[0][1] = dp[0][2] = dp[1][2] = Integer.MAX_VALUE;

        for (int i=1; i<len; i++){
            int isRed = chars[i] == 'r' ? 1 : 0;
            int isYellow = chars[i] == 'y' ? 1 : 0;
            dp[i][0] = dp[i-1][0] + isYellow;
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][1]) + isRed;
            if (i>=2){
                // 右半部分 的叶子 必须是第2个元素之后的元素
                dp[i][2] = Math.min(dp[i-1][1], dp[i-1][2]) + isYellow;
            }
        }

        return dp[len-1][2];
    }

    public static void main(String[] args) {
        int rrr = new LCP19().minimumOperations("rrr");
        System.out.println(rrr);
    }
}
