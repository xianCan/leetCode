package interview;

/**
 * @author xianCan
 * @date 2021/4/2 17:13
 *
 * 面试题 17.21. 直方图的水量（困难）
 *
 *  给定一个直方图(也称柱状图)，假设有人从上面源源不断地倒水，最后直方图能存多少水量?直方图的宽度为 1。

    上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的直方图，在这种情况下，可以接 6 个单位的水（蓝色部分表示水）。 感谢 Marcos 贡献此图。

    示例:

    输入: [0,1,0,2,1,0,1,3,2,1,2,1]
    输出: 6

 */
public class Interview1721 {

    /**
     * 双指针
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int len = height.length, leftHigh = len - 1, rightHigh = 0;
        for (int i = len - 2; i >= 0; i--){
            if (height[i] >= height[leftHigh]){
                leftHigh = i;
            }
        }
        for (int i = 1; i < len; i++){
            if (height[i] >= height[rightHigh]){
                rightHigh = i;
            }
        }

        int ans = 0, tmp = -1;
        for (int i = 0; i < leftHigh; i++){
            if (tmp == -1 && height[i] == 0){
                continue;
            } else if (tmp == -1) {
                tmp = i;
            } else if (height[i] < height[tmp]){
                ans += height[tmp] - height[i];
            } else if (height[i] >= height[tmp]){
                tmp = i;
            }
        }

        tmp = -1;
        for (int i = len - 1; i > rightHigh; i--){
            if (tmp == -1 && height[i] == 0){
                continue;
            } else if (tmp == -1) {
                tmp = i;
            } else if (height[i] < height[tmp]){
                ans += height[tmp] - height[i];
            } else if (height[i] >= height[tmp]){
                tmp = i;
            }
        }

        for (int i = leftHigh + 1; i < rightHigh; i++){
            ans += height[leftHigh] - height[i];
        }

        return ans;
    }

    public static void main(String[] args) {
        int trap = new Interview1721().trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1});
        System.out.println(trap);
    }
}
