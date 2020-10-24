package leetCode;

/**
 * @author xianCan
 * @date 2020/9/21 22:24
 *
 * 538. 把二叉搜索树转换为累加树（简单）
 *
 *  给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。

    例如：

    输入: 原始二叉搜索树:
    5
    /   \
    2     13

    输出: 转换为累加树:
    18
    /   \
    20     13


 */
public class LeetCode538 {

    private int sum=0;

    public TreeNode convertBST(TreeNode root) {
        //二叉搜索树中序遍历是顺序的，那么按照右根左（反中序遍历）的顺序遍历，就是倒序的，也就是从后往前遍历
        if (root != null){
            convertBST(root.right);
            sum += root.val;
            root.val = sum;
            convertBST(root.left);
        }
        return root;
    }
}
