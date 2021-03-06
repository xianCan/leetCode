package leetCode;

/**
 * @author xianCan
 * @date 2020/9/22 21:29
 *
 * 654. 最大二叉树（中等）
 *
 *  给定一个不含重复元素的整数数组。一个以此数组构建的最大二叉树定义如下：

    二叉树的根是数组中的最大元素。
    左子树是通过数组中最大值左边部分构造出的最大二叉树。
    右子树是通过数组中最大值右边部分构造出的最大二叉树。
    通过给定的数组构建最大二叉树，并且输出这个树的根节点。

     

    示例 ：

    输入：[3,2,1,6,0,5]
    输出：返回下面这棵树的根节点：

    6
    /   \
    3     5
    \    /
    2  0
    \
    1
     

    提示：

    给定的数组的大小在 [1, 1000] 之间。

 */
public class LeetCode654 {

    private int[] nums;

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        this.nums = nums;
        return helper(0, nums.length-1);
    }

    private TreeNode helper(int left, int right){
        if (left > right){
            return null;
        }

        int idx=left;
        for (int i=left; i<=right; i++){
            if (nums[i] > nums[idx]){
                idx = i;
            }
        }

        TreeNode node = new TreeNode(nums[idx]);
        node.left = helper(left, idx-1);
        node.right = helper(idx+1, right);
        return node;
    }
}
