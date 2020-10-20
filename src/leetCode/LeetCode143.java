package leetCode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xianCan
 * @date 2020/10/20 20:31
 *
 * 143. 重排链表（中等）
 *
 *  给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
    将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…

    你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

    示例 1:

    给定链表 1->2->3->4, 重新排列为 1->4->2->3.
    示例 2:

    给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.

 */
public class LeetCode143 {


    /**
     * 双端队列
     * @param head
     */
    public void reorderList(ListNode head) {
        if (head == null) return;

        Deque<ListNode> deque = new LinkedList<>();
        ListNode cur = head;
        while (cur != null){
            deque.add(cur);
            ListNode next = cur.next;
            cur.next = null;
            cur = next;
        }

        cur = null;
        while (!deque.isEmpty()){
            ListNode left = deque.pollFirst();
            ListNode right = deque.pollLast();
            if (cur == null){
                cur = left;
                cur.next = right;
                cur = right;
            } else {
                cur.next = left;
                cur.next.next = right;
                cur = right;
            }
        }
    }

    /**
     * 线性表
     * @param head
     */
    public void reorderList2(ListNode head){
        if (head==null)return;
        List<ListNode> list = new ArrayList<>();
        ListNode cur = head;
        while (cur != null){
            list.add(cur);
            cur = cur.next;
        }

        int i = 0, j = list.size() - 1;
        while (i < j) {
            list.get(i).next = list.get(j);
            i++;
            if (i == j) {
                break;
            }
            list.get(j).next = list.get(i);
            j--;
        }
        list.get(i).next = null;
    }

    /**
     * 寻找链表中点 + 链表逆序 + 合并链表
     * @param head
     */
    public void reorderList3(ListNode head) {
        if (head == null) {
            return;
        }
        ListNode mid = middleNode(head);
        ListNode l1 = head;
        ListNode l2 = mid.next;
        mid.next = null;
        l2 = reverseList(l2);
        mergeList(l1, l2);
    }

    //寻找链表中点
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    //逆序链表
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    //合并链表
    public void mergeList(ListNode l1, ListNode l2) {
        ListNode l1_tmp;
        ListNode l2_tmp;
        while (l1 != null && l2 != null) {
            l1_tmp = l1.next;
            l2_tmp = l2.next;

            l1.next = l2;
            l1 = l1_tmp;

            l2.next = l1;
            l2 = l2_tmp;
        }
    }

    public static void main(String[] args) {
        ListNode node5 = new ListNode(5);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        new LeetCode143().reorderList2(node1);
        System.out.println(node1);
    }
}
