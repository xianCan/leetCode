package leetCode;

import java.util.LinkedList;

/**
 * @author xianCan
 * @date 2020/8/15 10:50
 *
 * 546. 移除盒子（困难）
 *
 *  给出一些不同颜色的盒子，盒子的颜色由数字表示，即不同的数字表示不同的颜色。
    你将经过若干轮操作去去掉盒子，直到所有的盒子都去掉为止。每一轮你可以移除具有相同颜色的连续 k 个盒子（k >= 1），这样一轮之后你将得到 k*k 个积分。
    当你将所有盒子都去掉之后，求你能获得的最大积分和。

     

    示例：

    输入：boxes = [1,3,2,2,2,3,4,3,1]
    输出：23
    解释：
    [1, 3, 2, 2, 2, 3, 4, 3, 1]
    ----> [1, 3, 3, 4, 3, 1] (3*3=9 分)
    ----> [1, 3, 3, 3, 1] (1*1=1 分)
    ----> [1, 1] (3*3=9 分)
    ----> [] (2*2=4 分)
     

    提示：

    1 <= boxes.length <= 100
    1 <= boxes[i] <= 100

 */
public class LeetCode546 {

    /**
     * 暴力递归
     * @param boxes
     * @return
     */
    public int removeBoxes(int[] boxes) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i: boxes){
            linkedList.add(i);
        }
        return getMaxSum(linkedList);
    }

    private int getMaxSum(LinkedList<Integer> linkedList){
        int sum = 0;
        if (linkedList.isEmpty())
            return sum;

        int size = linkedList.size();
        for (int i=0; i <size; ){
            int tmp = linkedList.get(i);
            int j=i+1;
            while (j < size){
                if (tmp == linkedList.get(j)){
                    j++;
                } else {
                    break;
                }
            }
            LinkedList<Integer> list = new LinkedList<>();
            for (int k=0; k<i; k++){
                list.add(linkedList.get(k));
            }
            for (int l=j; l<size; l++){
                list.add(linkedList.get(l));
            }
            sum = Math.max(sum, (j-i)*(j-i) + getMaxSum(list));
            i=j;
        }
        return sum;
    }

    public static void main(String[] args) {
        int i = new LeetCode546().removeBoxes(new int[]{1, 3, 2, 2, 2, 3, 4, 3, 1});
        System.out.println(i);
    }
}
