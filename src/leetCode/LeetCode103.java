package leetCode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xianCan
 * @date 2020/12/22 20:37
 *
 * 103. 二叉树的锯齿形层序遍历（中等）
 *
 *  给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。

    例如：
    给定二叉树 [3,9,20,null,null,15,7],

    3
    / \
    9  20
    /  \
    15   7
    返回锯齿形层序遍历如下：

    [
    [3],
    [20,9],
    [15,7]
    ]

 */
public class LeetCode103 {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();

        Deque<TreeNode> deque = new LinkedList<>();
        if (root != null){
            deque.offerFirst(root);
        }

        boolean leftFlag = true;
        while (!deque.isEmpty()){
            int len = deque.size();
            List<Integer> list = new ArrayList<>();
            while (len > 0){
                TreeNode node;
                if (leftFlag){
                    node = deque.pollFirst();
                    if (node.left != null){
                        deque.offerLast(node.left);
                    }
                    if (node.right != null){
                        deque.offerLast(node.right);
                    }
                } else {
                    node = deque.pollLast();
                    if (node.right != null){
                        deque.offerFirst(node.right);
                    }
                    if (node.left != null){
                        deque.offerFirst(node.left);
                    }
                }
                list.add(node.val);
                len--;
            }
            leftFlag = !leftFlag;
            ans.add(list);
        }

        return ans;
    }
}
