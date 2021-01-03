package leetCode;

/**
 * @author xianCan
 * @date 2021/1/3 9:01
 *
 * 86. 分隔链表（中等）
 *
 *  给你一个链表和一个特定值 x ，请你对链表进行分隔，使得所有小于 x 的节点都出现在大于或等于 x 的节点之前。

    你应当保留两个分区中每个节点的初始相对位置。

     

    示例：

    输入：head = 1->4->3->2->5->2, x = 3
    输出：1->2->2->4->3->5

 */
public class LeetCode86 {

    public ListNode partition(ListNode head, int x) {
        ListNode head1 = new ListNode(0);
        ListNode head2 = new ListNode(0);

        ListNode cur1 = head1, cur2 = head2;
        while (head != null){
            ListNode next = head.next;
            head.next = null;
            if (head.val < x){
                cur1.next = head;
                cur1 = cur1.next;
            } else {
                cur2.next = head;
                cur2 = cur2.next;
            }
            head = next;
        }
        cur1.next = head2.next;
        return head1.next;
    }

    public static void main(String[] args) {
        /*ListNode node6 = new ListNode(2);
        ListNode node5 = new ListNode(5, node6);
        ListNode node4 = new ListNode(2, node5);
        ListNode node3 = new ListNode(3, node4);*/
        ListNode node2 = new ListNode(4);
        ListNode node1 = new ListNode(1, node2);
        ListNode partition = new LeetCode86().partition(node1, 3);
        System.out.println(partition);
    }
}
