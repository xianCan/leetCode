package leetCode;

import java.util.*;

/**
 * @author xianCan
 * @date 2020/11/6 19:55
 *
 * 1356. 根据数字二进制下 1 的数目排序（简单）
 *
 *  给你一个整数数组 arr 。请你将数组中的元素按照其二进制表示中数字 1 的数目升序排序。

    如果存在多个数字二进制中 1 的数目相同，则必须将它们按照数值大小升序排列。

    请你返回排序后的数组。

     

    示例 1：

    输入：arr = [0,1,2,3,4,5,6,7,8]
    输出：[0,1,2,4,8,3,5,6,7]
    解释：[0] 是唯一一个有 0 个 1 的数。
    [1,2,4,8] 都有 1 个 1 。
    [3,5,6] 有 2 个 1 。
    [7] 有 3 个 1 。
    按照 1 的个数排序得到的结果数组为 [0,1,2,4,8,3,5,6,7]
    示例 2：

    输入：arr = [1024,512,256,128,64,32,16,8,4,2,1]
    输出：[1,2,4,8,16,32,64,128,256,512,1024]
    解释：数组中所有整数二进制下都只有 1 个 1 ，所以你需要按照数值大小将它们排序。
    示例 3：

    输入：arr = [10000,10000]
    输出：[10000,10000]
    示例 4：

    输入：arr = [2,3,5,7,11,13,17,19]
    输出：[2,3,5,17,7,11,13,19]
    示例 5：

    输入：arr = [10,100,1000,10000]
    输出：[10,100,10000,1000]
     

    提示：

    1 <= arr.length <= 500
    0 <= arr[i] <= 10^4

 */
public class LeetCode1356 {

    public int[] sortByBits(int[] arr) {
        List<Integer> list = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr){
            int tmp = num;
            int bitCount=0;
            while (tmp > 0){
                if ((tmp & 1) == 1){
                    bitCount++;
                }
                tmp = tmp >> 1;
            }
            list.add(num);
            map.put(num, bitCount);
        }

        list.sort((o1, o2) -> {
            Integer i1 = map.get(o1);
            Integer i2 = map.get(o2);
            if (!Objects.equals(i1, i2)){
                return i1 - i2;
            } else {
                return o1 - o2;
            }
        });

        for (int i=0; i<arr.length; i++){
            arr[i] = list.get(i);
        }

        return arr;
    }

    public static void main(String[] args) {
        int[] ints = new LeetCode1356().sortByBits(new int[]{1024,512,256,128,64,32,16,8,4,2,1});
        System.out.println(ints);
    }
}
