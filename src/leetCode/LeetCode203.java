package leetCode;

/**
 * @author xianCan
 * @date 2020/10/31 15:43
 *
 * 203. 移除链表元素（简单）
 *
 *  删除链表中等于给定值 val 的所有节点。

    示例:

    输入: 1->2->6->3->4->5->6, val = 6
    输出: 1->2->3->4->5
 */
public class LeetCode203 {

    public ListNode removeElements(ListNode head, int val) {
        ListNode newHead = new ListNode(-1);
        newHead.next = head;
        ListNode pre=newHead, cur = head;

        while (cur != null){
            if (cur.val == val){
                cur = cur.next;
                pre.next = cur;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }

        return newHead.next;
    }

    public static void main(String[] args) {
        ListNode node7 = new ListNode(6);
        ListNode node6 = new ListNode(5, node7);
        ListNode node5 = new ListNode(4, node6);
        ListNode node4 = new ListNode(3, node5);
        ListNode node3 = new ListNode(6, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        ListNode node = new LeetCode203().removeElements(node1, 6);
        System.out.println(node);
    }
}
