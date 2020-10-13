package leetCode;

/**
 * @author xianCan
 * @date 2020/10/13 22:53
 *
 * 24. 两两交换链表中的节点（中等）
 *
 *  定一个链表，两两交换其中相邻的节点，并返回交换后的链表。

    你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

     

    示例 1：


    输入：head = [1,2,3,4]
    输出：[2,1,4,3]
    示例 2：

    输入：head = []
    输出：[]
    示例 3：

    输入：head = [1]
    输出：[1]
     

    提示：

    链表中节点的数目在范围 [0, 100] 内
    0 <= Node.val <= 100

 */
public class LeetCode24 {

    public ListNode swapPairs(ListNode head) {
        if (head == null) return null;

        ListNode first = new ListNode(0);

        ListNode prev=first, cur=head, next=head.next;
        first.next = head;

        while (next != null){
            ListNode tmp = next.next;
            prev.next = next;
            next.next = cur;
            cur.next = tmp;

            prev = cur;
            cur = tmp;
            next = tmp != null ? tmp.next : null;
        }

        return first.next;
    }
}
