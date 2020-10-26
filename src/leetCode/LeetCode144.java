package leetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author xianCan
 * @date 2020/10/27 0:05
 *
 * 144. 二叉树的前序遍历（中等）
 *
 *  给定一个二叉树，返回它的 前序 遍历。

     示例:

    输入: [1,null,2,3]
    1
    \
    2
    /
    3

    输出: [1,2,3]
    进阶: 递归算法很简单，你可以通过迭代算法完成吗？

 */
public class LeetCode144 {

    /**
     * 递归
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        return list;
    }

    private void dfs(TreeNode node, List<Integer> list){
        if (node == null)return;
        list.add(node.val);
        dfs(node.left, list);
        dfs(node.right, list);
    }

    /**
     * 迭代
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root != null){
            stack.push(root);
        }

        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            list.add(node.val);

            if (node.right != null){
                stack.push(node.right);
            }
            if (node.left != null){
                stack.push(node.left);
            }
        }

        return list;
    }
}
