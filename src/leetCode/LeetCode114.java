package leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xianCan
 * @date 2020/8/2 9:58
 *
 * 114. 二叉树展开为链表
 *
 *  给定一个二叉树，原地将它展开为一个单链表。

    例如，给定二叉树

    1
    / \
    2   5
    / \   \
    3   4   6
    将其展开为：

    1
    \
    2
    \
    3
    \
    4
    \
    5
    \
    6

 */
public class LeetCode114 {

    /**
     * 时间复杂度O(N)+空间复杂度O(N)
     * @param root
     */
    public void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        recursive(list, root);
        int size = list.size();
        for (int i=1; i<size; i++){
            TreeNode prev = list.get(i-1), curr=list.get(i);
            prev.left = null;
            prev.right = curr;
        }
    }

    private void recursive(List<TreeNode> list, TreeNode root){
        if (root != null){
            list.add(root);
            recursive(list, root.left);
            recursive(list, root.right);
        }
    }

    /**
     * 时间复杂度O(N)+空间复杂度O(1)
     *
     *      注意到前序遍历访问各节点的顺序是根节点、左子树、右子树。如果一个节点的左子节点为空，则该节点不需要进行展开操作。如果一个
     *  节点的左子节点不为空，则该节点的左子树中的最后一个节点被访问之后，该节点的右子节点被访问。该节点的左子树中最后一个被访问的节
     *  点是左子树中的最右边的节点，也是该节点的前驱节点。因此，问题转化成寻找当前节点的前驱节点。
     *
     *      具体做法是：对于当前节点，如果其左子节点不为空，则在其左子树中找到最右边的节点，作为前驱节点，将当前节点的右子节点赋给前
     *  驱节点的右子节点，然后将当前节点的左子节点赋给当前节点的右子节点，并将当前节点的左子节点设为空。对当前节点处理结束后，继续处
     *  理链表中的下一个节点，直到所有节点都处理结束。
     *
     * @param root
     */
    public void flatten2(TreeNode root){
        TreeNode cur = root;
        while (cur != null){
            TreeNode left = cur.left;
            if (left != null){
                TreeNode right = cur.right;
                TreeNode prev = left;
                while (prev.right != null){
                    prev = prev.right;
                }
                prev.right = right;
                cur.left = null;
                cur.right = left;
            }
            cur = cur.right;
        }
    }

    public static void main(String[] args) {
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);

        TreeNode node2 = new TreeNode(2, node3, node4);
        TreeNode node6 = new TreeNode(6);
        TreeNode node5 = new TreeNode(5, null, node6);
        TreeNode node1 = new TreeNode(1, node2, node5);
        new LeetCode114().flatten2(node1);
        System.out.println();
    }
}
