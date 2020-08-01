package leetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xianCan
 * @date 2020/8/1 17:03
 *
 * 337. 打家劫舍 III
 *
 *  在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。

    计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。

    示例 1:

    输入: [3,2,3,null,3,null,1]

     3
    / \
    2   3
    \   \
    3   1

    输出: 7
    解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
    示例 2:

    输入: [3,4,5,1,3,null,1]

      3
    / \
    4   5
    / \   \
    1   3   1

    输出: 9
    解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
 */
public class LeetCode337 {

    /**
     * 暴力递归，也能过...
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        if (root == null) return 0;
        return recursive(root);
    }

    public int recursive(TreeNode root){
        if (root == null) return 0;
        TreeNode left = root.left;
        TreeNode right = root.right;
        int rob = root.val;
        if (left != null){
            rob += recursive(left.left);
            rob += recursive(left.right);
        }
        if (right != null){
            rob += recursive(right.left);
            rob += recursive(right.right);
        }
        int noRob = recursive(left) + recursive(right);
        return Math.max(rob, noRob);
    }

    /**
     * 带备忘录的暴力递归，已经和动态规划属于同一时间复杂度
     * @param root
     * @return
     */
    public int rob2(TreeNode root) {
        if (root == null) return 0;
        Map<TreeNode, Integer> dp = new HashMap<>();
        return recursive2(root, dp);
    }

    public int recursive2(TreeNode root, Map<TreeNode, Integer> dp){
        if (root == null) return 0;
        if (dp.containsKey(root)){
            return dp.get(root);
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        int rob = root.val;
        if (left != null){
            rob += recursive2(left.left, dp);
            rob += recursive2(left.right, dp);
        }
        if (right != null){
            rob += recursive2(right.left, dp);
            rob += recursive2(right.right, dp);
        }
        int noRob = recursive2(left, dp) + recursive2(right,dp);
        int res = Math.max(rob, noRob);
        dp.put(root, res);
        return res;
    }

    public static void main(String[] args) {
        /*TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(3);
        TreeNode node6 = new TreeNode(1);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.right = node6;*/
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(1);
        node1.left = node2;
        node1.right = node3;
        node2.right = node4;
        node3.right = node5;
        int rob = new LeetCode337().rob2(node1);
        System.out.println(rob);

    }
}
