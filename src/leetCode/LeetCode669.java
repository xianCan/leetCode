package leetCode;

/**
 * @author xianCan
 * @date 2020/10/24 10:47
 *
 * 669. 修剪二叉搜索树（简单）
 *
 *  给你二叉搜索树的根节点 root ，同时给定最小边界low 和最大边界 high。通过修剪二叉搜索树，使得所有节点的值在[low, high]中。修剪树不应该改变保留在树中的元素的相对结构（即，如果没有被移除，原有的父代子代关系都应当保留）。 可以证明，存在唯一的答案。

    所以结果应当返回修剪好的二叉搜索树的新的根节点。注意，根节点可能会根据给定的边界发生改变。

     

    示例 1：


    输入：root = [1,0,2], low = 1, high = 2
    输出：[1,null,2]
    示例 2：


    输入：root = [3,0,4,null,2,null,null,1], low = 1, high = 3
    输出：[3,2,null,1]
    示例 3：

    输入：root = [1], low = 1, high = 2
    输出：[1]
    示例 4：

    输入：root = [1,null,2], low = 1, high = 3
    输出：[1,null,2]
    示例 5：

    输入：root = [1,null,2], low = 2, high = 4
    输出：[2]
     

    提示：

    树中节点数在范围 [1, 104] 内
    0 <= Node.val <= 104
    树中每个节点的值都是唯一的
    题目数据保证输入是一棵有效的二叉搜索树
    0 <= low <= high <= 104

 */
public class LeetCode669 {

    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        } else if (root.val < low ){
            return trimBST(root.right, low, high);
        } else if (root.val > high){
            return trimBST(root.left, low, high);
        } else {
            root.left = trimBST(root.left, low, high);
            root.right = trimBST(root.right, low, high);
            return root;
        }
    }

    public static void main(String[] args) {
        TreeNode node5 = new TreeNode(1);
        TreeNode node4 = new TreeNode(2, node5, null);
        TreeNode node3 = new TreeNode(4);
        TreeNode node2 = new TreeNode(0, null, node4);
        TreeNode node1 = new TreeNode(3, node2, node3);
        TreeNode node = new LeetCode669().trimBST(node1, 1, 3);
        System.out.println(node.val);
    }
}