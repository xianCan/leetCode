package leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xianCan
 * @date 2020/11/21 9:16
 *
 * 148. 排序链表（中等）
 *
 *  给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。

    进阶：

    你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
     

    示例 1：


    输入：head = [4,2,1,3]
    输出：[1,2,3,4]
    示例 2：


    输入：head = [-1,5,3,4,0]
    输出：[-1,0,3,4,5]
    示例 3：

    输入：head = []
    输出：[]
     

    提示：

    链表中节点的数目在范围 [0, 5 * 104] 内
    -105 <= Node.val <= 105


    最适合链表的排序：归并
 */
public class LeetCode148 {

    /**
     * 暴力
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        while (head != null){
            ListNode next = head.next;
            head.next = null;
            list.add(head);
            head = next;
        }

        list.sort((o1, o2) -> o1.val - o2.val);

        ListNode newHead = new ListNode(0), cur=newHead;
        for (ListNode node : list){
            cur.next = node;
            cur = cur.next;
        }
        return newHead.next;
    }

    /**
     * 归并：自顶向下
     * 时间复杂度：O(n log n)，空间复杂度：O(log n)
     * @param head
     * @return
     */
    public ListNode sortList2(ListNode head){
        if (head == null || head.next == null){
            return head;
        }
        ListNode slow=head, fast=head.next;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode mid = slow.next;
        slow.next = null;
        ListNode node1 = sortList2(head);
        ListNode node2 = sortList2(mid);
        return merge(node1, node2);
    }

    private ListNode merge(ListNode node1, ListNode node2) {
        ListNode dummyHead = new ListNode(0);
        ListNode cur=dummyHead;
        while (node1 != null && node2 != null){
            if (node1.val <= node2.val){
                cur.next = node1;
                node1 = node1.next;
            } else {
                cur.next = node2;
                node2 = node2.next;
            }
            cur = cur.next;
        }
        if (node1 != null){
            cur.next = node1;
        } else if (node2 != null){
            cur.next = node2;
        }

        return dummyHead.next;
    }

    /**
     * 归并：自底向上
     * 时间复杂度：O(n log n)， 空间复杂度：O(1)
     * @param head
     * @return
     */
    public ListNode sortList3(ListNode head){
        if (head == null || head.next == null){
            return head;
        }
        int len=0;
        ListNode node=head;
        while (node != null){
            len++;
            node = node.next;
        }

        ListNode newHead = new ListNode(0, head);
        for (int subLen=1; subLen<len; subLen=subLen<<1){
            ListNode pre=newHead, cur=newHead.next;
            while (cur != null){
                //找到第一个头结点
                ListNode head1 = cur;
                for (int i=1; i<subLen && cur != null; i++){
                    cur = cur.next;
                }

                //找到第二个头结点
                ListNode head2 = null;
                if (cur != null){
                    head2 = cur.next;
                    cur.next = null;
                }
                cur = head2;
                for (int i=1; i<subLen && cur != null; i++){
                    cur = cur.next;
                }

                //找到下一个cur节点
                ListNode next=null;
                if (cur != null){
                    next = cur.next;
                    cur.next = null;
                }

                //合并两个头结点
                pre.next = merge(head1, head2);
                while (pre.next != null){
                    pre = pre.next;
                }
                //将下一个cur节点赋给cur
                cur = next;
            }
        }
        return newHead.next;
    }

    public static void main(String[] args) {
        ListNode node5 = new ListNode(0);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(5, node3);
        ListNode node1 = new ListNode(-1,node2);
        ListNode node = new LeetCode148().sortList2(node1);
        System.out.println(node);
    }
}
