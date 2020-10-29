package leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xianCan
 * @date 2020/10/29 20:59
 *
 * 129. 求根到叶子节点数字之和（中等）
 *
 *  给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。

    例如，从根到叶子节点路径 1->2->3 代表数字 123。

    计算从根到叶子节点生成的所有数字之和。

    说明: 叶子节点是指没有子节点的节点。

    示例 1:

    输入: [1,2,3]
    1
    / \
    2   3
    输出: 25
    解释:
    从根到叶子节点路径 1->2 代表数字 12.
    从根到叶子节点路径 1->3 代表数字 13.
    因此，数字总和 = 12 + 13 = 25.
    示例 2:

    输入: [4,9,0,5,1]
    4
    / \
    9   0
     / \
    5   1
    输出: 1026
    解释:
    从根到叶子节点路径 4->9->5 代表数字 495.
    从根到叶子节点路径 4->9->1 代表数字 491.
    从根到叶子节点路径 4->0 代表数字 40.
    因此，数字总和 = 495 + 491 + 40 = 1026.

 */
public class LeetCode129 {

    private StringBuilder path;
    private List<String> list;

    public int sumNumbers(TreeNode root) {
        this.path = new StringBuilder();
        this.list = new ArrayList<>();
        dfs(root);
        int res=0;
        for (String str : list){
            res += Integer.parseInt(str);
        }

        return res;
    }

    private void dfs(TreeNode node){
        if (node == null){
            return;
        }
        path.append(node.val);
        if (node.left == null && node.right==null){
            list.add(new String(path));
        }
        dfs(node.left);
        dfs(node.right);
        path.deleteCharAt(path.length()-1);
    }

    /**
     * 深度优先的思想
     * @param root
     * @return
     */
    public int sumNumbers2(TreeNode root) {
        return helper(root, 0);
    }

    private int helper(TreeNode node, int pathValue){
        if (node == null) return 0;

        pathValue = pathValue * 10 + node.val;
        if (node.left == null && node.right == null){
            return pathValue;
        } else {
            return helper(node.left, pathValue) + helper(node.right, pathValue);
        }
    }

    private int ans=0;
    private int pathValue=0;

    /**
     * 基于回溯的思想
     * @param root
     * @return
     */
    public int sumNumbers3(TreeNode root) {
        this.ans = 0;
        this.pathValue = 0;
        helper2(root);
        return ans;
    }

    private void helper2(TreeNode node){
        if (node==null)return;

        pathValue = pathValue * 10 + node.val;
        if (node.left == null && node.right == null){
            ans += pathValue;
        }

        helper2(node.left);
        helper2(node.right);
        pathValue = pathValue / 10;
    }

    public static void main(String[] args) {
        TreeNode node5 = new TreeNode(1);
        TreeNode node4 = new TreeNode(5);
        TreeNode node3 = new TreeNode(0);
        TreeNode node2 = new TreeNode(9, node4, node5);
        TreeNode node1 = new TreeNode(4, node2, node3);
        int i = new LeetCode129().sumNumbers3(node1);
        System.out.println(i);
    }
}
