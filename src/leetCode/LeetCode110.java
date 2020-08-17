package leetCode;

/**
 * @author xianCan
 * @date 2020/8/17 21:48
 *
 * 110.平衡二叉树（简单）
 *
 *  给定一个二叉树，判断它是否是高度平衡的二叉树。

    本题中，一棵高度平衡二叉树定义为：

    一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。

    示例 1:

    给定二叉树 [3,9,20,null,null,15,7]

    3
    / \
    9  20
    /  \
    15   7
    返回 true 。

    示例 2:

    给定二叉树 [1,2,2,3,3,null,null,4,4]

    1
    / \
    2   2
    / \
    3   3
    / \
    4   4
    返回 false 。
 */
public class LeetCode110 {

    /**
     * 自底向上，O(N)
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null){
            return true;
        }
        return getHeight(root) != -1;
    }

    private int getHeight(TreeNode node){
        if (node == null){
            return 0;
        }
        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);
        if (leftHeight==-1 || rightHeight==-1 || Math.abs(leftHeight-rightHeight)>1){
            return -1;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * 自顶向下，O(N^2)
     * @param root
     * @return
     */
    public boolean isBalanced2(TreeNode root){
        if (root == null){
            return true;
        }
        return Math.abs(getHeight2(root.left) - getHeight2(root.right)) <=1 && isBalanced2(root.left) && isBalanced2(root.right);
    }

    public int getHeight2(TreeNode node){
        if (node==null){
            return 0;
        }
        return Math.max(getHeight2(node.left), getHeight2(node.right)) + 1;
    }

}
