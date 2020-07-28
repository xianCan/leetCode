package leetCode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author xianCan
 * @date 2020/7/28 20:51
 *
 * 104. 二叉树的最大深度
 *
 * 给定一个二叉树，找出其最大深度。

    二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。

    说明: 叶子节点是指没有子节点的节点。

    示例：
    给定二叉树 [3,9,20,null,null,15,7]，

    3
    / \
    9  20
    /  \
    15   7
    返回它的最大深度 3 。

 */
public class LeetCode104 {

    /**
     * 广度优先遍历
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth=0;
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size > 0){
                TreeNode treeNode = queue.poll();
                TreeNode left = treeNode.left;
                TreeNode right = treeNode.right;
                if (left != null)
                    queue.offer(left);
                if (right != null){
                    queue.offer(right);
                }
                size--;
            }
            depth++;
        }
        return depth;
    }
}
