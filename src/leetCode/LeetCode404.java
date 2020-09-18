package leetCode;

/**
 * @author xianCan
 * @date 2020/9/19 0:02
 *
 * 404. 左叶子之和（简单）
 *
 *  计算给定二叉树的所有左叶子之和。

    示例：

    3
    / \
    9  20
    /  \
    15   7

    在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24


 */
public class LeetCode404 {

    public int sumOfLeftLeaves(TreeNode root) {
        return dfs(false, root);
    }

    private int dfs(boolean isLeft, TreeNode node){
        if (node == null){
            return 0;
        }

        TreeNode left = node.left;
        TreeNode right = node.right;
        if (isLeft && left == null && right == null){
            return node.val;
        }
        return dfs(true, left) + dfs(false, right);
    }

    public static void main(String[] args) {
        TreeNode node5 = new TreeNode(7);
        TreeNode node4 = new TreeNode(15);
        TreeNode node3 = new TreeNode(20, node4, node5);
        //TreeNode node2 = new TreeNode(9);
        TreeNode node1 = new TreeNode(3, null, node3);
        int i = new LeetCode404().sumOfLeftLeaves(node1);
        System.out.println(i);
    }
}
