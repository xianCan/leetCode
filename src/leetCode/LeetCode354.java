package leetCode;

import java.util.Arrays;

/**
 * @author xianCan
 * @date 2020/12/13 22:45
 *
 * 354. 俄罗斯套娃信封问题（困难）
 *
 *  给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。

    请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。

    说明:
    不允许旋转信封。

    示例:

    输入: envelopes = [[5,4],[6,4],[6,7],[2,3]]
    输出: 3
    解释: 最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。

 */
public class LeetCode354 {

    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (o1, o2) -> {
            if (o1[0] == o2[0]){
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });

        int ans = 0, n = envelopes.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < i; j++){
                if (envelopes[j][0] < envelopes[i][0] && envelopes[j][1] < envelopes[i][1]){
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }
            dp[i] += 1;
            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }

    public static void main(String[] args) {
        int i = new LeetCode354().maxEnvelopes(new int[][]{{5, 4}, {6, 4}, {6, 7}, {2, 3}});
        System.out.println(i);
    }
}
