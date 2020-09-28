package leetCode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author xianCan
 * @date 2020/9/28 22:00
 *
 * 117. 填充每个节点的下一个右侧节点指针 II（中等）
 *
 *  给定一个二叉树

    struct Node {
    int val;
    Node *left;
    Node *right;
    Node *next;
    }
    填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。

    初始状态下，所有 next 指针都被设置为 NULL。

     

    进阶：

    你只能使用常量级额外空间。
    使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
     

    示例：



    输入：root = [1,2,3,4,5,null,7]
    输出：[1,#,2,3,#,4,5,7,#]
    解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
     

    提示：

    树中的节点数小于 6000
    -100 <= node.val <= 100

 */
public class LeetCode117 {

    /**
     * 按层遍历（队列），空间复杂度O(N)
     * @param root
     * @return
     */
    public Node connect(Node root) {
        Queue<Node> queue = new LinkedList<>();
        if (root != null){
            queue.offer(root);
        }

        while (!queue.isEmpty()){
            int size = queue.size();
            Node cur=null, next;
            while (size > 0){
                next = queue.poll();
                if (next.left != null){
                    queue.offer(next.left);
                }
                if (next.right != null){
                    queue.offer(next.right);
                }
                if (cur != null){
                    cur.next = next;
                }
                cur = next;
                size--;
            }
        }
        return root;
    }

    private Node last=null;
    private Node nextStart=null;

    /**
     * 模拟按层遍历，空间复杂度O(1)
     * @param root
     * @return
     */
    public Node connect2(Node root) {
        if (root == null){
            return null;
        }
        Node start = root;
        while (start != null){
            last = null;
            nextStart = null;
            for (Node p=start; p!=null; p = p.next){
                if (p.left != null){
                    helper(p.left);
                }
                if (p.right != null){
                    helper(p.right);
                }
            }
            start = nextStart;
        }
        return root;
    }

    private void helper(Node node){
        if (last != null){
            last.next = node;
        }
        if (nextStart == null){
            nextStart = node;
        }
        last = node;
    }

    static class Node{
        int val;
        Node left;
        Node right;
        Node next;

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
