package leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xianCan
 * @date 2020/8/18 20:58
 *
 * 109. 有序链表转换二叉搜索树（中等）
 *
 *  给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。

    本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。

    示例:

    给定的有序链表： [-10, -3, 0, 5, 9],

    一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：

    0
    / \
    -3   9
    /   /
    -10  5

 */
public class LeetCode109 {

    /**
     * 快慢指针
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null){
            return null;
        }
        if (head.next == null){
            return new TreeNode(head.val);
        }
        ListNode fast=head, slow=head, pre=null;
        while (fast != null && fast.next != null){
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        pre.next=null;
        TreeNode root = new TreeNode(slow.val);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(slow.next);
        return root;
    }

    /**
     * 将二叉树扁平化为数组，通过索引操作
     * @param head
     * @return
     */
    public TreeNode sortedListToBST2(ListNode head){
        if (head==null){
            return null;
        }
        List<Integer> list = new ArrayList<>();
        while (head != null){
            list.add(head.val);
            head = head.next;
        }
        return helper(list, 0, list.size()-1);
    }

    private TreeNode helper(List<Integer> list, int left, int right){
        if (left > right){
            return null;
        }

        int mid = left + (right-left) / 2;
        TreeNode root = new TreeNode(list.get(mid));
        root.left = helper(list, left, mid-1);
        root.right = helper(list, mid+1, right);
        return root;
    }
}
