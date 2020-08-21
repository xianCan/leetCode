package leetCode;

/**
 * @author xianCan
 * @date 2020/8/21 23:29
 *
 * 951. 翻转等价二叉树（中等）
 *
 *  我们可以为二叉树 T 定义一个翻转操作，如下所示：选择任意节点，然后交换它的左子树和右子树。

    只要经过一定次数的翻转操作后，能使 X 等于 Y，我们就称二叉树 X 翻转等价于二叉树 Y。

    编写一个判断两个二叉树是否是翻转等价的函数。这些树由根节点 root1 和 root2 给出。

     

    示例：

    输入：root1 = [1,2,3,4,5,6,null,null,null,7,8], root2 = [1,3,2,null,6,4,5,null,null,null,null,8,7]
    输出：true
    解释：我们翻转值为 1，3 以及 5 的三个节点。

     

    提示：

    每棵树最多有 100 个节点。
    每棵树中的每个值都是唯一的、在 [0, 99] 范围内的整数。

 */
public class LeetCode951 {

    /**
     * 递归
     *
     *  时间复杂度： O(min(N_1, N_2))，其中 N_1，N_2 分别是二叉树 root1，root2 的大小。
        空间复杂度： O(min(H_1, H_2))，其中 H_1，H_2 分别是二叉树 root1， root2 的高度。
     * @param root1
     * @param root2
     * @return
     */
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;
        if (root1.val != root2.val) return false;

        return (flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right)) ||
                (flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left));
    }

    /**
     * 方法二：
     *
     * 思路：
     * 让树中所有节点的左孩子都小于右孩子，如果当前不满足就翻转。我们把这种状态的二叉树称为 标准态。所有等价二叉树在转换成标准态后都是完全一样的。
     *
     * 算法：
     * 用深度优先遍历来对比这两棵树在标准态下是否完全一致。对于两颗等价树，在标准态下遍历的结果一定是一样的。
     *
     * 复杂度分析：
     * 时间复杂度： O(N_1 + N_2)，其中 N_1，N_2 分别为二叉树 root1，root2 的大小。
     * 空间复杂度： O(N_1 + N_2)，其中 H_1，H_2 是二叉树 root1，root2 的高度。
     */

}
