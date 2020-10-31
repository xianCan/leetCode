package leetCode;

/**
 * @author xianCan
 * @date 2020/10/31 15:57
 *
 * 206. 反转链表（简单）
 *
 *  反转一个单链表。

    示例:

    输入: 1->2->3->4->5->NULL
    输出: 5->4->3->2->1->NULL
    进阶:
    你可以迭代或递归地反转链表。你能否用两种方法解决这道题？

 */
public class LeetCode206 {

    /**
     * 迭代
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode pre=null, cur=head;

        while (cur != null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }

    /**
     * 递归
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head){
        return helper(head, null);
    }

    private ListNode helper(ListNode cur, ListNode pre){
        if (cur==null){
            return pre;
        }
        ListNode next = cur.next;
        cur.next = pre;
        pre = cur;
        return helper(next, pre);
    }

    public static void main(String[] args) {
        ListNode node5 = new ListNode(5);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        ListNode node = new LeetCode206().reverseList2(node1);
        System.out.println(node);
    }
}
