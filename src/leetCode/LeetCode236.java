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

    /**
     * 方法二：递归
     *
     *  1.求最小公共祖先，需要从底向上遍历，那么二叉树，只能通过后序遍历（即：回溯）实现从低向上的遍历方式。

        2.在回溯的过程中，必然要遍历整颗二叉树，即使已经找到结果了，依然要把其他节点遍历完，
        因为要使用递归函数的返回值（也就是代码中的left和right）做逻辑判断。

        3.要理解如果返回值left为空，right不为空为什么要返回right，为什么可以用返回right传给上一层结果。
     *
     * 时间复杂度：O(N)
     * 空间复杂度：O(N)
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == q || root == p)return root;
        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);

        //如果left 和 right都不为空，说明此时root就是最近公共节点。
        if (left != null && right != null) return root;
        //如果left为空，right不为空，就返回right，说明目标节点是通过right返回的，反之依然
        if (left == null) return right;
        return left;
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
