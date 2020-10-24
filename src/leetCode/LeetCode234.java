package leetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author xianCan
 * @date 2020/10/23 0:28
 *
 * 234. 回文链表（简单）
 *
 *  请判断一个链表是否为回文链表。

    示例 1:

    输入: 1->2
    输出: false
    示例 2:

    输入: 1->2->2->1
    输出: true
    进阶：
    你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？

 */
public class LeetCode234 {

    /**
     * 复制到数组
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null) return true;

        List<Integer> list = new ArrayList<>();
        while (head != null){
            list.add(head.val);
            head = head.next;
        }
        for (int i=0, j=list.size()-1; i<=j; i++, j--){
            if (!Objects.equals(list.get(i), list.get(j))){
                return false;
            }
        }
        return true;
    }

    /**
     * 找到链表中点，反转链表，然后逐个比较是否相等
     * @param args
     */

    public static void main(String[] args) {
        ListNode node5 = new ListNode(1);
        ListNode node4 = new ListNode(1);
        ListNode node3 = new ListNode(2, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1);
        boolean palindrome = new LeetCode234().isPalindrome(node1);
        System.out.println(palindrome);
    }
}
