package leetCode;

/**
 * @author xianCan
 * @date 2020/8/18 21:56
 *
 * 124. 二叉树中的最大路径和（困难）
 *
 *  给定一个非空二叉树，返回其最大路径和。

    本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。

    示例 1:

    输入: [1,2,3]

    1
    / \
    2   3

    输出: 6
    示例 2:

    输入: [-10,9,20,null,null,15,7]

       -10
       / \
      9  20
        /  \
       15   7

    输出: 42

 */
public class LeetCode124 {

    private int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        helper(root);
        return maxSum;
    }

    private int helper(TreeNode root){
        if (root == null){
            return 0;
        }
        int left = Math.max(0, helper(root.left));
        int right = Math.max(0, helper(root.right));

        int nodeValue = root.val + left + right;
        maxSum = Math.max(nodeValue, maxSum);

        return root.val + Math.max(left, right);
    }

    public static void main(String[] args) {
        TreeNode node5 = new TreeNode(7);
        TreeNode node4 = new TreeNode(15);
        TreeNode node3 = new TreeNode(20, node4, node5);
        TreeNode node2 = new TreeNode(9);
        TreeNode node1 = new TreeNode(-10, node2, node3);

        int i = new LeetCode124().maxPathSum(node1);
        System.out.println(i);
    }
}
