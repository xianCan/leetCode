package leetCode;

/**
 * @author xianCan
 * @date 2020/10/11 18:34
 *
 * 83. 删除排序链表中的重复元素（简单）
 *
 *  给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。

    示例 1:

    输入: 1->1->2
    输出: 1->2
    示例 2:

    输入: 1->1->2->3->3
    输出: 1->2->3


 */
public class LeetCode83 {

    public ListNode deleteDuplicates(ListNode head) {
        ListNode slow=head, fast=head;

        while (fast != null){
            if (slow.val != fast.val){
                slow.next = fast;
                slow = slow.next;
            }
            fast = fast.next;
        }
        if (slow != null){
            slow.next = null;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode node5 = new ListNode(3);
        ListNode node4 = new ListNode(3, node5);
        ListNode node3 = new ListNode(2, node4);
        ListNode node2 = new ListNode(1, node3);
        ListNode node1 = new ListNode(1, node2);
        ListNode node = new LeetCode83().deleteDuplicates(node1);
        System.out.println(node.val);
    }
}
