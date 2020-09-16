package leetCode;

/**
 * @author xianCan
 * @date 2020/9/16 21:45
 *
 * 25. K 个一组翻转链表（困难）
 *
 *  给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。

    k 是一个正整数，它的值小于或等于链表的长度。

    如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。

     

    示例：

    给你这个链表：1->2->3->4->5

    当 k = 2 时，应当返回: 2->1->4->3->5

    当 k = 3 时，应当返回: 3->2->1->4->5

     

    说明：

    你的算法只能使用常数的额外空间。
    你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。

 */
public class LeetCode25 {

    /**
     * 每k个一组进行递归
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;

        ListNode right=head;
        for (int i=0; i<k; i++){
            if (right == null){
                return head;
            }
            right = right.next;
        }

        ListNode newHead = helper(head, right);//左闭右开
        head.next = reverseKGroup(right, k);
        return newHead;
    }

    private ListNode helper(ListNode left, ListNode right){
        ListNode prev=null, cur=left, next;
        while (cur != right){
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        return prev;
    }

    public static void main(String[] args) {
        ListNode node5 = new ListNode(5);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        ListNode node = new LeetCode25().reverseKGroup(node1, 6);
        System.out.println(node);
    }
}
