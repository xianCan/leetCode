package leetCode;

/**
 * @author xianCan
 * @date 2020/10/10 22:30
 *
 * 142. 环形链表 II（中等）
 *
 *  给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。

    为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。

    说明：不允许修改给定的链表。

    进阶：

    你是否可以不用额外空间解决此题？
     

    示例 1：



    输入：head = [3,2,0,-4], pos = 1
    输出：返回索引为 1 的链表节点
    解释：链表中有一个环，其尾部连接到第二个节点。
    示例 2：



    输入：head = [1,2], pos = 0
    输出：返回索引为 0 的链表节点
    解释：链表中有一个环，其尾部连接到第一个节点。
    示例 3：



    输入：head = [1], pos = -1
    输出：返回 null
    解释：链表中没有环。
     

    提示：

    链表中节点的数目范围在范围 [0, 104] 内
    -105 <= Node.val <= 105
    pos 的值为 -1 或者链表中的一个有效索引

 */
public class LeetCode142 {

    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        ListNode slow=head, fast=head.next;
        while (fast != null){
            if (slow == fast){
                ListNode cur = head;
                slow = slow.next;
                while (cur != slow){
                    cur = cur.next;
                    slow = slow.next;
                }
                return slow;
            }
            slow = slow.next;
            if (fast.next == null){
                return null;
            }
            fast = fast.next.next;
        }
        return null;
    }

    public static void main(String[] args) {
        ListNode node4 = new ListNode(-4);
        ListNode node3 = new ListNode(0, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(3, node2);
        node4.next = node2;
        ListNode node = new LeetCode142().detectCycle(node1);
        System.out.println(node.val);
    }
}
