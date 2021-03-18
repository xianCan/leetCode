package leetCode;

/**
 * @author xianCan
 * @date 2021/3/18 22:59
 *
 * 92. 反转链表 II（中等）
 *
 *  给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 

    示例 1：


    输入：head = [1,2,3,4,5], left = 2, right = 4
    输出：[1,4,3,2,5]
    示例 2：

    输入：head = [5], left = 1, right = 1
    输出：[5]
     

    提示：

    链表中节点数目为 n
    1 <= n <= 500
    -500 <= Node.val <= 500
    1 <= left <= right <= n
     

    进阶： 你可以使用一趟扫描完成反转吗？

 */
public class LeetCode92 {

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right){
            return head;
        }

        ListNode newHead = new ListNode(0), cur = newHead, pre;
        newHead.next = head;
        int count = 0;

        while (cur.next != null){
            count++;
            pre = cur;
            cur = cur.next;
            if (left == count){
                pre.next = reverse(cur, right - left + 1);
                break;
            }
        }

        return newHead.next;
    }

    private ListNode reverse(ListNode node, int len){
        ListNode pre = null, cur = node;
        for (; len > 0; len--){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        node.next = cur;

        return pre;
    }

    public static void main(String[] args) {
        ListNode newHead5 = new ListNode(5);
        ListNode newHead4 = new ListNode(4, newHead5);
        ListNode newHead3 = new ListNode(3, newHead4);
        ListNode newHead2 = new ListNode(2, newHead3);
        ListNode newHead1 = new ListNode(1, newHead2);
        ListNode node = new LeetCode92().reverseBetween(newHead1, 1, 5);
        System.out.println(node);
    }
}
