package leetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author xianCan
 * @date 2020/9/14 21:18
 *
 * 94. 二叉树的中序遍历（中等）
 *
 *  给定一个二叉树，返回它的中序 遍历。

    示例:

    输入: [1,null,2,3]
    1
    \
    2
    /
    3

    输出: [1,3,2]
    进阶: 递归算法很简单，你可以通过迭代算法完成吗？

 */
public class LeetCode94 {

    /**
     * 递归
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        dfs(root, ans);
        return ans;
    }

    private void dfs(TreeNode root, List<Integer> ans) {
        if (root != null){
            dfs(root.left, ans);
            ans.add(root.val);
            dfs(root.right, ans);
        }
    }

    /**
     * 非递归
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        while (root != null || !stack.isEmpty()){
            while (root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            ans.add(root.val);
            root = root.right;
        }
        return ans;
    }
}
