package leetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author xianCan
 * @date 2020/8/22 17:22
 *
 * 236. 二叉树的最近公共祖先（中等）
 *
 *  给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

    百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

    例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]



     

    示例 1:

    输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
    输出: 3
    解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
    示例 2:

    输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
    输出: 5
    解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
     

    说明:

    所有节点的值都是唯一的。
    p、q 为不同节点且均存在于给定的二叉树中。

 */
public class LeetCode236 {

    Map<Integer, TreeNode> map  = new HashMap<>();
    Set<Integer> visited = new HashSet<>();

    /**
     * 方法一：存储父节点
     *
     * 时间复杂度：O(N)
     * 空间复杂度：O(N)
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root);
        while (p != null){
            visited.add(p.val);
            p = map.get(p.val);
        }
        while (q != null){
            if (visited.contains(q.val)){
                return q;
            }
            q = map.get(q.val);
        }
        return null;
    }

    private void dfs(TreeNode root){
        if (root.left != null){
            map.put(root.left.val, root);
            dfs(root.left);
        }
        if (root.right != null){
            map.put(root.right.val, root);
            dfs(root.right);
        }
    }

    private TreeNode ans;

    /**
     * 方法二：递归
     *
     * 定义 fx 表示 x 节点的子树中是否包含 p 节点或 q 节点，如果包含为 true，否则为 false
     *
     * 最近公共祖先满足：x = (flson && frson) ∣∣ ((x = p ∣∣ x = q) && (flson ∣∣ frson))
     *
     * 时间复杂度：O(N)
     * 空间复杂度：O(N)
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return ans;
    }

    private boolean dfs(TreeNode root, TreeNode p, TreeNode q){
        if (root == null)return false;
        boolean lson = dfs(root.left, p, q);
        boolean rson = dfs(root.right, p, q);
        if ((lson && rson) || ((root.val == p.val || root.val == q.val) && (lson || rson))){
            ans = root;
        }
        return lson || rson || (root.val == p.val || root.val == q.val);
    }

    public static void main(String[] args) {
        TreeNode node9 = new TreeNode(4);
        TreeNode node8 = new TreeNode(7);
        TreeNode node7 = new TreeNode(8);
        TreeNode node6 = new TreeNode(0);
        TreeNode node5 = new TreeNode(2, node8, node9);
        TreeNode node4 = new TreeNode(6);
        TreeNode node3 = new TreeNode(1, node6, node7);
        TreeNode node2 = new TreeNode(5, node4, node5);
        TreeNode node1 = new TreeNode(3, node2, node3);

        TreeNode node = new LeetCode236().lowestCommonAncestor(node1, node2, node9);
        System.out.println(node);
    }
}
