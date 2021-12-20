package leetCode;

import java.util.Arrays;

/**
 * @author xianCan
 * @date 2021/12/20 21:00
 *
 * 475. 供暖器（中等）
 *
 *  冬季已经来临。 你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖。

    在加热器的加热半径范围内的每个房屋都可以获得供暖。

    现在，给出位于一条水平线上的房屋 houses 和供暖器 heaters 的位置，请你找出并返回可以覆盖所有房屋的最小加热半径。

    说明：所有供暖器都遵循你的半径标准，加热的半径也一样。

     

    示例 1:

    输入: houses = [1,2,3], heaters = [2]
    输出: 1
    解释: 仅在位置2上有一个供暖器。如果我们将加热半径设为1，那么所有房屋就都能得到供暖。
    示例 2:

    输入: houses = [1,2,3,4], heaters = [1,4]
    输出: 1
    解释: 在位置1, 4上有两个供暖器。我们需要将加热半径设为1，这样所有房屋就都能得到供暖。
    示例 3：

    输入：houses = [1,5], heaters = [2]
    输出：3
     

    提示：

    1 <= houses.length, heaters.length <= 3 * 104
    1 <= houses[i], heaters[i] <= 109

 */
public class LeetCode475 {

    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int ans = 0, l = 0, r = 0;
        for (int house : houses){
            while (r + 1 < heaters.length && heaters[r] < house){
                r++;
            }
            while (l + 1 < heaters.length && heaters[l + 1] <= house){
                l++;
            }
            ans = Math.max(ans, Math.min(Math.abs(heaters[l] - house), Math.abs(heaters[r] - house)));
        }
        return ans;
    }
}
