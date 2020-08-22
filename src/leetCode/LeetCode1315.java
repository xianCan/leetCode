package leetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author xianCan
 * @date 2020/8/22 20:42
 *
 * 1315. 祖父节点值为偶数的节点和（中等）
 *
 *  给你一棵二叉树，请你返回满足以下条件的所有节点的值之和：

    该节点的祖父节点的值为偶数。（一个节点的祖父节点是指该节点的父节点的父节点。）
    如果不存在祖父节点值为偶数的节点，那么返回 0 。

    示例：

    输入：root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
    输出：18
    解释：图中红色节点的祖父节点的值为偶数，蓝色节点为这些红色节点的祖父节点。
     

    提示：

    树中节点的数目在 1 到 10^4 之间。
    每个节点的值在 1 到 100 之间。

 */
public class LeetCode1315 {

    /**
     * 类似双队列的做法：按层遍历
     * @param root
     * @return
     */
    public int sumEvenGrandparent(TreeNode root) {
        if (root == null){
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        List<TreeNode> list = new ArrayList<>();
        queue.offer(root);

        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            TreeNode left = node.left;
            TreeNode right = node.right;
            if (left != null){
                queue.offer(left);
            }
            if (right != null){
                queue.offer(right);
            }
            if (node.val%2==0){
                if (left != null){
                    list.add(left);
                }
                if (right != null){
                    list.add(right);
                }
            }
        }

        int res=0;
        for (TreeNode node : list){
            res += node.left != null ? node.left.val : 0;
            res += node.right != null ? node.right.val : 0;
        }
        return res;
    }

    /**
     * 递归，深度优先
     * @param root
     * @return
     */
    public int sumEvenGrandparent2(TreeNode root){
        if (root == null){
            return 0;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;

        int res=0;
        if (root.val % 2 == 0){
            if (left != null){
                res += left.left != null ? left.left.val : 0;
                res += left.right != null ? left.right.val : 0;
            }
            if (right != null){
                res += right.left != null ? right.left.val : 0;
                res += right.right != null ? right.right.val : 0;
            }
        }

        return res + sumEvenGrandparent2(left) + sumEvenGrandparent2(right);
    }
}
