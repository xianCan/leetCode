package leetCode;

/**
 * @authod xianCan
 * @date 2018/12/21 13:57
 *
 * 题目描述：
 *     给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例：
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 *
 * 说明：
 * 给定的 n 保证是有效的。
 *
 * 进阶：
 * 你能尝试使用一趟扫描实现吗？
 */
public class LeetCode19 {
    /**
     * 遍历两遍的方法
     * @param head 头节点
     * @param n 要删除倒数第几个节点的位置
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //弄一个-1的节点
        ListNode newHead = new ListNode(-1),cur=newHead;
        cur.next=head;
        int length=0;
        while (cur!=null){
            length++;
            cur =cur.next;
        }
        cur=newHead;
        int delete=length-n;
        while (delete>=1){
            ListNode pre=cur;
            cur=cur.next;
            if (delete==1){
                pre.next = cur.next;
                break;
            }
            delete--;
        }
        return newHead.next;
    }

    /**
     * 遍历一遍的方法，先快指针走n步，再快慢指针一起走，当快指针为空时，慢指针指向的就是要删除的位置
     * @param head 头节点
     * @param n 要删除倒数第几个节点的位置
     * @return
     */
    public ListNode removeNthFromEndSearchOne(ListNode head, int n) {
        //弄一个-1的节点
        ListNode newHead = new ListNode(-1),quick=newHead,slow=newHead;
        quick.next=head;
        while (quick!=null){
            if (n>=1){
                quick=quick.next;
                n--;
            }else {
                ListNode pre=slow;
                slow = slow.next;
                quick = quick.next;
                if (quick==null){
                    pre.next = slow.next;
                    break;
                }
            }
        }
        return newHead.next;
    }
}
