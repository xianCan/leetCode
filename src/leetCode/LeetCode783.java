package leetCode;

/**
 * @author xianCan
 * @date 2021/4/13 21:03
 *
 * 783. 二叉搜索树节点最小距离（简单）
 *
 *  给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。

    注意：本题与 530：https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/ 相同

     

    示例 1：


    输入：root = [4,2,6,1,3]
    输出：1
    示例 2：


    输入：root = [1,0,48,null,null,12,49]
    输出：1
     

    提示：

    树中节点数目在范围 [2, 100] 内
    0 <= Node.val <= 105

 */
public class LeetCode783 {

    private int ans = Integer.MAX_VALUE;
    private Integer pre = null;

    public int minDiffInBST(TreeNode root) {
        dfs(root);
        return ans;
    }

    private void dfs(TreeNode node){
        if (node == null){
            return;
        }

        dfs(node.left);
        if (pre != null){
            ans = Math.min(Math.abs(pre - node.val), ans);
        }
        pre = node.val;
        dfs(node.right);
    }
}
