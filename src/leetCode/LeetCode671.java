package leetCode;

/**
 * @author xianCan
 * @date 2021/7/27 21:19
 *
 * 671. 二叉树中第二小的节点（简单）
 *
 *  给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一个。

    更正式地说，root.val = min(root.left.val, root.right.val) 总成立。

    给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。

     

    示例 1：


    输入：root = [2,2,5,null,null,5,7]
    输出：5
    解释：最小的值是 2 ，第二小的值是 5 。
    示例 2：


    输入：root = [2,2,2]
    输出：-1
    解释：最小的值是 2, 但是不存在第二小的值。
     

    提示：

    树中节点数目在范围 [1, 25] 内
    1 <= Node.val <= 231 - 1
    对于树中每个节点 root.val == min(root.left.val, root.right.val)

 */
public class LeetCode671 {

    int[] mins;

    public int findSecondMinimumValue(TreeNode root) {
        mins = new int[]{-1, -1};
        dfs(root);
        return mins[1] != -1 && mins[1] > mins[0] ? mins[1] : -1;
    }

    private void dfs(TreeNode node){
        if (node == null){
            return;
        }

        if (mins[0] == -1){
            mins[0] = node.val;
        } else if (mins[1] == -1 && node.val > mins[0]){
            mins[1] = node.val;
        } else if (mins[1] != -1 && node.val > mins[0]){
            mins[1] = Math.min(mins[1], node.val);
        }
        dfs(node.left);
        dfs(node.right);
    }

    public static void main(String[] args) {
        TreeNode node3 = new TreeNode(5);
        TreeNode node2 = new TreeNode(8);
        TreeNode node1 = new TreeNode(5, node2, node3);
        int secondMinimumValue = new LeetCode671().findSecondMinimumValue(node1);
        System.out.println();
    }
}
