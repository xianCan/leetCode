package leetCode;

import java.util.*;

/**
 * @author xianCan
 * @date 2021/7/31 11:06
 *
 * 987. 二叉树的垂序遍历（困难）
 *
 *  给你二叉树的根结点 root ，请你设计算法计算二叉树的 垂序遍历 序列。

    对位于 (row, col) 的每个结点而言，其左右子结点分别位于 (row + 1, col - 1) 和 (row + 1, col + 1) 。树的根结点位于 (0, 0) 。

    二叉树的 垂序遍历 从最左边的列开始直到最右边的列结束，按列索引每一列上的所有结点，形成一个按出现位置从上到下排序的有序列表。如果同行同列上有多个结点，则按结点的值从小到大进行排序。

    返回二叉树的 垂序遍历 序列。

     

    示例 1：


    输入：root = [3,9,20,null,null,15,7]
    输出：[[9],[3,15],[20],[7]]
    解释：
    列 -1 ：只有结点 9 在此列中。
    列  0 ：只有结点 3 和 15 在此列中，按从上到下顺序。
    列  1 ：只有结点 20 在此列中。
    列  2 ：只有结点 7 在此列中。
    示例 2：


    输入：root = [1,2,3,4,5,6,7]
    输出：[[4],[2],[1,5,6],[3],[7]]
    解释：
    列 -2 ：只有结点 4 在此列中。
    列 -1 ：只有结点 2 在此列中。
    列  0 ：结点 1 、5 和 6 都在此列中。
    1 在上面，所以它出现在前面。
    5 和 6 位置都是 (2, 0) ，所以按值从小到大排序，5 在 6 的前面。
    列  1 ：只有结点 3 在此列中。
    列  2 ：只有结点 7 在此列中。
    示例 3：


    输入：root = [1,2,3,4,6,5,7]
    输出：[[4],[2],[1,5,6],[3],[7]]
    解释：
    这个示例实际上与示例 2 完全相同，只是结点 5 和 6 在树中的位置发生了交换。
    因为 5 和 6 的位置仍然相同，所以答案保持不变，仍然按值从小到大排序。
     

    提示：

    树中结点数目总数在范围 [1, 1000] 内
    0 <= Node.val <= 1000

 */
public class LeetCode987 {

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        Map<Integer, Map<Integer, LinkedList<Integer>>> map = new TreeMap<>();
        dfs(root, 0, 0, map);

        List<List<Integer>> res = new ArrayList<>();
        for (Map<Integer, LinkedList<Integer>> val : map.values()){
            List<Integer> list = new ArrayList<>();
            for (LinkedList<Integer> linkedList : val.values()){
                list.addAll(linkedList);
            }
            res.add(list);
        }

        return res;
    }

    private void dfs(TreeNode node, int row, int col, Map<Integer, Map<Integer, LinkedList<Integer>>> map){
        if (node == null){
            return;
        }
        Map<Integer, LinkedList<Integer>> rowMap = map.getOrDefault(col, new TreeMap<>());
        LinkedList<Integer> rowList = rowMap.getOrDefault(row, new LinkedList<>());
        int idx = 0;
        for (; idx < rowList.size(); idx++){
            if (node.val <= rowList.get(idx)){
                break;
            }
        }
        rowList.add(idx, node.val);
        rowMap.put(row, rowList);
        map.put(col, rowMap);

        dfs(node.left, row + 1, col - 1, map);
        dfs(node.right, row + 1, col + 1, map);
    }

    public static void main(String[] args) {
        TreeNode node7 = new TreeNode(7);
        TreeNode node6 = new TreeNode(5);
        TreeNode node5 = new TreeNode(6);
        TreeNode node4 = new TreeNode(4);
        TreeNode node3 = new TreeNode(3, node6, node7);
        TreeNode node2 = new TreeNode(2, node4, node5);
        TreeNode node1 = new TreeNode(1, node2, node3);
        List<List<Integer>> lists = new LeetCode987().verticalTraversal(node1);
        System.out.println(lists);
    }
}
