package leetCode;

/**
 * @author xianCan
 * @date 2020/10/12 20:51
 *
 * 530. 二叉搜索树的最小绝对差（简单）
 *
 *  给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。

    示例：

    输入：

    1
    \
    3
    /
    2

    输出：
    1

    解释：
    最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
     

    提示：

    树中至少有 2 个节点。
    本题与 783 https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/ 相同

 */
public class LeetCode530 {

    private int prev=-1;
    private int res=Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {
        dfs(root);
        return res;
    }

    private void dfs(TreeNode node){
        if (node==null){
            return;
        }
        dfs(node.left);
        if (prev != -1){
            res = Math.min(res, Math.abs(node.val-prev));
        }
        prev=node.val;
        dfs(node.right);
    }

    public static void main(String[] args) {
        TreeNode node3 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3, node3, null);
        TreeNode node1 = new TreeNode(1, null, node2);
        int minimumDifference = new LeetCode530().getMinimumDifference(node1);
        System.out.println(minimumDifference);
    }
}
