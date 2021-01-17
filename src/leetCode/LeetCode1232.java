package leetCode;

/**
 * @author xianCan
 * @date 2021/1/17 9:31
 *
 * 1232. 缀点成线（简单）
 *
 *  在一个 XY 坐标系中有一些点，我们用数组 coordinates 来分别记录它们的坐标，其中 coordinates[i] = [x, y] 表示横坐标为 x、纵坐标为 y 的点。

    请你来判断，这些点是否在该坐标系中属于同一条直线上，是则返回 true，否则请返回 false。

     

    示例 1：



    输入：coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
    输出：true
    示例 2：



    输入：coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
    输出：false
     

    提示：

    2 <= coordinates.length <= 1000
    coordinates[i].length == 2
    -10^4 <= coordinates[i][0], coordinates[i][1] <= 10^4
    coordinates 中不含重复的点

 */
public class LeetCode1232 {

    public boolean checkStraightLine(int[][] coordinates) {
        int x1 = coordinates[1][0] - coordinates[0][0];
        int y1 = coordinates[1][1] - coordinates[0][1];

        for (int i = 2; i < coordinates.length; ++i) {
            int x2 = coordinates[i][0] - coordinates[i - 1][0];
            int y2 = coordinates[i][1] - coordinates[i - 1][1];
            if (x1 * y2 != x2 * y1) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        boolean b = new LeetCode1232().checkStraightLine(new int[][]{{-3,-2},{-1,-2},{2,-2},{-2,-2},{0,-2}});
        System.out.println(b);
    }
}
