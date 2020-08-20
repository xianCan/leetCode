package leetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xianCan
 * @date 2020/8/20 23:20
 *
 * 113. 路径总和 II（中等）
 *
 *  给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。

    说明: 叶子节点是指没有子节点的节点。

    示例:
    给定如下二叉树，以及目标和 sum = 22，

    5
    / \
    4   8
    /   / \
    11  13  4
    /  \    / \
    7    2  5   1
    返回:

    [
    [5,4,11,2],
    [5,8,4,5]
    ]

 */
public class LeetCode113 {

    /**
     * 递归，dfs
     * @param root
     * @param sum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root==null){
            return result;
        }
        LinkedList<Integer> linkedList = new LinkedList<>();
        recursive(root, sum, linkedList, result);
        return result;
    }

    private void recursive(TreeNode root, int sum, LinkedList<Integer> linkedList, List<List<Integer>> result){
        if (root == null){
            return;
        }
        sum -= root.val;
        linkedList.addLast(root.val);
        if (root.left==null && root.right==null){
            if (sum==0){
                result.add(new LinkedList<>(linkedList));
            }
        }
        recursive(root.left, sum, linkedList, result);
        recursive(root.right, sum, linkedList, result);
        //将list的最后一个元素去掉，恢复原有集合
        linkedList.removeLast();
    }

    public static void main(String[] args) {
        TreeNode node10 = new TreeNode(1);
        TreeNode node9 = new TreeNode(5);
        TreeNode node8 = new TreeNode(2);
        TreeNode node7 = new TreeNode(7);
        TreeNode node6 = new TreeNode(4, node9, node10);
        TreeNode node5 = new TreeNode(13);
        TreeNode node4 = new TreeNode(11, node7, node8);
        TreeNode node3 = new TreeNode(8, node5, node6);
        TreeNode node2 = new TreeNode(4, node4, null);
        TreeNode node1 = new TreeNode(5, node2, node3);
        List<List<Integer>> lists = new LeetCode113().pathSum(node1, 22);
        System.out.println(lists);
    }
}
