package leetCode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author xianCan
 * @date 2020/8/7 21:47
 *
 * 100. 相同的树（简单）
 *
 *  给定两个二叉树，编写一个函数来检验它们是否相同。

    如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。

    示例 1:

    输入:       1         1
    / \       / \
    2   3     2   3

    [1,2,3],   [1,2,3]

    输出: true
    示例 2:

    输入:      1          1
    /           \
    2             2

    [1,2],     [1,null,2]

    输出: false
    示例 3:

    输入:       1         1
    / \       / \
    2   1     1   2

    [1,2,1],   [1,1,2]

    输出: false
 */
public class LeetCode100 {

    /**
     * 深度优先
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p==null && q==null){
            return true;
        } else if (p==null || q==null){
            return false;
        } else if (p.val != q.val){
            return false;
        } else {
          return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }

    /**
     * 广度遍历
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree2(TreeNode p, TreeNode q){
        Queue<TreeNode> queue1 = new LinkedList<>();
        queue1.offer(p);
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue2.offer(q);

        while (!queue1.isEmpty() && !queue2.isEmpty()){
            TreeNode node1 = queue1.poll();
            TreeNode node2 = queue2.poll();
            if (node1==null && node2==null){
                continue;
            } else if (node1==null || node2==null){
                return false;
            } else if (node1.val != node2.val){
                return false;
            } else {
                queue1.offer(node1.left);
                queue1.offer(node1.right);

                queue2.offer(node2.left);
                queue2.offer(node2.right);
            }
        }
        return queue1.size() == queue2.size();
    }
}
