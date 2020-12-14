package leetCode;

/**
 * @author xianCan
 * @date 2020/11/18 19:24
 *
 * 134. 加油站（中等）
 *
 *  在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。

    你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。

    如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。

    说明: 

    如果题目有解，该答案即为唯一答案。
    输入数组均为非空数组，且长度相同。
    输入数组中的元素均为非负数。
    示例 1:

    输入:
    gas  = [1,2,3,4,5]
    cost = [3,4,5,1,2]

    输出: 3

    解释:
    从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
    开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
    开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
    开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
    开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
    开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
    因此，3 可为起始索引。
    示例 2:

    输入:
    gas  = [2,3,4]
    cost = [3,4,3]

    输出: -1

    解释:
    你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
    我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
    开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
    开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
    你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
    因此，无论怎样，你都不可能绕环路行驶一周。

 */
public class LeetCode134 {


    /**
     * 暴力
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length, res;

        for (int i=0; i<len; i++){
            if (gas[i] < cost[i]){
                continue;
            }
            res = gas[i] - cost[i];
            for (int j= i==len-1 ? 0 : i+1; ; j= j==len-1 ? 0 : j+1){
                if (j==i){
                    return i;
                }
                res = res + gas[j] - cost[j];
                if (res < 0){
                    break;
                }
            }
        }

        return -1;
    }

    /**
     * O(N)
     *
     * 从 x,y 之间的任何一个加油站出发，都无法到达加油站 y 的下一个加油站。

     在发现了这一个性质后，算法就很清楚了：我们首先检查第 0 个加油站，并试图判断能否环绕一周；如果不能，就从第一个无法到达的加油站开始继续检查。

     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit2(int[] gas, int[] cost) {
        int len=gas.length, i=0;

        while (i < len){
            int sumOfGas=0, sumOfCost=0;
            int cnt=0;
            while (cnt < len){
                int j = (i + cnt) % len;
                sumOfGas += gas[j];
                sumOfCost += cost[j];
                if (sumOfCost > sumOfGas) {
                    break;
                }
                cnt++;
            }
            if (cnt == len) {
                return i;
            } else {
                i = i + cnt + 1;
            }
        }
        return -1;
    }

    /**
     * 当前累加rest[j]的和curSum一旦小于0，起始位置至少要是j+1，因为从j开始一定不行
     *
     * 如果x到不了y+1（但能到y），那么从x到y的任一点出发都不可能到达y+1。因为从其中任一点出发的话，相当于从0开始加油，
     * 而如果从x出发到该点则不一定是从0开始加油，可能还有剩余的油。既然不从0开始都到不了y，那么从0开始就更不可能到达y了
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit3(int[] gas, int[] cost) {
        int curSum=0, totalSum=0, start=0;
        for (int i=0; i < gas.length; i++){
            curSum += gas[i] - cost[i];
            totalSum += gas[i] - cost[i];
            if (curSum < 0){
                start = i + 1;
                curSum = 0;
            }
        }
        if (totalSum < 0) return -1;
        return start;
    }

    public static void main(String[] args) {
        int i = new LeetCode134().canCompleteCircuit2(new int[]{2,3,4}, new int[]{3,4,3});
        System.out.println(i);
    }
}
