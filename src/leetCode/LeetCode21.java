package leetCode;

/**
 * @authod xianCan
 * @date 2018/12/21 16:44
 *
 * 题目描述：
 *     将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * 示例：
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 */
public class LeetCode21 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode newHead = new ListNode(-1),cur=newHead;
        while (l1!=null && l2!=null){
            if (l1.val<l2.val){
                ListNode temp1=l1.next;
                cur.next=l1;
                cur = cur.next;
                l1=temp1;
            } else {
                ListNode temp2=l2.next;
                cur.next=l2;
                cur = cur.next;
                l2=temp2;
            }
        }
        if (l1==null)
            cur.next=l2;
        if (l2==null)
            cur.next=l1;
        return newHead.next;
    }
}
