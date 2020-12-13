package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xianCan
 * @date 2020/12/13 18:41
 *
 * 5245. 堆叠长方体的最大高度（困难）
 *
 *  给你 n 个长方体 cuboids ，其中第 i 个长方体的长宽高表示为 cuboids[i] = [widthi, lengthi, heighti]（下标从 0 开始）。请你从 cuboids 选出一个 子集 ，并将它们堆叠起来。

    如果 widthi <= widthj 且 lengthi <= lengthj 且 heighti <= heightj ，你就可以将长方体 i 堆叠在长方体 j 上。你可以通过旋转把长方体的长宽高重新排列，以将它放在另一个长方体上。

    返回 堆叠长方体 cuboids 可以得到的 最大高度 。

     

    示例 1：



    输入：cuboids = [[50,45,20],[95,37,53],[45,23,12]]
    输出：190
    解释：
    第 1 个长方体放在底部，53x37 的一面朝下，高度为 95 。
    第 0 个长方体放在中间，45x20 的一面朝下，高度为 50 。
    第 2 个长方体放在上面，23x12 的一面朝下，高度为 45 。
    总高度是 95 + 50 + 45 = 190 。
    示例 2：

    输入：cuboids = [[38,25,45],[76,35,3]]
    输出：76
    解释：
    无法将任何长方体放在另一个上面。
    选择第 1 个长方体然后旋转它，使 35x3 的一面朝下，其高度为 76 。
    示例 3：

    输入：cuboids = [[7,11,17],[7,17,11],[11,7,17],[11,17,7],[17,7,11],[17,11,7]]
    输出：102
    解释：
    重新排列长方体后，可以看到所有长方体的尺寸都相同。
    你可以把 11x7 的一面朝下，这样它们的高度就是 17 。
    堆叠长方体的最大高度为 6 * 17 = 102 。
     

    提示：

    n == cuboids.length
    1 <= n <= 100
    1 <= widthi, lengthi, heighti <= 100

 */
public class LeetCode5245 {

    private boolean[] used;
    private List<List<Tube>> listList;
    private int ans;

    /**
     * 暴力回溯
     * @param cuboids
     * @return
     */
    public int maxHeight(int[][] cuboids) {
        used = new boolean[cuboids.length];
        listList = new ArrayList<>(cuboids.length);
        ans=0;
        for (int[] cuboid : cuboids){
            List<Tube> tubes = new ArrayList<>();
            tubes.add(new Tube(Math.max(cuboid[0], cuboid[1]), Math.min(cuboid[0], cuboid[1]), cuboid[2]));
            tubes.add(new Tube(Math.max(cuboid[0], cuboid[2]), Math.min(cuboid[0], cuboid[2]), cuboid[1]));
            tubes.add(new Tube(Math.max(cuboid[1], cuboid[2]), Math.min(cuboid[1], cuboid[2]), cuboid[0]));
            listList.add(tubes);
        }
        backTrack(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0);
        return ans;
    }

    private void backTrack(int preLength, int preWidth, int preHeight, int heightSum){
        for (int i=0; i < listList.size(); i++){
            if (!used[i]){
                used[i] = true;
                for (Tube tube : listList.get(i)){
                    if (tube.length <= preLength && tube.width <= preWidth && tube.height <= preHeight){
                        int nHeight = tube.height + heightSum;
                        ans = Math.max(ans, nHeight);
                        backTrack(tube.length, tube.width, tube.height, nHeight);
                    }
                }
                used[i] = false;
            }

        }
    }

    class Tube{
        int length;
        int width;
        int height;

        public Tube(int length, int width, int height) {
            this.length = length;
            this.width = width;
            this.height = height;
        }
    }

    /**
     * 动态规划，类似于最长上升子序列
     *
     * 进行两次排序
     * 1、将每个长方体的三边升序排列。
     * 2、将所有长方体升序排列（这里是按照长宽高递增来考虑的，显然，这与题目中的描述是等价的）。因为最优解下，长方体从小到大排列的次序必然是升序排列，所以这一步排序可以保证我们能够得到最优解。
     * @param cuboids
     * @return
     */
    public int maxHeight2(int[][] cuboids) {
        int len = cuboids.length;
        for (int[] cuboid : cuboids){
            Arrays.sort(cuboid);
        }
        Arrays.sort(cuboids, (o1, o2) -> {
            if (o1[0] == o2[0]){
                if (o1[1] == o2[1]){
                    return o1[2] - o2[2];
                }
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });

        int[] dp = new int[len];
        int res = 0;

        for (int i=0; i < len; i++){
            for (int j=0; j < i; j++){
                if (cuboids[j][0] <= cuboids[i][0] && cuboids[j][1] <= cuboids[i][1] && cuboids[j][2] <= cuboids[i][2]){
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }
            dp[i] += cuboids[i][2];
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        /*{13,72,18},{72,88,79},{14,100,85},{46,82,74},{62,51,98},{46,31,4},{100,68,69},
                {26,64,41},{78,95,31},{73,45,29},{28,20,84},{79,48,30},{40,78,55},{23,96,34},{71,40,22},{46,30,65},{78,26,13},{83,87,30},{4,62,38},
                {8,68,82},{68,79,44},{46,39,99},{69,11,28},{14,41,48},{39,37,44},{21,46,79},{53,63,98},{30,42,72},{34,6,71},{21,14,88},{44,97,30},
                {6,49,65},{37,72,75},{29,87,69},{84,26,79},{91,66,26},{69,20,97},{69,15,34},{36,31,40},{25,92,54},{12,35,16},{74,45,92},{4,79,69},{43,48,16}*/
        //int i = new LeetCode5245().maxHeight2(new int[][]{{56,19,74},{59,21,34},{77,9,53},{19,99,14},{43,50,59},{24,80,40},{37,81,31},{99,74,94},{15,20,38},{51,13,49},{54,74,80},{4,86,29},{47,97,59},{86,29,7},{98,48,68},{87,92,64},{3,70,30},{2,45,79},{77,79,55},{46,43,57},{16,10,18},{32,61,48},{59,16,20},{93,5,72},{49,8,83},{46,77,76},{10,48,35},{88,19,90},{29,63,51},{34,38,70},{42,84,27},{57,42,77},{48,93,96},{92,95,18},{47,82,53},{55,67,15},{24,30,65},{26,21,60},{8,75,14},{53,8,82},{24,29,91},{92,22,63},{18,87,94},{85,4,11},{35,39,17},{57,89,39},{81,99,92},{45,82,70}});
        int i = new LeetCode5245().maxHeight2(new int[][]{{7, 11, 17}, {7, 17, 11}, {11, 7, 17}, {11, 17, 7}, {17, 7, 11}, {17, 11, 7}});
        System.out.println(i);
    }
}
