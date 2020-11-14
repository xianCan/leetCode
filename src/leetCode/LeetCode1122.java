package leetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xianCan
 * @date 2020/11/14 9:23
 *
 * 1122. 数组的相对排序（简单）
 *
 *  给你两个数组，arr1 和 arr2，

    arr2 中的元素各不相同
    arr2 中的每个元素都出现在 arr1 中
    对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。

     

    示例：

    输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
    输出：[2,2,2,1,4,3,3,9,6,7,19]
     

    提示：

    arr1.length, arr2.length <= 1000
    0 <= arr1[i], arr2[i] <= 1000
    arr2 中的元素 arr2[i] 各不相同
    arr2 中的每个元素 arr2[i] 都出现在 arr1 中

 */
public class LeetCode1122 {

    /**
     * 自定义排序
     * @param arr1
     * @param arr2
     * @return
     */
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();

        for(int num : arr1) {
            list.add(num);
        }
        for(int i = 0; i < arr2.length; i++) {
            map.put(arr2[i], i);
        }
        list.sort((x, y) -> {
            if(map.containsKey(x) || map.containsKey(y)) {
                return map.getOrDefault(x, 1001) - map.getOrDefault(y, 1001);
            }
            return x - y;
        });

        for(int i = 0; i < arr1.length; i++) arr1[i] = list.get(i);
        return arr1;
    }

    /**
     * 计数排序
     * @param arr1
     * @param arr2
     * @return
     */
    public int[] relativeSortArray2(int[] arr1, int[] arr2){
        int[] count = new int[1001];
        // 将 arr1 中的数记录下来
        for (int num1 : arr1) {
            count[num1]++;
        }
        // 先安排 arr2 中的数
        int i = 0;
        for (int num2 : arr2) {
            while (count[num2] > 0) {
                arr1[i++] = num2;
                count[num2]--;
            }
        }
        // 再排剩下的数
        for (int num1 = 0; num1 < count.length; num1++) {
            while (count[num1] > 0) {
                arr1[i++] = num1;
                count[num1]--;
            }
        }
        return arr1;
    }
}
