package interview;

import leetCode.ListNode;
import leetCode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author xianCan
 * @date 2020/10/14 22:48
 *
 * 面试题 04.03. 特定深度节点链表（中等）
 *
 *  给定一棵二叉树，设计一个算法，创建含有某一深度上所有节点的链表（比如，若一棵树的深度为 D，则会创建出 D 个链表）。返回一个包含所有深度的链表的数组。

    示例：

    输入：[1,2,3,4,5,null,7,8]

    1
    /  \
    2    3
    / \    \
    4   5    7
    /
    8

    输出：[[1],[2,3],[4,5,7],[8]]

 */
public class Interview0403 {

    public ListNode[] listOfDepth(TreeNode tree) {
        List<ListNode> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        if (tree != null){
            queue.offer(tree);
        }

        while (!queue.isEmpty()){
            int size = queue.size();
            ListNode head = new ListNode(0);
            ListNode cur = head;
            while (size > 0){
                TreeNode node = queue.poll();
                ListNode tmp = new ListNode(node.val);
                cur.next = tmp;
                cur = tmp;
                if (node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }
                size--;
            }
            list.add(head.next);
        }

        ListNode[] res = new ListNode[list.size()];
        for (int i=0; i<list.size(); i++){
            res[i] = list.get(i);
        }
        return res;
    }
}
