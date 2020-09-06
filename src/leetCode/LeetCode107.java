package leetCode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author xianCan
 * @date 2020/9/6 10:27
 *
 * 107. 二叉树的层次遍历 II（简单）
 *
 *  给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）

    例如：
    给定二叉树 [3,9,20,null,null,15,7],

    3
    / \
    9  20
    /  \
    15   7
    返回其自底向上的层次遍历为：

    [
    [15,7],
    [9,20],
    [3]
    ]

 */
public class LeetCode107 {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null){
            queue.offer(root);
        }

        while (!queue.isEmpty()){
            int size = queue.size();
            LinkedList<Integer> linkedList = new LinkedList<>();
            while (size > 0){
                TreeNode node = queue.poll();
                linkedList.add(node.val);
                if (node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }
                size--;
            }
            res.addFirst(linkedList);
        }

        return res;
    }

    public static void main(String[] args) {
        TreeNode node5 = new TreeNode(7);
        TreeNode node4 = new TreeNode(15);
        TreeNode node3 = new TreeNode(20, node4, node5);
        TreeNode node2 = new TreeNode(9);
        TreeNode node1 = new TreeNode(3, node2, node3);
        List<List<Integer>> lists = new LeetCode107().levelOrderBottom(node1);
        System.out.println(lists);
    }
}
