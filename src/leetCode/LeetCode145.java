package leetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author xianCan
 * @date 2020/8/13 21:25
 *
 * 145. 二叉树的后序遍历（困难）
 *
 * 给定一个二叉树，返回它的 后序 遍历。

    示例:

    输入: [1,null,2,3]
    1
    \
    2
    /
    3

    输出: [3,2,1]
    进阶: 递归算法很简单，你可以通过迭代算法完成吗？

 */
public class LeetCode145 {

    /**
     * 递归
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        recursive(root, res);
        return res;
    }

    private void recursive(TreeNode root, List<Integer> res) {
        if (root != null){
            recursive(root.left, res);
            recursive(root.right, res);
            res.add(root.val);
        }
    }

    /**
     * 迭代
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal2(TreeNode root){
        LinkedList<Integer> res = new LinkedList<>();

        Stack<TreeNode> stack = new Stack<>();
        if (root != null){
            stack.push(root);
        }
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            res.addFirst(node.val);
            TreeNode left = node.left;
            if (left != null){
                stack.push(left);
            }
            TreeNode right = node.right;
            if (right != null){
                stack.push(right);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode node7 = new TreeNode(7);
        TreeNode node6 = new TreeNode(6);
        TreeNode node5 = new TreeNode(5);
        TreeNode node4 = new TreeNode(4);
        TreeNode node3 = new TreeNode(3, node6, node7);
        TreeNode node2 = new TreeNode(2, node4, node5);
        TreeNode node1 = new TreeNode(1, node2, node3);
        List<Integer> list = new LeetCode145().postorderTraversal2(node1);
        for (Integer i : list){
            System.out.println(i);
        }
    }
}
