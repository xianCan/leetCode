package leetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author xianCan
 * @date 2020/9/12 0:04
 *
 * 637. 二叉树的层平均值（简单）
 *
 *  给定一个非空二叉树, 返回一个由每层节点平均值组成的数组。

    示例 1：

    输入：
    3
    / \
    9  20
    /  \
    15   7
    输出：[3, 14.5, 11]
    解释：
    第 0 层的平均值是 3 ,  第1层是 14.5 , 第2层是 11 。因此返回 [3, 14.5, 11] 。
     

    提示：

    节点值的范围在32位有符号整数范围内。

 */
public class LeetCode637 {

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null){
            queue.offer(root);
        }

        while (!queue.isEmpty()){
            int len=queue.size(), size = queue.size();
            long sum = 0;
            while (size > 0){
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }
                size--;
            }
            res.add((double) sum / len);
        }
        return res;
    }
}
