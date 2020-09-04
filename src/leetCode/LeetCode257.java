package leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xianCan
 * @date 2020/9/4 23:30
 *
 * 257. 二叉树的所有路径（简单）
 *
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。

    说明: 叶子节点是指没有子节点的节点。

    示例:

    输入:

    1
    /   \
    2     3
    \
    5

    输出: ["1->2->5", "1->3"]

    解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3

 */
public class LeetCode257 {

    private List<String> res;

    public List<String> binaryTreePaths(TreeNode root) {
        this.res = new ArrayList<>();
        dfs(root, "");
        return res;
    }

    private void dfs(TreeNode node, String path){
        if (node != null){
            StringBuilder builder = new StringBuilder(path);
            builder.append(node.val);
            if (node.left == null && node.right == null){
                res.add(builder.toString());
            } else {
                builder.append("->");
                dfs(node.left, builder.toString());
                dfs(node.right, builder.toString());
            }
        }
    }
}
