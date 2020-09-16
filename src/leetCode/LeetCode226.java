package leetCode;

/**
 * @author xianCan
 * @date 2020/9/16 20:48
 *
 * 226. 翻转二叉树（简单）
 *
 *  翻转一棵二叉树。

    示例：

    输入：

    4
    /   \
    2     7
    / \   / \
    1   3 6   9
    输出：

    4
    /   \
    7     2
    / \   / \
    9   6 3   1

 */
public class LeetCode226 {

    public TreeNode invertTree(TreeNode root) {
        if(root == null)return null;

        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}
