package leetCode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author xianCan
 * @date 2020/8/9 19:27
 *
 * 101. 对称二叉树（简单）
 *
 * 给定一个二叉树，检查它是否是镜像对称的。

    例如，二叉树 [1,2,2,3,4,4,3] 是对称的。

    1
    / \
    2   2
    / \ / \
    3  4 4  3
     

    但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:

    1
    / \
    2   2
    \   \
    3    3
     

    进阶：

    你可以运用递归和迭代两种方法解决这个问题吗？

 */
public class LeetCode101 {

    /**
     * 递归
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        return root == null || isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode left, TreeNode right){
        if (left == null && right == null){
            return true;
        } else if (left == null || right == null){
            return false;
        } else if (left.val != right.val){
            return false;
        }
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }

    /**
     * 迭代
     * @param root
     * @return
     */
    public boolean isSymmetric2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(root);

        TreeNode left, right;
        while (!queue.isEmpty()){
            left = queue.poll();
            right = queue.poll();
            if (left == null && right == null){
                continue;
            } else if ((left == null || right == null) || (left.val != right.val)){
                return false;
            }
            queue.offer(left.left);
            queue.offer(right.right);

            queue.offer(left.right);
            queue.offer(right.left);
        }
        return true;
    }
}
