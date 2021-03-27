package leetCode;

/**
 * @author xianCan
 * @date 2021/3/27 10:12
 *
 * 61. 旋转链表（中等）
 *
 *  给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。

    示例 1：


    输入：head = [1,2,3,4,5], k = 2
    输出：[4,5,1,2,3]
    示例 2：


    输入：head = [0,1,2], k = 4
    输出：[2,0,1]
     

    提示：

    链表中节点的数目在范围 [0, 500] 内
    -100 <= Node.val <= 100
    0 <= k <= 2 * 109

 */
public class LeetCode61 {

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null){
            return head;
        }
        int len = 0;
        ListNode cur = head;
        while (cur != null){
            len++;
            cur = cur.next;
        }
        k = k % len;
        if (k == 0){
            return head;
        }
        k = len - k - 1;
        cur = head;
        while (k > 0){
            cur = cur.next;
            k--;
        }

        ListNode newHead = cur.next, tail = cur.next;
        cur.next = null;
        while (tail.next != null){
            tail = tail.next;
        }
        tail.next = head;
        return newHead;
    }


}
