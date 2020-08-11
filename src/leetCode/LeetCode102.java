package leetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author xianCan
 * @date 2020/8/11 21:24
 *
 * 102. 二叉树的层序遍历（中等）
 *
 *  给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 *
    示例：
    二叉树：[3,9,20,null,null,15,7],

    3
    / \
    9  20
    /  \
    15   7
    返回其层次遍历结果：

    [
    [3],
    [9,20],
    [15,7]
    ]

 */
public class LeetCode102 {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null){
            queue.offer(root);
        }

        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> tmp = new ArrayList<>();
            while (size > 0){
                TreeNode poll = queue.poll();
                if (poll.left != null){
                    queue.offer(poll.left);
                }
                if (poll.right != null){
                    queue.offer(poll.right);
                }
                tmp.add(poll.val);
                size--;
            }
            res.add(tmp);
        }
        return res;
    }
}
