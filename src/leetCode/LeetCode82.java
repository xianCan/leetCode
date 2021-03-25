package leetCode;

/**
 * @author xianCan
 * @date 2021/3/25 22:16
 *
 * 82. 删除排序链表中的重复元素 II（中等）
 *
 *  存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数字。

    返回同样按升序排列的结果链表。

     

    示例 1：


    输入：head = [1,2,3,3,4,4,5]
    输出：[1,2,5]
    示例 2：


    输入：head = [1,1,1,2,3]
    输出：[2,3]
     

    提示：

    链表中节点数目在范围 [0, 300] 内
    -100 <= Node.val <= 100
    题目数据保证链表已经按升序排列

 */
public class LeetCode82 {

    public ListNode deleteDuplicates(ListNode head) {
        ListNode newHead = new ListNode(-101);
        newHead.next = head;
        ListNode pre = newHead, cur = head;

        while (cur != null){
            boolean f = false;
            while (cur.next != null && cur.val == cur.next.val){
                cur = cur.next;
                f = true;
            }
            if (f){
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
        ListNode listNode7 = new ListNode(5);
        ListNode listNode6 = new ListNode(5,listNode7);
        ListNode listNode5 = new ListNode(3, listNode6);
        ListNode listNode4 = new ListNode(2, listNode5);
        ListNode listNode3 = new ListNode(1, listNode4);
        ListNode listNode2 = new ListNode(1, listNode3);
        ListNode listNode = new ListNode(1, listNode2);
        ListNode node = new LeetCode82().deleteDuplicates(listNode);
        System.out.println(node);
    }
}
